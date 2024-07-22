package src.main.java.exception;

public class InvalidRentalException extends RuntimeException {
  public InvalidRentalException(String message) {
    super(message);
  }
}