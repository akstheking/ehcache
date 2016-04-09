package org.akhil.ehcache.attributes;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.search.Attribute;
import net.sf.ehcache.search.Query;
import net.sf.ehcache.search.Results;
import net.sf.ehcache.search.attribute.DynamicAttributesExtractor;
import net.sf.ehcache.search.expression.Or;

import org.akhil.ehcache.bean.Gender;
import org.akhil.ehcache.bean.Person;

public class DynamicAggregator {
	
	public static void main(String[] args) {
		CacheManager CM = CacheManager.newInstance("src/main/resources/ehcache3.xml");
		String cacheNames[] = CM.getCacheNames();
		
		Cache cache = CM.getCache("cache1");
		Map<Integer, Person> map = new HashMap<>();
		int i = 0;
		map.put(i++, new Person("Akhil", LocalDate.of(1991, 6, 15), Gender.MALE, 72.3d));
		map.put(i++, new Person("Darpan", LocalDate.of(1992, 11, 19), Gender.MALE, 62.1d));
		map.put(i++, new Person("Goldberg", LocalDate.of(1978, 4, 9), Gender.MALE, 272.5d));
		map.put(i++, new Person("Mithaai", LocalDate.of(1985, 11, 19), Gender.FEMALE, 52.0d));
		map.put(i++, new Person("Khaana", LocalDate.of(1992, 4, 12), Gender.FEMALE, 47.7d));
		
		/*for(Map.Entry<Integer, Person> entry : map.entrySet()) {
			System.out.println(entry.getKey() + " " + entry.getValue());
			cache.put(new Element(entry.getKey(), entry.getValue()));
		}*/
		
		cache.registerDynamicAttributesExtractor(new DynamicAttributesExtractor() {
			
			@Override
			public Map<String, String> attributesFor(Element element) {
				Person person = (Person) element.getObjectValue();
				String name = person.getName();
				String initials = name.substring(0, 1);
				Map<String, String> map = new HashMap<>();
				map.put("initials", initials);
				return map;
			}
		});
		
		for(Map.Entry<Integer, Person> entry : map.entrySet()) {
			System.out.println(entry.getKey() + " " + entry.getValue());
			cache.put(new Element(entry.getKey(), entry.getValue()));
		}
		
		Attribute<Integer> age = cache.getSearchAttribute("age");
		Attribute<Gender> gender = cache.getSearchAttribute("gender");
		Attribute<Double> weight = cache.getSearchAttribute("weight");
		Attribute<String> initials = cache.getSearchAttribute("initials");
		
		Query query = cache.createQuery();
		query.includeValues();
		query.addCriteria(new Or(gender.eq(Gender.FEMALE), initials.eq("D")));
		Results results = query.execute();
		QueryUtils.printQueryResults(results);
		
		
	}

}
