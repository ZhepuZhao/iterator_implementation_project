package a6novice;

import comp401.sushi.Plate;

public class BeltPlateException extends Exception{

	private int position;
	private Plate plate_to_be_set;
	private Belt belt;
	
	public BeltPlateException(int position, Plate plate_to_be_set, Belt belt) {
		// TODO Auto-generated constructor stub
		super("The plate at this position has alreayd been occupied");
		this.position = position;
		this.plate_to_be_set = plate_to_be_set;
		this.belt = belt;
		
	}
	public int getPosition() {
		return this.position;
	}
	public Plate getPlateToSet() {
		return this.plate_to_be_set;
	}
	public Belt getBelt() {
		return this.belt;
	}

}
