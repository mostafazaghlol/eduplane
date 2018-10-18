package com.mostafazaghloul.eduplane.Adapters;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mostafazaghloul.eduplane.Activities.CoursesItemActivity;
import com.mostafazaghloul.eduplane.Models.courseModel;
import com.mostafazaghloul.eduplane.R;

import java.util.List;

public class courseAdapter extends RecyclerView.Adapter<courseAdapter.MyViewHolder> {

    private List<courseModel> coursesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, date;
        public ImageView imageView;
        public RelativeLayout relativeLayout;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.courseTitle);
            date = (TextView) view.findViewById(R.id.courseTime);
            imageView = (ImageView) view.findViewById(R.id.face);
            relativeLayout = (RelativeLayout) view.findViewById(R.id.courselayout);
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
        final courseModel course = coursesList.get(position);
        holder.title.setText(course.getCourseTitle());
        holder.date.setText(course.getCourseTime());
        holder.imageView.setImageResource(course.getCourseImage());
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Toast.makeText(view.getContext(), ""+String.valueOf(course.getCourseId()), Toast.LENGTH_SHORT).show();
                Intent i = new Intent(view.getContext(),CoursesItemActivity.class);
                i.putExtra("course_id",Integer.parseInt(course.getCourseId()));
                view.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return coursesList.size();
    }
}