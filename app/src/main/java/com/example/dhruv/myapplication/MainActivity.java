package com.example.dhruv.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private EditText Name;
    private EditText Password;
    private TextView Info;
    private Button Login;
    private TextView Register;
    private int counter = 5;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    private TextView forgotPassword;
private String UserName, UserPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Name = (EditText) findViewById(R.id.etName);
        Password = (EditText) findViewById(R.id.etPassword);
        Info = (TextView) findViewById(R.id.tvInfo);
        Login = (Button) findViewById(R.id.btnLogin);
        Register = (TextView) findViewById(R.id.tvRegister);
        Info.setText("No.of attempts reamaining: 5");
        forgotPassword = (TextView) findViewById(R.id.tvForgotPassword);

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        FirebaseUser user = firebaseAuth.getCurrentUser();

        if (user != null)
            //finish();
            startActivity(new Intent(MainActivity.this, SecondActivity.class));
        //if the user has already logged in then the second activity will be opened

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(Name.getText().toString().trim(), Password.getText().toString().trim());
            }
        });

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RegistrationActivity.class));

                //Intent intent = new Intent(MainActivity.this, RegistrationActivity.class);
                //intent.addFlags(intent.FLAG_ACTIVITY_NEW_TASK | intent.FLAG_ACTIVITY_CLEAR_TASK);
                //startActivity(intent);
                //finish();

            }
        });
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, PasswordActivity.class
                ));
            }
        });

    }

    private void validate(String userName, String userPassword) {
        UserName = Name.getText().toString();
        UserPassword = Password.getText().toString();
        progressDialog.setMessage("It may take a few seconds, please wait patiently!");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(userName, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    progressDialog.dismiss();
                    Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    //checkEmailVerification();
                } else {
                    Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                    counter--;
                    Info.setText("No.of attempts remaining:" + counter);
                    progressDialog.dismiss();
                    if (counter == 0) {
                        Login.setEnabled(false);
                    }
                }
            }
        });
    }

    //private void checkEmailVerification(){
        //FirebaseUser firebaseUser = firebaseAuth.getInstance().getCurrentUser();
        //Boolean emailflag = firebaseUser.isEmailVerified();
        //firebaseAuth.signInWithEmailAndPassword(UserName,UserPassword);
        //if(emailflag){
            //finish();
            //startActivity(new Intent(MainActivity.this, SecondActivity.class));
        //}else{
            //Toast.makeText(this,"Verify your email", Toast.LENGTH_SHORT).show();
            //firebaseAuth.signOut();
        //}
    //}

}