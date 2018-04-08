package a6jedi;

import java.util.Iterator;
import java.util.NoSuchElementException;

import comp401.sushi.Plate;

public class PriceThresholdBeltIterator implements Iterator<Plate> {

	private Belt belt;
	private int start_position;
	private double max_price;
	public PriceThresholdBeltIterator(Belt belt, int start_position, double max_price) {
		// TODO Auto-generated constructor stub
		this.belt = belt;
		this.start_position = start_position;
		this.max_price = max_price;
	}

	// check if the next plate meeting the price threshold requirement exists
	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		int tempPosition = this.start_position;
		while (tempPosition != (this.start_position + belt.getSize())) {
			if (this.belt.getPlateAtPosition(belt.validatePosition(tempPosition)) != null &&
					(belt.getPlateAtPosition(tempPosition).getPrice() <= this.max_price)) {
				return true;
			} else {
				tempPosition ++;
				continue;
			}
		}
		return false;
	}

	// move to the next plate if exist and meets the price threshold as well
	@Override
	public Plate next() {
		// TODO Auto-generated method stub
		if (!this.hasNext()) {
			throw new NoSuchElementException();
		} else {
			while (this.belt.getPlateAtPosition(
					this.belt.validatePosition(start_position))== null || 
					(belt.getPlateAtPosition(start_position).getPrice() > this.max_price)) {
				this.start_position = this.belt.validatePosition(start_position + 1);
			}
			Plate tempPlate = this.belt.getPlateAtPosition(
					this.belt.validatePosition(start_position));
			this.start_position = this.belt.validatePosition(start_position + 1);
			return tempPlate;
		}
	}

}
