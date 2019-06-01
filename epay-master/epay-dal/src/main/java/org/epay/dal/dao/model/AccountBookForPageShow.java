package org.epay.dal.dao.model;

import java.io.Serializable;

public class AccountBookForPageShow implements Serializable {

    private String account_book_id;

    private String mch_id;

    private String items_id;

    //new add
    private String items_name;
    private String items_filename;
    private String upload_date;
    private String affect_date;
    private String expiry_date;

    private String user_id;

    private String user_name;

    private String currency;

    // update to String @2019年5月22日10:34:00
    private String items_money;

    private String pay_time;

    private Byte pay_status;

    private String reserve1;

    private String reserve2;

    private String reserve3;

    private String reserve4;

    private String reserve5;

    public String getAffect_date() {
        return affect_date;
    }

    public void setAffect_date(String affect_date) {
        this.affect_date = affect_date;
    }

    public String getExpiry_date() {
        return expiry_date;
    }

    public void setExpiry_date(String expiry_date) {
        this.expiry_date = expiry_date;
    }

    public String getAccount_book_id() {
        return account_book_id;
    }

    public void setAccount_book_id(String account_book_id) {
        this.account_book_id = account_book_id;
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getItems_id() {
        return items_id;
    }

    public void setItems_id(String items_id) {
        this.items_id = items_id;
    }

    public String getItems_name() {
        return items_name;
    }

    public void setItems_name(String items_name) {
        this.items_name = items_name;
    }

    public String getItems_filename() {
        return items_filename;
    }

    public void setItems_filename(String items_filename) {
        this.items_filename = items_filename;
    }

    public String getUpload_date() {
        return upload_date;
    }

    public void setUpload_date(String upload_date) {
        this.upload_date = upload_date;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getItems_money() {
        return items_money;
    }

    public void setItems_money(String items_money) {
        this.items_money = items_money;
    }

    public String getPay_time() {
        return pay_time;
    }

    public void setPay_time(String pay_time) {
        this.pay_time = pay_time;
    }

    public Byte getPay_status() {
        return pay_status;
    }

    public void setPay_status(Byte pay_status) {
        this.pay_status = pay_status;
    }

    public String getReserve1() {
        return reserve1;
    }

    public void setReserve1(String reserve1) {
        this.reserve1 = reserve1;
    }

    public String getReserve2() {
        return reserve2;
    }

    public void setReserve2(String reserve2) {
        this.reserve2 = reserve2;
    }

    public String getReserve3() {
        return reserve3;
    }

    public void setReserve3(String reserve3) {
        this.reserve3 = reserve3;
    }

    public String getReserve4() {
        return reserve4;
    }

    public void setReserve4(String reserve4) {
        this.reserve4 = reserve4;
    }

    public String getReserve5() {
        return reserve5;
    }

    public void setReserve5(String reserve5) {
        this.reserve5 = reserve5;
    }
}