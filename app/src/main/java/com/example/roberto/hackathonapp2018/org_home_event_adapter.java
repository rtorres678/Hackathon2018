package com.example.roberto.hackathonapp2018;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class org_home_event_adapter extends RecyclerView.Adapter<org_home_event_adapter.MyViewHolder> {
    private List<Event> listEvents;
    private Context context;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewName;
        public TextView textViewDesc;
        public Button button;
        public MyViewHolder(View v) {
            super(v);
            textViewName = (TextView) itemView.findViewById(R.id.eventNameTextView);
            textViewDesc = (TextView) itemView.findViewById(R.id.eventDescriptionTextView);
            button = (Button) itemView.findViewById(R.id.button2);

        }
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.org_home_event_view , viewGroup,false);
        return new MyViewHolder(v);
    }

    public org_home_event_adapter(List<Event> listEvents, Context context) {
        this.listEvents = listEvents;
        this.context = context;
    }

    public org_home_event_adapter(List<Event> listEvents) {
        this.listEvents = listEvents;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder viewHolder, int i) {
        final Event listEvent = listEvents.get(i);
        viewHolder.textViewName.setText("Event Name: "+ listEvent.getName());
        viewHolder.textViewDesc.setText(listEvent.getDescription());

        viewHolder.button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), eventDetailsActivity.class);
                intent.putExtra("Event", listEvent);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listEvents.size();
    }

}
