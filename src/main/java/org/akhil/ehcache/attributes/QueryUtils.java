package org.akhil.ehcache.attributes;

import org.akhil.ehcache.bean.Alcohol;
import org.akhil.ehcache.bean.Person;

import net.sf.ehcache.search.Result;
import net.sf.ehcache.search.Results;

public class QueryUtils {
	
	public static void printQueryResults(Results results) {
		for(Result result : results.all()) {
//			Integer key = (Integer) result.getKey();
//			System.out.println("key : " + key);
			Object value = result.getValue();
			if(value instanceof Person) {
				Person person = (Person) value;
				System.out.println(person);
			} else if (value instanceof Alcohol) {
				Alcohol alcohol = (Alcohol) value;
				System.out.println(alcohol);
			} else {
				System.out.println("Unrecognized value");
			}
		}
	}

}
