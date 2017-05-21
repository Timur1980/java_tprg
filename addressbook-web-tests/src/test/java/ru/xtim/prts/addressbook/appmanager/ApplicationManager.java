package ru.xtim.prts.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import java.util.concurrent.TimeUnit;

/**
 * Created by timur.khisamutdinov on 21.05.2017.
 */
public class ApplicationManager {

    FirefoxDriver wd;

    private ContractHelper contractHelper;
    private GroupHelper groupHelper;
    private NavigationHelper navigationHelper;
    private SessionHelper sessionHelper;


    public void init() {
        wd = new FirefoxDriver(new FirefoxOptions().setLegacy(true).setBinary("C:/Program Files/Mozilla Firefox/firefox.exe"));
        wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        wd.get("http://localhost/addressbook/");
        wd.findElement(By.cssSelector("html")).click();
        groupHelper =new GroupHelper(wd);
        navigationHelper = new NavigationHelper(wd);
        contractHelper = new ContractHelper(wd);
        sessionHelper = new SessionHelper(wd);
        sessionHelper.login("admin", "secret");
    }


    public void stop() {
        wd.quit();
    }

    public GroupHelper getGroupHelper() {
        return groupHelper;
    }

    public NavigationHelper getNavigationHelper() {
        return navigationHelper;
    }

    public ContractHelper getContractHelper() {
        return contractHelper;
    }

    public SessionHelper getSessionHelper() {
        return sessionHelper;
    }
}
