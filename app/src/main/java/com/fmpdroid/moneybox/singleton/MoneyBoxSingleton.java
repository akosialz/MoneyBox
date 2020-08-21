package com.fmpdroid.moneybox.singleton;

import com.fmpdroid.moneybox.dto.MoneyBoxDto;

import java.util.ArrayList;
import java.util.List;

public class MoneyBoxSingleton {

    private List<MoneyBoxDto> moneyBoxList;

    private static MoneyBoxSingleton instance;

    private MoneyBoxSingleton(){

    }
    public static MoneyBoxSingleton getInstance() {
        if (instance == null) {
            instance = new MoneyBoxSingleton();
        }
        return instance;
    }

    public void addMoneyBox(MoneyBoxDto moneybox) {
        if (moneyBoxList == null){
            moneyBoxList = new ArrayList<>();
        }
        moneyBoxList.add(moneybox);
    }

    public java.util.List<MoneyBoxDto> getMoneyBoxList(){
        return moneyBoxList;
    }
}
