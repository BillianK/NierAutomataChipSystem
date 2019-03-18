package diamond.black.model;

import java.util.ArrayList;

public class Origin extends Fusion {

	/**
	 * @param collection of chips
	 * @param inputs collection of chips
	 * @return a collection of chips, all possible needed associations for best chip fusion
	 */
	protected static ArrayList<Chip> origin(ArrayList<Chip> collection, ArrayList<Chip> inputs) {

		int indexA = 0, indexB = 0;
		int[] cABs = new int[2];
		int rank;
		ArrayList<Chip> composants = new ArrayList<>();
		ArrayList<Chip> targets = new ArrayList<>();

		

//		Util.screen(collection);

		rank = collection.get(indexB).getRank();

		collection.addAll(copy(inputs));

		collection = removeRankSuperiorTo(collection, rank);
		collection = removeWithinRankExcept(collection, rank, collection.get(0));
		
		collection.sort(Sort.byCost);
//		Util.screen(collection);
		
		while (indexB < collection.size() && rank > 0) {

			targets = selectRank(collection, rank);
//			 System.out.println("________________");
//			 System.out.println("TARGET(S)");
//			 ChipUtil.screen(targets);

			if (!targets.isEmpty()) {

				cABs = minMaxCostAB(targets);

				rank--;

				composants = selectRank(collection, rank);
//				System.out.println("COMPOSANT(S)");
//				ChipUtil.screen(composants);

				while (indexA < composants.size()) {

//					System.out.println("________________");
//					System.out.println("COMPARE " + composants.get(indexA));
//					System.out.println("cABs [" + cABs[0] + "," + cABs[1] + "]");
//					System.out.println("________________");
					if (composants.get(indexA).getFusionCost() >= cABs[0]
							&& composants.get(indexA).getFusionCost() <= cABs[1]) {
//						System.out.println("ADD CHIP");
					} else {
//						System.out.println("DEL CHIP");
						collection.remove(composants.get(indexA));
					}
					indexA++;
				}
				indexA = 0;
			}
			indexB++;
		}
		collection.sort(Sort.byRank.reversed());
		// screen(collection);
		return collection;
	}

	private static ArrayList<Chip> removeRankSuperiorTo(ArrayList<Chip> collection, int rank) {

		int indexA = 0;

		while (indexA < collection.size()) {
			if (collection.get(indexA).getRank() > rank) {
				collection.remove(indexA);
				indexA--;
			}
			indexA++;
		}

		return collection;
	}

	private static ArrayList<Chip> removeWithinRankExcept(ArrayList<Chip> collection, int rank, Chip chip) {

		ArrayList<Chip> recycled = new ArrayList<>();

		for (Chip chp : collection) {
			if (chp.getRank() != rank)
				if (!(chp.equals(chip)))
					recycled.add(chp);
		}
		recycled.add(chip);

		return recycled;
	}

}
