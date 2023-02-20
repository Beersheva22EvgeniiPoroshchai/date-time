package telran.time.application;

import java.time.*;
import java.time.format.TextStyle;
import java.util.Arrays;
import java.util.Locale;
import java.util.stream.IntStream;

public class PrintCalendar {
	
	private static final String LANGUAGE_TAG = "en";
	private static final int YEAR_OFFSET = 10;
	private static final int WIDTH_FIELD = 4;
	private static Locale locale = Locale.forLanguageTag(LANGUAGE_TAG);

	public static void main(String[] args) {
		
		try {
			int monthYear[] = getMonthYear(args);
			printCalendar(monthYear[0], monthYear[1], monthYear[2]);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	
	}

		private static void printCalendar(int month, int year, int setFirstDay) {
		printTittle(month, year);
		printWeekDays(setFirstDay);
		printDays(month, year, setFirstDay);
		
		}

	
	private static void printDays(int month, int year, int setFirstDay) {
		System.out.println();
		int weekDayNumber = getFirstDay(month, year, setFirstDay);
		int offset = getOffset(weekDayNumber);
		int nDays = YearMonth.of(year, month).lengthOfMonth();
		System.out.printf("%s", " ".repeat(offset));
		for (int date = 1; date <= nDays; date++) {
			System.out.printf("%4d", date);
			if (++weekDayNumber > 7) {
				System.out.println();
				weekDayNumber = 1;
			}
			
		}
	}

	
	private static int getFirstDay(int month, int year, int setFirstDay) {
			int res = LocalDate.of(year, month, 1).getDayOfWeek().getValue() + (DayOfWeek.values().length) - setFirstDay;
			return res > DayOfWeek.values().length ? res -= DayOfWeek.values().length : res;
		
	}

	private static int getOffset(int weekDayNumber) {
		return (weekDayNumber - 1) * WIDTH_FIELD;
	}

	
	private static void printWeekDays(int setFirstDay) {
		System.out.print("  ");
		DayOfWeek[] days = DayOfWeek.values();
		int j = 0;
		for (int i = setFirstDay; j < days.length; j++) {
		System.out.printf("%s ", days[i].getDisplayName(TextStyle.SHORT, locale));
		i++;
		while (i > days.length -1) i = 0;
		}
			
	}

	

	private static void printTittle(int month, int year) {
		System.out.printf("%s%d, %s\n", " ".repeat(YEAR_OFFSET), year, Month.of(month).
				getDisplayName(TextStyle.FULL, locale));
		
	}

	private static int[] getMonthYear(String[] args) throws Exception {
		return args.length == 0 ? getCurrentMonthYear() : getMonthYearArgs(args); 
	}

	private static int[] getCurrentMonthYear() {
		LocalDate current = LocalDate.now();
		return new int[] {current.getMonth().getValue(), current.getYear(), DayOfWeek.of(1).getValue()-1};
	}

	private static int[] getMonthYearArgs(String[] args) throws Exception {
		return new int[] {getMonthArgs(args), getYearArgs(args), getDay(args)};
	}

	private static int getDay(String[] args) throws Exception {
		int res = 0;
		if (args.length > 2) {
			try {
				res = DayOfWeek.valueOf(args[2].toUpperCase()).getValue()-1;
				
			} catch (Exception e) {
				throw new Exception ("Call first day by full week name!");
			}
		}
		return res;
	}

	private static int getYearArgs(String[] args) throws Exception  {
		int res = LocalDate.now().getYear();
		if (args.length > 1) {
			try {
				res = Integer.parseInt(args[1]);
				if (res < 0) {
					throw new Exception ("Year must be a positive number");
				}
			} catch (NumberFormatException e) {
				throw new Exception ("Year must be a number");
			}
			}
		return res;
	}

	private static int getMonthArgs(String[] args) throws Exception {
		try {
			int res = Integer.parseInt(args[0]);
			if (res < 1 || res > 12) {
				throw new Exception("Month should be a number in the range from 1 to 12");
			}
			return res;
		} catch (NumberFormatException e) {
			throw new Exception("Month should be a number");
		}
	}


}
