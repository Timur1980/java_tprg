package ru.xtim.prts.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.xtim.prts.addressbook.model.GroupData;
import ru.xtim.prts.addressbook.model.Groups;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by timur.khisamutdinov on 21.05.2017.
 */
public class GroupHelper  extends BaseHelper{

    public GroupHelper(WebDriver wd) {
        super(wd);
    }

    public void returnToGroupPage() {
        click(By.linkText("group page"));
    }

    public void submitGroupCreation() {
        click(By.name("submit"));
    }

    public void fillGroupForm(GroupData groupData) {
        type(By.name("group_name"), groupData.getName());
        type(By.name("group_header"), groupData.getHeader());
        type(By.name("group_footer"), groupData.getFooter());
    }

    public void initGroupCreation() {
        click(By.xpath("//div[@id='content']/form/input[4]"));
    }

    public void deleteSelectedGroups() {
        click(By.name("delete"));
    }

    public void selectGroupById(int id) {
        wd.findElement(By.cssSelector("input[value='"+id+"']")).click();
    }

    public void initGroupModification() {
        click(By.name("edit"));
    }

    public void submitGroupModification() {
        click(By.name("update"));
    }

    public void create(GroupData group) {
        initGroupCreation();
        fillGroupForm(group);
        submitGroupCreation();
        groupCashe=null;
        returnToGroupPage();
    }

    public void modify(GroupData group) {
        selectGroupById(group.getId());
        initGroupModification();
        fillGroupForm(group);
        submitGroupModification();
        groupCashe=null;
        returnToGroupPage();
    }


    public void delete(GroupData group) {
        selectGroupById(group.getId());
        deleteSelectedGroups();
        groupCashe=null;
        returnToGroupPage();
    }

    public boolean IsThereAGroup() {
        return isElementPresent(By.name("selected[]"));
    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }


    private Groups groupCashe=null;

    public Groups all() {
        if (groupCashe!=null) {
            return new Groups(groupCashe);
        }
        groupCashe = new Groups();
        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
        for (WebElement element : elements) {
            String name=element.getText();
            int id=Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            groupCashe.add(new GroupData().withId(id).withName(name));
        }
        return new Groups(groupCashe);
    }
}
