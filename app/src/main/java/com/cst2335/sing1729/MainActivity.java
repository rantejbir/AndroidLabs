package com.cst2335.sing1729;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ResourceBundle;

public class MainActivity extends AppCompatActivity {

    private CheckBox cb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = findViewById(R.id.button_click);

        btn.setOnClickListener( ibtn ->{

            Toast.makeText(this, R.string.toast_message, Toast.LENGTH_LONG).show();
        });
//        Button btn = findViewById(R.id.button);
        CompoundButton myCb = findViewById(R.id.checkBox);


        myCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            Snackbar snackbar = Snackbar.make(findViewById(R.id.checkBox), R.string.checkboxOn, Snackbar.LENGTH_LONG);
            Snackbar snackbar2 = Snackbar.make(findViewById(R.id.checkBox), R.string.checkboxOff, Snackbar.LENGTH_LONG);

            @Override
            public void onCheckedChanged(CompoundButton cb, boolean isChecked) {

                if(isChecked){

                    snackbar.show();
                    snackbar.setAction("undo", click -> cb.setChecked(!isChecked));
                    snackbar.setAction("Undo", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            snackbar2.show();
                            myCb.setChecked(false);
                        }
                    });
                }
                else{
                    snackbar2.show();
                    snackbar2.setAction("undo", click -> cb.setChecked(!isChecked));
                    snackbar2.setAction("Undo", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            snackbar.show();
                            myCb.setChecked(true);
                        }
                    });

                }

            }

        });






    }
}