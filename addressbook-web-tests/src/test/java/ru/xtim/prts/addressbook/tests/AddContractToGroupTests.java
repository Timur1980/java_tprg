package ru.xtim.prts.addressbook.tests;

import org.hibernate.annotations.Type;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.xtim.prts.addressbook.model.ContractData;
import ru.xtim.prts.addressbook.model.Contracts;
import ru.xtim.prts.addressbook.model.GroupData;
import ru.xtim.prts.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by timur.khisamutdinov on 25.06.2017.
 */
public class AddContractToGroupTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contracts().size() == 0) {
            if (app.db().groups().size() == 0) {
                app.goTo().groupPage();
                Groups before = app.db().groups();
                GroupData groupTest1 = new GroupData().withName("test 1").withHeader("header 1").withFooter("footer 1");
                app.group().create(groupTest1);
                assertThat(app.db().groups().size(), equalTo(before.size() + 1));
                Groups after = app.db().groups();
                assertThat(after, equalTo(
                        before.withAdded(groupTest1.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

            }
            app.goTo().homePage();
            Contracts before = app.db().contracts();
            ContractData contract = new ContractData().withFirstname("Testname").withMiddlename("Testmiddle").
                    withLastname("Testlast").withNickname("Testnick").withTitle("Testtitle").withCompany("Testcompany").
                    withAddress("Testaddress").withPhonehome("999-99-99").withMobilephone("888-88-88").withWorkphone("777-77-77");
            app.contract().create(contract,true);
            app.goTo().homePage();
            assertThat(app.db().contracts().size(), equalTo(before.size() + 1));
            Contracts after = app.db().contracts();
            assertThat(after, equalTo(
                    before.withAdded(contract.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
        }


    }

    @Test()
    public void addContactToGroupTest() {
        Contracts contactsAtAll = app.db().contracts();
        ContractData contact = contactsAtAll.iterator().next();
        Groups groupsContact = contact.getGroups();
        Groups groupsAtAll = app.db().groups();
        GroupData group;

        if (groupsContact.size() == groupsAtAll.size()) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test A").withHeader("header A").withFooter("footer A"));
            Groups groupsAddNew = app.db().groups();
            groupsAddNew.removeAll(groupsAtAll);
            group = groupsAddNew.iterator().next();

        } else {
            groupsAtAll.removeAll(groupsContact);
            group = groupsAtAll.iterator().next();
        }
        app.goTo().homePage();
        app.contract().addContractToGroup(contact, group);
        app.goTo().homePage();
        assertThat(app.db().contracts(contact.getId()).getGroups(), equalTo(groupsContact.withAdded(group)));
    }


}
