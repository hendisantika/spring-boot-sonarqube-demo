package id.my.hendisantika.sonarqubedemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-sonarqube-demo
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 16/03/25
 * Time: 09.56
 * To change this template use File | Settings | File Templates.
 */
@RestController
public class IndexController {
    @GetMapping("/")
    public String index() {
        return "Hello World! " + LocalDateTime.now();
    }
}
