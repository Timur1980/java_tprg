package ru.xtim.prts.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.xtim.prts.addressbook.model.ContractData;
import ru.xtim.prts.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

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
        List<ContractData> before = app.getContractHelper().getContractList();
        //int before =app.getContractHelper().getContractCount();
        app.getContractHelper().selectContract(before.size()-1);
        ContractData contract = new ContractData (before.get(before.size()-1).getId(),"Testname2", "Testmiddle", "Testlast", "Testnick", "Testtitle", "Testcompany", "Testaddress", "999-99-99", "888-88-88", "777-77-77",null);
        app.getContractHelper().fillContractForm(contract,false);
        app.getContractHelper().submitContractModification();
        app.getNavigationHelper().gotoHomePage();
        List<ContractData> after = app.getContractHelper().getContractList();
        //int after =app.getContractHelper().getContractCount();
        Assert.assertEquals(after.size(),before.size());
        before.remove(before.size()-1);
        before.add(contract);
        Assert.assertEquals(new HashSet<Object>(before),new HashSet<Object>(after));
    }

}
