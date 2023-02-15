package telran.time;

import java.time.DayOfWeek;
import java.time.temporal.*;


public class NextFriday13 implements TemporalAdjuster {

	@Override
	public Temporal adjustInto(Temporal temporal) {
		
		temporal = temporal.with(ChronoField.DAY_OF_MONTH, 13);
		while (temporal.get(ChronoField.DAY_OF_WEEK) != DayOfWeek.FRIDAY.ordinal()+1) {
			temporal = temporal.plus(1, ChronoUnit.MONTHS);
			
		}
		return temporal;
		
	}
}
