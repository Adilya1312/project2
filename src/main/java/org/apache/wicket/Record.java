package org.apache.wicket;

import java.io.Serializable;
import java.util.Date;

public class Record implements Serializable {
    private int id;
    private String date;
    private int days;
    private String amountPerMonth;
    private String percentPerMonth;
    private String left;
    private String totalPerMonth;

    Record(int id, String date, int days,String amountPerMonth, String percentPerMonth, String left, String totalPerMonth){

        this.id=id;
        this.date=date;
        this.days=days;
        this.amountPerMonth=amountPerMonth;
        this.percentPerMonth=percentPerMonth;
        this.left=left;
        this.totalPerMonth=totalPerMonth;

    }


    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public int getDays() {
        return days;
    }

    public String getAmountPerMonth() {
        return amountPerMonth;
    }

    public String getPercentPerMonth() {
        return percentPerMonth;
    }

    public String getLeft() {
        return left;
    }

    public String getTotalPerMonth() {
        return totalPerMonth;
    }
}
