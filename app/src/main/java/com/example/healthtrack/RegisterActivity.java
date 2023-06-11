package com.example.healthtrack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    EditText edUsername,edEmail, edPassword, edConfirm;
    Button btn;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        edUsername=findViewById(R.id.editTextRegUsername);
        edEmail=findViewById(R.id.editTextRegEmail);
        edPassword=findViewById(R.id.editTextRegPassword);
        edConfirm=findViewById(R.id.editTextRegConfirmPassword);
        btn=findViewById(R.id.buttonRegister);
        tv=findViewById(R.id.textViewExistingUser);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username=edUsername.getText().toString();
                String email=edEmail.getText().toString();
                String password=edPassword.getText().toString();
                String confirm=edConfirm.getText().toString();
                Database db=new Database(getApplicationContext(),"healthtrack",null, 1);
                if(username.length()==0 || email.length()==0 || password.length()==0||confirm.length()==0){
                    Toast.makeText(getApplicationContext(),"Please fill the above details completely",Toast.LENGTH_SHORT).show();
                }
                else {
                    if (password.compareTo(confirm) == 0) {
                        if (isValid(password)) {
                            db.register(username,email,password);
                            Toast.makeText(getApplicationContext(), "Registration Inserted", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                        } else {
                            Toast.makeText(getApplicationContext(), "Password must contain at least 8 characters, having letter, digit, and special character", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Password and Confirm password didn't match!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
    }
    public static boolean isValid(String passwordHere){
        int f1=0, f2=0, f3=0;
        if(passwordHere.length()<8){
            return false;
        }
        else{
            for(int p=0;p<passwordHere.length();p++){
                if(Character.isLetter(passwordHere.charAt(p))){
                    f1=1;
                }
            }
            for(int p=0;p<passwordHere.length();p++){
                if(Character.isDigit(passwordHere.charAt(p))){
                    f2=1;
                }
            }
            for(int p=0;p<passwordHere.length();p++){
                char c=passwordHere.charAt(p);
                if(c>=33&&c<=46||c==64){
                    f3=1;
                }
            }
            if(f1==1 && f2==1 && f3==1){
                return true;
            }
            return false;
        }
    }
}

