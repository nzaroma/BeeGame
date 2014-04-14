package ru.roman.bee.model.unit.utils;

import com.badlogic.gdx.utils.Array;

import ru.roman.bee.model.unit.Unit;

public class UnitUtils {

	public static <T extends Unit> T getLastUnitOfType(Array<Unit> unitArray, Class<T> class1) {

		for(int i = unitArray.size -1; i>=0 ; i-- ) {
			Unit tempUnit = unitArray.get(i);
			if (class1.isInstance(tempUnit)) {
				return (T) tempUnit;				
			}				
		}
		return null;
		
		
	}
	
}
