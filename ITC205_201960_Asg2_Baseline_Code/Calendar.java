import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Calendar {
	
	private static Calendar calendarInstance; // change SeLf to meaningful name 
	private static java.util.Calendar calender; // Change CaLeNdAr to calendar according to camel case notation
	
	
	private Calendar() {
		calender = java.util.Calendar.getInstance(); // Change CaLeNdAr to calendar according to camel case notation
	}
	
	public static Calendar INSTANCE() {
		if (calendarInstance == null) { // change SeLf to meaningful name
			calendarInstance = new Calendar(); // change SeLf to meaningful name
		}
		return calendarInstance; // change SeLf to meaningful name
	}
	
	public void incrementDate(int days) {
		calender.add(java.util.Calendar.DATE, days); // Change CaLeNdAr to calendar according to camel case notation		
	}
	
	public synchronized void setDate(Date date) { // change Set_dATE to setDate
		try {
			calender.setTime(date); // Change CaLeNdAr to calendar according to camel case notation
	        calender.set(java.util.Calendar.HOUR_OF_DAY, 0);   // Change CaLeNdAr to calendar according to camel case notation
	        calender.set(java.util.Calendar.MINUTE, 0);  // Change CaLeNdAr to calendar according to camel case notation 
	        calender.set(java.util.Calendar.SECOND, 0);  // Change CaLeNdAr to calendar according to camel case notation 
	        calender.set(java.util.Calendar.MILLISECOND, 0); // Change CaLeNdAr to calendar according to camel case notation
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}	
	}
	public synchronized Date Date() {
		try {
	        calender.set(java.util.Calendar.HOUR_OF_DAY, 0);  // Change CaLeNdAr to calendar according to camel case notation 
	        calender.set(java.util.Calendar.MINUTE, 0);   // Change CaLeNdAr to calendar according to camel case notation
	        calender.set(java.util.Calendar.SECOND, 0);  // Change CaLeNdAr to calendar according to camel case notation 
	        calender.set(java.util.Calendar.MILLISECOND, 0); // Change CaLeNdAr to calendar according to camel case notation
			return calender.getTime(); // Change CaLeNdAr to calendar according to camel case notation
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}	
	}

	public synchronized Date getDueDate(int loanPeriod) { // change Due_Date to getDueDate
		Date toDate = Date(); // change NoW to toDate
		calender.add(java.util.Calendar.DATE, loanPeriod); // Change CaLeNdAr to calendar according to camel case notation
		Date dueDate = calender.getTime(); // Change CaLeNdAr to calendar according to camel case notation, change DuEdAtE to dueDate
		calender.setTime(toDate); // Change CaLeNdAr to calendar according to camel case notation, change NoW to toDate
		return dueDate;// change DuEdAtE to dueDate
	}
	
	public synchronized long getDifferenceDatesFromToday(Date targetDate) { // change Get_Days_Difference method name to getDifferenceDatesFromToday
		
		long differenceMilliSeconds = Date().getTime() - targetDate.getTime(); // change Diff_Millis to differenceMilliSeconds
	    long differenceDates = TimeUnit.DAYS.convert(differenceMilliSeconds, TimeUnit.MILLISECONDS);// change Diff_Millis to differenceMilliSeconds, change Diff_Days to differenceDates
	    return differenceDates; // change Diff_Days to differenceDates
	}

}
