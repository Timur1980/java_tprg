package ru.xtim.prts.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by timur.khisamutdinov on 21.05.2017.
 */
public class NavigationHelper extends BaseHelper {


    public NavigationHelper(FirefoxDriver wd) {
        super(wd);
    }

    public void gotoGroupPage() {
        click(By.linkText("groups"));
    }

    public void returnToHome() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void gotoHomePage() {
        click(By.linkText("home"));
    }

}
