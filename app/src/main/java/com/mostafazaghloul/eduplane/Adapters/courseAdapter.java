package com.mostafazaghloul.eduplane.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mostafazaghloul.eduplane.Models.courseModel;
import com.mostafazaghloul.eduplane.R;

import java.util.List;

public class courseAdapter extends RecyclerView.Adapter<courseAdapter.MyViewHolder> {

    private List<courseModel> coursesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, date;
        public ImageView imageView;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.courseTitle);
            date = (TextView) view.findViewById(R.id.courseTime);
            imageView = (ImageView) view.findViewById(R.id.face);
        }
    }


    public courseAdapter(List<courseModel> courseModelList) {
        this.coursesList = courseModelList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.course_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        courseModel course = coursesList.get(position);
        holder.title.setText(course.getCourseTitle());
        holder.date.setText(course.getCourseTime());
        holder.imageView.setImageResource(course.getCourseImage());
    }

    @Override
    public int getItemCount() {
        return coursesList.size();
    }
}