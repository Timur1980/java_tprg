package ru.xtim.prts.mantis.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by timur.khisamutdinov on 02.07.2017.
 */
@Entity
@Table(name = "mantis_user_table")
public class UserData {

    @Id
    @Column(columnDefinition = "INT(10)", name = "id")
    long id;

    @Column(name = "username")
    String username;

    @Column(name = "email")
    String email;


    public long getId() {
        return id;
    }


    public String getUsername() {
        return username;
    }


    public String getEmail() {
        return email;
    }

}
