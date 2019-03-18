package diamond.black.model;

import java.util.Comparator;

public class Sort {

	public static Comparator<Chip> byPriority = Comparator.comparing(Chip::getPriority);
	public static Comparator<Chip> byRank = Comparator.comparing(Chip::getRank);
	public static Comparator<Chip> byFusionCost = Comparator.comparing(Chip::getFusionCost);
	public static Comparator<Chip> byCost = Comparator.comparing(Chip::getCost);
	public static Comparator<Chip> byCostB = Comparator.comparing(Chip::getCostB);
	public static Comparator<Chip> byFusionRank = Comparator.comparing(Chip::getFusionRank);;

}
