package diamond.black.model;

import java.util.ArrayList;
import java.util.function.Predicate;

public abstract class Fusion {

	protected static Predicate<Chip> numberNotZeroFilter = e -> e.getNumber() != 0;

	protected static ArrayList<Chip> copy(ArrayList<Chip> collection) {

		ArrayList<Chip> Chips = new ArrayList<>();

		for (Chip chp : collection) {

			Chip newChip = new Chip();
			newChip.setRank(chp.getRank());
			newChip.setCost(chp.getCost());
			newChip.setCostB(chp.getCostB());
			newChip.setPriority(chp.getPriority());
			newChip.setFusionRank(chp.getFusionRank());
			newChip.setFusionCost(chp.getFusionCost());
			newChip.setNumber(chp.getNumber());
			Chips.add(newChip);
		}

		return Chips;
	}

	protected static Chip copy(Chip chip) {

		Chip newChip = new Chip();

		newChip.setRank(chip.getRank());
		newChip.setCost(chip.getCost());
		newChip.setCostB(chip.getCostB());
		newChip.setPriority(chip.getPriority());
		newChip.setFusionRank(chip.getFusionRank());
		newChip.setFusionCost(chip.getFusionCost());
		newChip.setNumber(chip.getNumber());

		return newChip;
	}

	protected static int[] minMaxCost(ArrayList<Chip> chips) {

		int minCA, maxCA;
		int[] xtrm = new int[2];

		chips.sort(Sort.byCost);

		minCA = chips.get(0).getCost();
		maxCA = chips.get(chips.size() - 1).getCost();

		xtrm[0] = minCA;
		xtrm[1] = maxCA;

		return xtrm;
	}

	protected static int[] minMaxCostAB(ArrayList<Chip> chips) {

		int[] cAs = new int[2];
		int[] cBs = new int[2];
		int[] xtrm = new int[2];

		cAs = minMaxCost(chips);
		cBs = minMaxCostB(chips);

		xtrm[0] = (cAs[0] < cBs[0]) ? cAs[0] : cBs[0];
		xtrm[1] = (cAs[1] > cBs[1]) ? cAs[1] : cBs[1];

		return xtrm;
	}

	protected static int[] minMaxCostB(ArrayList<Chip> chips) {

		int minCB, maxCB;
		int[] xtrm = new int[2];

		chips.sort(Sort.byCostB);

		minCB = chips.get(0).getCostB();
		maxCB = chips.get(chips.size() - 1).getCostB();

		xtrm[0] = minCB;
		xtrm[1] = maxCB;

		return xtrm;
	}

	protected static int numberOfChip(ArrayList<Chip> collection) {

		int sum = 0;

		for (Chip chp : collection)
			sum += chp.getNumber();

		return sum;
	}

	protected static ArrayList<Chip> regroupChip(ArrayList<Chip> chips, boolean transformNumber) {

		int indexA = 0, indexB = 1;

		chips.sort(Sort.byRank.reversed());

		while (indexA < chips.size() - 1) {

			if (chips.get(indexA).getRank() == chips.get(indexB).getRank()
					&& chips.get(indexA).getCost() == chips.get(indexB).getCost()
					&& chips.get(indexA).getCostB() == chips.get(indexB).getCostB()
					&& chips.get(indexA).getFusionRank() == chips.get(indexB).getFusionRank()
					&& chips.get(indexA).getFusionCost() == chips.get(indexB).getFusionCost()) {
				if (transformNumber)
					chips.get(indexA).setNumber((chips.get(indexA).getNumber() + chips.get(indexB).getNumber()));

				chips.remove(indexB);

				indexB--;
			}

			indexB++;

			if (indexB >= chips.size()) {
				indexA++;
				indexB = (indexA + 1);
			}
		}

		return chips;
	}

	protected static ArrayList<Chip> selectRank(ArrayList<Chip> collection, int rank) {

		ArrayList<Chip> selected = new ArrayList<>();

		for (Chip chp : collection) {
			if (chp.getRank() == rank)
				selected.add(chp);
		}

		return selected;
	}
}
