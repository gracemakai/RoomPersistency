package com.grace.roompersistency.database;

import android.app.Application;
import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.grace.roompersistency.database.dao.UserDao;
import com.grace.roompersistency.database.dataentity.User;

@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public abstract UserDao userDao();

    public static AppDatabase getAppDatabase(Application application){
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(application,
                    AppDatabase.class, "user_database").build();
        }
        return INSTANCE;
    }

    public static void destroyInstance(){
        INSTANCE = null;
    }
}
