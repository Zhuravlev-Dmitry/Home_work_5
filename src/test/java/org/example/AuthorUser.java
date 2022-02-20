package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;


public class AuthorUser extends AbstractClassTest {

    @Test
    void checkOrderGood (){
        getDriver().findElement(By.cssSelector("a > i.fa.fa-user-circle")).click();
        Assertions
                .assertTrue(getDriver()
                        .getCurrentUrl()
                        .equals("https://www.trxtraining.ru/users/login/"),"Страница входа не доступна");

        Actions loginIn = new Actions(getDriver());
        loginIn.sendKeys(getDriver().findElement(By.id("user_email")),"dmitry.yand2.mail@yandex.ru")
                .sendKeys(getDriver().findElement(By.id("user_password")),"TRXtest1")
                .click(getDriver().findElement(By.xpath(".//*[@id='login_form']/div/input[@name='commit']")))
                .build()
                .perform();
        Assertions.assertTrue(getDriver()
                .findElement(By.xpath("//*[@id='new_user']//a[@href='/users/settings/']"))
                .getText().equals("Персональная информация"));

        getDriver().findElement(By.cssSelector("div.mainmenu-content > ul > li > a[href='/shop/']")).click();
        getDriver().findElement(By.cssSelector("div.mainmenu-content > ul > li> ul > li> a[href = '/shop/aksessuary/']")).click();

        Assertions
                .assertTrue(getDriver()
                        .getCurrentUrl()
                        .equals("https://www.trxtraining.ru/shop/aksessuary/"),"Страница не доступна");

        getDriver().findElement(By.xpath("//*[@id='content_wrapper']//p/a[@href='/shop/aksessuary/trx-shejker/']")).click();

        Assertions
                .assertTrue(getDriver()
                        .getCurrentUrl()
                        .equals("https://www.trxtraining.ru/shop/aksessuary/trx-shejker/"),"Страница не доступна");

        Actions orderGood = new Actions(getDriver());
        orderGood.click(getDriver().findElement(By.xpath("//*[@id='pdp-carousel-indicators']/div/div/li[@aria-describedby='slick-slide11']/img")))
                .click(getDriver().findElement(By.id("add-to-cart")))
                 .build()
                 .perform();

        Assertions.assertTrue(getDriver()
                .findElement(By.cssSelector("#alert-message > div > div > div.modal-body > div"))
                .getAttribute("class").equals("message"));
    }

}
