package ru.xtim.prts.addressbook.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.xtim.prts.addressbook.model.ContractData;
import ru.xtim.prts.addressbook.model.Contracts;
import ru.xtim.prts.addressbook.model.GroupData;
import ru.xtim.prts.addressbook.model.Groups;

import java.util.List;

/**
 * Created by timur.khisamutdinov on 24.06.2017.
 */
public class DbHelper {

    private final SessionFactory sessionFactory;

    public DbHelper() {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }


    public Groups groups() {

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<GroupData> result = session.createQuery( "from GroupData" ).list();
        session.getTransaction().commit();
        session.close();
        return new Groups(result);
    }

    public Contracts contracts(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<ContractData> result = session.createQuery( "from ContractData where deprecated='0000-00-00'" ).list();
        session.getTransaction().commit();
        session.close();
        return new Contracts(result);
    }

}
