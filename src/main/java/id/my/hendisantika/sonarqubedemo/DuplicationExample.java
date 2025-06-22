package id.my.hendisantika.sonarqubedemo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA. Project : spring-boot-sonarqube-demo User: hendisantika Link:
 * s.id/hendisantika Email: hendisantika@yahoo.co.id Telegram : @hendisantika34 Date: 16/03/25 Time:
 * 09.47 To change this template use File | Settings | File Templates.
 */
public class DuplicationExample {
    // Code smell 1: Unused fields
    private String unusedField1;
    private int unusedField2;
    private final List<String> unusedList = new ArrayList<>();

    // Code smell 3: Overly complex method
    public int complexMethod(int a, int b, int c, int d, int e) {
        int result = 0;

        if (a > 0) {
            if (b > 0) {
                if (c > 0) {
                    if (d > 0) {
                        if (e > 0) {
                            result = a + b + c + d + e;
                        } else {
                            result = a + b + c + d;
                        }
                    } else {
                        if (e > 0) {
                            result = a + b + c + e;
                        } else {
                            result = a + b + c;
                        }
                    }
                } else {
                    if (d > 0) {
                        if (e > 0) {
                            result = a + b + d + e;
                        } else {
                            result = a + b + d;
                        }
                    } else {
                        if (e > 0) {
                            result = a + b + e;
                        } else {
                            result = a + b;
                        }
                    }
                }
            } else {
                // Similar nested if-else structure continues...
                result = a;
            }
        }

        return result;
    }

    // Code smell 4: Long method
    public void longMethod() {
        int sum = 0;

        // Step 1: Initialize variables
        int var1 = 10;
        int var2 = 20;
        int var3 = 30;

        // Step 2: Perform calculation
        sum = var1 + var2 + var3;
        System.out.println("Sum: " + sum);

        // Step 3: More calculations
        double average = sum / 3.0;
        System.out.println("Average: " + average);

        // Step 4: Even more calculations
        double standardDeviation =
                Math.sqrt(
                        (Math.pow(var1 - average, 2)
                                + Math.pow(var2 - average, 2)
                                + Math.pow(var3 - average, 2))
                                / 3);
        System.out.println("Standard Deviation: " + standardDeviation);

        // Step 5: Print results in different formats
        System.out.println("Results in different formats:");
        System.out.println("Sum (decimal): " + sum);
        System.out.println("Sum (hex): " + Integer.toHexString(sum));
        System.out.println("Sum (binary): " + Integer.toBinaryString(sum));

        // Step 6: Calculate more statistics
        int min = Math.min(Math.min(var1, var2), var3);
        int max = Math.max(Math.max(var1, var2), var3);
        System.out.println("Min: " + min);
        System.out.println("Max: " + max);
        System.out.println("Range: " + (max - min));

        // Step 7: Final calculations
        double geometricMean = Math.cbrt(var1 * var2 * var3);
        System.out.println("Geometric Mean: " + geometricMean);

        // This method is too long and should be split into smaller methods
    }

    // Code smell 5: Duplicated code blocks
    public void processData1(List<Integer> data) {
        // Duplicated processing logic
        int sum = 0;
        for (Integer value : data) {
            sum += value;
        }
        double average = (double) sum / data.size();

        System.out.println("Processing data set 1:");
        System.out.println("Sum: " + sum);
        System.out.println("Average: " + average);
    }

    public void processData2(List<Integer> data) {
        // Duplicated processing logic
        int sum = 0;
        for (Integer value : data) {
            sum += value;
        }
        double average = (double) sum / data.size();

        System.out.println("Processing data set 2:");
        System.out.println("Sum: " + sum);
        System.out.println("Average: " + average);
    }

    // Code smell 6: Too many parameters
    public void methodWithTooManyParameters(
            String param1,
            String param2,
            String param3,
            String param4,
            int param5,
            int param6,
            int param7,
            int param8,
            double param9,
            double param10,
            boolean param11,
            boolean param12) {

        // Method body
        System.out.println("Method with too many parameters");
    }

    // Code smell 7: Switch statement without default
    public String getDayOfWeek(int day) {
        switch (day) {
            case 1:
                return "Monday";
            case 2:
                return "Tuesday";
            case 3:
                return "Wednesday";
            case 4:
                return "Thursday";
            case 5:
                return "Friday";
            case 6:
                return "Saturday";
            case 7:
                return "Sunday";
            // Missing default case
        }
        return null; // This will be reached if day is not 1-7
    }

    // Code smell 8: Magic numbers
    public double calculateCircleArea(double radius) {
        return 3.14159 * radius * radius; // Magic number 3.14159 instead of Math.PI
    }

    // Code smell 2: Duplicated classes
    public static class CopyPaste1 {
        private final String name;
        private final String description;

        public CopyPaste1(String name, String description) {
            this.name = name;
            this.description = description;
        }

        // Getter methods that are identical in both classes
        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }

        // Duplicated method with complex logic
        public double calculateSomething(double input) {
            double result = 0;
            for (int i = 0; i < 10; i++) {
                if (i % 2 == 0) {
                    result += Math.pow(input, i);
                } else {
                    result -= Math.pow(input, i) / 2;
                }
            }
            return result;
        }
    }

    public static class CopyPaste2 {
        private final String name;
        private final String description;

        public CopyPaste2(String name, String description) {
            this.name = name;
            this.description = description;
        }

        // Getter methods that are identical in both classes
        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }

        // Duplicated method with complex logic
        public double calculateSomething(double input) {
            double result = 0;
            for (int i = 0; i < 10; i++) {
                if (i % 2 == 0) {
                    result += Math.pow(input, i);
                } else {
                    result -= Math.pow(input, i) / 2;
                }
            }
            return result;
        }
    }
}
