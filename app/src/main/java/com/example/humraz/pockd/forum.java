package com.example.humraz.pockd;

/**
 * Created by humra on 8/16/2016.
 */
public class forum {
    private String name;
private String desc;
    private String pincode;
long stackId;
    private String c;
        public forum() {
      /*Blank default constructor essential for Firebase*/
        }
        public forum(String a)
        {

        }
        //Getters and setters
        public String getName() {
            return name;
        }
        public long getStackId() { return stackId; }
        public void setName(String name) {
            this.name = name;
        }

        public String getDesc() {
            return desc;
        }
    public String getPincode() {
        return pincode;
    }

        public void setDesc(String att) {
            this.desc = att;
        }
    public void setPincode(String att) {
        this.pincode = att;
    }
        @Override
        public String toString() {

            return name+"  "+desc;
        }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }
}