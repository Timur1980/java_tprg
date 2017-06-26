package ru.xtim.prts.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.xtim.prts.addressbook.model.ContractData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by timur.khisamutdinov on 17.06.2017.
 */
public class ContractDataGenerator {


    @Parameter(names="-c",description = "Contracts count")
    private int count;

    @Parameter(names = "-f",description = "Target file")
    private String file;

    @Parameter(names="-d",description = "Data format")
    private String format;

    public static void main(String[] args) throws IOException {
        ContractDataGenerator generator=new ContractDataGenerator();
        JCommander jcommander= new JCommander(generator);
        try {
            jcommander.parse(args);
        }catch (ParameterException ex){
            ex.usage();
            return;
        }
        generator.run();
    }

    private void run() throws IOException {
        List<ContractData> contracts = generateContracts(count);
        if (format.equals("xml")){
            saveAsXml(contracts, new File(file));
        } else if (format.equals("json")){
            saveAsJson(contracts, new File(file));
        }
        else {
            System.out.println("Unrecognized format "+format);
        }
    }

    private void saveAsJson(List<ContractData>contracts, File file) throws IOException {
        Gson gson=new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json=gson.toJson(contracts);
        try(Writer writer = new FileWriter(file)){
            writer.write(json);
        }
    }

    private void saveAsXml(List<ContractData> contracts, File file) throws IOException {
        XStream xstream =new XStream();
        xstream.processAnnotations(ContractData.class);
        String xml=xstream.toXML(contracts);
        try(Writer writer = new FileWriter(file)) {
            writer.write(xml);
        }
    }

    private List<ContractData> generateContracts(int count) {
        List<ContractData> contracts = new ArrayList<ContractData>();
        for (int i = 0; i < count; i++) {
            contracts.add(new ContractData().withFirstname(String.format("Testname%s", i))
                    .withMiddlename(String.format("Testmiddle%s", i)).withLastname(String.format("Testlast%s", i))
                    .withNickname(String.format("Testnick%s",i)).withTitle(String.format("Testtitle%s",i)).withCompany(String.format("Testcompany%s",i))
                    .withAddress(String.format("Testaddress%s",i)).withPhonehome("999-99-99").withMobilephone("888-88-88").withWorkphone("777-777-77"));
        }
        return contracts;
    }


}
