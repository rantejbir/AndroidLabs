package com.cst2335.sing1729;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
public class ProfileActivity extends AppCompatActivity {
    private ActivityResultLauncher<Intent> myPictureTakerLauncher;
    private static final String LOGCAT_TAG = "ProfileActivity";
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


//        getting email printed at profile activity
        EditText emailEditText = findViewById(R.id.emailAddress);
//      using intent class to send email to other page



        Intent intent = getIntent();
        String email = intent.getStringExtra("EMAIL");
        emailEditText.setText(email);
        Button takePictureButton = findViewById(R.id.button2);



        takePictureButton.setOnClickListener(v -> dispatchTakePictureIntent());
        myPictureTakerLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        ImageView imgView = findViewById(R.id.imageView);
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent data = result.getData();
                            assert data != null;
                            Bitmap imgBitmap = (Bitmap) data.getExtras().get("data");
                            imgView.setImageBitmap(imgBitmap);
                        } else if (result.getResultCode() == Activity.RESULT_CANCELED) {
                            Log.i(LOGCAT_TAG, "User refused to capture a picture.");
                        }
                    }
                }
        );
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            myPictureTakerLauncher.launch(takePictureIntent);
        }
    }



}