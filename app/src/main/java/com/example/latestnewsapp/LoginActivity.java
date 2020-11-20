package com.example.latestnewsapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.latestnewsapp.database.DbAccessObj;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName() ;
    public static final String MYPREFS = "myprefs";
    public static final String NAMEKEY = "namekey";
    public static final String PWDKEY = "pwdkey";

    DbAccessObj dbAccessObj;
    EditText nameEditText,pwdEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        Log.i(TAG,"onCreate");
        nameEditText =  findViewById(R.id.editTextName);
        pwdEditText = findViewById(R.id.editTextPwd);

        dbAccessObj = new DbAccessObj(this);
        dbAccessObj.openDb();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG,"onStart");
    }


    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG,"onstop");

    }

    public void clickHandler(View view) {
        Log.e(TAG,"clickHandler");

        switch (view.getId()){
            case R.id.buttonlogin:
                getCredentials();
                break;
            case R.id.buttonput:
                String title = nameEditText.getText().toString();
                String subtitle = pwdEditText.getText().toString();
                if(!title.isEmpty()) {
                    dbAccessObj.createRow(title, subtitle);
                }
                else{
                    Toast.makeText(this, "Kindly Enter Username & Password", Toast.LENGTH_SHORT).show();
                }
                break;

        }
    }

    private void startHome() {
        Intent hIntent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(hIntent);
    }

    private void getCredentials() {

        String userentry = nameEditText.getText().toString();
        String username = dbAccessObj.uservalid(userentry);
        if(!userentry.isEmpty()){


        if(userentry.equals(username)){
            String pwd = dbAccessObj.query(userentry);
            String userpwd = pwdEditText.getText().toString();
            if(pwd.equals(userpwd)){
                startHome();
            }
            else{
                Toast.makeText(this, "Invalid Password", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(this, "Invalid Username Kindly register", Toast.LENGTH_SHORT).show();
        }

        }
        else{
            Toast.makeText(this, "Kindly Enter Username & Password", Toast.LENGTH_SHORT).show();
        }

    }
    public void handleDb(View view) {
        Log.i(TAG,"on clicking check");

        //get the data from the edittext
        String name = nameEditText.getText().toString();
        String pwd = pwdEditText.getText().toString();
        //create a file names myprefs
        SharedPreferences preferences = getSharedPreferences(MYPREFS,MODE_PRIVATE);
        //open the file
        SharedPreferences.Editor editor = preferences.edit();
        //write to the file
        editor.putString(NAMEKEY,name);
        editor.putString(PWDKEY,pwd);
        //save the file
        editor.apply();
    }




}

