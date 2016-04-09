package org.akhil.ehcache.attributes;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.search.Query;
import net.sf.ehcache.search.Results;

public class Test2 {
	
	public static void main(String[] args) {
		CacheManager cm = CacheManager.newInstance("src/main/resources/ehcache3.xml");
		Cache cache = cm.getCache("cache1");
		
		Query query =cache.createQuery();
		query.addCriteria(Query.KEY.eq(4));
		query.includeValues();
		Results results = query.execute();
		QueryUtils.printQueryResults(results);
	}

}
