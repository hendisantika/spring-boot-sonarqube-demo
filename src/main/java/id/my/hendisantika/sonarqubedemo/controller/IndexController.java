package id.my.hendisantika.sonarqubedemo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by IntelliJ IDEA. Project : spring-boot-sonarqube-demo User: hendisantika Link:
 * s.id/hendisantika Email: hendisantika@yahoo.co.id Telegram : @hendisantika34 Date: 16/03/25 Time:
 * 09.56 To change this template use File | Settings | File Templates.
 */
@RestController
public class IndexController {
    // Issue: Public static logger
    public static final Logger LOGGER = Logger.getLogger(IndexController.class.getName());
    // Issue: Hardcoded credentials
    private static final String API_KEY = "1234567890abcdef";
    // Issue: Unused field
    private String unusedField;

    @GetMapping("/")
    public String index() {
        // Issue: Logging sensitive information
        LOGGER.info("User accessed the index page at " + LocalDateTime.now());
        return "Hello World! " + LocalDateTime.now();
    }

    // Issue: Method returns ResponseEntity<Object> which is too generic
    @GetMapping("/data")
    public ResponseEntity<Object> getData() {
        Map<String, Object> data = new HashMap<>();
        data.put("timestamp", LocalDateTime.now());
        data.put("message", "Data retrieved successfully");

        // Issue: Returning 200 OK status explicitly instead of using ResponseEntity.ok()
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    // Issue: Using @RequestMapping instead of specific HTTP method annotations
    @RequestMapping(value = "/legacy", method = RequestMethod.GET)
    public String legacyEndpoint() {
        return "Legacy endpoint";
    }

    // Issue: Not handling exceptions properly
    @GetMapping("/error-prone")
    public String errorProneEndpoint() {
        try {
            // Some code that might throw an exception
            int result = 10 / 0; // Will throw ArithmeticException
            return "Success";
        } catch (Exception e) {
            // Issue: printStackTrace to console instead of proper logging
            e.printStackTrace();
            return "Error occurred";
        }
    }

    // Issue: Path variable without validation
    @GetMapping("/users/{id}")
    public String getUserById(@PathVariable String id) {
        // Issue: No validation of the id parameter
        return "User with ID: " + id;
    }

    // Issue: Request parameter without validation
    @GetMapping("/search")
    public String search(@RequestParam String query) {
        // Issue: No validation of the query parameter
        return "Search results for: " + query;
    }

    // Issue: Returning void with side effects
    @PostMapping("/process")
    public void processData(@RequestBody String data) {
        // Process the data without returning anything
        LOGGER.info("Processing data: " + data);
        // No return value, which is not RESTful
    }

    // Issue: Hardcoded sleep
    @GetMapping("/slow")
    public String slowEndpoint() {
        try {
            // Hardcoded sleep
            Thread.sleep(5000);
            return "This endpoint is slow";
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return "Interrupted";
        }
    }

    // Issue: Duplicate code
    @GetMapping("/duplicate1")
    public String duplicate1() {
        // Duplicated code block
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            result.append("Item ").append(i).append(", ");
        }
        return result.toString();
    }

    @GetMapping("/duplicate2")
    public String duplicate2() {
        // Duplicated code block
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            result.append("Item ").append(i).append(", ");
        }
        return result.toString();
    }

    // Issue: Overly complex method
    @GetMapping("/complex")
    public String complexMethod(
            @RequestParam(required = false) String param1,
            @RequestParam(required = false) String param2,
            @RequestParam(required = false) String param3) {
        StringBuilder result = new StringBuilder();

        if (param1 != null) {
            if (param1.length() > 5) {
                if (param1.startsWith("a")) {
                    result.append("Param1 starts with 'a' and is longer than 5 characters");
                } else {
                    result.append("Param1 does not start with 'a' but is longer than 5 characters");
                }
            } else {
                if (param1.startsWith("a")) {
                    result.append("Param1 starts with 'a' but is not longer than 5 characters");
                } else {
                    result.append("Param1 does not start with 'a' and is not longer than 5 characters");
                }
            }
        }

        // Similar nested conditions for param2 and param3...

        return result.toString();
    }
}
