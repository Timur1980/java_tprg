package ru.xtim.prts.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.xtim.prts.addressbook.model.ContractData;
import ru.xtim.prts.addressbook.model.Contracts;
import ru.xtim.prts.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by timur.khisamutdinov on 21.05.2017.
 */
public class ContractDeletionTest extends TestBase {

    @BeforeMethod
    public void encurePreconditions(){
        app.goTo().groupPage();
        if (app.db().groups().size()==0){
            app.group().create(new GroupData().withName("test 1").withHeader("header 1").withFooter("footer 1"));
        }
        app.goTo().homePage();
        if (app.db().contracts().size()==0) {
            app.contract().create(new ContractData().withFirstname("Testname").withMiddlename("Testmiddle").
                    withLastname("Testlast").withNickname("Testnick").withTitle("Testtitle").withCompany("Testcompany").
                    withAddress("Testaddress").withPhonehome("999-99-99").withMobilephone("888-88-88").withWorkphone("777-77-77").withGroup("test 1"), true);
            app.goTo().homePage();
        }
    }


    @Test(enabled = true)
    public void testContractDeletion() {
        //Contracts before = app.contract().all();
        Contracts before=app.db().contracts();
        ContractData deleteContract=before.iterator().next();
        app.contract().delete(deleteContract);
        app.goTo().homePage();
        assertThat(app.contract().count(),equalTo(before.size()-1));
        //Contracts after = app.contract().all();
        Contracts after =app.db().contracts();
        assertThat(after, equalTo(before.withOut(deleteContract)));
    }


}
