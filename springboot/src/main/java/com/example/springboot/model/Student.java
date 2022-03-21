package com.example.springboot.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "student")
public class Student {

    @Transient
    public static final String SEQUENCE_NAME = "user_sequence";

    @Id
    private int id;
    private String name;
    private String email;
    private String rollno;
    private String mobile;
    private String aadhar;
    private String address;
    private String pincode;
    private String gender;

    public Student() {
        super();
    }

    public Student(int id, String name, String email, String rollno, String mobile, String aadhar, String address,
            String pincode, String gender) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.rollno = rollno;
        this.mobile = mobile;
        this.aadhar = aadhar;
        this.address = address;
        this.pincode = pincode;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRollno() {
        return rollno;
    }

    public void setRollno(String rollno) {
        this.rollno = rollno;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAadhar() {
        return aadhar;
    }

    public void setAadhar(String aadhar) {
        this.aadhar = aadhar;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Student [aadhar=" + aadhar + ", address=" + address + ", email=" + email + ", gender=" + gender
                + ", id=" + id + ", mobile=" + mobile + ", name=" + name + ", pincode=" + pincode + ", rollno=" + rollno
                + "]";
    }

}
