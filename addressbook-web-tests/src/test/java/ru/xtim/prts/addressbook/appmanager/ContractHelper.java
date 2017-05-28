package ru.xtim.prts.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.xtim.prts.addressbook.model.ContractData;

/**
 * Created by timur.khisamutdinov on 21.05.2017.
 */
public class ContractHelper extends BaseHelper{


    public ContractHelper(WebDriver wd) {
        super(wd);
    }

    public void submitContractForm() {
        click(By.name("theform"));
    }

    public void fillContractForm(ContractData contractData, Boolean creation) {
        type(By.name("firstname"),contractData.getFirstname());
        type(By.name("middlename"),contractData.getMiddlename());
        type(By.name("lastname"),contractData.getLastname());
        type(By.name("nickname"),contractData.getNickname());
        type(By.name("title"),contractData.getTitle());
        type(By.name("company"),contractData.getCompany());
        type(By.name("address"),contractData.getAddress());
        type(By.name("home"),contractData.getPhonehome());
        type(By.name("mobile"),contractData.getMobilephone());
        type(By.name("work"),contractData.getWorkphone());

        if (creation){
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contractData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void initContractCreation() {
        click(By.linkText("add new"));
    }


    public void selectContract() {
        click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
    }

    public void selectContractID() {
        click(By.name("selected[]"));
    }

    public void returnToHome() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void deleteSelectedContract() {
        click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
        wd.switchTo().alert().accept();
    }


    public void submitContractModification() {
        click(By.xpath("//div[@id='content']/form[1]/input[22]"));
    }

    public void createContract(ContractData contract,Boolean creation) {
        initContractCreation();
        fillContractForm(contract,creation);
        submitContractForm();
        returnToHome();
    }

    public boolean IsThereAContract() {
        return isElementPresent(By.name("selected[]"));
    }
}
