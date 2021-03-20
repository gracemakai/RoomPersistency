package com.grace.roompersistency.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.grace.roompersistency.database.dataentity.User;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("SELECT * FROM user WHERE firstName LIKE :firstName AND secondName LIKE :secondName ")
    User findByName(String firstName, String secondName);

    @Query("SELECT COUNT(*) from user")
    int countUsers();

    @Insert
    void insertAll(User user);

    @Delete
    void delete(User user);
}
