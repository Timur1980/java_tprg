package ru.xtim.prts.addressbook.model;

public class ContractData {
    private int id;
    private final String firstname;
    private final String middlename;
    private final String lastname;
    private final String nickname;
    private final String title;
    private final String company;
    private final String address;
    private final String phonehome;
    private final String mobilephone;
    private final String workphone;
    private String group;




    public ContractData(String firstname, String middlename, String lastname, String nickname, String title, String company, String address, String phonehome, String mobilephone, String workphone, String group) {
        this.id=0;
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.nickname = nickname;
        this.title = title;
        this.company = company;
        this.address = address;
        this.phonehome = phonehome;
        this.mobilephone = mobilephone;
        this.workphone = workphone;
        this.group = group;
    }



    public ContractData(int id, String firstname, String middlename, String lastname, String nickname, String title, String company, String address, String phonehome, String mobilephone, String workphone, String group) {
        this.id=id;
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.nickname = nickname;
        this.title = title;
        this.company = company;
        this.address = address;
        this.phonehome = phonehome;
        this.mobilephone = mobilephone;
        this.workphone = workphone;
        this.group = group;
    }

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


    public void setId(int id) {
        this.id = id;
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
