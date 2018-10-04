package com.mostafazaghloul.eduplane.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mostafazaghloul.eduplane.Models.courseModel;
import com.mostafazaghloul.eduplane.Models.notificationModel;
import com.mostafazaghloul.eduplane.R;

import java.util.List;

public class notificationAdapter extends RecyclerView.Adapter<notificationAdapter.MyViewHolder> {

    private List<notificationModel> notificationsList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, date;
        public ImageView imageView;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.notificationTitle);
            date = (TextView) view.findViewById(R.id.notificationTime);
            imageView = (ImageView) view.findViewById(R.id.facenotification);
        }
    }


    public notificationAdapter(List<notificationModel> notificationModelList) {
        this.notificationsList = notificationModelList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.notification_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        notificationModel course = notificationsList.get(position);
        holder.title.setText(course.getNotificationTitle());
        holder.date.setText(course.getNotificationTime());
        holder.imageView.setImageResource(course.getNotificationImage());
    }

    @Override
    public int getItemCount() {
        return notificationsList.size();
    }
}