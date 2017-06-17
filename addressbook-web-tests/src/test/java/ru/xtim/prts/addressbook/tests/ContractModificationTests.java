package ru.xtim.prts.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.xtim.prts.addressbook.model.ContractData;
import ru.xtim.prts.addressbook.model.Contracts;
import ru.xtim.prts.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

/**
 * Created by timur.khisamutdinov on 22.05.2017.
 */
public class ContractModificationTests extends  TestBase {

    @BeforeMethod
    public void encurePreconditions(){
        app.goTo().groupPage();
        if (app.group().all().size()==0){
            app.group().create(new GroupData().withName("test 1").withHeader("header 1").withFooter("footer 1"));
        }
        app.goTo().homePage();
        if (app.contract().all().size()==0) {
            app.contract().create(new ContractData().withFirstname("Testname").withMiddlename("Testmiddle").
                    withLastname("Testlast").withNickname("Testnick").withTitle("Testtitle").withCompany("Testcompany").
                    withAddress("Testaddress").withPhonehome("999-99-99").withMobilephone("888-88-88").withWorkphone("777-77-77").withGroup("test 1"), true);
            app.goTo().homePage();
        }
    }


    @Test(enabled = true)
    public void testContractModification() {
        Contracts before = app.contract().all();
        ContractData modifyContract=before.iterator().next();
        ContractData contract = new ContractData().withId(modifyContract.getId()).withFirstname("Testname").withMiddlename("Testmiddle").
                withLastname("Testlast").withNickname("Testnick").withTitle("Testtitle").withCompany("Testcompany").
                withAddress("Testaddress").withPhonehome("999-99-99").withMobilephone("888-88-88").withWorkphone("777-77-77");
        app.contract().modify(contract);
        app.goTo().homePage();
        assertThat(app.contract().count(),equalTo(before.size()));
        Contracts after = app.contract().all();
        //assertEquals(after.size(),before.size());
        assertThat(after, equalTo(before.withOut(modifyContract).withAdded(contract)));
    }



}
