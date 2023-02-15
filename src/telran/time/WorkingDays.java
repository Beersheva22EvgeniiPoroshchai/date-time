package telran.time;

import java.time.DayOfWeek;
import java.time.temporal.*;
import java.util.Arrays;

public class WorkingDays implements TemporalAdjuster {
	
	DayOfWeek[] dayOffs;
	int amountDays;
	
	
		@Override
	public Temporal adjustInto(Temporal temporal) {
			DayOfWeek newDay = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
			short usuallDays = (short) Arrays.stream(dayOffs).filter(day -> newDay.getValue() < day.getValue()).count();
			short justDays = (short) ((amountDays / 7) * dayOffs.length + usuallDays + amountDays);
			return temporal.plus(justDays, ChronoUnit.DAYS);

			
	}
	
	public WorkingDays(DayOfWeek[] dayOffs, int amountDays) {
		
	this.dayOffs = dayOffs;
	this.amountDays = amountDays;
	
		}
	

}
