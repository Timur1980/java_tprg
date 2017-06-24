package ru.xtim.prts.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;

@XStreamAlias("contract")
@Entity
@Table(name = "addressbook")
public class ContractData {

    @XStreamOmitField
    @Id
    @Column(name="id")
    private int id=Integer.MAX_VALUE;

    @Expose
    @Column(name="firstname")
    private String firstname;

    @Expose
    @Column(name="middlename")
    private String middlename;

    @Expose
    @Column(name="lastname")
    private String lastname;

    @Expose
    @Column(name="nickname")
    private String nickname;

    @Expose
    @Column(name="title")
    private String title;

    @Expose
    @Column(name="company")
    private String company;

    @Expose
    @Column(name="address")
    @Type(type="text")
    private String address;

    @Expose
    @Column(name="home")
    @Type(type="text")
    private String phonehome;

    @Expose
    @Column(name="mobile")
    @Type(type="text")
    private String mobilephone;

    @Expose
    @Column(name="work")
    @Type(type="text")
    private String workphone;

    @Expose
    @Transient
    private String group;

    @Transient
    private String allPhones;

    @Column(name="email")
    @Type(type="text")
    private String email1;

    @Column(name="email2")
    @Type(type="text")
    private String email2;

    @Column(name="email3")
    @Type(type="text")
    private String email3;

    @Transient
    private String allMails;

    @Column(name="photo")
    @Type(type="text")
    private String photo;


    public int getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public String getLastname() {
        return lastname;
    }

    public String getNickname() {
        return nickname;
    }

    public String getTitle() {
        return title;
    }

    public String getCompany() {
        return company;
    }

    public String getAddress() {
        return address;
    }

    public String getPhonehome() {
        return phonehome;
    }

    public String getMobilephone() {
        return mobilephone;
    }

    public String getWorkphone() {
        return workphone;
    }

    public String getGroup() { return group; }

    public String getAllPhones() {
        return allPhones;
    }

    public String getEmail1() {
        return email1;
    }

    public String getEmail2() {
        return email2;
    }

    public String getEmail3() {
        return email3;
    }

    public String getAllMails() {
        return allMails;
    }

    public File getPhoto() {
        if (photo == null) {
            return null;
        } else {
            return new File(photo);
        }
    }

    public ContractData withId(int id) {
        this.id = id;
        return this;
    }

    public ContractData withFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public ContractData withMiddlename(String middlename) {
        this.middlename = middlename;
        return this;
    }

    public ContractData withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public ContractData withNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public ContractData withTitle(String title) {
        this.title = title;
        return this;
    }

    public ContractData withCompany(String company) {
        this.company = company;
        return this;
    }

    public ContractData withAddress(String address) {
        this.address = address;
        return this;
    }

    public ContractData withPhonehome(String phonehome) {
        this.phonehome = phonehome;
        return this;
    }

    public ContractData withMobilephone(String mobilephone) {
        this.mobilephone = mobilephone;
        return this;
    }

    public ContractData withWorkphone(String workphone) {
        this.workphone = workphone;
        return this;
    }

    public ContractData withGroup(String group) {
        this.group = group;
        return this;
    }

    public ContractData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public ContractData withEmail1(String email1) {
        this.email1 = email1;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContractData that = (ContractData) o;

        if (id != that.id) return false;
        if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
        if (middlename != null ? !middlename.equals(that.middlename) : that.middlename != null) return false;
        if (lastname != null ? !lastname.equals(that.lastname) : that.lastname != null) return false;
        if (nickname != null ? !nickname.equals(that.nickname) : that.nickname != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (company != null ? !company.equals(that.company) : that.company != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (phonehome != null ? !phonehome.equals(that.phonehome) : that.phonehome != null) return false;
        if (mobilephone != null ? !mobilephone.equals(that.mobilephone) : that.mobilephone != null) return false;
        return workphone != null ? workphone.equals(that.workphone) : that.workphone == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (middlename != null ? middlename.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (nickname != null ? nickname.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (company != null ? company.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (phonehome != null ? phonehome.hashCode() : 0);
        result = 31 * result + (mobilephone != null ? mobilephone.hashCode() : 0);
        result = 31 * result + (workphone != null ? workphone.hashCode() : 0);
        return result;
    }

    public ContractData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }

    public ContractData withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }


    public ContractData withAllMails(String allMails) {
        this.allMails = allMails;
        return this;
    }

    public ContractData withPhoto(File photo) {
        this.photo = photo.getPath();
        return this;
    }


    @Override
    public String toString() {
        return "ContractData{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", middlename='" + middlename + '\'' +
                '}';
    }

}
