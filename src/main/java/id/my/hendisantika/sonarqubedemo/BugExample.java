package id.my.hendisantika.sonarqubedemo;

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
    void run() throws InterruptedException {
        while (true) {
            System.out.println("Hello Bug!");
            Thread.sleep(5000);
        }
    }
}
