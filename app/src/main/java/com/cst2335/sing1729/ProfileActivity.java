package com.cst2335.sing1729;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
public class ProfileActivity extends AppCompatActivity {
    private ActivityResultLauncher<Intent> myPictureTakerLauncher;
    private static final String LOGCAT_TAG = "ProfileActivity";
    private EditText name;
    private EditText address;
    private TextView emailEditText;
    private Button saveButton;
    private Button ClearButton;
    public static final String SHARED = "sharedPrefs";
    public String NAME = "text";
    public String ADDRESS = "Address";



    @Override
    protected void onStart() {
        super.onStart();
        Log.e(LOGCAT_TAG, "Activity's onStart method");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(LOGCAT_TAG, "Activity's onResume method");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(LOGCAT_TAG, "Activity's onPause method");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(LOGCAT_TAG, "Activity's onStop method");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(LOGCAT_TAG, "Activity's onDestroy method");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e(LOGCAT_TAG, "onActivityResult method");
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_activity);

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        name = findViewById(R.id.Name);
        address = findViewById(R.id.Address);
        emailEditText = findViewById(R.id.emailAddress);
        saveButton = findViewById(R.id.button2);
        ClearButton = findViewById(R.id.button3);

        Intent intent = getIntent();
        String email = intent.getStringExtra("EMAIL");
        emailEditText.setText(email);

        String name1 = sharedPreferences.getString(NAME, "");
        String address1 = sharedPreferences.getString(ADDRESS, "");

        name.setText(name1);
        address.setText(address1);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                editor.putString(NAME, name.getText().toString());
                editor.putString(ADDRESS, address.getText().toString());

                editor.apply();
            }
        });

        ClearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.clear();
                editor.commit();

                name.setText("");
                address.setText("");

            }
        });
    }
}