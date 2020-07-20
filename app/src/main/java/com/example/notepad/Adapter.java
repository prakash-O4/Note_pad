package com.example.notepad;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    Context context;
    List<Data> dataList;

    public Adapter(Context context, List<Data> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        String heading = dataList.get(position).getTitle();
        String date = dataList.get(position).getDate();
        String time = dataList.get(position).getTime();
        long id = dataList.get(position).getId();
        holder.setData(heading,date,time,id);

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView heading, date, time,listID;

        //ImageView profile;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            heading = (TextView) itemView.findViewById(R.id.title);
            date = (TextView) itemView.findViewById(R.id.date);
            time = (TextView) itemView.findViewById(R.id.time);
            listID = (TextView) itemView.findViewById(R.id.listId);

        }

        private void setData(String title, String dateOne, String timeOne,long id) {
            heading.setText(title);
            date.setText(dateOne);
            time.setText(timeOne);
            listID.setText(String.valueOf(id));
        }
    }
}
