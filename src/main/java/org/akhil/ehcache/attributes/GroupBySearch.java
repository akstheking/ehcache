package org.akhil.ehcache.attributes;

import java.util.List;

import net.sf.ehcache.Cache;
import net.sf.ehcache.search.Attribute;
import net.sf.ehcache.search.Result;
import net.sf.ehcache.search.Results;

import org.akhil.ehcache.bean.Gender;

public class GroupBySearch {
	
	public static void main(String... args) {
		
		Cache cache = Loader.CACHE1;
		
		Attribute<Integer> age = cache.getSearchAttribute("age");
		Attribute<Gender> gender = cache.getSearchAttribute("gender");
		Attribute<Double> weight = cache.getSearchAttribute("weight");
		
		Results results = cache.createQuery().includeAggregator(weight.average()).includeAttribute(gender).addGroupBy(gender).execute();
		System.out.println("Results : " + results);
//		QueryUtils.printQueryResults(results);
		for(Result result : results.all()) {
			Gender gendre = result.getAttribute(gender);
			System.out.println("Gender : " + gendre);
			List<Object> aggregators = result.getAggregatorResults();
			for ( Object ag : aggregators) {
				Double avg = (Double) ag;
				System.out.println(" :: Avg : " + avg);
			}
		}
		
		
	}

}
