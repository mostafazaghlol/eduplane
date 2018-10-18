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
import com.mostafazaghloul.eduplane.Activities.ShowingContent;
import com.mostafazaghloul.eduplane.Models.courseItem;
import com.mostafazaghloul.eduplane.Models.courseModel;
import com.mostafazaghloul.eduplane.R;

import java.util.List;

public class courseItemAdapter extends RecyclerView.Adapter<courseItemAdapter.MyViewHolder> {

    private List<courseItem> coursesList;
    private String url;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, date,desc;
        public RelativeLayout relativeLayout;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.courseItemTitle);
            date = (TextView) view.findViewById(R.id.courseItemTime);
            desc = (TextView) view.findViewById(R.id.courseItemdesc);
            relativeLayout = (RelativeLayout) view.findViewById(R.id.courseItemlayout);
        }
    }


    public courseItemAdapter(List<courseItem> courseModelList) {
        this.coursesList = courseModelList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.course_sub_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final courseItem course = coursesList.get(position);
        holder.title.setText(course.getName());
        holder.date.setText(course.getCreated_at());
        holder.desc.setText(course.getDisc());

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                url = geturl(course.getFile(),CoursesItemActivity.course_type);
//                Toast.makeText(view.getContext(), ""+String.valueOf(course.getFile()), Toast.LENGTH_SHORT).show();
                Intent i = new Intent(view.getContext(),ShowingContent.class);
                i.putExtra("web",url);
                view.getContext().startActivity(i);
            }
        });
    }

    private String geturl(String file, String type) {
        if(type.equals("pdf")){
            return "http://drive.google.com/viewerng/viewer?embedded=true&url="+"http://edu.atls.sa/public/images/subjects/"+file;
        }else if(type.equals("video")){
            return "http://edu.atls.sa/public/images/subjects/"+file;
        }else if(type.equals("image")){
            return "http://edu.atls.sa/public/images/subjects/"+file;
        }else {
            return "http://drive.google.com/viewerng/viewer?embedded=true&url="+ "http://edu.atls.sa/public/images/subjects/"+file;
        }

    }

    @Override
    public int getItemCount() {
        return coursesList.size();
    }
}