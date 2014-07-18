package in.fissionlabs.kpinsights.service;

import java.util.Calendar;
import java.util.Date;

public class ExpiryDateGenerator {

	public static Date setExpiryDate(int field, int interval){
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(field, interval);
		
		return calendar.getTime();
		
	}
}
