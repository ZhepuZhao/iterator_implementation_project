package a6adept;

import java.util.Iterator;
import java.util.NoSuchElementException;

import comp401.sushi.Plate;
import comp401.sushi.PlateImpl;

public class Belt implements Iterable<Plate> {
	
	private Plate[] plates; // belt could be an array of plates
	private int size;
	private Plate tempPlate; // a temporary plate for remove method 
	public Belt(int size) {
		// TODO Auto-generated constructor stub
		if (size <= 0) {
			throw new IllegalArgumentException();
		}
		this.size = size;
		this.plates = new PlateImpl[size].clone();
	}
	
	// get the size of the belt
	public int getSize() {
		return this.size;
	}
	
	/*
	 * get a plate at a specified position
	 * input: position
	 * return: the plate at the position or some exception
	 */
	public Plate getPlateAtPosition(int position) {
		if (this.plates == null){
			return null;
		} else {
			return plates[this.validatePosition(position)];
		}
	}
	
	/*
	 * set a plate at a given position on the belt
	 * input: a plate and the position to set
	 */
	public void setPlateAtPosition(Plate plate, int position) throws BeltPlateException {
		if (plate == null) {
			throw new IllegalArgumentException();
		}
		if (this.plates[this.validatePosition(position)] != null) {
			throw new BeltPlateException(this.validatePosition(position), plate, this);
		}
		this.plates[this.validatePosition(position)] = plate;
	}
	
	/*
	 * set the plate to null at a specified position 
	 * input: the position to clear the plate
	 */
	public void clearPlateAtPosition(int position) {
		this.plates[this.validatePosition(position)] = null;
	}
	
	/*
	 * remove the plate at a specified position and return it
	 * input: the position to remove the plate
	 * return: the removed plate
	 */
	public Plate removePlateAtPosition(int position) {
		this.tempPlate = this.getPlateAtPosition(this.validatePosition(position));
		if (this.plates[this.validatePosition(position)] == null) {
			throw new NoSuchElementException();
		}
		this.clearPlateAtPosition(this.validatePosition(position));
		return this.tempPlate;
	}
	
	/*
	 * set a plate at the nearest position to the given position
	 * input: position, plate
	 * return: the final position where the plate is set
	 */
	public int setPlateNearestToPosition(Plate plate, int position) 
			throws BeltFullException {
		int tempPosition = validatePosition(position + 1);
		if (this.plates[this.validatePosition(position)] == null) {
			this.plates[this.validatePosition(position)] = plate;
			return this.validatePosition(position);
		} else {
			while (tempPosition != (validatePosition(position))) {
				if (this.plates[tempPosition] == null) {
					this.plates[tempPosition] = plate;
					return tempPosition;
				} else {
					tempPosition = validatePosition(tempPosition + 1);
				}
			}
		}
		throw new BeltFullException(this);
	}
	
	/*
	 * normalize the position
	 * input: original position
	 * return: normalized position
	 */
	public int validatePosition(int position) {
		if (position < 0) {
			return (position % this.getSize() + this.getSize());
		} else if (position >= this.getSize()) {
			return position % this.getSize();
		} else {
			return position;
		}
	}
	
	/*
	 * override iterator
	 * return: a iterator starts from the position of index 0
	 */
	@Override
	public Iterator<Plate> iterator() {
		// TODO Auto-generated method stub
		return new BeltIterator(this, 0);
	}
	
	/*
	 * create a iterator starting at a specified position
	 * input: the original position
	 * return: new iterator object staring at the normalized position
	 */
	public Iterator<Plate> iteratorFromPosition(int position) {
		return new BeltIterator(this, this.validatePosition(position));
	}
	
	/*
	 * rotate plates on the belt:
	 * move all the plates to the next position, the last one to the first one
	 */
	public void rotate() {
		Plate[] rotatedPlates = new PlateImpl[this.getSize()];
		for (int i = 0; i < this.getSize(); i++) {
			rotatedPlates[validatePosition(i + 1)] = this.plates[i];
		}
		this.plates = rotatedPlates;
	}
}
