import java.util.*;

public class SumCalculator {
    
    public static int parseStringToInteger(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format: " + str);
            return 0; // Returning 0 for invalid inputs
        }
    }
    
    public static int calculateSum(List<Integer> numbers) {
        int sum = 0;
        for (int num : numbers) { // Unboxing happens here
            sum += num;
        }
        return sum;
    }
    
    public static void main(String[] args) {
        List<Integer> numbers1 = new ArrayList<>();
        numbers1.add(10); // Autoboxing
        numbers1.add(20);
        numbers1.add(30);
        numbers1.add(parseStringToInteger("40"));
        numbers1.add(parseStringToInteger("50"));
        System.out.println("The sum of the list is: " + calculateSum(numbers1));
        
        List<Integer> numbers2 = new ArrayList<>();
        numbers2.add(parseStringToInteger("100"));
        numbers2.add(parseStringToInteger("200"));
        numbers2.add(parseStringToInteger("300"));
        System.out.println("The sum of the list is: " + calculateSum(numbers2));
        
        List<Integer> numbers3 = new ArrayList<>();
        numbers3.add(parseStringToInteger("50"));
        numbers3.add(parseStringToInteger("invalid")); // This should print an error message
        numbers3.add(parseStringToInteger("70"));
        System.out.println("The sum of the list is: " + calculateSum(numbers3));
    }
}
