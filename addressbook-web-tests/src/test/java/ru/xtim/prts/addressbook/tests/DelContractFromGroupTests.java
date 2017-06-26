package ru.xtim.prts.addressbook.tests;

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
public class DelContractFromGroupTests extends TestBase {


    @BeforeMethod
    public void ensurePreconditions() {

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
        if (app.db().contracts().size() == 0) {
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

    @Test
    public void delContractFromGroupTest() {

        app.goTo().groupPage();
        GroupData group = app.db().groups().iterator().next();

        boolean isGroupWithContracts = true;

        for (GroupData groupData : app.db().groups()) {
            if (groupData.getContracts().size() > 0) {
                group = groupData;
                isGroupWithContracts = false;
                break;
            }
        }

        if (isGroupWithContracts) {
            ContractData contact = app.db().contracts().iterator().next();
            app.goTo().homePage();
            app.contract().addContractToGroup(contact, group);
            app.goTo().homePage();
            assertThat(app.db().groups(group.getId()).getContracts(), equalTo(group.getContracts().withAdded(contact)));
        }

        Contracts before = app.db().groups(group.getId()).getContracts();
        ContractData contract = app.db().groups(group.getId()).getContracts().iterator().next();
        app.goTo().homePage();
        app.contract().deleteContractFromGroup(group, contract);
        app.goTo().homePage();
        Contracts after = app.db().groups(group.getId()).getContracts();
        assertThat(after, equalTo(before.withOut(contract)));
    }
}

