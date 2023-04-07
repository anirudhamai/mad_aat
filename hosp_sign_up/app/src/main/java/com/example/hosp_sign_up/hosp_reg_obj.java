package com.example.hosp_sign_up;

public class hosp_reg_obj {
    public String uname,name,email,ph,pass,lat,lon;
    public hosp_reg_obj() {
    }

    public hosp_reg_obj(String uname,String name, String email,String pass, String phone,String lat, String lon) {
        this.uname=uname;
        this.name = name;
        this.email = email;
        this.pass=pass;
        this.ph=phone;
        this.lat=lat;
        this.lon=lon;
    }
    public String getPass(){
        return this.pass;
    }
    public String getlat(){
        return this.lat;
    }
    public String getlon(){
        return this.lon;
    }
    public String getname(){
        return this.name;
    }



}
