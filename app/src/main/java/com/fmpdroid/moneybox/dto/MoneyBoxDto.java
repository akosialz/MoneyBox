package com.fmpdroid.moneybox.dto;

import android.os.Parcel;
import android.os.Parcelable;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor @Data
public class MoneyBoxDto implements Parcelable {

    private String name;
    private String description;
    private float remainingAmount;
    private float targetAmount;
    private String dateCreated;
    private String targetDate;
    private boolean allowReminder;

    protected MoneyBoxDto(Parcel in) {
        name = in.readString();
        description = in.readString();
        remainingAmount = in.readFloat();
        targetAmount = in.readFloat();
        dateCreated = in.readString();
        targetDate = in.readString();
        allowReminder = in.readByte() != 0;
    }

    public static final Creator<MoneyBoxDto> CREATOR = new Creator<MoneyBoxDto>() {
        @Override
        public MoneyBoxDto createFromParcel(Parcel in) {
            return new MoneyBoxDto(in);
        }

        @Override
        public MoneyBoxDto[] newArray(int size) {
            return new MoneyBoxDto[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(description);
        parcel.writeFloat(remainingAmount);
        parcel.writeFloat(targetAmount);
        parcel.writeString(dateCreated);
        parcel.writeString(targetDate);
        parcel.writeByte((byte) (allowReminder ? 1 : 0));
    }
}
