package org.akhil.ehcache.attributes;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.akhil.ehcache.bean.Gender;
import org.akhil.ehcache.bean.Person;

public class Loader {

	public static CacheManager CM;
	public static Cache CACHE1;
	
	static {
		try {
			load();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws InterruptedException {
//		Thread.currentThread().dumpStack();
//		load();
	}

	private static void load() throws InterruptedException {
		System.out.println("calling load");
		CM = CacheManager.newInstance("src/main/resources/ehcache3.xml");
		String cacheNames[] = CM.getCacheNames();
		System.out.println(Arrays.toString(cacheNames));
		
		Map<Integer, Person> map = new HashMap<>();
		int i = 0;
		map.put(i++, new Person("Akhil", LocalDate.of(1991, 6, 15), Gender.MALE, 72.3d));
		map.put(i++, new Person("Darpan", LocalDate.of(1992, 11, 19), Gender.MALE, 62.1d));
		map.put(i++, new Person("Goldberg", LocalDate.of(1978, 4, 9), Gender.MALE, 272.5d));
		map.put(i++, new Person("Mithaai", LocalDate.of(1985, 11, 19), Gender.FEMALE, 52.0d));
		map.put(i++, new Person("Khaana", LocalDate.of(1992, 4, 12), Gender.FEMALE, 47.7d));
		
		
		CACHE1 = CM.getCache("cache1");
		for(Map.Entry<Integer, Person> entry : map.entrySet()) {
			System.out.println(entry.getKey() + " " + entry.getValue());
			CACHE1.put(new Element(entry.getKey(), entry.getValue()));
		}
		//cache1.put(new Element(i++, new Alcohol(230.30, 37)));
		
//		Test.main(cacheNames);
		
		
	}
}
