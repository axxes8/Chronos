package com.team6.chronos;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AlertAdapter extends RecyclerView.Adapter<AlertAdapter.ViewHolder> {
    private List<Alert> data;
    private Context context;

    public AlertAdapter(Context context, List<Alert> data){
        this.data = data;
        this.context = context;
    }

    @Override
    public AlertAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int itemNum) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.row_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AlertAdapter.ViewHolder viewHolder, int itemNum) {

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView nameField, serviceField, dateField, timeField;

        public ViewHolder(View itemView) {
            super(itemView);
            nameField = itemView.findViewById(R.id.nameField);
            serviceField = itemView.findViewById(R.id.serviceField);
            dateField = itemView.findViewById(R.id.dateField);
            timeField = itemView.findViewById(R.id.timeField);
        }

        public TextView getNameField() {
            return nameField;
        }

        public TextView getServiceField() {
            return serviceField;
        }

        public TextView getDateField() {
            return dateField;
        }

        public TextView getTimeField() { return timeField; }
    }

}
