package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;


public class SerfingSite extends AbstractClassTest{

    @Test
    void checkPagination (){
        getDriver().findElement(By.cssSelector("div.mainmenu-content > ul > li > a[href='/publikacii/']")).click();
        getDriver().findElement(By.cssSelector("div.mainmenu-content > ul > li> ul > li> a[href = '/publikacii/novosti/']")).click();
        Assertions
                .assertTrue(getDriver()
                        .getCurrentUrl()
                        .equals("https://www.trxtraining.ru/publikacii/novosti/"),"Страница новости не доступна");

        JavascriptExecutor javascr = (JavascriptExecutor) getDriver();
        javascr.executeScript("window.scrollBy(0,4200)");

        getDriver().findElement(By.cssSelector("#postlist > div.pagination.pagination__posts > ul > li.next > a")).click();
        Assertions
                .assertTrue(getDriver()
                        .getCurrentUrl()
                        .equals("https://www.trxtraining.ru/publikacii/novosti/?p=1"),"Не перешли на следующую страницу");

        getDriver().findElement(By.xpath("//*[@id='postlist']/div/ul/li/a[.='6']")).click();
        Assertions
                .assertTrue(getDriver()
                        .getCurrentUrl()
                        .equals("https://www.trxtraining.ru/publikacii/novosti/?p=5"),"Не перешли на 6-ю страницу");

        getDriver().findElement(By.xpath("//*[@id='postlist']/div/ul/li/a[.='←']")).click();
        Assertions
                .assertTrue(getDriver()
                        .getCurrentUrl()
                        .equals("https://www.trxtraining.ru/publikacii/novosti/?p=4"),"Не перешли на страницу назад");
        javascr.executeScript("window.scrollBy(0,3900)");

    }
    @Test
    void checkSearch (){
        Actions search = new Actions(getDriver());
        search.click(getDriver().findElement(By.xpath("//*[@id='main-menu']//i[@class='fa fa-search']")))
                .sendKeys(getDriver().findElement(By.id("search-input")),"тренажер")
                .click(getDriver().findElement(By.id("big-forward-arrow-icon")))
                .build()
                .perform();
        Assertions.assertTrue(getDriver().findElement(By.xpath("//*[@id='results-box']/div/div[@class='post-title']"))
                .getText().contains("По вашему запросу \"тренажер\" найдено товаров: "));
    }

}
