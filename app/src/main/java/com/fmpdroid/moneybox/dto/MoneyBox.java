package com.fmpdroid.moneybox.dto;

public class MoneyBox{

    private String Title;
    private String Description;
    private float TargetAmount;
    private String TargetDate;
    private boolean AllowReminder;
    private String DateCreated;

    public MoneyBox (){

    }
    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public float getTargetAmount() {
        return TargetAmount;
    }

    public void setTargetAmount(float targetAmount) {
        TargetAmount = targetAmount;
    }

    public String getTargetDate() {
        return TargetDate;
    }

    public void setTargetDate(String targetDate) {
        TargetDate = targetDate;
    }

    public boolean isAllowReminder() {
        return AllowReminder;
    }

    public void setAllowReminder(boolean allowReminder) {
        AllowReminder = allowReminder;
    }

    public String getDateCreated() {
        return DateCreated;
    }

    public void setDateCreated(String dateCreated) {
        DateCreated = dateCreated;
    }

}
