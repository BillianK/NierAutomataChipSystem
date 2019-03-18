package diamond.black.model;

import java.io.IOException;
import java.util.ArrayList;

public class Optimise extends Fusion {

	private static ArrayList<ArrayList<Chip>> filterUseful(ArrayList<Chip> chips) {

		int index = 0;
		ArrayList<Chip> removed = new ArrayList<>();
		ArrayList<ArrayList<Chip>> node;

		while (index < chips.size()) {
			if (chips.get(index).getPriority() < Chip.SPRAY) {
				removed.add(chips.get(index));
				chips.remove(index);
				index--;
			}
			index++;
		}

		node = new ArrayList<>(2);
		node.add(chips);
		node.add(removed);
		return node;
	}

	public static void optimise(ArrayList<Chip> chips) throws IOException {

		int count = 1;

		ArrayList<ArrayList<Chip>> nodes;
		ArrayList<Chip> dvp;
		ArrayList<Chip> extro;
		ArrayList<Chip> refine;
		ArrayList<Chip> matches;
		ArrayList<Chip> inputs;

		Dealer.setToInput(chips);

		chips = removeZero(regroupChip(chips, true));
		nodes = filterUseful(chips);
		chips = null;

		if (!nodes.get(1).isEmpty()) {
			System.out.println("SELL");
			Util.screen(nodes.get(1));
		}

		inputs = nodes.get(0);
		nodes = null;
		System.out.println("INPUTS");
		Util.screen(inputs);

		while (count < 10) {

			// INPUTS
			inputs.sort(Sort.byFusionRank.reversed().thenComparing(Sort.byFusionCost));
//			System.out.println("INPUTS");
//			Util.screen(inputs);
			// END INPUTS

			System.out.println(" -------------------------- TURN " + count);

			// FILTER ------------------------------------------------------------------
			matches = makeMatches(inputs, Chip.WIDESPRAY);
			matches.sort(Sort.byPriority.reversed().thenComparing(Sort.byCostB));
//			Util.screen(matches);
			// END FILTER ------------------------------------------------------------------

			// DEVELOP -----------------------------------------
			dvp = Develop.develop(inputs, matches);// Doesn't alter inputs
			dvp.sort(Sort.byRank.reversed());
			if (dvp.isEmpty()) {
				System.out.println("Can't make better...");
				break;
			}
			// System.out.println(" -------------------------- DEVELOP");
			// Util.screen(dvp);
			// END DEVELOP -----------------------------------------

			// ORIGIN -----------------------------------------------------
			extro = Origin.origin(dvp, inputs);// Doesn't alter inputs
			// System.out.println(" -------------------------------- ORIGIN");
			// Util.screen(extro);
			// END ORIGIN -----------------------------------------------------

			// REFINE -----------------------------------------------------
			refine = Refine.refine(extro);
			System.out.println(" ----------------------------------- REFINE");
			refine.sort(Sort.byRank.thenComparing(Sort.byCost).reversed());
			Util.screenInputsFilter(refine);
			// END REFINE -----------------------------------------------------

			// Subtract used inputs
			inputs = subtractNumber(inputs, selectEmptyCosts(refine));

			refine.get(0).setCost(0);
			refine.get(0).setCostB(0);
			inputs.add(refine.get(0));

			inputs = removeZero(regroupChip(inputs, true));

			count++;
		}
	}

