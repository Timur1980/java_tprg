package ru.xtim.prts.mantis.appmanager;

import org.openqa.selenium.By;

/**
 * Created by timur.khisamutdinov on 02.07.2017.
 */
public class NavigationHelper extends BaseHelper {

    public NavigationHelper(ApplicationManager app)
    {
       super(app);
    }

    public void managePage()
    {
        wd.findElement(By.cssSelector("a[href='/mantisbt-1.2.19/manage_overview_page.php']")).click();
     }

    public void manageUser()
    {
        wd.findElement(By.cssSelector("a[href='/mantisbt-1.2.19/manage_user_page.php']")).click();
    }

    public void logout()
    {
        click(By.linkText("Logout"));
    }

}
