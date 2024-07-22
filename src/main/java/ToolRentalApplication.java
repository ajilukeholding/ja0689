package src.main.java;

import src.main.java.exception.InvalidRentalException;
import src.main.java.model.Tool;
import src.main.java.service.RentalAgreement;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

public class ToolRentalApplication {

  private static final Map<String, Tool> tools = new HashMap<>();

  static {
    tools.put("LADW", new Tool("LADW", "Ladder", "Werner", 1.99, 1.99, 0.00));
    tools.put("CHNS", new Tool("CHNS", "Chainsaw", "Stihl", 1.49, 0.00, 1.49));
    tools.put("JAKD", new Tool("JAKD", "Jackhammer", "DeWalt", 2.99, 2.99, 2.99));
    tools.put("JAKR", new Tool("JAKR", "Jackhammer", "Ridgid", 2.99, 2.99, 2.99));
  }

  public static RentalAgreement checkoutTool(String toolCode, int rentalDays, int discountPercent, LocalDate checkoutDate) throws InvalidRentalException {
    if (rentalDays < 1) {
      throw new InvalidRentalException("Rental days must be 1 or more.");
    }
    if (discountPercent < 0 || discountPercent > 100) {
      throw new InvalidRentalException("Discount percent must be between 0 and 100.");
    }

    Tool tool = tools.get(toolCode);
    if (tool == null) {
      throw new InvalidRentalException("Invalid tool code: " + toolCode);
    }

    LocalDate dueDate = checkoutDate.plusDays(rentalDays - 1);
    double dailyCharge = tool.getWeekdayCharge();
    int chargeDays = 0;
    for (LocalDate date = checkoutDate.plusDays(1); date.isBefore(dueDate.plusDays(1)); date = date.plusDays(1)) {
      if (isChargeableDay(date, tool)) {
        chargeDays++;
      }
    }

    double preDiscountCharge = chargeDays * dailyCharge;
    double discountAmount = preDiscountCharge * discountPercent / 100.0;
    double finalCharge = Math.ceil((preDiscountCharge - discountAmount) * 100) / 100;

    return new RentalAgreement(toolCode, tool.getToolType(), tool.getBrand(), rentalDays, checkoutDate, dueDate, dailyCharge, chargeDays, preDiscountCharge, discountPercent, finalCharge);
  }

  private static boolean isChargeableDay(LocalDate date, Tool tool) {
    if (isWeekend(date) && tool.getWeekendCharge() > 0) {
      return true;
    }
    if (isHoliday(date) && tool.getHolidayCharge() > 0) {
      return true;
    }
    return !isWeekend(date) && !isHoliday(date);
  }

  private static boolean isWeekend(LocalDate date) {
    return date.getDayOfWeek().getValue() == 6 || date.getDayOfWeek().getValue() == 7;
  }

  private static boolean isHoliday(LocalDate date) {
    LocalDate independenceDay = LocalDate.of(date.getYear(), Month.JULY, 4);
    if (independenceDay.getDayOfWeek().getValue() == 6) {
      independenceDay = independenceDay.minusDays(1);
    } else if (independenceDay.getDayOfWeek().getValue() == 7) {
      independenceDay = independenceDay.plusDays(1);
    }
    LocalDate laborDay = LocalDate.of(date.getYear(), Month.SEPTEMBER, 1)
            .with(java.time.temporal.TemporalAdjusters.firstInMonth(java.time.DayOfWeek.MONDAY));
    return date.equals(independenceDay) || date.equals(laborDay);
  }



  List<String> strings = Arrays.asList("apple", "banana", "apricot", "cherry");
  String substring = "ap";
  List<String> result = strings.stream()
          .filter(s -> s.contains(substring))
          .collect(Collectors.toList());


  long count = strings.stream()
          .filter(s -> s.contains(substring))
          .count();

  Optional<String> firstMatch = strings.stream()
          .filter(s -> s.contains(substring))
          .findFirst();

  Optional<String> anyStr = strings.stream()
          .filter(s -> s.contains(substring))
          .findAny();


}
