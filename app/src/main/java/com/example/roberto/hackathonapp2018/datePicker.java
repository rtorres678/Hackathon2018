package com.example.roberto.hackathonapp2018;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class datePicker extends AppCompatActivity implements TimePickerInterface, DatePickerSelectionInterface {
    private static final String FILE_NAME = "events.txt";

    EditText org;
    EditText event;
    EditText loca;
    EditText desc;
    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditText = findViewById(R.id.edit_text);
    }*/

    public void save(View v) {
        String text = (String) org.getText().toString();
        String t = (String) event.getText().toString();
        String te = (String)loca.getText().toString();
        String tex = (String) desc.getText().toString();
        FileOutputStream fos = null;
        Event eventNew = new Event( t, te, tex, false,"");

        try {
            fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
            fos.write(text.getBytes());

            org.getText().clear();
            event.getText().clear();
            loca.getText().clear();
            desc.getText().clear();
            Toast.makeText(this, "Saved to " + getFilesDir() + "/" + FILE_NAME,
                    Toast.LENGTH_LONG).show();

            Intent intent = new Intent();
            intent.putExtra("Event", eventNew);
            setResult(RESULT_OK, intent);
            finish();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private long mLastTimePickerValue;
    private long mLastDatePickerValue;
    private TextView mSelectedDateOrTimeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_picker);
        initViews();

        org = findViewById(R.id.organization);
        event = findViewById(R.id.event);
        loca = findViewById(R.id.location);
        desc = findViewById(R.id.description);
    }

    private void initViews() {
        mSelectedDateOrTimeTextView = findViewById(R.id.selectedDateTime);
        Button mDatePickerButton = findViewById(R.id.datePicker);
        mDatePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO call date picker fragment here
                openDatePicker();
            }
        });

        Button mTimePickerButton = findViewById(R.id.timePicker);
        mTimePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO call time picker fragment here
                openTimePicker();
            }
        });
    }

    private void openDatePicker() {
        DatePickerFragment datePickerFragment = new DatePickerFragment();

        if (mLastDatePickerValue > 0) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(mLastDatePickerValue);
            Bundle bundle = new Bundle();
            bundle.putInt("year", calendar.get(Calendar.YEAR));
            bundle.putInt("month", calendar.get(Calendar.MONTH)+1);
            bundle.putInt("day", calendar.get(Calendar.DAY_OF_MONTH));
            datePickerFragment.setArguments(bundle);
        }
        datePickerFragment.delegate = datePicker.this;
        datePickerFragment.setCancelable(false);
        datePickerFragment.show(getSupportFragmentManager(), "datePicker");
    }

    private void openTimePicker() {
        TimePickerFragment timePicker = new TimePickerFragment();

        if (mLastTimePickerValue > 0) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(mLastTimePickerValue);
            Bundle bundle = new Bundle();
            bundle.putInt("hour", calendar.get(Calendar.HOUR_OF_DAY));
            bundle.putInt("minute", calendar.get(Calendar.MINUTE));
            timePicker.setArguments(bundle);
        }
        timePicker.delegate = (TimePickerInterface) datePicker.this;
        timePicker.setCancelable(false);
        timePicker.show(getSupportFragmentManager(), "timePicker");
    }

    //@Override
    public void onTimeSelected(int hours, int minute) {
        mLastDatePickerValue=0;
        mSelectedDateOrTimeTextView.setText(String.valueOf(AppUtils.formatCharLength(2, hours) + ":" + AppUtils.formatCharLength(2, minute)));
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat format1 = new SimpleDateFormat("dd MM yyyy", Locale.getDefault());
        String formatted = format1.format(cal.getTime());
        mLastTimePickerValue = AppUtils.timeIntoTimeStamp(formatted + " " + hours + ":" + minute);
    }

    @Override
    public void onDateSelected(int day, int month, int year) {
        mLastTimePickerValue=0;
        mSelectedDateOrTimeTextView.setText(String.valueOf(AppUtils.formatCharLength(2, day) + " " + AppUtils.formatCharLength(2, (month + 1)) + " " + year));
        mLastDatePickerValue = AppUtils.dateIntoTimeStamp(String.valueOf(day + " " + (month) + " " + year));
    }
}