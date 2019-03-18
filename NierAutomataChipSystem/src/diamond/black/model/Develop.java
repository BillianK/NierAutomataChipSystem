package diamond.black.model;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Develop extends Fusion {

	/**
	 * @param inputsCopy of a collection of chips
	 * @param matchesCopy associations table of chips 
	 * @return a collection of chips, product of associations between preceding parameters
	 */
	protected static ArrayList<Chip> develop(ArrayList<Chip> inputs, ArrayList<Chip> matches) {

		ArrayList<Chip> matchesCopy = copy(matches);
		matches = null;
		ArrayList<Chip> inputsCopy = copy(inputs);
		inputs = null;
		ArrayList<Chip> fusions = new ArrayList<>();
		ArrayList<Chip> allFusions = new ArrayList<>();
		ArrayList<Chip> currentInputs;
		ArrayList<Chip> currentMatches;
		int rank;

		
		// Start with smallest rank
		inputsCopy.sort(Sort.byRank);

//		ChipUtil.screen(inputs);

		rank = inputsCopy.get(0).getRank();

		while (rank < 8) {

			currentInputs = selectRank(inputsCopy, rank);
			currentInputs.addAll(fusions);
			currentInputs = regroupChip(currentInputs, true);
//			System.out.println("CURRENT INPUTS");
//			Util.screen(currentInputs);
			if (!currentInputs.isEmpty()) {
				currentInputs.sort(Sort.byFusionCost);

				rank++;

				currentMatches = selectRank(matchesCopy, rank);
				if (currentMatches.isEmpty())
					break;
//				System.out.println("MATCHES");
//				Util.screen(currentMatches);

				fusions = fuse(currentInputs, currentMatches);
//				System.out.println("FUSIONS");
//				Util.screen(fusions);

				allFusions.addAll(copy(fusions));
			} else {
				rank++;
			}
		}
		// System.out.println("FUSIONS");
		// screen(allFusions);
		return allFusions;
	}

	private static ArrayList<Chip> fuse(ArrayList<Chip> inputs, ArrayList<Chip> matches) {

		boolean flagA = false, flagB = false;

		int index = 0, indexA = 0, indexB = 0, indexC = 0;

		int loop = numberOfChip(inputs) * 2;

		while (index < loop) {
//			System.out.println("-----------------------");
//			ChipUtil.screen(inputs);
//			System.out.println(matches.get(indexA));
			while (indexB < inputs.size()) {

				if (inputs.get(indexB).getNumber() > 0) {
					if (inputs.get(indexB).getFusionCost() == matches.get(indexA).getCost()) {
						flagA = true;
						break;
					}

				}
				indexB++;
			}

			if (flagA) {
				while (indexC < inputs.size()) {

					if (inputs.get(indexC).getNumber() > 0
							&& inputs.get(indexC).getFusionCost() == matches.get(indexA).getCostB()) {
						if (!inputs.get(indexB).equals(inputs.get(indexC))) {

							flagB = true;
							break;

						} else if (inputs.get(indexB).getNumber() > 1) {

							flagB = true;
							break;

						}
					}
					indexC++;
				}
			}

			if (flagA && flagB) {
//				System.out.println(inputs.get(indexB));
//				System.out.println(inputs.get(indexC));
				if (inputs.get(indexB).equals(inputs.get(indexC))) {

					if (inputs.get(indexB).getNumber() > 1) {

						inputs.get(indexB).setNumber(inputs.get(indexB).getNumber() - 2);
						matches.get(indexA).setNumber(matches.get(indexA).getNumber() + 1);

					}
				} else {

					inputs.get(indexB).setNumber(inputs.get(indexB).getNumber() - 1);
					inputs.get(indexC).setNumber(inputs.get(indexC).getNumber() - 1);
					matches.get(indexA).setNumber(matches.get(indexA).getNumber() + 1);

				}
			}
			flagA = false;
			flagB = flagA;
			indexB = 0;
			indexC = 0;
			indexA++;
			index++;

			if (indexA == matches.size())
				indexA = 0;
		}
		matches = matches.stream().filter(numberNotZeroFilter).collect(Collectors.toCollection(ArrayList::new));
		return matches;
	}

}
