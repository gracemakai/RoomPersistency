package com.grace.roompersistency.viewModel;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.grace.roompersistency.database.DBWorker;
import com.grace.roompersistency.database.dataentity.User;

import java.util.List;

public class InputViewModel extends ViewModel {

    DBWorker dbWorker;
    LiveData<Integer> insertResult;
    LiveData<List<User>> allUsers;

    public InputViewModel(Application application) {
        dbWorker = new DBWorker(application);
        insertResult = dbWorker.getInsertResult();
    }

    public LiveData<Integer> getInsertResult() {
        return insertResult;
    }

    public LiveData<List<User>> getAllUsersResult() {
        return allUsers;
    }

    public void addUser(User user){
        dbWorker.addUser(user);
    }

    public LiveData<List<User>> getUsers(){
        return dbWorker.getAll();
    }
}
