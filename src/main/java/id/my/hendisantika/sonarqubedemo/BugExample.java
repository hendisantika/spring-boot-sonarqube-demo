package id.my.hendisantika.sonarqubedemo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-sonarqube-demo
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 16/03/25
 * Time: 09.47
 * To change this template use File | Settings | File Templates.
 */
public class BugExample {
    private String name;

    // Bug 1: Infinite loop
    void run() throws InterruptedException {
        while (true) {
            System.out.println("Hello Bug!");
            Thread.sleep(5000);
        }
    }

    // Bug 2: Null pointer exception
    public void processName() {
        // name is not initialized, will cause NullPointerException
        System.out.println(name.toLowerCase());
    }

    // Bug 3: Resource leak
    public String readFile(String fileName) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(fileName));
            return reader.readLine();
        } catch (IOException e) {
            return null;
        }
        // Missing finally block to close the reader
    }

    // Bug 4: Incorrect equals/hashCode implementation
    @Override
    public boolean equals(Object obj) {
        return obj instanceof BugExample;
    }
    // Missing hashCode override

    // Bug 5: Boxed primitive for identity comparison
    public boolean compareIntegers(Integer a, Integer b) {
        return a == b; // Using == instead of equals()
    }

    // Bug 6: Unused variable and dead code
    public void unusedVariables() {
        int unused = 42;
        if (false) {
            System.out.println("This will never execute");
        }
    }

    // Bug 7: Empty catch block
    public void emptyCatch() {
        try {
            int result = 10 / 0;
        } catch (ArithmeticException e) {
            // Empty catch block
        }
    }

    // Bug 8: Collection not updated in loop
    public void modifyListInLoop() {
        List<String> items = new ArrayList<>();
        items.add("item1");
        items.add("item2");

        for (String item : items) {
            if (item.equals("item1")) {
                items.add("item3"); // Concurrent modification
            }
        }
    }
}
