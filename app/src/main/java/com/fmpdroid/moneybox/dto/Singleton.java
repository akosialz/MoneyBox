package com.fmpdroid.moneybox.dto;

import java.util.ArrayList;
import java.util.List;

public class Singleton {

    private List<MoneyBox> moneyBoxList;

    private static Singleton instance;

    private Singleton(){

    }
    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    public void addMoneyBox(MoneyBox moneybox) {
        if (moneyBoxList == null){
            moneyBoxList = new ArrayList<>();
        }
        moneyBoxList.add(moneybox);
    }

    public List<MoneyBox> getMoneyBoxList(){
        return moneyBoxList;
    }
}
