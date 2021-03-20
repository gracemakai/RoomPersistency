package com.grace.roompersistency.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.grace.roompersistency.R;
import com.grace.roompersistency.database.AppDatabase;
import com.grace.roompersistency.database.DBWorker;
import com.grace.roompersistency.database.dataentity.User;
import com.grace.roompersistency.viewModel.InputViewModel;
import com.grace.roompersistency.viewModel.InputViewModelFactory;

public class InputFragment extends Fragment {

    View view;
    EditText firstName, secondName, age;
    Button saveBtn;

    InputViewModel inputViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_input, container, false);

        inputViewModel = ViewModelProviders.of(this,
                new InputViewModelFactory(getActivity().getApplication())).get(InputViewModel.class);
        initViews();

        return view;
    }

    private void initViews() {

        firstName = view.findViewById(R.id.firstName_input);
        secondName = view.findViewById(R.id.secondName_input);
        age = view.findViewById(R.id.age_input);
        saveBtn = view.findViewById(R.id.saveBtn);

        saveBtn.setOnClickListener(v -> {
            if (!firstName.getText().toString().isEmpty() &&
                    !secondName.getText().toString().isEmpty() &&
                    !age.getText().toString().isEmpty()) {
                saveData();
            }else{
                Toast.makeText(getContext(), "Fill All fields", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void saveData() {

        User user = new User(firstName.getText().toString(), secondName.getText().toString(),
                Integer.parseInt(age.getText().toString()));

        inputViewModel.getInsertResult().observe(this, integer -> {
            if (integer == 1){
                Toast.makeText(getContext(), "User saved", Toast.LENGTH_SHORT).show();

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.frag_container, new ReadFragment());
                fragmentTransaction.commit();
            } else {
                Toast.makeText(getContext(), "User not saved", Toast.LENGTH_SHORT).show();
            }
        });

        inputViewModel.addUser(user);

    }

    @Override
    public void onDestroy() {
        AppDatabase.destroyInstance();
        super.onDestroy();
    }
}