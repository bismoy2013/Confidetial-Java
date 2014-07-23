package in.fissionlabs.kpinsights.service;

import java.util.Calendar;
import java.util.Date;
/**
 * Service class used to generate expiry date
 * @author bismoy
 *
 */
public class ExpiryDateGenerator {

	/**
	 * Generates a Date
	 * @param field Either Calendar.DATE or Calendar.HOUR
	 * @param interval Interval in Days if Calendar.DATE is used as first parameter, or in hours if Calendar.HOUR is used
	 * @returns Date object for expired one
	 */
	public static Date setExpiryDate(int field, int interval){
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(field, interval);
		
		return calendar.getTime();
		
	}
}
