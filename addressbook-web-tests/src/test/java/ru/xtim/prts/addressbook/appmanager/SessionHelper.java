package ru.xtim.prts.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by timur.khisamutdinov on 21.05.2017.
 */
public class SessionHelper extends BaseHelper {

    public SessionHelper(WebDriver wd)
    {
       super(wd);
    }

    public void login(String username, String password) {
        type(By.name("user"),username);
        type(By.name("pass"),password);
        click(By.xpath("//form[@id='LoginForm']/input[3]"));
    }
}
