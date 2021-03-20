package com.grace.roompersistency.database;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.grace.roompersistency.database.dataentity.User;

import java.util.List;

public class DBWorker {

    private static final String TAG = DBWorker.class.getSimpleName();
    private MutableLiveData<Integer> insertResult = new MutableLiveData<>();
    private MutableLiveData<List<User>> allUsers = new MutableLiveData<List<User>>();
    private final AppDatabase database;

    public DBWorker(Application application) {
        this.database = AppDatabase.getAppDatabase(application);
    }

    public MutableLiveData<Integer> getInsertResult() {
        return insertResult;
    }

    public MutableLiveData<List<User>> getAllUsers() {
        return allUsers;
    }

    public void addUser(User user){
        new Thread(){
            @Override
            public void run() {
                super.run();
                 try {
                     database.userDao().insertAll(user);
                     insertResult.postValue(1);
                 } catch (Exception e){
                     Log.e(TAG, "run: ", e);
                     insertResult.postValue(0);
                 }
            }
        }.start();

    }

    public int dbSize(){
        return database.userDao().countUsers();
    }

    public  void delete(User user){
        database.userDao().delete(user);
    }

    public User findUser(String firstName, String secondName){
        return database.userDao().findByName(firstName, secondName);
    }

    public MutableLiveData<List<User>> getAll(){

        new Thread(){
            @Override
            public void run() {
                super.run();

                try {
                    allUsers.postValue(database.userDao().getAll());
                } catch (Exception e){
                    Log.e(TAG, "run: ", e);
                }
            }
        }.start();

        return allUsers;

    }
}
