package ru.xtim.prts.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.xtim.prts.addressbook.model.ContractData;
import ru.xtim.prts.addressbook.model.Contracts;
import ru.xtim.prts.addressbook.model.GroupData;
import ru.xtim.prts.addressbook.model.Groups;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContractCreationTests extends TestBase {


    @BeforeMethod
    public void encurePreconditions(){
        if (app.db().groups().size()==0){
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test 1").withHeader("header 1").withFooter("footer 1"));
        }
        app.goTo().homePage();
    }


    @DataProvider
    public Iterator<Object[]> validContractsFromXml() throws IOException {
        try(BufferedReader reader = new BufferedReader(new FileReader(new File(app.properties().getProperty("web.resourceContractsXml"))))){
            String xml="";
            String line=reader.readLine();
            while(line!=null){
                xml+=line;
                line=reader.readLine();
            }
            XStream xstream=new XStream();
            xstream.processAnnotations(ContractData.class);
            List<ContractData> contracts=(List<ContractData>) xstream.fromXML(xml);
            return contracts.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
        }

    }


    @DataProvider
    public Iterator<Object[]> validContractsFromJson() throws IOException {
        try(BufferedReader reader = new BufferedReader(new FileReader(new File(app.properties().getProperty("web.resourceContractsJson"))))){
            String json="";
            String line=reader.readLine();
            while(line!=null){
                json+=line;
                line=reader.readLine();
            }
            Gson gson=new Gson();
            List<ContractData> contracs=gson.fromJson(json,new TypeToken<List<ContractData>>(){}.getType());
            return contracs.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
        }

    }


    @Test(dataProvider="validContractsFromJson")
    public void testContactCreation(ContractData contract) {
        //Contracts before =app.contract().all();
        Groups groups =app.db().groups();
        Contracts before =app.db().contracts();
        app.contract().create(contract.inGroup(groups.iterator().next()),true);
        app.goTo().homePage();
        assertThat(app.contract().count(),equalTo(before.size()+1));
        //Contracts after =app.contract().all();
        Contracts after = app.db().contracts();
        assertThat(after, equalTo(before.withAdded(contract.
                withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt()))));
    }

}
