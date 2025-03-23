package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Main {
    public static void main(String[] args) {
        WebDriver driver = new SafariDriver();

        try {
            driver.get("https://stg-cloud.v-key.com/");

            Thread.sleep(3000);

            // Search username and input data
            WebElement usernameField = driver.findElement(By.xpath("//*[@id=\"vkey-external-wrapper\"]/div[2]/div/div/div/div[2]/form/div/div[1]/input"));
            usernameField.sendKeys("<username>");

            // Search password and input data
            WebElement passwordField = driver.findElement(By.xpath("//*[@id=\"vkey-external-wrapper\"]/div[2]/div/div/div/div[2]/form/div/div[2]/input"));
            passwordField.sendKeys("<password>");
            Thread.sleep(2000);

            // Search and click Signin button
            WebElement signInButton = driver.findElement(By.xpath("//*[@id=\"vkey-external-wrapper\"]/div[2]/div/div/div/div[2]/form/div/button/span[1]"));

            if (signInButton.isEnabled()) {
                //signInButton.click();
                // Use JavaScript to click
                JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
                jsExecutor.executeScript("arguments[0].click();", signInButton);
                System.out.println("Clicked Sign In button using JavaScript");
            } else {
                System.out.println("Sign In button is not enabled");
            }

            Thread.sleep(5000);

            if (driver.getTitle().contains("Dashboard")) {
                System.out.println("Test Signin => Passed");
            } else {
                System.out.println("Test Signin Failed");
            }
        } catch (Exception e) {
            System.out.println("Test 1 Failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
}