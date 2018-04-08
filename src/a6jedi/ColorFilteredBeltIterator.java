package a6jedi;

import java.util.Iterator;
import java.util.NoSuchElementException;

import comp401.sushi.Plate;

public class ColorFilteredBeltIterator implements Iterator<Plate> {

	private Belt belt;
	private int start_position;
	private Plate.Color color_filter;
	public ColorFilteredBeltIterator(Belt belt, int start_position, Plate.Color color_filter) {
		// TODO Auto-generated constructor stub
		this.belt = belt;
		this.start_position = start_position;
		this.color_filter = color_filter;
	}

	// check if the next plate meeting the color filter requirement exists 
	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		int tempPosition = this.start_position;
		while (tempPosition != (this.start_position + belt.getSize())) {
			if (this.belt.getPlateAtPosition(belt.validatePosition(tempPosition)) != null &&
					(belt.getPlateAtPosition(tempPosition).getColor() == this.color_filter)) {
				return true;
			} else {
				tempPosition ++;
				continue;
			}
		}
		return false;
	}

	// move to the next plate if exist and meets the color filter as well
	@Override
	public Plate next() {
		// TODO Auto-generated method stub
		if (!this.hasNext()) {
			throw new NoSuchElementException();
		} else {
			while (this.belt.getPlateAtPosition(this.belt.validatePosition(start_position)) == null 
					||(belt.getPlateAtPosition(start_position).getColor() != this.color_filter)) {
				this.start_position = this.belt.validatePosition(start_position + 1);
			}
			Plate tempPlate = this.belt.getPlateAtPosition(
					this.belt.validatePosition(start_position));
			this.start_position = this.belt.validatePosition(start_position + 1);
			return tempPlate;
		}	}

}
