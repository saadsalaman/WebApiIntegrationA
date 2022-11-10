package com.example.webapiintegration;
public class MyContact {
    String name,phno,address;

    public MyContact(String name, String phno, String address) {
        this.name = name;
        this.phno = phno;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhno() {
        return phno;
    }

    public void setPhno(String phno) {
        this.phno = phno;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
