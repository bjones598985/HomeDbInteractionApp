package com.q29ideas.HomeDbInteractionApp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import com.q29ideas.HomeDbInteractionApp.data.model.Record;

//Created on 2/17/2018

public class RecordsAdapter extends RecyclerView.Adapter<RecordsAdapter.ViewHolder> {

    private List<Record> recordList;

    public RecordsAdapter(List<Record> recordList) {
        this.recordList = recordList;
    }

    @Override
    public RecordsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View postView = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);

        return new RecordsAdapter.ViewHolder(postView);
    }

    @Override
    public void onBindViewHolder(RecordsAdapter.ViewHolder holder, int position) {
        Record record = recordList.get(position);
        TextView textView = holder.tv;
        textView.setText("Id: " + record.getId() + ", CardioType: " + record.getCardioType());
    }

    @Override
    public int getItemCount() {
        return recordList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv;

        public ViewHolder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(android.R.id.text1);
        }
    }

    public void updateRecords(List<Record> list) {
        recordList = list;
        notifyDataSetChanged();
    }
}
