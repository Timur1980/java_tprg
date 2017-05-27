package ru.xtim.prts.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;


import java.util.concurrent.TimeUnit;

import static sun.plugin2.util.BrowserType.*;

/**
 * Created by timur.khisamutdinov on 21.05.2017.
 */
public class ApplicationManager {

    WebDriver wd;

    private ContractHelper contractHelper;
    private GroupHelper groupHelper;
    private NavigationHelper navigationHelper;
    private SessionHelper sessionHelper;
    private String browser;

    public ApplicationManager(String browser)
    {
        this.browser=browser;
    }

    public void init() {
        if (browser.equals(BrowserType.FIREFOX)) {
            wd = new FirefoxDriver(new FirefoxOptions().setLegacy(true).setBinary("C:/Program Files/Mozilla Firefox/firefox.exe"));
        } else if (browser.equals(BrowserType.CHROME)){
            wd=new ChromeDriver();
        } else if (browser.equals(BrowserType.IE)){
            wd=new InternetExplorerDriver();
        }
        wd.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        wd.get("http://localhost/addressbook/");
        //wd.findElement(By.cssSelector("html")).click();
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
