package ru.xtim.prts.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.xtim.prts.addressbook.model.ContractData;
import ru.xtim.prts.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.getNavigationHelper().gotoGroupPage();
        if (! app.getGroupHelper().IsThereAGroup()){
            app.getNavigationHelper().gotoGroupPage();
            app.getGroupHelper().createGroup(new GroupData("test1", "test2", "test3"));
        }
        app.getNavigationHelper().gotoHomePage();
        List<ContractData> before =app.getContractHelper().getContractList();
        //int before =app.getContractHelper().getContractCount();
        ContractData contract = new ContractData("Testname", "Testmiddle", "Testlast", "Testnick", "Testtitle", "Testcompany", "Testaddress", "999-99-99", "888-88-88", "777-77-77","test1");
        app.getContractHelper().createContract(contract,true);
        app.getNavigationHelper().gotoHomePage();
        List<ContractData> after = app.getContractHelper().getContractList();
        Assert.assertEquals(after.size(),before.size()+1);
        //List<ContractData> after =app.getContractHelper().getContractList();
        //Assert.assertEquals(after.size(),before.size()+1);
        contract.setId(after.stream().max((c1,c2) -> Integer.compare(c1.getId(),c2.getId())).get().getId());
        before.add(contract);
        Assert.assertEquals(new HashSet<Object>(before),new HashSet<Object>(after));

    }

}
