package com.example.labourmanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.labourmanagementsystem.databinding.ActivitySignupBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class SignupActivity extends AppCompatActivity {

    private ActivitySignupBinding binding;
    private String email="",password="";
    private FirebaseAuth auth;
    private String userId;
    EditText userName;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();
        auth=FirebaseAuth.getInstance();

        userName=findViewById(R.id.username);
        progressDialog= new ProgressDialog(this);
        progressDialog.setTitle("Please Wait");
        progressDialog.setMessage("Creating your Account");
        progressDialog.setCanceledOnTouchOutside(false);
        checkUser();
        binding.signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateData();
            }
        });
        binding.aha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignupActivity.this,LoginActivity.class));
                finish();
            }
        });
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

         else if(password.length()<6){
             //password length less than 6.
            binding.password.setError("password must be atleast 6 char long");

        }else if(userName.length()<1){
             userName.setError("you must provide your valid CNIC");
        }
         else {
             //data is valid now continue.
             firebaseSignup();
        }


    }

    private void firebaseSignup() {
        progressDialog.show();
        auth.createUserWithEmailAndPassword(email,password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        //signup suucess
                        //show progress dialog
                        progressDialog.dismiss();
                        // get user data
                        FirebaseUser user= auth.getCurrentUser();
                        String mail = user.getEmail();
                        //////////

                        FirebaseUser users= FirebaseAuth.getInstance().getCurrentUser();
                        userId=users.getUid();

                        final HashMap<String,Object> map= new HashMap<>();
                        map.put("Email",email);
                        map.put("Password",password);
                        map.put("CNIC",userName.getText().toString().trim());
                        FirebaseDatabase.getInstance().getReference().child("Users").child(userId).setValue(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {

                                    }
                                });

                        Toast.makeText(SignupActivity.this, "Account created\n"+email, Toast.LENGTH_SHORT).show();
                        //move to main activity
                        startActivity(new Intent(SignupActivity.this,MainActivity.class));
                        finish();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        progressDialog.dismiss();
                        //signup failed
                        Toast.makeText(SignupActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
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
}