	private static ArrayList<Chip> makeMatches(ArrayList<Chip> inputs, int priority) throws IOException {

		ArrayList<Chip> chipsTable;
		ArrayList<Chip> matches;
		Chip chipA = copy(inputs.get(0));
		Chip chipB = copy(chipA);
		Chip chipC = null;
		boolean occurence = false;

		// If odd and diamond
		if (chipA.getPriority() == Chip.DIAMOND) {

			if (chipA.getFusionRank() % 2 != 0) {

				// reversematches of same rank, +1 fusioncost

				chipB.setFusionCost(chipB.getFusionCost() + 1);
				chipB.setPriority(Chip.FOCUS);

				for (Chip c : inputs) {
					if (chipB.equals(c)) {
						occurence = true;
						inputs = null;
						break;
					}
				}

				if (occurence) {
					// find fusion with these two
					chipsTable = Util.loadFile("ChipsRecords.csv");

					for (Chip c : chipsTable) {
						if (chipA.getFusionRank() == c.getRank() && chipA.getFusionCost() == c.getCost()
								&& chipB.getFusionCost() == c.getCostB()) {
							chipC = c;
							break;
						}
					}

					matches = reverseMatches(chipC, priority);

				} else
					matches = reverseMatches(chipB, priority);

			} else {
				// reversematches of same rank, same fusioncost
				if (chipA.getNumber() > 1) {

					// find first superior IV 9 + IV 9 = V 11

					chipsTable = Util.loadFile("ChipsRecords.csv");

					for (Chip c : chipsTable) {
						if (chipA.getFusionRank() == c.getRank())
							if (chipA.getFusionCost() == c.getCost())
								if (chipA.getFusionCost() == c.getCostB()) {
									chipC = c;
									break;
								}
					}
					
					matches = reverseMatches(chipC, priority);

				} else {
					matches = reverseMatches(chipA, priority);
				}
			}

		} else {
			// If chip isn't a diamond
			chipsTable = Util.loadFile("ChipsRecords.csv");

			for (Chip c : chipsTable) {
				if (chipA.getFusionRank() == c.getFusionRank() && c.getPriority() == Chip.DIAMOND) {
					chipC = c;
					break;
				}
			}

			matches = reverseMatches(chipC, priority);
		}

		return matches;
	}

	private static ArrayList<Chip> removeZero(ArrayList<Chip> chips) {

		int index = 0;

		while (index < chips.size()) {
			// System.out.println(chips.get(index));
			if (chips.get(index).getNumber() == 0) {
				chips.remove(index);
				index--;
			}
			index++;
		}
		return chips;
	}

	private static ArrayList<Chip> reverseMatches(Chip chip, int priority) throws IOException {

		ArrayList<Chip> chipsTable;
		ArrayList<Chip> matches;
		ArrayList<Chip> temp;

		int[] cABs = new int[2];
		int rank = chip.getFusionRank();

		chipsTable = Util.loadFile("ChipsRecords.csv");

		matches = new ArrayList<>();

		cABs[0] = chip.getFusionCost();
		cABs[1] = cABs[0];

		while (0 < rank) {

			for (Chip chp : chipsTable) {

				if (chp.getFusionRank() == rank)
					if (chp.getFusionCost() >= cABs[0] && chp.getFusionCost() <= cABs[1]
							&& chp.getPriority() >= priority) {
						matches.add(copy(chp));
					}
			}

			rank = matches.get(matches.size() - 1).getRank();

			temp = new ArrayList<>();

			for (Chip cp : matches) {
				if (cp.getRank() == rank) {
					temp.add(cp);
				}
			}

			cABs = Fusion.minMaxCostAB(temp);

		}
		return matches;
	}

	private static ArrayList<Chip> selectEmptyCosts(ArrayList<Chip> collection) {
		ArrayList<Chip> selected = new ArrayList<>();

		for (Chip chp : collection) {
			if (chp.getCost() == 0)
				selected.add(chp);
		}

		return selected;
	}

	private static ArrayList<Chip> subtractNumber(ArrayList<Chip> inputs, ArrayList<Chip> inputsOfRefine) {

		int index = 0;

		while (index < inputsOfRefine.size()) {
			// System.out.println(inputsOfRefine.get(index));
			// System.out.println("_______________");
			for (Chip chip : inputs) {
				if (inputsOfRefine.get(index).equals(chip)) {
					// System.out.println(inputsOfRefine.get(index));
					// System.out.println(chip);
					chip.setNumber(chip.getNumber() - inputsOfRefine.get(index).getNumber());
					// chip.setNumber(chip.getNumber() - inputsOfRefine.get(index).getNumber());
					// System.out.println(chip);
					// System.out.println("_______________");
					break;
				}
			}
			index++;
		}

		return inputs;
	}
}
