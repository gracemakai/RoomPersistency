package com.grace.roompersistency.database.dataentity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user")
public class User {

    @PrimaryKey(autoGenerate = true)
    private int uid;

    @ColumnInfo(name = "firstName")
    private String firstName;

    @ColumnInfo(name = "secondName")
    private String secondName;

    @ColumnInfo(name = "age")
    private int age;

    public User(String firstName, String secondName, int age) {
        this.age = age;
        this.firstName = firstName;
        this.secondName = secondName;
        this.age = age;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
