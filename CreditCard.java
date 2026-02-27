/*
Name: Ann C.
Course: C155
Assignment: Credit Card Validations
Date: Feb.25.2026
Purpose: Check if a credit card number is valid using Luhn's algorithm.
         The program repeats until the user enters "end".
*/

import java.util.Scanner;

public class CreditCard {
public static void main(String[] args) {
      Scanner input = new Scanner(System.in);
      String userInput;

      while (true) {
          System.out.print("Enter a credit card number as a long integer (or type 'end' to quit): ");
          userInput = input.nextLine();

          if (userInput.equalsIgnoreCase("end")) {
              System.out.println("Bye Bye!~");
              break;
          }

          try {
              long cardNumber = Long.parseLong(userInput);

              if (isValid(cardNumber)) {
                  System.out.println(cardNumber + " is valid");
              } else {
                  System.out.println(cardNumber + " is invalid");
              }
          } catch (NumberFormatException e) {
              System.out.println(userInput + " is invalid input");
          }
      }

      input.close();
  }

  public static boolean isValid(long number) {
      return (getSize(number) >= 13 && getSize(number) <= 16) &&
              (prefixMatched(number, 4) || prefixMatched(number, 5) || 
              prefixMatched(number, 37) || prefixMatched(number, 6)) &&
              ((sumOfDoubleEvenPlace(number) + sumOfOddPlace(number)) % 10 == 0);
  }

  public static int sumOfDoubleEvenPlace(long number) {
      int sum = 0;
      String numStr = Long.toString(number);
      for (int i = numStr.length() - 2; i >= 0; i -= 2) {
          int doubled = 2 * Character.getNumericValue(numStr.charAt(i));
          sum += getDigit(doubled);
      }
      return sum;
  }

  public static int getDigit(int number) {
      if (number < 10)
          return number;
      else
          return number / 10 + number % 10;
  }

  public static int sumOfOddPlace(long number) {
      int sum = 0;
      String numStr = Long.toString(number);
      for (int i = numStr.length() - 1; i >= 0; i -= 2) {
          sum += Character.getNumericValue(numStr.charAt(i));
      }
      return sum;
  }

  public static boolean prefixMatched(long number, int d) {
      return getPrefix(number, getSize(d)) == d;
  }

  public static int getSize(long d) {
      return Long.toString(d).length();
  }

  public static long getPrefix(long number, int k) {
      String numStr = Long.toString(number);
      if (numStr.length() < k)
          return number;
      return Long.parseLong(numStr.substring(0, k));
  }
}
