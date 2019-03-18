package diamond.black.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@EqualsAndHashCode(exclude = { "number" })
@Setter(AccessLevel.PROTECTED)
@NoArgsConstructor
public class Chip {

	public static final int INPUT = 4, DIAMOND = 3, FOCUS = 2, SPRAY = 1, WIDESPRAY = 0;
	public static String output = "";
	private int rank;
	private int cost;
	private int costB;
	private int priority = -1;
	private int fusionRank;
	private int fusionCost;
	@Setter(AccessLevel.PUBLIC)
	private int number;

	public Chip(int fusionRank) {

		this.setRank(fusionRank - 1);
		this.setFusionRank(fusionRank);
	}

	public Chip(int fusionRank, int fusionCost) {

		this(fusionRank);
		this.setFusionCost(fusionCost);
	}

	protected Chip(int rank, int cost, int costB, int fusionRank, int fusionCost) {

		this(fusionRank, fusionCost);
		this.setCost(cost);
		this.setCostB(costB);
	}

	private String integerToPriority(int number) {

		if (number == -1)
			return new String("#");
		else if (number == 0)
			return new String("_");
		else if (number == WIDESPRAY)
			return new String("-");
		else if (number == SPRAY)
			return new String("+");
		else if (number == FOCUS)
			return new String("++");
		else if (number == DIAMOND)
			return new String("*");
		else if (number == INPUT)
			return new String("**");
		else
			return new String("Er");
	}

	protected String IntegerToRomanNumber(int number) {

		if (number == -1)
			return new String("");
		else if (number == 0)
			return new String("0");
		else if (number == 1)
			return new String("I");
		else if (number == 2)
			return new String("II");
		else if (number == 3)
			return new String("III");
		else if (number == 4)
			return new String("IV");
		else if (number == 5)
			return new String("V");
		else if (number == 6)
			return new String("VI");
		else if (number == 7)
			return new String("VII");
		else if (number == 8)
			return new String("VIII");
		else
			return new String("Er");
	}

	private String integerToString(int number) {

		if (number == 0)
			return new String("-");
		else
			return new String(String.valueOf(number));
	}

	public String toFileString() {

		return new String(
				rank + "," + cost + "," + costB + "," + fusionRank + "," + priority + "," + fusionCost + "," + number);
	}

	public String toString() {

		String rankString = IntegerToRomanNumber(this.getRank());
		String fusionRankString = IntegerToRomanNumber(this.getFusionRank());
		String priority = integerToPriority(this.getPriority());
		String costA = integerToString(this.getCost());
		String costB = integerToString(this.getCostB());

		return new String(rankString + " [" + costA + " " + costB + "] " + priority + " " + fusionRankString + " "
				+ this.getFusionCost() + " x " + this.getNumber());
	}
}
