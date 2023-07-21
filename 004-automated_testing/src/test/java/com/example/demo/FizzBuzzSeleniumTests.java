package com.example.demo;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = DemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FizzBuzzSeleniumTests {
    List<String> expectedRules = Arrays.asList("If the number is divisible by 3, then we print 'fizz'.",
            "If the number is divisible by 5, then we print 'buzz'.",
            "If the number is divisible by both, we print 'fizzbuzz'.",
            "If a none of the rules apply output the number.");
    @LocalServerPort
    private int port;
    private WebDriver driver;
    private String base;

    @BeforeAll
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setupTest() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        this.base = "http://localhost:" + port;
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }

    @Test
    void testTitle() {
        // Open the webpage
        driver.get(this.base + "/fizzbuzz");
        // Get the title
        String title = driver.getTitle();
        // Check it is what we expect it to be
        assertThat(title).contains("Getting Started: Serving Web Content");
    }

    @Test
    void testRules() {
        // Open the webpage
        driver.get(this.base + "/fizzbuzz");

        // Find the div containing all of the rules
        WebElement rules = driver.findElement(By.id("rules"));

        // Get the children (first level only)
        List<WebElement> children = rules.findElements(By.xpath(".//*[not(*)]")); // Use Xpath to get the first level children

        // Create a list to store the rules
        List<String> actualRules = new ArrayList<String>();
        for (WebElement child : children) {
            // Get the text from each rule and store it in the list
            actualRules.add(child.getText());
        }

        // Check the list contains what we expected
        assertEquals(expectedRules, actualRules);
    }


}