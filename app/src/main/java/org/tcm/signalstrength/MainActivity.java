package org.tcm.signalstrength;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    public static final String[] EXTRA_MESSAGE = {"org.tcm.signalstrength.MESSAGE",""};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText("Time Interval (Seconds)");

        NumberPicker numberPicker = (NumberPicker) findViewById(R.id.numberPickerTime);
        numberPicker.setMaxValue(60);
        numberPicker.setMinValue(5);
        numberPicker.setValue(15);

    }

    /** Called when the user taps the Start button */
    public void start(View view) {
        Intent intent = new Intent(this, DisplaySignalActivity.class);
        NumberPicker numberPicker = (NumberPicker) findViewById(R.id.numberPickerTime);

        String message = "Data_"+System.currentTimeMillis()+"_file.csv";

        intent.putExtra(EXTRA_MESSAGE[0], message);
        intent.putExtra(EXTRA_MESSAGE[1], numberPicker.getValue()+"");
        startActivity(intent);
    }

}



