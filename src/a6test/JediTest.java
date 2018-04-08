package a6test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

import a6jedi.Belt;
import a6jedi.BeltFullException;
import a6jedi.BeltPlateException;
import comp401.sushi.BluePlate;
import comp401.sushi.GoldPlate;
import comp401.sushi.GreenPlate;
import comp401.sushi.Plate;
import comp401.sushi.PlatePriceException;
import comp401.sushi.RedPlate;
import comp401.sushi.Sashimi;
import comp401.sushi.Sushi;

class JediTest {

	@Test
	void postionTest() throws PlatePriceException, BeltPlateException {
		Belt belt1 = new Belt(7);
		Sushi sashimi = new Sashimi(Sashimi.SashimiType.CRAB);
		Plate greenPlate = new GreenPlate(sashimi) ;
		Plate redPlate = new RedPlate(sashimi);
		Plate bluePlate = new BluePlate(sashimi);
		// the price of gold plate should be greater than or equal to 5.0
		Plate goldPlate = new GoldPlate(sashimi, 5.0); 
		
		// set the plate
		belt1.setPlateAtPosition(greenPlate, 1); 
		belt1.setPlateAtPosition(redPlate, 3);
		belt1.setPlateAtPosition(bluePlate, 4);
		belt1.setPlateAtPosition(goldPlate, 5);
		
		// validate position test
		assertEquals(belt1.getPlateAtPosition(0), belt1.getPlateAtPosition(7));
		assertEquals(belt1.getPlateAtPosition(5), belt1.getPlateAtPosition(-2));
		
		// setPlateAtPosition test
		assertThrows(BeltPlateException.class, () -> {
			belt1.setPlateAtPosition(greenPlate, 1);
			});
		
		// clear test
		belt1.setPlateAtPosition(greenPlate, 0);
		belt1.clearPlateAtPosition(0);
		assertEquals(belt1.getPlateAtPosition(0), null);
		
		// remove test
		assertThrows(NoSuchElementException.class, () -> {
			belt1.removePlateAtPosition(0);
		});
		belt1.removePlateAtPosition(1);
		assertEquals(belt1.getPlateAtPosition(1),null);
	}
	
	@Test
	void setPlateNearestToPositionTest() throws PlatePriceException, BeltPlateException, BeltFullException {
		Belt belt2 = new Belt(7);
		Sushi sashimi = new Sashimi(Sashimi.SashimiType.CRAB);
		Plate greenPlate = new GreenPlate(sashimi) ;
		Plate redPlate = new RedPlate(sashimi);
		Plate bluePlate = new BluePlate(sashimi);
		// the price of gold plate should be greater than or equal to 5.0
		Plate goldPlate = new GoldPlate(sashimi, 5.0); 
		
		// set the plate
		belt2.setPlateAtPosition(greenPlate, 1); 
		belt2.setPlateAtPosition(redPlate, 3);
		belt2.setPlateAtPosition(bluePlate, 4);
		belt2.setPlateAtPosition(goldPlate, 5);
		
		belt2.setPlateNearestToPosition(bluePlate, 0);
		assertEquals(bluePlate, belt2.getPlateAtPosition(0));
		belt2.setPlateNearestToPosition(redPlate, 1);
		assertEquals(redPlate, belt2.getPlateAtPosition(2));
		belt2.setPlateNearestToPosition(goldPlate, 4);
		assertEquals(goldPlate, belt2.getPlateAtPosition(6));
		assertThrows(BeltFullException.class, () -> {
			belt2.setPlateNearestToPosition(bluePlate, 0);		
		});
		
	}
	
	@Test 
	void iteratorTest() throws PlatePriceException, BeltPlateException {
		Belt belt3 = new Belt(7);
		Belt belt4 = new Belt(7);
		Belt belt5 = new Belt(7);
		Sushi sashimi = new Sashimi(Sashimi.SashimiType.CRAB);
		Plate greenPlate = new GreenPlate(sashimi) ;
		Plate redPlate = new RedPlate(sashimi);
		Plate bluePlate = new BluePlate(sashimi);
		// the price of gold plate should be greater than or equal to 5.0
		Plate goldPlate = new GoldPlate(sashimi, 5.0); 
		
		// belt is not empty
		belt3.setPlateAtPosition(greenPlate, 1); 
		belt3.setPlateAtPosition(redPlate, 3);
		belt3.setPlateAtPosition(bluePlate, 4);
		belt3.setPlateAtPosition(goldPlate, 5);
		belt5.setPlateAtPosition(redPlate, 6);
		
		// hasNext test (start from 0)	
		assertTrue(belt3.iterator().hasNext());
		assertTrue(belt5.iterator().hasNext());
		
		// hasNext test (start from a specific position)
		assertTrue(belt3.iteratorFromPosition(5).hasNext());
		assertTrue(belt5.iteratorFromPosition(5).hasNext());
		
		// next test (start from 0)
		Iterator<Plate> iter1 = belt3.iterator();
		assertEquals(greenPlate, iter1.next());
		assertEquals(redPlate, iter1.next());
		assertEquals(bluePlate, iter1.next());
		assertEquals(goldPlate, iter1.next());
		
		// next test (start from a specific position)
		Iterator<Plate> iter2 = belt3.iteratorFromPosition(5);
		assertEquals(goldPlate, iter2.next());
		assertEquals(greenPlate, iter2.next());
		assertEquals(redPlate, iter2.next());
		
		// belt is empty
		assertFalse(belt4.iterator().hasNext());
		assertFalse(belt4.iteratorFromPosition(4).hasNext());
		
		assertThrows(NoSuchElementException.class, () -> {
			belt4.iterator().next();
		});
		assertThrows(NoSuchElementException.class, () -> {
			belt4.iteratorFromPosition(5).next();
		});
		
	}
	
