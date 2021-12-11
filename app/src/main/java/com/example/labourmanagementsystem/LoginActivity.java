package com.example.labourmanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.example.labourmanagementsystem.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private String email="",password="";
    private ProgressDialog progressDialog;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        auth= FirebaseAuth.getInstance();
        getSupportActionBar().hide();


        progressDialog= new ProgressDialog(this);
        progressDialog.setTitle("Please Wait");
        progressDialog.setMessage("Logging In");
        progressDialog.setCanceledOnTouchOutside(false);

        checkUser();

        binding.caa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,SignupActivity.class));
                finish();
            }
        });

        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateData();
            }
        });
    }

    private void checkUser() {
        //check if user is already logged in
        //if already logged in then open main activity
        FirebaseUser user = auth.getCurrentUser();
        if(user !=null){
            //user is already logged in
            startActivity(new Intent(this,MainActivity.class));
            finish();

        }
    }

    private void validateData() {
        //get data

        email=binding.email.getText().toString().trim();
        password=binding.password.getText().toString().trim();

        //validate data
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            //email format is invalid dont proceed furthur.
            binding.email.setError("Invalid Email Format");
        }
        else if (TextUtils.isEmpty(password)){
            //no password enter
            binding.password.setError("Enter Password");}

        
        else {
            //data is valid now continue.
            firebaseLogin();
        }
    }

    private void firebaseLogin() {
        progressDialog.show();

        auth.signInWithEmailAndPassword(email,password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        FirebaseUser user= auth.getCurrentUser();
                        String mail = user.getEmail();
                        Toast.makeText(LoginActivity.this, "Logged In\n"+email, Toast.LENGTH_SHORT).show();
                        //move to main activity
                        startActivity(new Intent(LoginActivity.this,MainActivity.class));
                        finish();


                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // login failed
                        Toast.makeText(LoginActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}