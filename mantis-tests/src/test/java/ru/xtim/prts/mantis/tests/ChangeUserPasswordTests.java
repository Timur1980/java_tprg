package ru.xtim.prts.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.xtim.prts.mantis.appmanager.HttpSession;
import ru.xtim.prts.mantis.model.MailMessage;
import ru.xtim.prts.mantis.model.UserData;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

/**
 * Created by timur.khisamutdinov on 02.07.2017.
 */
public class ChangeUserPasswordTests extends TestBase {


    @BeforeMethod
    public void startMailStart(){
        app.mail().start();
    }


    @Test
    public void testChangePassword() throws MessagingException, IOException {
        UserData user=app.db().users().iterator().next();
        app.user().login("administrator","root");
        app.navigation().managePage();
        app.navigation().manageUser();
        app.user().openUserDetails(user);
        app.user().resetPassword();
        app.navigation().logout();
        String newPassword="password1";
        List<MailMessage> mailMessages = app.mail().waitForMail(1, 20000);
        String Link = findConfirmationLink(mailMessages, user.getEmail());
        app.user().changePassword(Link, newPassword);
        HttpSession session = app.newSession();
        assertTrue(session.login(""+user.getUsername()+"", ""+newPassword+""));
        assertTrue(session.isLoggedInAs(""+user.getUsername()+""));
    }

        private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
            MailMessage message = mailMessages.stream().
                    findFirst().get();
            VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().
                    oneOrMore().build();
            return regex.getText(message.text);
        }

    @AfterMethod(alwaysRun = true)
    public void stopMailStop(){
        app.mail().stop();
    }




}
