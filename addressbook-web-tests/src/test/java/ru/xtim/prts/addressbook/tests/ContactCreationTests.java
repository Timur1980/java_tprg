package ru.xtim.prts.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.xtim.prts.addressbook.model.ContractData;
import ru.xtim.prts.addressbook.model.Contracts;
import ru.xtim.prts.addressbook.model.GroupData;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {


    @BeforeMethod
    public void encurePreconditions(){
        app.goTo().groupPage();
        if (app.group().all().size()==0){
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("Test1").withHeader("Test2").withFooter("Test2"));
        }
        app.goTo().homePage();
    }


    @Test(enabled = true)
    public void testContactCreation() {
        Contracts before =app.contract().all();
        File photo =new File("src/test/resources/foto.png");
        ContractData contract = new ContractData().withFirstname("Testname").withMiddlename("Testmiddle").
        withLastname("Testlast").withNickname("Testnick").withTitle("Testtitle").withCompany("Testcompany").
        withAddress("Testaddress").withPhonehome("999-99-99").withMobilephone("888-88-88").
        withWorkphone("777-77-77").withGroup("test1").withPhoto(photo);
        app.contract().create(contract,true);
        app.goTo().homePage();
        assertThat(app.contract().count(),equalTo(before.size()+1));
        Contracts after = app.contract().all();
        //assertThat(after.size(), equalTo(before.size()+1));
        assertThat(after, equalTo(before.withAdded(contract.
                withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt()))));
    }

}
