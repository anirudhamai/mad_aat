package com.example.hosp_sign_up;

public class bookings {
    public String emergency;
    public double eta;


    public bookings() {
    }

    public bookings(double ETA,String emr_type) {
        this.eta=ETA;
        this.emergency=emr_type;
    }
    public String geteta(){
        return String.valueOf(this.eta);
    }
    public String getemr(){
        return this.emergency;
    }

}
