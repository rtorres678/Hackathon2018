package com.example.roberto.hackathonapp2018;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class eventDetailsActivity extends AppCompatActivity {
    private TextView textView;
    private TextView textView2;
    Event event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);
        textView = (TextView)findViewById(R.id.textView);
        textView2 = (TextView)findViewById(R.id.textView2);
        event = (Event)getIntent().getSerializableExtra("Event");

        textView.setText(event.getName());
        textView2.setText(event.getDescription());

    }
}
