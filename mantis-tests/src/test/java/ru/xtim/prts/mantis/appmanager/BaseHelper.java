package ru.xtim.prts.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import java.io.File;

/**
 * Created by timur.khisamutdinov on 21.05.2017.
 */
public class BaseHelper {

    protected ApplicationManager app;
    protected WebDriver wd;

    public BaseHelper(ApplicationManager app) {
        this.app=app;
        this.wd=app.getDriver();
    }

    public void click(By locator) {
        wd.findElement(locator).click();
    }

    public void type(By locator, String text) {
        click(locator);
        if (text!=null) {
            String existingTest= wd.findElement(locator).getAttribute("value");
            if (! text.equals(existingTest)) {
                wd.findElement(locator).clear();
                wd.findElement(locator).sendKeys(text);
            }
        }
    }

    public void attach(By locator, File file) {
        if (file!=null) {
               wd.findElement(locator).sendKeys(file.getAbsolutePath());
            }
        }


    public boolean isAlertPresent() {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    protected boolean isElementPresent(By locator) {
        try {
            wd.findElement(locator);
            return true;
        } catch (NoSuchElementException ex){
            return false;
        }
    }
}
