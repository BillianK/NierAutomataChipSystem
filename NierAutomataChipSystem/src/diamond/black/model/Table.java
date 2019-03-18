package diamond.black.model;

import java.util.ArrayList;

public class Table {

	private static int addMagicNumber(int rank) {

		if (rank < 3)
			return 1;
		else if (rank < 5)
			return 2;
		else if (rank < 7)
			return 3;
		else if (rank < 9)
			return 4;
		return 0;
	}

	private static int baseCostOfChip(int rank) {

		if (rank == 0)
			return 4;
		if (rank == 1)
			return 5;
		if (rank == 2)
			return 6;
		if (rank == 3)
			return 7;
		if (rank == 4)
			return 9;
		if (rank == 5)
			return 11;
		if (rank == 6)
			return 14;
		if (rank == 7)
			return 17;
		if (rank == 8)
			return 21;
		return 0;
	}

	/**
	 * @return a collection of chips, produce associations table
	 */
	protected static ArrayList<Chip> table() {

		float cost;
		float cA = baseCostOfChip(7);
		float cB = cA;
		int rank = 7;
		int currentFusionCost;
		int baseCostOfChip;
		int buffer = 5;
		ArrayList<Chip> tab = new ArrayList<>();

		while (rank >= 0) {

			baseCostOfChip = baseCostOfChip(rank);
			currentFusionCost = (baseCostOfChip + addMagicNumber(rank) + buffer);

			cost = ((cA + cB) / 2);

			if (rank % 2 != 0 || rank == 0)
				cost = (float) (Math.floor(cost) + addMagicNumber(rank));
			else
				cost = (float) (Math.ceil(cost) + addMagicNumber(rank));

			if (cost <= currentFusionCost) {
				tab.add(new Chip(rank, (int) cA, (int) cB, (rank + 1), (int) cost));
			}
			cB++;

			if (cost == currentFusionCost) {
				cA++;
				cB = cA;
			} else if (cost > currentFusionCost) {
				rank--;
				cA = baseCostOfChip(rank);
				cB = cA;
			}
		}
		return tab;
	}
}
