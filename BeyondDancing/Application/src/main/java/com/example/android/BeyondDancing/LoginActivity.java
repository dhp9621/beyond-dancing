package com.example.android.BeyondDancing;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Observable;
import java.util.Observer;




public class LoginActivity extends Activity implements Observer {
    DanceModel DModel;
    Button LoginButton;
    Button NewSignIn;
    EditText UsernameEntry;
    EditText PasswordEntry;

    String usernamestr;
    String passwordstr;
    //ProgressDIalog instead of progressbar, freeze screen so user don't back out during login in or signup
    //  final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this, R.style.Theme_AppCompat_DayNight_DarkActionBar);

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        DModel = DanceModel.getInstance();

        setContentView(R.layout.activity_login);

        //progressDialog.setIndeterminate(true);
        //progressDialog.setMessage("Logining in...");
        UsernameEntry =findViewById(R.id.usernameentry);
        PasswordEntry =findViewById(R.id.passwordentry);
        LoginButton =findViewById(R.id.loginbutton);
        NewSignIn =findViewById(R.id.newsignup);
        usernamestr = "";
        passwordstr = "";
                NewSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( v.getContext(),SignupActivity.class);
                startActivity(intent);
            }
        });
        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogIn();
            }
        });


        DModel.addObserver(this);



    }

    private void LogIn(){
        usernamestr = UsernameEntry.getText().toString();
        passwordstr = PasswordEntry.getText().toString();
        if(checkValid()){
            if(true) {
                Toast.makeText(this, "Welcome: " + usernamestr,
                        Toast.LENGTH_LONG).show();

                SignUpreset();
                Intent intent = new Intent( this,MainActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText( this, "Login failed!",
                        Toast.LENGTH_LONG).show();

            }

        }else{
            Toast.makeText( this, "invalid entry, retry!",
                    Toast.LENGTH_LONG).show();
        }
    }
    private void SignUpreset(){
        usernamestr ="";
        passwordstr ="";
        UsernameEntry.setText("");
        PasswordEntry.setText("");
    }


    private boolean checkValid(){
        boolean result = true;
        if (usernamestr.isEmpty() || usernamestr.length() <   5 || usernamestr.length() > 15) {
            result = false;
            UsernameEntry.setError("between 5 and 15  characters");
        } else {
            UsernameEntry.setError(null);
        }

        if (passwordstr.isEmpty() || passwordstr.length() <  8 || passwordstr.length() > 16) {
            result = false;
            PasswordEntry.setError("between 8 and 16  characters");
        } else {
            PasswordEntry.setError(null);
        }
        return result;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // Observer Methods
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    //
    @Override
    public void update(Observable o, Object arg) {

    }
    protected void onDestroy()
    {
        super.onDestroy();


        // Remove observer when activity is destroyed.
        DModel.deleteObserver(this);
    }
}
