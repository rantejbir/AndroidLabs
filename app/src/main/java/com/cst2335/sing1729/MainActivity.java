package com.cst2335.sing1729;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
public class MainActivity extends AppCompatActivity {
    private CheckBox checkBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lab3_activity_main);
//        getting view of text and button bt R class
        EditText emailEditText = findViewById(R.id.editTextTextEmailAddress);
        Button loginButton = findViewById(R.id.button);
//      adding a onclick listener method to perform action.
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//              passing email address to other activity.
                String email_address = emailEditText.getText().toString();
                Intent goToProfile = new Intent(MainActivity.this, ProfileActivity.class);
                goToProfile.putExtra("EMAIL", email_address);
                startActivity(goToProfile);
            }
        } );
    }
}