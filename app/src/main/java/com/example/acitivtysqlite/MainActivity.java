package com.example.acitivtysqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper db;
    EditText elogin_username, elogin_password;
    Button loginbtn, singupbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DatabaseHelper(this);
        elogin_username = (EditText)findViewById(R.id.login_username);
        elogin_password = (EditText)findViewById(R.id.login_password);
        loginbtn = (Button)findViewById(R.id.login_btn);
        singupbtn = (Button)findViewById(R.id.signup_btn);

        singupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                startActivity(intent);
            }
        });

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = elogin_username.getText().toString();
                String password = elogin_password.getText().toString();

                Intent intent = new Intent(MainActivity.this,MainActivity3.class);

                Boolean chkusernamepassword = db.usernamepassword(username, password);

                if (chkusernamepassword){
                    Toast.makeText(getApplicationContext(), "Wrong email or Password", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplication(), "Successfully Login", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }
            }
        });
    }
}