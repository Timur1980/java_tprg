package ru.xtim.prts.addressbook;

import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        initContractCreation();
        fillContractForm(new ContractData("Testname", "Testmiddle", "Testlast", "Testnick", "Testtitle", "Testcompany", "Testaddress", "999-99-99", "888-88-88", "777-77-77"));
        submitContractForm();
        returnToHome();
    }

}
