package org.akhil.ehcache.attributes;


import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.search.Attribute;
import net.sf.ehcache.search.Query;
import net.sf.ehcache.search.Results;
import net.sf.ehcache.search.expression.Or;

import org.akhil.ehcache.bean.Gender;

public class Test {
	
	public static void main(String[] args) {
		CacheManager cm = CacheManager.newInstance("src/main/resources/ehcache3.xml");
		Cache cache = cm.getCache("cache1");
		
		Attribute<Integer> age = cache.getSearchAttribute("age");
		Attribute<Gender> gender = cache.getSearchAttribute("gender");
		Attribute<Double> weight = cache.getSearchAttribute("weight");
		
		Query query = cache.createQuery();
		query.includeValues();
		query.addCriteria(weight.le(55.0d)).addCriteria(new Or(gender.eq(Gender.FEMALE), age.between(20, 30)));
		Results results = query.execute();
		QueryUtils.printQueryResults(results);
		
	}

}
