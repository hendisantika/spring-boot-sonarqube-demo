package id.my.hendisantika.sonarqubedemo;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-sonarqube-demo
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 16/03/25
 * Time: 09.48
 * To change this template use File | Settings | File Templates.
 */
public class HotSpotExample {
    public boolean isValid(String pin) {
        return pin.matches(readRegex());
    }

}
