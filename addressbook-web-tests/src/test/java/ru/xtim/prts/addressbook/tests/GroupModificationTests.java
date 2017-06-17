package ru.xtim.prts.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.xtim.prts.addressbook.model.GroupData;
import ru.xtim.prts.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

/**
 * Created by timur.khisamutdinov on 21.05.2017.
 */
public class GroupModificationTests extends TestBase {

    @BeforeMethod
    public void encurePreconditions(){
        app.goTo().groupPage();
        if (app.group().all().size()==0){
            app.group().create(new GroupData().withName("test 1").withHeader("header 1").withFooter("footer 1"));
        }
    }

    @Test(enabled = true)
    public void testGroupModification() {
        Groups before = app.group().all();
        GroupData modifyGroup=before.iterator().next();
        GroupData group = new GroupData().
                withId(modifyGroup.getId()).withName("test 1").withHeader("header 1").withFooter("footer 1");
        app.group().modify(group);
        assertThat(app.group().count(),equalTo(before.size()));
        Groups after = app.group().all();
        //assertEquals(after.size(),before.size());
        assertThat(after, equalTo(before.withOut(modifyGroup).withAdded(group)));
    }




}
