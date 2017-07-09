package ru.xtim.prts.mantis.tests;

import org.testng.annotations.Test;
import ru.xtim.prts.mantis.appmanager.HttpSession;

import javax.xml.rpc.ServiceException;
import java.io.IOException;

import static org.testng.Assert.assertTrue;

/**
 * Created by timur.khisamutdinov on 01.07.2017.
 */
public class LoginTests extends TestBase {


    @Test
    public void testLogin() throws IOException, ServiceException {
        HttpSession session = app.newSession();
        assertTrue(session.login("administrator", "root"));
        assertTrue(session.isLoggedInAs("administrator"));
    }
}
