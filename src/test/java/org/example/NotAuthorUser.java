package org.example;



import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.interactions.Actions;

public class NotAuthorUser extends AbstractClassTest{
    @Test
    void checkOrderGood (){
        getDriver().findElement(By.cssSelector("div.mainmenu-content > ul > li > a[href='/shop/']")).click();
        getDriver().findElement(By.cssSelector("div.mainmenu-content > ul > li> ul > li> a[href = '/shop/petli-trx/']")).click();

        Assertions
                .assertTrue(getDriver()
                        .getCurrentUrl()
                        .equals("https://www.trxtraining.ru/shop/petli-trx/"),"Страница не доступна");

        getDriver().findElement(By.xpath("//div/a[@href='/shop/petli-trx/petli-trx-move/']")).click();
        Assertions
                .assertTrue(getDriver()
                        .getCurrentUrl()
                        .equals("https://www.trxtraining.ru/shop/petli-trx/petli-trx-move/"),"Страница не доступна");

        Actions showOrderGood = new Actions(getDriver());
        showOrderGood.click(getDriver().findElement(By.cssSelector("div > li[data-slick-index='1']> img")))
                .click(getDriver().findElement(By.xpath("//div/li[@data-slick-index='2']/img")))
                .click(getDriver().findElement(By.cssSelector("div > li[data-slick-index='3']> img")))
                .click(getDriver().findElement(By.id("quantitySelectBoxItArrowContainer")))
                .click(getDriver().findElement(By.xpath("//*[@id='quantitySelectBoxItOptions']/li[@data-id='2']")))
                .click(getDriver().findElement(By.id("add-to-cart")))
                .build()
                .perform();
        Assertions.assertTrue(getDriver()
                .findElement(By.cssSelector("#alert-message > div > div > div.modal-body > div"))
                .getAttribute("class").equals("message"));

    }
    @Test
    void checkOrderWithCookie (){
        getDriver().manage().addCookie(new Cookie("customer-id","UNgdqwsh"));
        getDriver().manage().addCookie(new Cookie("_fbp","fb.1.1645335547948.1964140155"));
        getDriver().findElement(By.cssSelector(".mini-cart > a[href='/emarket/cart/']")).click();
        Assertions.assertTrue(getDriver()
                .findElement(By.id("cart_item_698079"))
                .isDisplayed());
    }


}
