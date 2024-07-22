package test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import src.main.java.ToolRentalApplication;
import src.main.java.exception.InvalidRentalException;
import src.main.java.service.RentalAgreement;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ToolRentalApplicationTest {

  @Test
  public void testCheckoutTool_InvalidRentalDays() {
    Executable checkout = () -> ToolRentalApplication.checkoutTool("LADW", 0, 0, LocalDate.now());
    assertThrows(InvalidRentalException.class, checkout);
  }

  @Test
  public void testCheckoutTool_InvalidDiscountPercent() {
    Executable checkout = () -> ToolRentalApplication.checkoutTool("LADW", 1, -1, LocalDate.now());
    assertThrows(InvalidRentalException.class, checkout);
  }

  @Test
  public void testCheckoutTool_InvalidToolCode() {
    Executable checkout = () -> ToolRentalApplication.checkoutTool("INVALID", 1, 0, LocalDate.now());
    assertThrows(InvalidRentalException.class, checkout);
  }

  @Test
  public void testCheckoutTool_Ladder_Weekday() throws Exception {
    RentalAgreement agreement = ToolRentalApplication.checkoutTool("LADW", 3, 10, LocalDate.of(2024, 7, 15));
    agreement.printAgreement();
    assertEquals(3, agreement.getRentalDays());
    assertEquals(LocalDate.of(2024, 7, 15), agreement.getCheckoutDate());
    assertEquals(LocalDate.of(2024, 7, 17), agreement.getDueDate());
    assertEquals(1.99, agreement.getDailyCharge());
    assertEquals(3, agreement.getChargeDays());
    assertEquals(5.97, agreement.getPreDiscountCharge());
    assertEquals(10, agreement.getDiscountPercent());
    assertEquals(0.59, agreement.getDiscountAmount());
    assertEquals(5.38, agreement.getFinalCharge());
  }

  @Test
  public void testCheckoutTool_Jackhammer_Weekend() throws Exception {
    RentalAgreement agreement = ToolRentalApplication.checkoutTool("JAKR", 2, 0, LocalDate.of(2024, 7, 13)); // Saturday checkout
    agreement.printAgreement();
    assertEquals(2, agreement.getRentalDays());
    assertEquals(LocalDate.of(2024, 7, 13), agreement.getCheckoutDate());
    assertEquals(LocalDate.of(2024, 7, 14), agreement.getDueDate());
    assertEquals(2.99, agreement.getDailyCharge());
    assertEquals(2, agreement.getChargeDays());
    assertEquals(5.98, agreement.getPreDiscountCharge());
    assertEquals(0, agreement.getDiscountPercent());
    assertEquals(0.00, agreement.getDiscountAmount());
    assertEquals(5.98, agreement.getFinalCharge());
  }

  @Test
  public void testCheckoutTool_Chainsaw_Holiday() throws Exception {
    LocalDate holiday = LocalDate.of(2024, 7, 4); // Check if 4th of July is on a weekday
    if (holiday.getDayOfWeek().getValue() < 6) {
      holiday = holiday.plusDays(1); // Adjust for weekend observance
    }
    RentalAgreement agreement = ToolRentalApplication.checkoutTool("CHNS", 4, 0, holiday);
    agreement.printAgreement();
    assertEquals(4, agreement.getRentalDays());
    assertEquals(holiday, agreement.getCheckoutDate());
    assertEquals(holiday.plusDays(3), agreement.getDueDate());
    assertEquals(1.49, agreement.getDailyCharge());
    assertEquals(3, agreement.getChargeDays()); // Excludes checkout date (holiday)
    assertEquals(4.47, agreement.getPreDiscountCharge());
    assertEquals(0, agreement.getDiscountPercent());
    assertEquals(0.00, agreement.getDiscountAmount());
    assertEquals(4.47, agreement.getFinalCharge());
  }
}
