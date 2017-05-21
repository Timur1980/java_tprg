package ru.xtim.prts.addressbook.tests;

import org.testng.annotations.Test;
import ru.xtim.prts.addressbook.model.ContractData;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.getContractHelper().initContractCreation();
        app.getContractHelper().fillContractForm(new ContractData("Testname", "Testmiddle", "Testlast", "Testnick", "Testtitle", "Testcompany", "Testaddress", "999-99-99", "888-88-88", "777-77-77"));
        app.getContractHelper().submitContractForm();
        app.getNavigationHelper().returnToHome();
    }

}
