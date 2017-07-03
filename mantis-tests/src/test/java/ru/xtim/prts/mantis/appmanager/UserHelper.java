package ru.xtim.prts.mantis.appmanager;

import org.openqa.selenium.By;
import ru.xtim.prts.mantis.model.UserData;

/**
 * Created by timur.khisamutdinov on 02.07.2017.
 */
public class UserHelper extends BaseHelper {

    public UserHelper(ApplicationManager app){
           super(app);
    }

    public void openUserDetails(UserData user)
    {
       wd.findElement(By.cssSelector("a[href='manage_user_edit_page.php?user_id=" + user.getId() + "']")).click();
    }

    public void resetPassword()
    {
       click(By.cssSelector("input[value='Reset Password']"));
    }

    public void changePassword(String passwordLink, String newPassword)
   {
       wd.get(passwordLink);
       type(By.name("password"), newPassword);
       type(By.name("password_confirm"), newPassword);
       click(By.cssSelector("input[value='Update User']"));
   }

    public void login(String username, String password) {
        wd.get(app.getProperty("web.baseUrl"));
        type(By.name("username"),username);
        type(By.name("password"),password);
        click(By.cssSelector("input[value='Login']"));
        }




}
