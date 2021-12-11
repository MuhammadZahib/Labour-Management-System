package com.example.labourmanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.labourmanagementsystem.databinding.ActivitySideBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class SideActivity extends AppCompatActivity {

    ActivitySideBinding binding;
    FirebaseAuth auth;
    String userID,email;
    EditText currentPassword,newPassword;
    TextView editProfile;
    Button update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       binding= ActivitySideBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        currentPassword=findViewById(R.id.current_password);
        newPassword=findViewById(R.id.nw_password);
        update=findViewById(R.id.updateCredentil);
        editProfile=findViewById(R.id.edit_prrofile);

        auth= FirebaseAuth.getInstance();
        checkUser();
//
//        binding.main.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//              //  startActivity(new Intent(SideActivity.this,MainActivity.class));
//            }
//        });

        //logged out

        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentPassword.setVisibility(View.VISIBLE);
                newPassword.setVisibility(View.VISIBLE);
                update.setVisibility(View.VISIBLE);
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Credetials();
            }
        });


        binding.logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
                checkUser();
            }
        });
    }

    private void Credetials() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

// Get auth credentials from the user for re-authentication. The example below shows
// email and password credentials but there are multiple possible providers,
// such as GoogleAuthProvider or FacebookAuthProvider.
        AuthCredential credential = EmailAuthProvider
                .getCredential(email, currentPassword.getText().toString().trim());

// Prompt the user to re-provide their sign-in credentials
        user.reauthenticate(credential)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            user.updatePassword(newPassword.getText().toString().trim()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(SideActivity.this,"Password change Successfully",Toast.LENGTH_LONG).show();
                                        newPassword.setText("");
                                        currentPassword.setText("");
                                        currentPassword.setVisibility(View.GONE);
                                        newPassword.setVisibility(View.GONE);
                                        update.setVisibility(View.GONE);
                                    } else {
                                        Toast.makeText(SideActivity.this,"Error password not changed",Toast.LENGTH_LONG).show();

                                    }
                                }
                            });
                        } else {
                            Toast.makeText(SideActivity.this,"Error authorization Failed",Toast.LENGTH_LONG).show();

                        }
                    }
                });
    }

    private void checkUser() {
        // check if user is not logged in then moved to the logged in activity.
        FirebaseUser user = auth.getCurrentUser();
        if(user!=null){

            // user logged in
             email=user.getEmail();
            // set to main email
            binding.mainEmail.setText(email);

            FirebaseUser users= FirebaseAuth.getInstance().getCurrentUser();
            userID=users.getUid();
          //  FirebaseDatabase.getInstance().getReference().child("Users").child()
//            //user not logged in
//            startActivity(new Intent(this,LoginActivity.class));
//            finish();

        }else{
            Intent intent = new Intent();
            intent.putExtra("check",true);
            setResult(100,intent);
           // startActivity(new Intent(SideActivity.this,LoginActivity.class));
            finish();

        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
