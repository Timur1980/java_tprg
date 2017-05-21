package ru.xtim.prts.addressbook.tests;

import org.testng.annotations.Test;
import ru.xtim.prts.addressbook.model.ContractData;

/**
 * Created by timur.khisamutdinov on 22.05.2017.
 */
public class ContractModificationTests extends  TestBase {

    @Test
    public void testContractModification() {
        app.getNavigationHelper().gotoHomePage();
        app.getContractHelper().selectContract();
        app.getContractHelper().fillContractForm(new ContractData("Testname1", "Testmiddle", "Testlast", "Testnick", "Testtitle", "Testcompany", "Testaddress", "999-99-99", "888-88-88", "777-77-77"));
        app.getContractHelper().submitContractModification();
    }

}
