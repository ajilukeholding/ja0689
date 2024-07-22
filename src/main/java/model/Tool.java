package src.main.java.model;

public class Tool {
  private final String toolCode;
  private final String toolType;
  private final String brand;
  private final double weekdayCharge;
  private final double weekendCharge;
  private final double holidayCharge;

  public Tool(String toolCode, String toolType, String brand, double weekdayCharge, double weekendCharge, double holidayCharge) {
    this.toolCode = toolCode;
    this.toolType = toolType;
    this.brand = brand;
    this.weekdayCharge = weekdayCharge;
    this.weekendCharge = weekendCharge;
    this.holidayCharge = holidayCharge;
  }

  public String getToolCode() {
    return toolCode;
  }

  public String getToolType() {
    return toolType;
  }

  public String getBrand() {
    return brand;
  }

  public double getWeekdayCharge() {
    return weekdayCharge;
  }

  public double getWeekendCharge() {
    return weekendCharge;
  }

  public double getHolidayCharge() {
    return holidayCharge;
  }
}

