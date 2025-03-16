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
public class DuplicationExample {
    public static class CopyPaste1 {
        private final String name;
        private final String description;

        public CopyPaste1(String name, String description) {
            this.name = name;
            this.description = description;
        }
    }

    public static class CopyPaste2 {
        private final String name;
        private final String description;

        public CopyPaste2(String name, String description) {
            this.name = name;
            this.description = description;
        }
    }
}
