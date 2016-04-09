package org.akhil.ehcache.init;

import java.util.Arrays;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class Test2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CacheManager cm = CacheManager.newInstance("src/main/resources/ehcache2.xml");
		String[] cacheNames = cm.getCacheNames();
		System.out.println(Arrays.toString(cacheNames));
		
//		cm.addCache("cache1");
		
		Cache c1 = cm.getCache("cache2");
		
		c1.put(new Element("1", "One"));
		c1.put(new Element("2", "Tow"));
		c1.put(new Element("3", "Three"));
		
		System.out.println(c1.get("1").getObjectValue());
		System.out.println(c1.isKeyInCache("4"));
		cm.shutdown();

	}

}
