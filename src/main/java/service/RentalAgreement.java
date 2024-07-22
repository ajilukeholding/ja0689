package src.main.java.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RentalAgreement {
  private final String toolCode;
  private final String toolType;
  private final String brand;
  private final int rentalDays;
  private final LocalDate checkoutDate;
  private final LocalDate dueDate;
  private final double dailyCharge;
  private final int chargeDays;
  private final double preDiscountCharge;
  private final int discountPercent;
  private final double discountAmount;
  private final double finalCharge;

  public RentalAgreement(String toolCode, String toolType, String brand, int rentalDays, LocalDate checkoutDate, LocalDate dueDate, double dailyCharge, int chargeDays, double preDiscountCharge, int discountPercent, double finalCharge) {
    this.toolCode = toolCode;
    this.toolType = toolType;
    this.brand = brand;
    this.rentalDays = rentalDays;
    this.checkoutDate = checkoutDate;
    this.dueDate = dueDate;
    this.dailyCharge = dailyCharge;
    this.chargeDays = chargeDays;
    this.preDiscountCharge = preDiscountCharge;
    this.discountPercent = discountPercent;
    this.discountAmount = preDiscountCharge * discountPercent / 100.0;
    this.finalCharge = finalCharge;
  }

  public void printAgreement() {
    System.out.println("Tool code: " + toolCode);
    System.out.println("Tool type: " + toolType);
    System.out.println("Brand: " + brand);
    System.out.println("Rental days: " + rentalDays);
    System.out.println("Checkout date: " + checkoutDate.format(DateTimeFormatter.ofPattern("MM/dd/yy")));
    System.out.println("Due date: " + dueDate.format(DateTimeFormatter.ofPattern("MM/dd/yy")));
    System.out.println("Daily rental charge: $" + String.format("%.2f", dailyCharge));
    System.out.println("Charge days: " + chargeDays);
    System.out.println("Pre-discount charge: $" + String.format("%.2f", preDiscountCharge));
    System.out.println("Discount percent: " + discountPercent + "%");
    System.out.println("Discount amount: $" + String.format("%.2f", discountAmount));
    System.out.println("Final charge: $" + String.format("%.2f", finalCharge));
  }

  public String getToolCode(){
    return toolCode;
  }
  public String getToolType(){
    return toolType;
  }
  public String getBrand(){
    return brand;
  }
  public int getRentalDays(){
    return rentalDays;
  }
  public LocalDate getCheckoutDate() {
    return checkoutDate;
  }
  public LocalDate getDueDate(){
    return dueDate;
  }
  public double getDailyCharge() {
    return dailyCharge;
  }

  public int getChargeDays() {
    return chargeDays;
  }

  public double getPreDiscountCharge() {
    return preDiscountCharge;
  }
  public int getDiscountPercent() {
    return discountPercent;
  }
  public double getDiscountAmount() {
    return discountAmount;
  }

  public double getFinalCharge() {
    return finalCharge;
  }
}
