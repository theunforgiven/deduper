package com.validity.deduper;

import com.opencsv.bean.CsvBindByName;

import java.util.Objects;

public final class UserRecord {
    @CsvBindByName(required = true)
    private int id = 0;
    @CsvBindByName()
    private String first_name = "";
    @CsvBindByName()
    private String last_name = "";
    @CsvBindByName()
    private String company = "";
    @CsvBindByName()
    private String email = "";
    @CsvBindByName()
    private String address1 = "";
    @CsvBindByName()
    private String address2 = "";
    @CsvBindByName()
    private String zip = "";
    @CsvBindByName()
    private String city = "";
    @CsvBindByName()
    private String state_long = "";
    @CsvBindByName()
    private String state = "";
    @CsvBindByName()
    private String phone = "";

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState_long() {
        return state_long;
    }

    public void setState_long(String state_long) {
        this.state_long = state_long;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRecord that = (UserRecord) o;
        return id == that.id &&
                first_name.equals(that.first_name) &&
                last_name.equals(that.last_name) &&
                company.equals(that.company) &&
                email.equals(that.email) &&
                address1.equals(that.address1) &&
                address2.equals(that.address2) &&
                zip.equals(that.zip) &&
                city.equals(that.city) &&
                state_long.equals(that.state_long) &&
                state.equals(that.state) &&
                phone.equals(that.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, first_name, last_name, company, email, address1, address2, zip, city, state_long, state, phone);
    }

    @Override
    public String toString() {
        return "UserRecord{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", company='" + company + '\'' +
                ", email='" + email + '\'' +
                ", address1='" + address1 + '\'' +
                ", address2='" + address2 + '\'' +
                ", zip='" + zip + '\'' +
                ", city='" + city + '\'' +
                ", state_long='" + state_long + '\'' +
                ", state='" + state + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
