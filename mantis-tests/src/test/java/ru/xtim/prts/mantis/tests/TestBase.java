package ru.xtim.prts.mantis.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.xtim.prts.mantis.appmanager.ApplicationManager;
import ru.xtim.prts.mantis.model.Issue;

import javax.xml.rpc.ServiceException;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;


/**
 * Created by timur.khisamutdinov on 21.05.2017.
 */
public class TestBase {

    protected final static ApplicationManager app =
            new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));

    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
        app.ftp().upload(new File("src/test/resources/config_inc.php"), "config_inc.php", "config_inc.php.bak");
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws IOException {
        app.ftp().restore("config_inc.php.bak", "config_inc.php");
        app.stop();
    }

    public boolean isIssueOpen(int issueId) throws RemoteException, ServiceException, MalformedURLException {
        String status =app.soap().statusIssue(issueId);
        if (!(status.equals("resolved"))) {
            return true;
        }
        return false;
    }

    public void skipIfNotFixed(int issueId) throws RemoteException, ServiceException, MalformedURLException, SkipException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }


}


