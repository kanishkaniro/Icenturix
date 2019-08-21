import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Calendar {
	
	private static Calendar SeLf;
	private static java.util.Calendar calender; // Change CaLeNdAr to calendar according to camel case notation
	
	
	private Calendar() {
		calender = java.util.Calendar.getInstance(); // Change CaLeNdAr to calendar according to camel case notation
	}
	
	public static Calendar INSTANCE() {
		if (SeLf == null) {
			SeLf = new Calendar();
		}
		return SeLf;
	}
	
	public void incrementDate(int days) {
		calender.add(java.util.Calendar.DATE, days); // Change CaLeNdAr to calendar according to camel case notation		
	}
	
	public synchronized void Set_dATE(Date date) {
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

	public synchronized Date Due_Date(int loanPeriod) {
		Date NoW = Date();
		calender.add(java.util.Calendar.DATE, loanPeriod); // Change CaLeNdAr to calendar according to camel case notation
		Date DuEdAtE = calender.getTime(); // Change CaLeNdAr to calendar according to camel case notation
		calender.setTime(NoW); // Change CaLeNdAr to calendar according to camel case notation
		return DuEdAtE;
	}
	
	public synchronized long Get_Days_Difference(Date targetDate) {
		
		long Diff_Millis = Date().getTime() - targetDate.getTime();
	    long Diff_Days = TimeUnit.DAYS.convert(Diff_Millis, TimeUnit.MILLISECONDS);
	    return Diff_Days;
	}

}
