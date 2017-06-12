package ru.xtim.prts.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.xtim.prts.addressbook.appmanager.ApplicationManager;

/**
 * Created by timur.khisamutdinov on 21.05.2017.
 */
public class TestBase {

    protected final static ApplicationManager app = new ApplicationManager(BrowserType.FIREFOX);

    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
    }

    @AfterSuite
    public void tearDown() {
        app.stop();
    }

}
