package ru.xtim.prts.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.xtim.prts.addressbook.model.ContractData;
import ru.xtim.prts.addressbook.model.GroupData;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by timur.khisamutdinov on 13.06.2017.
 */
public class ContactPhoneTests extends TestBase {

    @BeforeMethod
    public void encurePreconditions(){
        app.goTo().groupPage();
        if (app.group().all().size()==0){
            app.group().create(new GroupData().withName("test1").withHeader("test2").withFooter("test3"));
        }
        app.goTo().homePage();
        if (app.contract().all().size()==0) {
            app.contract().create(new ContractData().withFirstname("Testname").withMiddlename("Testmiddle").
                    withLastname("Testlast").withNickname("Testnick").withTitle("Testtitle").withCompany("Testcompany").
                    withAddress("Testaddress").withPhonehome("999-99-99").withMobilephone("888-88-88").withWorkphone("777-77-77").withGroup("test1"), true);
            app.goTo().homePage();
        }
    }

    @Test
    public void testContractPhones(){
        app.goTo().homePage();
        ContractData contract =app.contract().all().iterator().next();
        ContractData contractInfoFromEditForm=app.contract().infoFromEditForm(contract);
        assertThat(contract.getAllPhones(),equalTo(margePhones(contractInfoFromEditForm)));
     }

    private String margePhones(ContractData contract) {

        return Arrays.asList(contract.getPhonehome(),contract.getMobilephone(),contract.getWorkphone())
                .stream().filter((s) ->!s.equals(""))
                .map(ContactPhoneTests::cleaned)
                .collect(Collectors.joining("\n"));
    }

    public static String cleaned(String phone){
        return phone.replaceAll("\\s","").replaceAll("[-()]","");
    }
}
