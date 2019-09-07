package com.example.hp.electrician.pojo;

public class UserPojo {

    private String name;
    private String mobile;
    private  String emailreg;
    private String passwordreg;
    private String cpassword;
    private String imageurl;

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String id;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmailreg() {
        return emailreg;
    }

    public void setEmailreg(String emailreg) {
        this.emailreg = emailreg;
    }

    public String getPasswordreg() {
        return passwordreg;
    }

    public void setPasswordreg(String passwordreg) {
        this.passwordreg = passwordreg;
    }

    public String getCpassword() {
        return cpassword;
    }

    public void setCpassword(String cpassword) {
        this.cpassword = cpassword;
    }

}
