package eg.gov.iti.yummy.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName="UserData")
public class UserEntity {
    @PrimaryKey(autoGenerate = true)
    @NotNull
    int userId;
    String userName;
    String password;
    @ColumnInfo(defaultValue = "")
    String favourite;
    @ColumnInfo(defaultValue = "")
    String saturday;
    @ColumnInfo(defaultValue = "")
    String sunday;
    @ColumnInfo(defaultValue = "")
    String monday;
    @ColumnInfo(defaultValue = "")
    String tuesday;
    @ColumnInfo(defaultValue = "")
    String wednesday;
    @ColumnInfo(defaultValue = "")
    String thursday;
    @ColumnInfo(defaultValue = "")
    String friday;

    public UserEntity() {
    }

    public UserEntity(int userId, String userName, String password) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getFavourite() {return favourite;}

    public void setFavourite(String favourite) {
        this.favourite = favourite;
    }

    public String getSaturday() {
        return saturday;
    }

    public void setSaturday(String saturday) {
        this.saturday = saturday;
    }

    public String getSunday() {
        return sunday;
    }

    public void setSunday(String sunday) {
        this.sunday = sunday;
    }

    public String getMonday() {
        return monday;
    }

    public void setMonday(String monday) {
        this.monday = monday;
    }

    public String getTuesday() {
        return tuesday;
    }

    public void setTuesday(String tuesday) {
        this.tuesday = tuesday;
    }

    public String getWednesday() {
        return wednesday;
    }

    public void setWednesday(String wednesday) {
        this.wednesday = wednesday;
    }

    public String getThursday() {
        return thursday;
    }

    public void setThursday(String thursday) {
        this.thursday = thursday;
    }

    public String getFriday() {
        return friday;
    }

    public void setFriday(String friday) {
        this.friday = friday;
    }
}
