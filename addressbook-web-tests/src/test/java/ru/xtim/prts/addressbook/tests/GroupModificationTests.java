package ru.xtim.prts.addressbook.tests;


import org.testng.annotations.Test;
import ru.xtim.prts.addressbook.model.GroupData;

/**
 * Created by timur.khisamutdinov on 21.05.2017.
 */
public class GroupModificationTests extends TestBase {


    @Test
    public void testGroupModification() {
        app.getNavigationHelper().gotoGroupPage();
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().initGroupModification();
        app.getGroupHelper().fillGroupForm(new GroupData("test1", "test2", "test3"));
        app.getGroupHelper().submitGroupModification();
        app.getGroupHelper().returnToGroupPage();
    }


}
