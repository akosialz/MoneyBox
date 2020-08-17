package com.fmpdroid.moneybox.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public @NoArgsConstructor class MoneyBoxDto {

    private @Getter @Setter String Title;
    private @Getter @Setter String Description;
    private @Getter @Setter float TargetAmount;
    private @Getter @Setter String TargetDate;
    private @Getter @Setter boolean AllowReminder;
    private @Getter @Setter String DateCreated;

}
