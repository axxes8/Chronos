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

    public AlertAdapter(Context context){
        this.data = new ArrayList<Alert>();
        this.context = context;
    }

    public AlertAdapter(List<Alert> data) {
        this.data = data;
    }

    @Override
    public AlertAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int itemNum) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.alert_item_layout, viewGroup, false);
        return new RecyclerView.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AlertAdapter.ViewHolder viewHolder, int itemNum) {

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView infoField, statusField, errorField;

        public ViewHolder(View itemView) {
            super(itemView);
            infoField = itemView.findViewById(infoField);
            statusField = itemView.findViewById(statusField);
            errorField = itemView.findViewById(errorField);
        }

        public TextView getInfoField() {
            return infoField;
        }

        public TextView getStatusField() {
            return statusField;
        }

        public TextView getErrorField() {
            return errorField;
        }
    }

}
