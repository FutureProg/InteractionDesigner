package interactiondesigner.utils;

import static org.junit.Assert.*;
import org.junit.Test;

public class CoordMapTest{

	@Test
	public void testPutGet(){
		CoordMap<Integer,String> map = new CoordMap<>();
		map.put(2, 1, "value");
		map.put(1,2,"value2");
		map.put(2,2,"value3");

		assertEquals(map.get(2, 1), "value");
		assertEquals(map.get(1,2), "value2");
		assertEquals(map.get(2,2), "value3");
	}

	public void testRemove(){
		CoordMap<Integer,String> map = new CoordMap<>();
		map.put(3, 1, "value");
		assertEquals(map.get(3, 1), "value");
		map.remove(3,1);
		assertNull(map.get(3, 1));
	}

}