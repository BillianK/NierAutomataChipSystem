package diamond.black.model;

import java.util.ArrayList;

public class Dealer {

	/**
	 * @param collection of chips
	 * @return a collection of chips set with priority valors
	 */
	protected static ArrayList<Chip> recommendationDealer(ArrayList<Chip> collection) {

		for (Chip chip : collection) {

			switch (chip.getRank()) {
			case 0:
				if (chip.getCost() == 4 && chip.getCostB() == 5)
					chip.setPriority(Chip.DIAMOND);
				else if (chip.getCost() == 5 && chip.getCostB() == 6)
					chip.setPriority(Chip.FOCUS);
				else if (chip.getCost() == 6 && chip.getCostB() == 7)
					chip.setPriority(Chip.SPRAY);
				else if (chip.getCost() == 7 && chip.getCostB() == 8)
					chip.setPriority(Chip.SPRAY);
				else if (chip.getCost() == 8 && chip.getCostB() == 9)
					chip.setPriority(Chip.SPRAY);
				else if (chip.getFusionCost() <= 10)
					chip.setPriority(Chip.WIDESPRAY);
				break;
			case 1:
				if (chip.getCost() == 5 && chip.getCostB() == 6)
					chip.setPriority(Chip.DIAMOND);
				else if (chip.getCost() == 6 && chip.getCostB() == 7)
					chip.setPriority(Chip.FOCUS);
				else if (chip.getCost() == 7 && chip.getCostB() == 8)
					chip.setPriority(Chip.SPRAY);
				else if (chip.getCost() == 8 && chip.getCostB() == 9)
					chip.setPriority(Chip.SPRAY);
				else if (chip.getFusionCost() <= 10)
					chip.setPriority(Chip.WIDESPRAY);
				break;
			case 2:
				if (chip.getCost() == 6 && chip.getCostB() == 6)
					chip.setPriority(Chip.DIAMOND);
				else if (chip.getCost() == 7 && chip.getCostB() == 7)
					chip.setPriority(Chip.FOCUS);
				else if (chip.getCost() == 8 && chip.getCostB() == 8)
					chip.setPriority(Chip.SPRAY);
				else if (chip.getCost() == 9 && chip.getCostB() == 9)
					chip.setPriority(Chip.SPRAY);
				break;
			case 3:
				if (chip.getCost() == 7 && chip.getCostB() == 8)
					chip.setPriority(Chip.DIAMOND);
				else if (chip.getCost() == 8 && chip.getCostB() == 9)
					chip.setPriority(Chip.FOCUS);
				else if (chip.getCost() == 9 && chip.getCostB() == 10)
					chip.setPriority(Chip.SPRAY);
				break;
			case 4:
				if (chip.getCost() == 9 && chip.getCostB() == 9)
					chip.setPriority(Chip.DIAMOND);
				else if (chip.getCost() == 10 && chip.getCostB() == 10)
					chip.setPriority(Chip.FOCUS);
				else if (chip.getCost() == 11 && chip.getCostB() == 11)
					chip.setPriority(Chip.SPRAY);
				break;
			case 5:
				if (chip.getCost() == 11 && chip.getCostB() == 12)
					chip.setPriority(Chip.DIAMOND);
				else if (chip.getCost() == 12 && chip.getCostB() == 13)
					chip.setPriority(Chip.FOCUS);
				break;
			case 6:
				if (chip.getCost() == 14 && chip.getCostB() == 14)
					chip.setPriority(Chip.DIAMOND);
				else if (chip.getCost() == 15 && chip.getCostB() == 15)
					chip.setPriority(Chip.FOCUS);
				break;
			case 7:
				if (chip.getCost() == 17 && chip.getCostB() == 18)
					chip.setPriority(Chip.DIAMOND);
				else if (chip.getFusionCost() <= 21)
					chip.setPriority(Chip.SPRAY);
				break;
			}
		}
		return collection;
	}

	/**
	 * @param collection of Chips set with INPUT priority valor
	 */
	protected static void setToInput(ArrayList<Chip> collection) {

		for (Chip chip : collection) {

			switch (chip.getFusionRank()) {
			case 0:
				if (chip.getFusionCost() == 4)
					chip.setPriority(Chip.DIAMOND);
				else if (chip.getFusionCost() <= 9)
					chip.setPriority(Chip.INPUT);
				break;
			case 1:
				if (chip.getFusionCost() == 5)
					chip.setPriority(Chip.DIAMOND);
				else if (chip.getFusionCost() <= 9)
					chip.setPriority(Chip.INPUT);
				break;
			case 2:
				if (chip.getFusionCost() == 6)
					chip.setPriority(Chip.DIAMOND);
				else if (chip.getFusionCost() <= 9)
					chip.setPriority(Chip.INPUT);
				break;
			case 3:
				if (chip.getFusionCost() == 7)
					chip.setPriority(Chip.DIAMOND);
				else if (chip.getFusionCost() <= 10)
					chip.setPriority(Chip.INPUT);
				break;
			case 4:
				if (chip.getFusionCost() == 9)
					chip.setPriority(Chip.DIAMOND);
				else if (chip.getFusionCost() <= 11)
					chip.setPriority(Chip.INPUT);
				break;
			case 5:
				if (chip.getFusionCost() == 11)
					chip.setPriority(Chip.DIAMOND);
				else if (chip.getFusionCost() <= 13)
					chip.setPriority(Chip.INPUT);
				break;
			case 6:
				if (chip.getFusionCost() == 14)
					chip.setPriority(Chip.DIAMOND);
				else if (chip.getFusionCost() <= 15)
					chip.setPriority(Chip.INPUT);
				break;
			case 7:
				if (chip.getFusionCost() == 17)
					chip.setPriority(Chip.DIAMOND);
				else if (chip.getFusionCost() <= 18)
					chip.setPriority(Chip.INPUT);
				break;
			}
		}
	}
}
