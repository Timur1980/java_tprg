package ru.xtim.prts.addressbook.appmanager;

import org.apache.http.annotation.Contract;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.xtim.prts.addressbook.model.ContractData;
import ru.xtim.prts.addressbook.model.Contracts;
import ru.xtim.prts.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by timur.khisamutdinov on 21.05.2017.
 */
public class ContractHelper extends BaseHelper{

    private Contracts contractCashe=null;

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
        type(By.name("email"),contractData.getEmail1());
        type(By.name("email2"),contractData.getEmail2());
        type(By.name("email3"),contractData.getEmail3());
        attach(By.name("photo"),contractData.getPhoto());
        if (creation){
            if (contractData.getGroups().size()>0) {
                new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contractData.getGroups().iterator().next().getName());
            }
        }
        else {
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
        contractCashe=null;
        returnToHome();
    }

    public void modify(ContractData contract) {
        selectContract(contract.getId());
        fillContractForm(contract,false);
        submitContractModification();
        contractCashe=null;
    }

    public void delete(ContractData contract) {
        selectContractById(contract.getId());
        deleteSelectedContract();
        contractCashe=null;
    }


    public boolean IsThereAContract() {
        return isElementPresent(By.name("selected[]"));
    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }


    public void chooseGroupToAdd(GroupData group){

        new Select(wd.findElement(By.cssSelector("select[name='to_group']"))).selectByValue(""+group.getId()+"");
        //new Select(wd.findElement(By.cssSelector("select[name='to_group']"))).selectByVisibleText(group.getName());
    }

    public void chooseGroupToDelete(GroupData group){

        new Select(wd.findElement(By.cssSelector("select[name=group]"))).selectByValue(""+group.getId()+"");
        //new Select(wd.findElement(By.name("group"))).selectByVisibleText(group.getName());
    }

    public void commitAddContract() {
        wd.findElement(By.name("add")).click();
    }

    public void commitDeleteContract() {
        wd.findElement(By.name("remove")).click();
    }

    public void addContractToGroup(ContractData contract, GroupData group) {
        selectContractById(contract.getId());
        chooseGroupToAdd(group);
        commitAddContract();
    }

    public void deleteContractFromGroup(GroupData group, ContractData contract ) {
        chooseGroupToDelete(group);
        selectContractById(contract.getId());
        commitDeleteContract();
    }



    public Contracts all() {

        if (contractCashe!=null)
        {
            return new Contracts(contractCashe);
        }
        contractCashe = new Contracts();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            int id=Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            String lastname =element.findElement(By.xpath("./td[2]")).getText();
            String firstname=element.findElement(By.xpath("./td[3]")).getText();
            String adress=element.findElement(By.xpath("./td[4]")).getText();
            //String [] phones =element.findElement(By.xpath("./td[6]")).getText().split("\n");
            String allMails=element.findElement(By.xpath("./td[5]")).getText();
            String allPhones=element.findElement(By.xpath("./td[6]")).getText();
            ContractData contract =new ContractData().withId(id).withFirstname(firstname).withLastname(lastname)
                     .withAddress(adress).withAllPhones(allPhones)
                     .withAllMails(allMails);
                   //.withPhonehome(phones[0]).withMobilephone(phones[1]).withWorkphone(phones[2]);
            contractCashe.add(contract);
        }
        return new Contracts(contractCashe);
    }

    public ContractData infoFromEditForm(ContractData contract) {
        initContractModificationById(contract.getId());
        String firstname=wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname=wd.findElement(By.name("lastname")).getAttribute("value");
        String address=wd.findElement(By.tagName("textarea")).getText();
        String home=wd.findElement(By.name("home")).getAttribute("value");
        String mobile=wd.findElement(By.name("mobile")).getAttribute("value");
        String work=wd.findElement(By.name("work")).getAttribute("value");
        String email1=wd.findElement(By.name("email")).getAttribute("value");
        String email12=wd.findElement(By.name("email2")).getAttribute("value");
        String email13=wd.findElement(By.name("email3")).getAttribute("value");
        wd.navigate().back();
        return new ContractData().withId(contract.getId()).withFirstname(firstname).withLastname(lastname)
                .withAddress(address).withPhonehome(home).withMobilephone(mobile).withWorkphone(work)
                .withEmail1(email1).withEmail2(email12).withEmail3(email13);
    }

    private void initContractModificationById(int id) {
        wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']",id))).click();
    }
}
