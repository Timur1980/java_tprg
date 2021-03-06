package ru.xtim.prts.mantis.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created by timur.khisamutdinov on 21.05.2017.
 */
public class ApplicationManager {

    private Properties properties;
    private WebDriver wd;
    private RegistrationHelper registrationHelper;
    private String browser;
    private FtpHelper ftp;
    private MailHelper mailHelper;
    private JamesHelper jamesHelper;
    private DbHelper dbHelper;
    private UserHelper userHelper;
    private NavigationHelper navigationHelper;
    private SoapHelper soapHelper;

    public ApplicationManager(String browser) {
        this.browser = browser;
        properties = new Properties();
    }

    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
        dbHelper=new DbHelper();
    }


    public WebDriver getDriver() {
        if (wd == null) {
            if (browser.equals(BrowserType.FIREFOX)) {
                wd = new FirefoxDriver(new FirefoxOptions().setLegacy(true).setBinary(properties.getProperty("web.pathFirefox")));
            } else if (browser.equals(BrowserType.CHROME)) {
                wd = new ChromeDriver();
            } else if (browser.equals(BrowserType.IE)) {
                wd = new InternetExplorerDriver();
            }
            wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            wd.get(properties.getProperty("web.baseUrl"));
        }
        return wd;
    }


    public RegistrationHelper registration() {
        if (registrationHelper == null) {
            registrationHelper = new RegistrationHelper(this);
        }
        return registrationHelper;
    }

    public FtpHelper ftp(){
        if (ftp==null) {
            ftp = new FtpHelper(this);
        }
        return ftp;
    }

    public MailHelper mail(){
        if (mailHelper==null){
            mailHelper=new MailHelper(this);
        }
        return mailHelper;
    }

    public JamesHelper james(){
        if (jamesHelper==null){
            jamesHelper=new JamesHelper(this);
        }
        return jamesHelper;
    }

    public UserHelper user(){
        if (userHelper==null){
            userHelper=new UserHelper(this);
        }
        return userHelper;
    }

    public NavigationHelper navigation(){
        if (navigationHelper==null){
            navigationHelper=new NavigationHelper(this);
        }
        return navigationHelper;
    }
    public SoapHelper soap(){
        if (soapHelper==null){
            soapHelper=new SoapHelper(this);
        }
        return soapHelper;
    }


    public void stop() {
        if (wd != null) {
            wd.quit();
        }
    }


    //public Properties properties() {return properties;}

    public HttpSession newSession() {
        return new HttpSession(this);
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public DbHelper db(){return dbHelper;}
}
