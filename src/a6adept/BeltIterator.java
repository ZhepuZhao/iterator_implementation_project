package a6adept;

import java.util.Iterator;
import java.util.NoSuchElementException;

import comp401.sushi.Plate;

public class BeltIterator implements Iterator<Plate> {

	private Belt belt;
	private int start_position;
	public BeltIterator(Belt belt, int start_position) {
		// TODO Auto-generated constructor stub
		if (belt == null) {
			throw new NoSuchElementException();
		}
		this.belt = belt;
		this.start_position = start_position;
	}

	// check if the next plate exists on the belt
	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		int tempPosition = this.start_position;
		while (tempPosition != (this.start_position + belt.getSize())) {
			if (this.belt.getPlateAtPosition(belt.validatePosition(tempPosition)) != null) {
				return true;
			} else {
				tempPosition ++;
				continue;
			}
		}
		return false;
	}

	// move to the next plate if exists
	@Override
	public Plate next() {
		// TODO Auto-generated method stub
		if (!this.hasNext()) {
			throw new NoSuchElementException();
		} else {
			while (this.belt.getPlateAtPosition(
					this.belt.validatePosition(start_position)) == null) {
				this.start_position = this.belt.validatePosition(start_position + 1);
			}
			Plate tempPlate = this.belt.getPlateAtPosition(
					this.belt.validatePosition(start_position));
			this.start_position = this.belt.validatePosition(start_position + 1);
			return tempPlate;
		}
	}
	
	// remove method is not supported in this case
	public void remove() {
		throw new UnsupportedOperationException();
	}

}
