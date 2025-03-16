package id.my.hendisantika.sonarqubedemo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.PosixFilePermissions;

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

    public void setPermissions(String filename) throws IOException {
        File f = new File(filename);
        if (!isValid(filename)) {
            throw new IllegalArgumentException();
        }
        Files.setPosixFilePermissions(f.toPath(), PosixFilePermissions.fromString("rwxrwxr--"));
    }

    String readRegex() {
        try (var file = new FileInputStream("/tmp/regex.txt")) {
            return new String(file.readAllBytes());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
