package ru.xtim.prts.addressbook.tests;

import org.testng.annotations.Test;

/**
 * Created by timur.khisamutdinov on 21.05.2017.
 */
public class ContractDeletionTest extends TestBase {

    @Test
    public void testContractDeletion(){
       app.getNavigationHelper().gotoHomePage();
       app.getContractHelper().selectContract();
       app.getContractHelper().deleteSelectedContract();
    }
}
