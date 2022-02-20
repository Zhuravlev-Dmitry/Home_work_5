package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


import java.util.concurrent.TimeUnit;

public abstract class AbstractClassTest {
    private static WebDriver driver;

    @BeforeAll
    static void initial () {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("--incognito");
        options.addArguments("disable-popup-blocking");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }
    @BeforeEach
    void goTo () throws InterruptedException {
        Assertions.assertDoesNotThrow( () -> driver.navigate().to("https://www.trxtraining.ru/"),"Страница не доступна");
    }

    @AfterAll
    static void close(){
         driver.quit();
    }
    public static WebDriver getDriver(){
        return driver;
    }

}
