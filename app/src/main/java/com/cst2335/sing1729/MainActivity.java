package com.cst2335.sing1729;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author Rantejbir Singh
 * @version jdk 1.7
 * @see android.app.Activity
 *this is main class
 */
public class MainActivity extends AppCompatActivity {
        /**
     * This is on create function
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lab5_activity);
        TextView text=findViewById(R.id.textView3);
        EditText password1 = findViewById(R.id.Password);
        Button loginButton = findViewById(R.id.button);
        loginButton.setOnClickListener(clk -> {
            String password = password1.getText().toString();
            if(!checkPasswordComplexity(password)){
                text.setText("You shall not pass");
            }
            else{
                text.setText("You meet the requirements");
            }
        });
    }
    /**
     * this function is checking for password complexicity
     * @param pw taking string password as a parameter
     * @return boolean value
     */
    Boolean checkPasswordComplexity(String pw){
        String toast_message;
        boolean foundUpperCase, foundLowerCase, foundNumber, foundSpecial, lengthValid;
        lengthValid= foundUpperCase = foundLowerCase = foundNumber = foundSpecial = false;
        if(pw.length()>=4 &&pw.length()<=20){
            lengthValid=true;
        }
        for (int i = 0; i < pw.length(); i++) {
            char c = pw.charAt(i);
            if (Character.isUpperCase(c)) {
                foundUpperCase = true;
            } else if (Character.isLowerCase(c)) {
                foundLowerCase = true;
            } else if (Character.isDigit(c)) {
                foundNumber = true;
            } else if (isSpecialCharacter(c)) {
                foundSpecial = true;
            }
        }
        if (!lengthValid) {
            Toast.makeText(this, R.string.toast_length, Toast.LENGTH_LONG).show();
            return false;
        }
        else if(!foundNumber) {
            Toast.makeText(this, R.string.toast_number, Toast.LENGTH_LONG).show();
            return false;
        }
        else if(!foundLowerCase) {
            Toast.makeText(this, R.string.toast_Lower, Toast.LENGTH_LONG).show();
            return false;
        }
        else if(!foundUpperCase) {
            Toast.makeText(this, R.string.toast_Upper, Toast.LENGTH_LONG).show();
            return false;
        }
        else if(!foundSpecial) {
            Toast.makeText(this, R.string.toast_Special, Toast.LENGTH_LONG).show();
            return false;
        }
        else
            return true;
    }
    /**
     *this function is checking special character
     * @param c taking as character
     * @return boolean value
     */
    boolean isSpecialCharacter(char c) {
        switch(c){
            case '#': case '$':case '&': case '%': case '^': case '*': case '!': case '@':case '?':
                return  true;
            default:
                return false;
        }
    }

}