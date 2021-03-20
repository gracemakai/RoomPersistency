package com.grace.roompersistency.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.grace.roompersistency.R;
import com.grace.roompersistency.adapters.OutPutRecyclerAdapter;
import com.grace.roompersistency.database.AppDatabase;
import com.grace.roompersistency.database.dataentity.User;
import com.grace.roompersistency.viewModel.InputViewModel;
import com.grace.roompersistency.viewModel.InputViewModelFactory;

import java.util.ArrayList;
import java.util.List;

public class ReadFragment extends Fragment {

    View view;
    RecyclerView recyclerView;
    OutPutRecyclerAdapter outPutRecyclerAdapter;
    InputViewModel inputViewModel;
    ArrayList<User> userArrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_read, container, false);
        userArrayList = new ArrayList<>();
        outPutRecyclerAdapter = new OutPutRecyclerAdapter(getContext(), userArrayList);
        inputViewModel = ViewModelProviders.of(this,
                new InputViewModelFactory(getActivity().getApplication())).get(InputViewModel.class);

        initViews();

        return view;
    }

    private void initViews() {
        recyclerView = view.findViewById(R.id.recyclerView_output);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

     //   inputViewModel.getUsers();

        inputViewModel.getUsers().observe(this, users ->
                recyclerView.setAdapter(new OutPutRecyclerAdapter(getContext(), (ArrayList<User>) users)));
    }

    @Override
    public void onDestroy() {
        AppDatabase.destroyInstance();
        super.onDestroy();
    }
}