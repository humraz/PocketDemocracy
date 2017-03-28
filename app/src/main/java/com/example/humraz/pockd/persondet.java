package com.example.humraz.pockd;

import java.security.PrivateKey;

/**
 * Created by humra on 8/16/2016.
 */
public class persondet {
    private String fname;
private String lname;
    private String adharno;

    private String phonenumber;
    private String dob;
    private String pincode;
long stackId;

        public persondet() {
      /*Blank default constructor essential for Firebase*/
        }
        public persondet(String a)
        {

        }
        //Getters and setters

        public long getStackId() { return stackId; }


    public void setPincode(String att) {
        this.pincode = att;
    }


    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getAdharno() {
        return adharno;
    }

    public void setAdharno(String adharno) {
        this.adharno = adharno;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }
}