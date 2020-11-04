package com.example.acitivtysqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {
    EditText eName, eAddress, eStatus, eUsername, ePassword, eConfirm_pass;
    RadioGroup gender;
    Button registerbtn, loginbtn;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        db = new DatabaseHelper(this);
        eName = (EditText)findViewById(R.id.name);
        eAddress = (EditText)findViewById(R.id.address);
        gender = findViewById(R.id.gender);
        eStatus = (EditText)findViewById(R.id.status);
        eUsername = (EditText)findViewById(R.id.username);
        ePassword = (EditText)findViewById(R.id.password);
        eConfirm_pass = (EditText)findViewById(R.id.cpassword);
        registerbtn = (Button)findViewById(R.id.register);
        loginbtn = (Button)findViewById(R.id.login);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this,MainActivity.class);
                startActivity(intent);
            }
        });

        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name1 = eName.getText().toString();
                String address1 = eAddress.getText().toString();
                String status1 = eStatus.getText().toString();
                String username1 = eUsername.getText().toString();
                String password1 = ePassword.getText().toString();
                String password2 = eConfirm_pass.getText().toString();

                RadioButton chkbtn = findViewById(gender.getCheckedRadioButtonId());
                String gender1 = chkbtn.getText().toString();

                if (name1.equals("")||address1.equals("")||gender1.equals("")||status1.equals("")||username1.equals("")||password1.equals("")||password2.equals("")){
                    if (name1.equals("")){
                        Toast.makeText(getApplicationContext(),"Name Field is empty",Toast.LENGTH_SHORT).show();
                    }
                    else if (address1.equals("")){
                        Toast.makeText(getApplicationContext(),"Address Field is empty",Toast.LENGTH_SHORT).show();
                    }
                    else if (gender1.equals("")){
                        Toast.makeText(getApplicationContext(),"Gender Field is empty",Toast.LENGTH_SHORT).show();
                    }
                    else if (status1.equals("")){
                        Toast.makeText(getApplicationContext(),"Status Field is empty",Toast.LENGTH_SHORT).show();
                    }
                    else if (username1.equals("")){
                        Toast.makeText(getApplicationContext(),"Username Field is empty",Toast.LENGTH_SHORT).show();
                    }
                    else if (password1.equals("")){
                        Toast.makeText(getApplicationContext(),"Password Field is empty",Toast.LENGTH_SHORT).show();
                    }
                    else if (password2.equals("")){
                        Toast.makeText(getApplicationContext(),"Confirm Password",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Fields are empty",Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    if (password1.equals(password2)){
                        Boolean chkusername = db.chkusername(username1);
                        if (chkusername==true){
                            Boolean insert = db.insert(name1,address1,gender1,status1,username1,password1);
                            if(insert==true){
                                Toast.makeText(getApplicationContext(),"Registered Successfully", Toast.LENGTH_SHORT).show();
                                eName.setText("");
                                eAddress.setText("");
                                eStatus.setText("");
                                ePassword.setText("");
                                eUsername.setText("");
                                eConfirm_pass.setText("");
                            }
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Email Already Exists", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Password do not match", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
    }
}