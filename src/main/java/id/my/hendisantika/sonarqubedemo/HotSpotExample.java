package id.my.hendisantika.sonarqubedemo;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.attribute.PosixFilePermissions;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Base64;
import java.util.Random;

/**
 * Created by IntelliJ IDEA. Project : spring-boot-sonarqube-demo User: hendisantika Link:
 * s.id/hendisantika Email: hendisantika@yahoo.co.id Telegram : @hendisantika34 Date: 16/03/25 Time:
 * 09.48 To change this template use File | Settings | File Templates.
 */
public class HotSpotExample {
  // Hotspot 1: Hardcoded credentials
  private static final String DB_URL = "jdbc:mysql://localhost:3306/mydb";
  private static final String DB_USER = "admin";
  private static final String DB_PASSWORD = "Password123!";

  // Hotspot 2: Hardcoded encryption key
  private static final String ENCRYPTION_KEY = "ThisIsA16ByteKey";
  private static final String ENCRYPTION_IV = "RandomInitVector";

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

  // Hotspot 3: Weak hashing algorithm
  public String hashPassword(String password) throws NoSuchAlgorithmException {
    MessageDigest md = MessageDigest.getInstance("MD5");
    byte[] digest = md.digest(password.getBytes());
    return new BigInteger(1, digest).toString(16);
  }

  // Hotspot 4: Insecure random
  public String generateToken() {
    Random random = new Random(); // Not using SecureRandom
    return String.valueOf(random.nextLong());
  }

  // Hotspot 5: SQL Injection vulnerability
  public void saveUser(String username, String data) {
    try {
      Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
      Statement stmt = conn.createStatement();

      // SQL Injection vulnerability
      String sql = "INSERT INTO users (username, data) VALUES ('" + username + "', '" + data + "')";
      stmt.execute(sql);

      stmt.close();
      conn.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  // Hotspot 6: Weak encryption
  public String encrypt(String data) {
    try {
      Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
      SecretKeySpec keySpec = new SecretKeySpec(ENCRYPTION_KEY.getBytes(), "AES");
      IvParameterSpec ivSpec = new IvParameterSpec(ENCRYPTION_IV.getBytes());

      cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
      byte[] encrypted = cipher.doFinal(data.getBytes());
      return Base64.getEncoder().encodeToString(encrypted);
    } catch (Exception e) {
      return null;
    }
  }

  // Hotspot 7: Insecure file permissions
  public void createTempFile(String content) throws IOException {
    File tempFile = File.createTempFile("sensitive", ".data");
    Files.write(tempFile.toPath(), content.getBytes());
    tempFile.setReadable(true, false); // World-readable
    tempFile.setWritable(true, false); // World-writable
  }

  // Hotspot 8: Predictable random
  public int rollDice() {
    Random random = new Random(123); // Fixed seed
    return random.nextInt(6) + 1;
  }
}
