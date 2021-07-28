package com.example.androidproject.UI.Auth;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.androidproject.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class login extends Fragment {
    private LoginViewModel loginViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        View root = inflater.inflate(R.layout.fragment_login, container, false);

        EditText email = root.findViewById(R.id.login_email);
        EditText password = root.findViewById(R.id.login_password);
        Button btn = root.findViewById(R.id.login_btn);
        ProgressBar progressBar = root.findViewById(R.id.login_progressBar);
        btn.setOnClickListener(v -> {
            btn.setEnabled(false);
            progressBar.setVisibility(View.VISIBLE);
            loginViewModel.signIn(
                    email.getText().toString(),
                    password.getText().toString(),
                    mail -> {
                        Snackbar.make(root, "email("+mail+") is good", 5 * 1000).show();
                        progressBar.setVisibility(View.GONE);
                        btn.setEnabled(true);
                        Navigation.findNavController(v).navigate(R.id.action_login_to_appActivity);
                    },
                    mail -> {
                        Snackbar.make(root, "invalid parameters (email or password)", 5 * 1000).show();
                        progressBar.setVisibility(View.GONE);
                        btn.setEnabled(true);
                    }
            );
        });

        return root;
    }
}