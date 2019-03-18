package diamond.black.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Util {

	private static void generateTable(String name) throws IOException {

		File records = new File(name);
		BufferedWriter writer;

		if (!records.exists()) {

			writer = new BufferedWriter(new FileWriter(records));
			// Set recommendations to chips
			Dealer.recommendationDealer(Table.table()).forEach(e -> {
				try {
					writer.write(new String("" + e.toFileString() + "\n"));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			});
			writer.close();
		}
		loadFile(name);
	}

	/**
	 * initializes associations table
	 * 
	 * @throws IOException if file isn't accessible
	 */
	public static void init() throws IOException {
		generateTable("ChipsRecords.csv");
	}

	/**
	 * @param name of file to load
	 * @return a collection of chips, the one of loaded file
	 * @throws IOException
	 */
	protected static ArrayList<Chip> loadFile(String name) throws IOException {

		ArrayList<Chip> chips = new ArrayList<>();
		File records = new File(name);
		String line;
		String split = ",";

		if (records.exists()) {
			try (BufferedReader reader = new BufferedReader(new FileReader(records))) {

				while ((line = reader.readLine()) != null) {

					String[] parameters = line.split(split);

					Chip tempChip = new Chip();
					tempChip.setRank(Integer.parseInt(parameters[0]));
					tempChip.setCost(Integer.parseInt(parameters[1]));
					tempChip.setCostB(Integer.parseInt(parameters[2]));
					tempChip.setPriority((Integer.parseInt(parameters[4])));
					tempChip.setFusionRank((Integer.parseInt(parameters[3])));
					tempChip.setFusionCost((Integer.parseInt(parameters[5])));
					tempChip.setNumber((Integer.parseInt(parameters[6])));
					chips.add(tempChip);
				}

			} catch (IOException e) {
				e.printStackTrace();
			}

			return chips;
		} else {
			init();
			loadFile(name);
		}
		return null;
	}

	/**
	 * @param name       of file to save
	 * @param collection of chips
	 * @throws IOException if file isn't accessible
	 */
	protected static void saveFile(String name, ArrayList<Chip> collection) throws IOException {

		BufferedWriter writer = new BufferedWriter(new FileWriter(name));

		collection.forEach(e -> {
			try {
				writer.write(new String("" + e.toFileString() + "\n"));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		writer.close();
	}

	/**
	 * @param collection of chips Display a collection of chips in console mode
	 */
	public static void screen(ArrayList<Chip> collection) {

		collection.stream().forEach(e -> System.out.println(e));
		System.out.println("______________");

	}

	/**
	 * @param collection of chips Display a collection of chips without INPUT in
	 *                   console mode
	 */
	public static void screenInputsFilter(ArrayList<Chip> collection) {

		collection.stream().forEach(e -> {
			if (e.getCost() > 0)
				System.out.println(e);
		});
		System.out.println("______________");

	}

}
