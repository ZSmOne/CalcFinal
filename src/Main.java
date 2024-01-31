import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        System.out.println(calc(input));
        scanner.close();
    }
    /**
     * Метод, выполняющий арифметические операции.
     */
    public static String calc(String input) {
        try {
            String[] expression = input.split("\\s+");

            if (expression.length != 3) {
                throw new Exception("Неверный формат данных");
            }

            String numberOne = expression[0].trim();
            String operator = expression[1].trim();
            String numberTwo = expression[2].trim();

            boolean isRoman = isValidRomanNumeral(numberOne) && isValidRomanNumeral(numberTwo);

            int num1 = isRoman ? convertRomanToInteger(numberOne) : Integer.parseInt(numberOne);
            int num2 = isRoman ? convertRomanToInteger(numberTwo) : Integer.parseInt(numberTwo);
            if (num1 > 10 || num2 > 10){
                throw new Exception("Неверный формат данных");
            }
            int result;
            switch (operator) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    if (num2 == 0) {
                        throw new ArithmeticException("Деление на 0");
                    }
                    result = num1 / num2;
                    break;
                default:
                    throw new Exception("Недопустимый арифметический оператор");
            }
            return isRoman ? convertToRoman(result) : String.valueOf(result);
        } catch (Exception e) {
            return "throws Exception";
        }
    }

    /**
     * Метод, выполняющий перевод результата арифметической операции в римское число.
     */
    public static String convertToRoman(int arabicNumber) {
       if (arabicNumber < 1 || arabicNumber > 100) {
            throw new IllegalArgumentException("Число должно быть в пределах от 1 до 100 включительно.");
        }

        int[] arabicValues = {100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romanNumerals = {"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        String romanNumber = "";

        for (int i = 0; arabicNumber > 0; i++) {
            while (arabicNumber - arabicValues[i] >= 0) {
                romanNumber += romanNumerals[i];
                arabicNumber -= arabicValues[i];
            }
        }
        return romanNumber;
    }
    /**
     * Метод, проверяющий является ли число римским.
     */
    private static boolean isValidRomanNumeral(String input) {
        return input.matches("^(X|IX|IV|V?I{0,3})$");
    }

    /**
     * Метод, выполняющий перевод введеных римских чисел в арабские.
     */

    private static int convertRomanToInteger(String roman) {
        Map<Character, Integer> romanNumerals = new HashMap<>();
        romanNumerals.put('I', 1);
        romanNumerals.put('V', 5);
        romanNumerals.put('X', 10);

        int result = 0;
        int prevValue = 0;

        for (int i = roman.length() - 1; i >= 0; i--) {
            int value = romanNumerals.get(roman.charAt(i));

            if (value < prevValue) {
                result -= value;
            } else {
                result += value;
            }
            prevValue = value;
        }
        return result;
    }
}
