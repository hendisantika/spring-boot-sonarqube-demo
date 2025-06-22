package id.my.hendisantika.sonarqubedemo.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class IndexControllerTest {

  @InjectMocks
  private IndexController indexController;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testIndex() {
    String result = indexController.index();
    assertNotNull(result);
    assertTrue(result.startsWith("Hello World!"));
  }

  @Test
  void testGetData() {
    ResponseEntity<Object> response = indexController.getData();

    assertNotNull(response);
    assertEquals(HttpStatus.OK, response.getStatusCode());

    @SuppressWarnings("unchecked")
    Map<String, Object> responseBody = (Map<String, Object>) response.getBody();
    assertNotNull(responseBody);
    assertTrue(responseBody.containsKey("timestamp"));
    assertTrue(responseBody.containsKey("message"));
    assertEquals("Data retrieved successfully", responseBody.get("message"));
  }

  @Test
  void testLegacyEndpoint() {
    String result = indexController.legacyEndpoint();
    assertEquals("Legacy endpoint", result);
  }

  @Test
  void testErrorProneEndpoint() {
    String result = indexController.errorProneEndpoint();
    assertEquals("Error occurred", result);
  }

  @Test
  void testGetUserById() {
    String userId = "123";
    String result = indexController.getUserById(userId);
    assertEquals("User with ID: " + userId, result);
  }

  @Test
  void testSearch() {
    String query = "test query";
    String result = indexController.search(query);
    assertEquals("Search results for: " + query, result);
  }

  @Test
  void testProcessData() {
    // Using a spy to verify the void method was called
    IndexController spyController = spy(indexController);

    String testData = "test data";
    spyController.processData(testData);

    // Verify that processData was called with the correct parameter
    verify(spyController, times(1)).processData(testData);
  }

  @Test
  void testSlowEndpoint() {
    String result = indexController.slowEndpoint();
    assertEquals("This endpoint is slow", result);
  }

  @Test
  void testDuplicate1() {
    String result = indexController.duplicate1();
    assertNotNull(result);
    assertTrue(result.contains("Item 0"));
    assertTrue(result.contains("Item 9"));
  }

  @Test
  void testDuplicate2() {
    String result = indexController.duplicate2();
    assertNotNull(result);
    assertTrue(result.contains("Item 0"));
    assertTrue(result.contains("Item 9"));
  }

  @Test
  void testComplexMethodWithAllParamsNull() {
    String result = indexController.complexMethod(null, null, null);
    assertEquals("", result);
  }

  @Test
  void testComplexMethodWithParam1() {
    String result = indexController.complexMethod("abcdef", null, null);
    assertEquals("Param1 starts with 'a' and is longer than 5 characters", result);
  }

  @Test
  void testComplexMethodWithParam1NotStartingWithA() {
    String result = indexController.complexMethod("bcdefg", null, null);
    assertEquals("Param1 does not start with 'a' but is longer than 5 characters", result);
  }

  @Test
  void testComplexMethodWithShortParam1StartingWithA() {
    String result = indexController.complexMethod("abc", null, null);
    assertEquals("Param1 starts with 'a' but is not longer than 5 characters", result);
  }

  @Test
  void testComplexMethodWithShortParam1NotStartingWithA() {
    String result = indexController.complexMethod("bcd", null, null);
    assertEquals("Param1 does not start with 'a' and is not longer than 5 characters", result);
  }
}
