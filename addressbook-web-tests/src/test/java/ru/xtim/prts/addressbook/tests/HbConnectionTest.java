package ru.xtim.prts.addressbook.tests;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.xtim.prts.addressbook.model.ContractData;
import ru.xtim.prts.addressbook.model.GroupData;

import java.awt.*;
import java.util.List;

/**
 * Created by timur.khisamutdinov on 23.06.2017.
 */
@Test
public class HbConnectionTest {

    private SessionFactory sessionFactory;

    @BeforeClass
    protected void setUp() throws Exception {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            e.printStackTrace();
            // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
            // so destroy it manually.
            StandardServiceRegistryBuilder.destroy( registry );
        }
    }

    /*
    public void testHbConnection(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<GroupData> result = session.createQuery( "from GroupData" ).list();
        for ( GroupData group: result ) {
            System.out.println(group);
        }
        session.getTransaction().commit();
        session.close();
    }
    */

    public void testHbConnectionContact(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<ContractData> result = session.createQuery( "from ContractData where deprecated='0000-00-00'" ).list();
        session.getTransaction().commit();
        session.close();
        for ( ContractData contact: result ) {
            System.out.println(contact);
            System.out.println(contact.getGroups());
        }
    }
}