	@Test
	void ColorFiltedBeltIterator() throws PlatePriceException, BeltPlateException {
		Belt belt3 = new Belt(7);
		Belt belt5 = new Belt(7);
		Sushi sashimi = new Sashimi(Sashimi.SashimiType.CRAB);
		Plate greenPlate = new GreenPlate(sashimi) ;
		Plate redPlate = new RedPlate(sashimi);
		// the price of gold plate should be greater than or equal to 5.0
		Plate goldPlate = new GoldPlate(sashimi, 5.0); 
		
		// belt is not empty
		belt3.setPlateAtPosition(greenPlate, 1); 
		belt3.setPlateAtPosition(redPlate, 3);
		belt3.setPlateAtPosition(greenPlate, 4);
		belt3.setPlateAtPosition(goldPlate, 5);
		belt5.setPlateAtPosition(redPlate, 6);
		
		Iterator<Plate> iter1 = belt3.iterator(Plate.Color.RED);
		assertTrue(iter1.hasNext());
		assertEquals(redPlate, iter1.next());
		Iterator<Plate> iter2 = belt3.iterator(Plate.Color.BLUE);
		assertFalse(iter2.hasNext());
	}
	
	@Test
	void PriceThresholdIterator() throws PlatePriceException, BeltPlateException {
		Belt belt3 = new Belt(7);
		Sushi sashimi = new Sashimi(Sashimi.SashimiType.CRAB);
		Plate greenPlate = new GreenPlate(sashimi) ;
		Plate redPlate = new RedPlate(sashimi);
		Plate bluePlate = new BluePlate(sashimi);
		// the price of gold plate should be greater than or equal to 5.0
		Plate goldPlate = new GoldPlate(sashimi, 5.0); 
		
		// belt is not empty
		belt3.setPlateAtPosition(greenPlate, 1); 
		belt3.setPlateAtPosition(bluePlate, 3);
		belt3.setPlateAtPosition(redPlate, 4);
		belt3.setPlateAtPosition(goldPlate, 5);
		belt3.setPlateAtPosition(redPlate, 6);
		
		Iterator<Plate> iter1 = belt3.iterator(2.0);
		assertTrue(iter1.hasNext());
		assertEquals(greenPlate, iter1.next());
		assertEquals(redPlate, iter1.next());
		assertEquals(redPlate, iter1.next());
		
		Iterator<Plate> iter2 = belt3.iteratorFromPosition(6, 2.0);
		assertTrue(iter2.hasNext());
		assertEquals(redPlate, iter2.next());
		assertEquals(greenPlate, iter2.next());
		Iterator<Plate> iter3 = belt3.iterator(0.0);
		assertFalse(iter3.hasNext());	
	}
	
	@Test
	void iteartorRemoveTest() throws PlatePriceException, BeltPlateException {
		Belt belt3 = new Belt(7);
		Sushi sashimi = new Sashimi(Sashimi.SashimiType.CRAB);
		Plate greenPlate = new GreenPlate(sashimi) ;
		Plate redPlate = new RedPlate(sashimi);
		Plate bluePlate = new BluePlate(sashimi);
		// the price of gold plate should be greater than or equal to 5.0
		Plate goldPlate = new GoldPlate(sashimi, 5.0); 
		
		// belt is not empty
		belt3.setPlateAtPosition(greenPlate, 1); 
		belt3.setPlateAtPosition(bluePlate, 3);
		belt3.setPlateAtPosition(redPlate, 4);
		belt3.setPlateAtPosition(goldPlate, 5);
		
		Iterator<Plate> iter2 = belt3.iterator();
		iter2.next(); // the start_position is 2 after calling the method

		iter2.next(); // the start_position is 4 after calling the method
		iter2.remove(); // remove the element whose position is 3
		assertEquals(null, belt3.getPlateAtPosition(3));
		assertThrows(IllegalArgumentException.class, () -> {
			iter2.remove();
		});

		Iterator<Plate> iter3 = belt3.iterator(); // new iterator
		assertThrows(IllegalArgumentException.class, () -> {
			// the previous element is null, because it didn't call the next() method
			iter3.remove();
		});
		
	

		
	}
}
