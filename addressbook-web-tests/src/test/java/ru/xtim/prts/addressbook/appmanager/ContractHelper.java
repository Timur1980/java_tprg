package ru.xtim.prts.addressbook.appmanager;

import org.apache.http.annotation.Contract;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.xtim.prts.addressbook.model.ContractData;
import ru.xtim.prts.addressbook.model.Contracts;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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


    public void selectContractById(int id) {
        wd.findElement(By.cssSelector("input[value='"+id+"']")).click();
    }

    public void selectContract(int id) {
        wd.findElement(By.cssSelector("a[href='edit.php?id="+id+"']")).click();
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

    public void create(ContractData contract, Boolean creation) {
        initContractCreation();
        fillContractForm(contract,creation);
        submitContractForm();
        returnToHome();
    }

    public void modify(ContractData contract) {
        selectContract(contract.getId());
        fillContractForm(contract,false);
        submitContractModification();
    }

    public void delete(ContractData contract) {
        selectContractById(contract.getId());
        deleteSelectedContract();
    }


    public boolean IsThereAContract() {
        return isElementPresent(By.name("selected[]"));
    }

    public int getContractCount() {
        return wd.findElements(By.name("selected[]")).size();
    }


    public Contracts all() {

        Contracts contracts = new Contracts();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            int id=Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            String lastname =element.findElement(By.xpath("./td[2]")).getText();
            String firstname=element.findElement(By.xpath("./td[3]")).getText();
            ContractData contract =new ContractData().withId(id).withFirstname(firstname).withLastname(lastname);
            contracts.add(contract);
        }
        return contracts;
    }

}
