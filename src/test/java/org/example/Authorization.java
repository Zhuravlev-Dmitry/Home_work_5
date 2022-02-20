package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

public class Authorization extends AbstractClassTest{
    @Test
    void checkPositiveSignIn (){
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
}
    @Test
    void checkNegativeSignIn (){
        getDriver().findElement(By.cssSelector("a > i.fa.fa-user-circle")).click();
        Assertions
                .assertTrue(getDriver()
                        .getCurrentUrl()
                        .equals("https://www.trxtraining.ru/users/login/"),"Страница входа не доступна");

        Actions loginIn = new Actions(getDriver());
        loginIn.sendKeys(getDriver().findElement(By.id("user_email")),"dmitry.yand2.mail@yandex.ru")
                .sendKeys(getDriver().findElement(By.id("user_password")),"TRXtestNegative")
                .click(getDriver().findElement(By.xpath(".//*[@id='login_form']/div/input[@name='commit']")))
                .build()
                .perform();
        Assertions.assertTrue(getDriver()
                .findElement(By.xpath("//ul[@class='woocommerce-error']/li"))
                .getText().equals("Вы ввели неверный логин или пароль. Проверьте раскладку клавиатуры, не нажата ли клавиша «Caps Lock» и попробуйте ввести логин и пароль ещё раз:"));
    }

    @Test
    void checkDuplicateAuthorisation (){
        getDriver().findElement(By.cssSelector("a > i.fa.fa-user-circle")).click();
        Assertions
                .assertTrue(getDriver()
                        .getCurrentUrl()
                        .equals("https://www.trxtraining.ru/users/login/"),"Страница входа не доступна");

        Actions registration = new Actions(getDriver());
        registration.sendKeys(getDriver().findElement(By.id("field_lname")),"Лутохин")
                .sendKeys(getDriver().findElement(By.id("field_fname")),"Дмитрий")
                .sendKeys(getDriver().findElement(By.id("field_father_name")),"Анатольевич")
                .sendKeys(getDriver().findElement(By.xpath("//input[@id='field_phone']")),"+7 (910) 234-93-75")
                .click(getDriver().findElement(By.id("email")))
                .sendKeys("dmitry.yand2.mail@yandex.ru")
                .sendKeys(getDriver().findElement(By.id("password")),"TRXtest1")
                .sendKeys(getDriver().findElement(By.id("password_confirm")),"TRXtest1")
                .click(getDriver().findElement(By.cssSelector(".sign-up-submit > .btn")))
                .build()
                .perform();
        Assertions.assertTrue(getDriver()
                .findElement(By.xpath("//*[@id='registrate_form']/div/div/div/ul/li"))
                .getText().equals("Пользователь с таким логином уже существует."));
    }
    @Test
    void checkNegativeAuthorisation (){
        getDriver().findElement(By.cssSelector("a > i.fa.fa-user-circle")).click();
        Assertions
                .assertTrue(getDriver()
                        .getCurrentUrl()
                        .equals("https://www.trxtraining.ru/users/login/"),"Страница входа не доступна");

        Actions registration = new Actions(getDriver());
        registration.sendKeys(getDriver().findElement(By.id("field_lname")),"asgfdsg")
                .sendKeys(getDriver().findElement(By.id("field_fname")),"sdfdfd")
                .sendKeys(getDriver().findElement(By.id("field_father_name")),"dszfzsd")
                .sendKeys(getDriver().findElement(By.xpath("//input[@id='field_phone']")),"+7 (910) 234-93-75")
                .click(getDriver().findElement(By.id("email")))
                .sendKeys("dmitry.yand2.@mail.ru")
                .sendKeys(getDriver().findElement(By.id("password")),"")
                .sendKeys(getDriver().findElement(By.id("password_confirm")),"")
                .click(getDriver().findElement(By.cssSelector(".sign-up-submit > .btn")))
                .build()
                .perform();
        Assertions.assertTrue(getDriver()
                .findElement(By.cssSelector("div.has-success > ul > li"))
                .getText().equals("Пароль не должен содержать пробелы."));
    }


}
