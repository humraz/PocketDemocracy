package com.example.humraz.pockd;

/**
 * Created by humra on 8/16/2016.
 */
public class notif {
    private String notif;
    private String msg;

long stackId;

        public notif() {
      /*Blank default constructor essential for Firebase*/
        }
        public notif(String a)
        {

        }
        //Getters and setters

        public long getStackId() { return stackId; }


    public String getNotif() {
        return notif;
    }

    public void setNotif(String notif) {
        this.notif = notif;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}