package ru.xtim.prts.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.xtim.prts.addressbook.model.ContractData;
import ru.xtim.prts.addressbook.model.GroupData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by timur.khisamutdinov on 13.06.2017.
 */
public class ContractMailTests extends TestBase {

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
                    withAddress("Testaddress").withPhonehome("999-99-99").withMobilephone("888-88-88").withWorkphone("777-77-77")
                    .withEmail1("1@mail.ru").withEmail2("2@mail.ru").withEmail3("3@mail.ru").withGroup("test 1"), true);
            app.goTo().homePage();
        }
    }

    @Test
    public void testContractMails(){
        app.goTo().homePage();
        ContractData contract =app.contract().all().iterator().next();
        ContractData contractInfoFromEditForm=app.contract().infoFromEditForm(contract);
        assertThat(contract.getAllMails(),equalTo(margeMail(contractInfoFromEditForm)));
    }

    private String margeMail(ContractData contract) {

        return Arrays.asList(contract.getEmail1(),contract.getEmail2(),contract.getEmail3())
                .stream().filter((s) ->!s.equals(""))
                .collect(Collectors.joining("\n"));
    }
}
