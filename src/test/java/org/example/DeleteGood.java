package org.example;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;

public class DeleteGood extends AbstractClassTest{

    @Test
    void checkDelete () {
        getDriver().findElement(By.cssSelector("a > i.fa.fa-user-circle")).click();
        Assertions
                .assertTrue(getDriver()
                        .getCurrentUrl()
                        .equals("https://www.trxtraining.ru/users/login/"), "Страница входа не доступна");

        Actions loginIn = new Actions(getDriver());
        loginIn.sendKeys(getDriver().findElement(By.id("user_email")), "dmitry.yand2.mail@yandex.ru")
                .sendKeys(getDriver().findElement(By.id("user_password")), "TRXtest1")
                .click(getDriver().findElement(By.xpath(".//*[@id='login_form']/div/input[@name='commit']")))
                .build()
                .perform();
        Assertions.assertTrue(getDriver()
                .findElement(By.xpath("//*[@id='new_user']//a[@href='/users/settings/']"))
                .getText().equals("Персональная информация"));

        getDriver().findElement(By.cssSelector(".mini-cart > a[href='/emarket/cart/']")).click();
        Assertions
                .assertTrue(getDriver()
                        .getCurrentUrl()
                        .equals("https://www.trxtraining.ru/emarket/cart/"), "Страница не доступна");
        try {
            getDriver().findElement(By.cssSelector(".remove-cart-item-text")).click();
            Assertions.assertTrue(getDriver()
                    .findElement(By.cssSelector(" div[class='col-xs-24'] > div"))
                    .getText().equals("Ваша корзина пуста"));
        }catch (NoSuchElementException e){
            System.out.println("Что бы удалить товар из корзины, необходимо его сначала туда добавить!");
        }
    }
}
