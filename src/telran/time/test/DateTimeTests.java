package telran.time.test;

import java.time.*;


import static org.junit.jupiter.api.Assertions.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import telran.time.BarMizvaAdjuster;
import telran.time.NextFriday13;
import telran.time.WorkingDays;

class DateTimeTests {

	
	@BeforeEach
	void setUp() throws Exception {
		
	}
	
	@Test
	void lacalDateTest() {
		LocalDate birthDateTG = LocalDate.parse("1814-03-09");
		LocalDate barMizvaTG = birthDateTG.plusYears(13);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMMM, YYYY, dd", Locale.US);
		System.out.println(birthDateTG);
		System.out.println(barMizvaTG.format(dtf));
		ChronoUnit unit = ChronoUnit.MONTHS;
		System.out.printf("Number of %s between %s and %s is %d", 
				unit, birthDateTG, barMizvaTG, unit.between(birthDateTG, barMizvaTG));
		System.out.println();
		
		
	}
	
	
	@Test
	void BarMizvaTest() {
		LocalDate current = LocalDate.now();
		assertEquals(current.plusYears(13), current.with(new BarMizvaAdjuster()));
		System.out.println(current.with(new BarMizvaAdjuster()));
		
		
	}
	
	@Test
	void NextFriday13Test() {
		LocalDate current = LocalDate.now();
		LocalDate nextFriday = LocalDate.of(2023, 10, 13);
		assertEquals(nextFriday, current.with(new NextFriday13()));
		
	
	}
	
	
	@Test
	void WorkingDaystest() { 
		LocalDate current = LocalDate.of(2023,02,22);
		DayOfWeek holidays[] = {DayOfWeek.FRIDAY, DayOfWeek.SATURDAY};
		assertEquals(current.plusDays(14), current.with(new WorkingDays(holidays, 10)));
		LocalDate current1 = LocalDate.of(2024,01,01);
		assertEquals(current1.plusDays(7), current1.with(new WorkingDays(holidays, 5)));
		
		
	}
	
	@Test
	void displayCurrentDateAndTimeCanadaTimeZones() {

		ZoneId.getAvailableZoneIds().stream().filter(c -> c.toLowerCase().contains("canada")).forEach(e -> 
		System.out.printf("\n%20s %-9s", e, ZonedDateTime.now(ZoneId.of(e)).toLocalDateTime()));

		
		
	}
	


}
