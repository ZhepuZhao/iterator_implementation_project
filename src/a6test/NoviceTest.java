package a6test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

import a6novice.Belt;
import a6novice.BeltPlateException;
import comp401.sushi.BluePlate;
import comp401.sushi.GoldPlate;
import comp401.sushi.GreenPlate;
import comp401.sushi.Plate;
import comp401.sushi.PlatePriceException;
import comp401.sushi.RedPlate;
import comp401.sushi.Sashimi;
import comp401.sushi.Sushi;

class NoviceTest {

	@Test
	void constructorTest() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Belt(0);
			});
		assertThrows(IllegalArgumentException.class, () -> {
			new Belt(-1);
		});
	}
	
	@Test
	void positionBoundaryTest() {
		Belt belt1 = new Belt(3);
		assertThrows(IllegalArgumentException.class, () -> {
			belt1.getPlateAtPosition(-1);
			});
		assertThrows(IllegalArgumentException.class, () -> {
			belt1.getPlateAtPosition(6);
			});
	}
	@Test
	void setAndGetPlateAtPositionTest() throws PlatePriceException, BeltPlateException {
		Belt belt = new Belt(7);
		Sushi sashimi = new Sashimi(Sashimi.SashimiType.CRAB);
		Plate greenPlate = new GreenPlate(sashimi) ;
		Plate redPlate = new RedPlate(sashimi);
		Plate bluePlate = new BluePlate(sashimi);
		// the price of gold plate should be greater than or equal to 5.0
		Plate goldPlate = new GoldPlate(sashimi, 5.0); 
		belt.setPlateAtPosition(greenPlate, 1); 
		belt.setPlateAtPosition(redPlate, 3);
		belt.setPlateAtPosition(bluePlate, 4);
		belt.setPlateAtPosition(goldPlate, 5);
		belt.setPlateAtPosition(redPlate, 6);
		
		// setPlateAtPosition test
		assertThrows(BeltPlateException.class, () -> {
			belt.setPlateAtPosition(greenPlate, 1);
			});
		
		// getPlateAtPosition test
		assertEquals(greenPlate, belt.getPlateAtPosition(1));
		assertEquals(redPlate, belt.getPlateAtPosition(3));
		assertEquals(null, belt.getPlateAtPosition(2));
	}
	
	@Test 
	void clearAndRemovePlateAtPosition() throws PlatePriceException, BeltPlateException {
		Belt belt = new Belt(3);
		Sushi sashimi = new Sashimi(Sashimi.SashimiType.CRAB);
		Plate greenPlate = new GreenPlate(sashimi);
		belt.setPlateAtPosition(greenPlate, 1); 
		belt.setPlateAtPosition(greenPlate, 2); 
		belt.clearPlateAtPosition(1);
		assertEquals(null, belt.getPlateAtPosition(1));
		belt.removePlateAtPosition(2);
		assertThrows(NoSuchElementException.class, () -> {
			belt.removePlateAtPosition(2);
		});
	}
	
	@Test
	void a() {
		
	}

}
