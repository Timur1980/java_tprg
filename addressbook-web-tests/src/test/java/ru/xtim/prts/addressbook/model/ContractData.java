package ru.xtim.prts.addressbook.model;

public class ContractData {
    private int id=Integer.MAX_VALUE;
    private String firstname;
    private String middlename;
    private String lastname;
    private String nickname;
    private String title;
    private String company;
    private String address;
    private String phonehome;
    private String mobilephone;
    private String workphone;
    private String group;
    private String allPhones;
    private String email1;
    private String email2;
    private String email3;
    private String allMails;


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

    @Override
    public String toString() {
        return "ContractData{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContractData that = (ContractData) o;

        if (id != that.id) return false;
        if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
        return lastname != null ? lastname.equals(that.lastname) : that.lastname == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        return result;
    }
}
