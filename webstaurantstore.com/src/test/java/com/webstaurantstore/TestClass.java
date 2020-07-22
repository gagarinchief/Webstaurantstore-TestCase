package com.webstaurantstore;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class TestClass {

    public static void main(String[] args) {


        System.setProperty("webdriver.chrome.silentOutput", "true");
        System.setProperty("webdriver.chrome.driver", "C:\\SelDrivers\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.setAcceptInsecureCerts(true);

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        driver.get("https://www.webstaurantstore.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        WebElement searchBtn = driver.findElement(By.xpath("//button[@class='btn btn-info banner-search-btn']"));
        WebElement searchBox = driver.findElement(By.xpath("//input[@id='searchval']"));
        searchBox.sendKeys("stainless work table");
        searchBtn.click();

        ArrayList<WebElement> resultsLinks = new ArrayList<>(driver.findElements(By.className("description")));
        Boolean isTable = true;

        for (WebElement element : resultsLinks
        ) {
            if (!element.getText().contains("Table")) {
                isTable = false;
            }
        }
        Assert.assertTrue(isTable);

        resultsLinks.get(resultsLinks.size() - 1).click();


        WebElement addToCart = driver.findElement(By.id("buyButton"));
        addToCart.click();
        WebElement viewCart = driver.findElement(By.xpath("//div[@class='amount-in-cart']//button[@class='btn btn-primary']"));
        viewCart.click();
        WebElement emptyCartBtn = driver.findElement(By.xpath("//a[@class='emptyCartButton btn btn-mini btn-ui pull-right']"));
        wait.until(ExpectedConditions.visibilityOf(emptyCartBtn)).click();
        WebElement devastateCart = driver.findElement(By.xpath("//div[@class='modal fade bs-native modal--sm modal--collapse show']//div[@class='modal-dialog']//button[@class='btn btn-primary']"));
        devastateCart.click();
        driver.quit();
    }


}
