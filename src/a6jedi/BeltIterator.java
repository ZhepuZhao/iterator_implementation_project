package a6jedi;

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
		}
		while (this.belt.getPlateAtPosition(
				this.belt.validatePosition(start_position))== null) {
			this.start_position = this.belt.validatePosition(start_position + 1);
		}
		Plate tempPlate = this.belt.getPlateAtPosition(
				this.belt.validatePosition(start_position));
		this.start_position = this.belt.validatePosition(start_position + 1);
		return tempPlate;
	}
	
	// remove the element at the current position
	@Override
	public void remove() {
		// if the previous element is not null, it means the next() method wasn't called
		// or the remove() method has already been called
		if (belt.getPlateAtPosition(this.belt.validatePosition(start_position - 1)) == null) {
			throw new IllegalArgumentException();
		}
		belt.removePlateAtPosition(belt.validatePosition(start_position - 1));
	}

}
