package com.example.roberto.hackathonapp2018;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class orgHomePageActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private List<Event> listEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_org_home_page);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        listEvents = new ArrayList<>();

        for(int i = 0; i < 5; i++){
            Event event = new Event("HackUTA","Nedderman Hall", "24 hour long hacking event",
                    false, "computer science");
            listEvents.add(event);
        };

        mAdapter = new org_home_event_adapter(listEvents, this);
        mRecyclerView.setAdapter(mAdapter);

    }

    public void onClick1(View v)
    {

        Intent intent = new Intent(this, orgHomePageActivity.class);
        //intent.putExtras();
        startActivity(intent);
    }
    public void onClick2(View v)
    {
        Intent intent = new Intent(this, datePicker.class);
        //intent.putExtra(EXTRA_MESSAGE, message);
        startActivityForResult(intent, 0);
    }

    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                // A contact was picked.  Here we will just display it
                // to the user.
                //startActivity(new Intent(Intent.ACTION_VIEW, data));
                Event newEvent = (Event) data.getSerializableExtra("Event");
                //onCreate(this.saved);
                listEvents.add(newEvent);
                mAdapter.notifyDataSetChanged();
            }
        }
    }

}

