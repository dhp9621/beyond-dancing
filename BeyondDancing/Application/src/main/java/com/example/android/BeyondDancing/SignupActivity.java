package com.example.android.BeyondDancing;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by jackp on 2018-07-15.
 */


public class SignupActivity extends Activity implements Observer {
    DanceModel DModel;
    Button SignupButton;
    Button HomeButton;


    EditText Emailentry;
    EditText UsernameSignup;
    EditText PasswordSignup;
    EditText PasswordRepeat;
    String newusernamestr;
    String newpasswordstr;
    String passwordrpstr;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set Content View
        DModel = DanceModel.getInstance();
        setContentView(R.layout.activity_signup);
        UsernameSignup = findViewById(R.id.newusername);
        PasswordSignup = findViewById(R.id.newpassword);
        PasswordRepeat = findViewById(R.id.passwordrepeat);
        SignupButton = findViewById(R.id.signupbutton);

        newusernamestr ="";
        newpasswordstr ="";
        passwordrpstr ="";

        SignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignUp();
            }
        });
        DModel.addObserver(this);
    }

    private void SignUp(){
        newusernamestr = UsernameSignup.getText().toString();
        newpasswordstr =PasswordSignup.getText().toString();
        passwordrpstr =PasswordSignup.getText().toString();


        if(checkValid()){
            if(true) {
                Toast.makeText(this, "Welcome: " + newusernamestr,
                        Toast.LENGTH_LONG).show();

                SignUpreset();
                Intent intent = new Intent( this,MainActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText( this, "signup failed!",
                        Toast.LENGTH_LONG).show();

            }

        }else{
            Toast.makeText( this, "invalid entry, retry!",
                    Toast.LENGTH_LONG).show();
        }
    }

    private void SignUpreset(){
        newusernamestr ="";
        newpasswordstr ="";
        passwordrpstr ="";
        UsernameSignup.setText("");
        PasswordSignup.setText("");
        PasswordRepeat.setText("");
    }
    private boolean checkValid(){
        boolean result = true;
        if (newusernamestr.isEmpty() || newusernamestr.length() <   5 || newusernamestr.length() > 15) {
            result = false;
            UsernameSignup.setError("between 5 and 15  characters");
        } else {
            UsernameSignup.setError(null);
        }

        if (newpasswordstr.isEmpty() || newpasswordstr.length() <  8 || newpasswordstr.length() > 16) {
            result = false;
            PasswordSignup.setError("between 8 and 16  characters");
        } else {
            PasswordSignup.setError(null);
        }
        if (!newpasswordstr.equals(passwordrpstr)){
            result = false;
            PasswordRepeat.setError("does not match");
        } else {
            PasswordRepeat.setError(null);
        }
        return result;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // Observer Methods
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////

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
