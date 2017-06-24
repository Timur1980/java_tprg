package ru.xtim.prts.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.xtim.prts.addressbook.model.GroupData;
import ru.xtim.prts.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupDeletionTests extends TestBase {

    @BeforeMethod
    public void encurePreconditions(){
        if (app.db().groups().size()==0)
        {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test 1").withHeader("header 1").withFooter("footer 1"));
        }
        /*
        app.goTo().groupPage();
        if (app.group().all().size()==0){
            app.group().create(new GroupData().withName("test 1").withHeader("header 1").withFooter("footer 1"));
        }
        */
    }

    @Test(enabled = true)
    public void testGroupDeletion() {
        //Groups before = app.group().all();
        Groups before =app.db().groups();
        GroupData deleteGroup=before.iterator().next();
        app.goTo().groupPage();
        app.group().delete(deleteGroup);
        assertThat(app.group().count(),equalTo(before.size()-1));
        Groups after =app.db().groups();
        //Groups after = app.group().all();
        //assertThat(after.size(),equalTo(before.size()-1));
        assertThat(after, equalTo(before.withOut(deleteGroup)));
    }

}
