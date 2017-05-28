package ru.xtim.prts.addressbook.tests;

import org.testng.annotations.Test;
import ru.xtim.prts.addressbook.model.ContractData;
import ru.xtim.prts.addressbook.model.GroupData;

/**
 * Created by timur.khisamutdinov on 22.05.2017.
 */
public class ContractModificationTests extends  TestBase {

    @Test
    public void testContractModification() {
        app.getNavigationHelper().gotoGroupPage();
        if (! app.getGroupHelper().IsThereAGroup()){
            app.getGroupHelper().createGroup(new GroupData("test1", "test2", "test3"));
        }
        app.getNavigationHelper().gotoHomePage();
        if (!app.getContractHelper().IsThereAContract()) {
            app.getContractHelper().createContract(new ContractData("Testname", "Testmiddle", "Testlast", "Testnick", "Testtitle", "Testcompany", "Testaddress", "999-99-99", "888-88-88", "777-77-77", "test1"), true);
            app.getNavigationHelper().gotoHomePage();
        }
        app.getContractHelper().selectContract();
        app.getContractHelper().fillContractForm(new ContractData("Testname1", "Testmiddle", "Testlast", "Testnick", "Testtitle", "Testcompany", "Testaddress", "999-99-99", "888-88-88", "777-77-77",null),false);
        app.getContractHelper().submitContractModification();
    }

}
