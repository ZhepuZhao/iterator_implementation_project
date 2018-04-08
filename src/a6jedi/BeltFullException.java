package a6jedi;

public class BeltFullException extends Exception {

	private Belt belt;
	public BeltFullException(Belt belt) {
		// TODO Auto-generated constructor stub
		this.belt = belt;
		
	}
	public Belt getBelt() {
		return this.belt;
	}


}
