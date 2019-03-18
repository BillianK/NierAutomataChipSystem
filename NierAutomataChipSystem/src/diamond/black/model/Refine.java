package diamond.black.model;

import java.util.ArrayList;

public class Refine extends Fusion {

	/**
	 * @param collection of chips
	 * @return a collection of chips, only what is necessary to obtain the best chip in the collection
	 */
	protected static ArrayList<Chip> refine(ArrayList<Chip> collection) {

		int cAStock = 0, cBStock = 0;
		int indexA = 0, indexB = 0;
		int rank;
		ArrayList<Chip> buyers = new ArrayList<>();
		ArrayList<Chip> suppliers = new ArrayList<>();
		ArrayList<Chip> buyerStock = new ArrayList<>();
		ArrayList<Chip> currentBuyerStock;
		Chip chip;

		collection.get(indexA).setNumber(1);

		rank = collection.get(indexA).getRank();

//		collection.sort(Sort.byRank.thenComparing(Sort.byPriority).thenComparing(Sort.byFusionCost)
//				.thenComparing(Sort.byCost).reversed());
		
//		Util.screen(collection);

		while (rank > -1) {

			buyers = selectRank(collection, rank);

			rank--;

			suppliers = selectRank(collection, rank);

			while (indexA < buyers.size()) {

				cAStock = 0;
				cBStock = cAStock;

				while (indexB < suppliers.size()) {

//					System.out.println("______________");
//					System.out.println("-> BUYER " + buyers.get(indexA));

					if (buyers.get(indexA).getCost() == 0)
						break;

//					System.out.println("SUPPLIER " + suppliers.get(indexB));

					while (suppliers.get(indexB).getFusionCost() == buyers.get(indexA).getCost()
							&& cAStock < buyers.get(indexA).getNumber() && suppliers.get(indexB).getNumber() > 0) {

						cAStock++;

						suppliers.get(indexB).setNumber(suppliers.get(indexB).getNumber() - 1);

						chip = new Chip();
						chip = copy((suppliers.get(indexB)));
						chip.setNumber(1);
						buyerStock.add(chip);

//						System.out.println("BUY CHIP ");
//						System.out.println("SUPPLIER REST = " + suppliers.get(indexB).getNumber());
					}
					while (suppliers.get(indexB).getFusionCost() == buyers.get(indexA).getCostB()
							&& cBStock < buyers.get(indexA).getNumber() && suppliers.get(indexB).getNumber() > 0) {

						cBStock++;

						suppliers.get(indexB).setNumber(suppliers.get(indexB).getNumber() - 1);

						chip = new Chip();
						chip = copy((suppliers.get(indexB)));
						chip.setNumber(1);
						buyerStock.add(chip);

//						System.out.println("BUY CHIP ");
//						System.out.println("SUPPLIER REST = " + suppliers.get(indexB).getNumber());
					}
					if (cAStock == buyers.get(indexA).getNumber() && cBStock == buyers.get(indexA).getNumber())
						break;
					indexB++;
				}
				indexB = 0;
				indexA++;
//				System.out.println("______________");
//				System.out.println("BUYERS STOCK");
//				Util.screen(buyerStock);
			}
			indexA = 0;
			collection = removeRankEqualTo(collection, rank);
//			System.out.println("--------------------------");
//			System.out.println("REMOVE");
//			ChipUtil.screen(suppliers);
//			System.out.println("RANK " + suppliers.get(0).getRank());
//			ChipUtil.screen(collection);
			buyerStock = regroupChip(buyerStock, true);
			currentBuyerStock = selectRank(buyerStock, rank);
			collection.addAll(currentBuyerStock);
//			System.out.println("ADD");
//			ChipUtil.screen(currentBuyerStock);
//			ChipUtil.screen(collection);
		}
		// collection.sort(ChipSorting.byRankFusion);
		// screen(collection);
		buyerStock.add(collection.get(0));
		buyerStock.sort(Sort.byRank.reversed());

		return buyerStock;
	}

	private static ArrayList<Chip> removeRankEqualTo(ArrayList<Chip> collection, int rank) {

		int indexA = 0;

		while (indexA < collection.size()) {
			if (collection.get(indexA).getRank() == rank) {
				collection.remove(indexA);
				indexA--;
			}
			indexA++;
		}

		return collection;
	}

}
