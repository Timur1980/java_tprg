package ru.xtim.prts.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import ru.xtim.prts.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

/**
 * Created by timur.khisamutdinov on 21.05.2017.
 */
public class GroupModificationTests extends TestBase {


    @Test
    public void testGroupModification() {
        app.getNavigationHelper().gotoGroupPage();
        if (! app.getGroupHelper().IsThereAGroup()){
            app.getGroupHelper().createGroup(new GroupData("test1", "test2", "test3"));
        }
        List<GroupData> before = app.getGroupHelper().getGroupList();
        //int before = app.getGroupHelper().getGroupCount();
        app.getGroupHelper().selectGroup(before.size()-1);
        app.getGroupHelper().initGroupModification();
        GroupData group = new GroupData(before.get(before.size()-1).getId(),"test1", "test2", "test3");
        app.getGroupHelper().fillGroupForm(group);
        app.getGroupHelper().submitGroupModification();
        app.getGroupHelper().returnToGroupPage();
        List<GroupData> after = app.getGroupHelper().getGroupList();
        //int after = app.getGroupHelper().getGroupCount();
        Assert.assertEquals(after.size(),before.size());

        before.remove(before.size()-1);
        before.add(group);
        Assert.assertEquals(new HashSet<Object>(before),new HashSet<Object>(after));

    }


}
