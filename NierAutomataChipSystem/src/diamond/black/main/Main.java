package diamond.black.main;

import java.io.IOException;
import java.util.ArrayList;

import diamond.black.model.Chip;
import diamond.black.model.Optimise;

public class Main {

	public static void main(String[] args) {

		ArrayList<Chip> chips = new ArrayList<>();

		Chip chip04 = new Chip(0, 4);
		chip04.setNumber(1);
		chips.add(chip04);

		Chip chip05 = new Chip(0, 5);
		chip05.setNumber(0);
		chips.add(chip05);

		Chip chip06 = new Chip(0, 6);
		chip06.setNumber(0);
		chips.add(chip06);

		Chip chip07 = new Chip(0, 7);
		chip07.setNumber(2);
		chips.add(chip07);

		Chip chip08 = new Chip(0, 8);
		chip08.setNumber(0);
		chips.add(chip08);

		Chip chip09 = new Chip(0, 9);
		chip09.setNumber(2);
		chips.add(chip09);

		Chip chip010 = new Chip(0, 10);
		chip010.setNumber(0);
		chips.add(chip010);

		Chip chip15 = new Chip(1, 5);
		chip15.setNumber(0);
		chips.add(chip15);

		Chip chip16 = new Chip(1, 6);
		chip16.setNumber(0);
		chips.add(chip16);

		Chip chip17 = new Chip(1, 7);
		chip17.setNumber(2);
		chips.add(chip17);

		Chip chip18 = new Chip(1, 8);
		chip18.setNumber(1);
		chips.add(chip18);

		Chip chip19 = new Chip(1, 9);
		chip19.setNumber(0);
		chips.add(chip19);

		Chip chip26 = new Chip(2, 6);
		chip26.setNumber(2);
		chips.add(chip26);

		Chip chip27 = new Chip(2, 7);
		chip27.setNumber(1);
		chips.add(chip27);

		Chip chip28 = new Chip(2, 8);
		chip28.setNumber(1);
		chips.add(chip28);

		Chip chip29 = new Chip(2, 9);
		chip29.setNumber(7);
		chips.add(chip29);
		
		Chip chip210 = new Chip(2, 10);
		chip210.setNumber(0);
		chips.add(chip210);
		
		Chip chip211 = new Chip(2, 11);
		chip211.setNumber(0);
		chips.add(chip211);
		
		Chip chip212 = new Chip(2, 12);
		chip212.setNumber(0);
		chips.add(chip212);

		Chip chip37 = new Chip(3, 7);
		chip37.setNumber(1);
		chips.add(chip37);

		Chip chip38 = new Chip(3, 8);
		chip38.setNumber(0);
		chips.add(chip38);

		Chip chip39 = new Chip(3, 9);
		chip39.setNumber(0);
		chips.add(chip39);

		Chip chip49 = new Chip(4, 9);
		chip49.setNumber(1);
		chips.add(chip49);

		Chip chip410 = new Chip(4, 10);
		chip410.setNumber(0);
		chips.add(chip410);

		Chip chip511 = new Chip(5, 11);
		chip511.setNumber(0);
		chips.add(chip511);

		Chip chip512 = new Chip(5, 12);
		chip512.setNumber(0);
		chips.add(chip512);

		Chip chip513 = new Chip(5, 13);
		chip513.setNumber(0);
		chips.add(chip513);

		try {
			Optimise.optimise(chips);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
