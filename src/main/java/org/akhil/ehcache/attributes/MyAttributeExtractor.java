package org.akhil.ehcache.attributes;

import java.time.LocalDate;

import net.sf.ehcache.Element;
import net.sf.ehcache.search.attribute.AttributeExtractor;
import net.sf.ehcache.search.attribute.AttributeExtractorException;

import org.akhil.ehcache.bean.Alcohol;
import org.akhil.ehcache.bean.Person;
import org.akhil.ehcache.utils.DateUtils;

public class MyAttributeExtractor implements AttributeExtractor{

	@Override
	public Object attributeFor(Element element, String attributeName)
			throws AttributeExtractorException {
		Object value = element.getObjectValue();
		if(value instanceof Person) {
			Person person = (Person) value;
			LocalDate birthDate = person.getDateOfBirth();
			int age =  DateUtils.calculateAge(birthDate);
			System.out.println("Retuned age : " + age + " for Person : " + person.getName() + " and DOB : " + person.getDateOfBirth());
			return age;
		} else if (value instanceof Alcohol) {
			Alcohol alcohol = (Alcohol) value;
			System.out.println("Returned age : " + alcohol.getAge() + " for Alcohol : " + alcohol);
			return alcohol.getAge();
		}
		// TODO Auto-generated method stub
		return null;
	}

}
