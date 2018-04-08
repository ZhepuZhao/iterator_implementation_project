package a6novice;

import java.util.NoSuchElementException;

import comp401.sushi.Plate;
import comp401.sushi.PlateImpl;

public class Belt {
	
	private Plate[] plates; // belt could be an array of plates
	private int size;
	private Plate tempPlate; // a temporary plate for removePlateAtPosition method 
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
		if (position < 0 || position >= this.getSize()) {
			throw new IllegalArgumentException();
		}
		if (this.plates == null){
			return null;
		} else {
			return plates[position];
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
		if (position < 0 || position >= this.getSize()) {
			throw new IllegalArgumentException();
		}
		if (this.plates[position] != null) {
			throw new BeltPlateException(position, plate, this);
		}
		this.plates[position] = plate;
	}
	
	/*
	 * set the plate to null at a specified position 
	 * input: the position to clear the plate
	 */
	public void clearPlateAtPosition(int position) {
		if (position < 0 || position >= this.getSize()) {
			throw new IllegalArgumentException();
		}
		this.plates[position] = null;
	}
	
	/*
	 * remove the plate at a specified position and return it
	 * input: the position to remove the plate
	 * return: the removed plate
	 */
	public Plate removePlateAtPosition(int position) {
		this.tempPlate = this.getPlateAtPosition(position);
		if (position < 0 || position >= this.getSize()) {
			throw new IllegalArgumentException();
		}
		if (this.plates[position] == null) {
			throw new NoSuchElementException();
		}
		this.clearPlateAtPosition(position);
		return this.tempPlate;
	}
}
