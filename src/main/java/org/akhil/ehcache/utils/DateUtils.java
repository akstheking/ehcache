package org.akhil.ehcache.utils;

import java.time.LocalDate;

public class DateUtils {
	
	public static int calculateAge(LocalDate birthDate) {
		LocalDate now = LocalDate.now();
		
		int birthYear = birthDate.getYear();
		int birthMonth = birthDate.getMonthValue();
		int birthDay = birthDate.getDayOfMonth();
		
		int currentYear = now.getYear();
		int currentMonth = now.getMonthValue();
		int currentDay = now.getDayOfMonth();
		
		int age = currentYear - birthYear;
		
		if(currentMonth < birthMonth) {
			age--;
		} else if (currentMonth == birthMonth && currentDay < birthDay) {
			age--;
		}
		return age;
	}

}
