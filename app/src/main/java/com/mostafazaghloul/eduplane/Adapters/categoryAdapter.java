package com.mostafazaghloul.eduplane.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.mostafazaghloul.eduplane.Activities.CourseReservationActivity;
import com.mostafazaghloul.eduplane.Activities.categoryActivity;
import com.mostafazaghloul.eduplane.Models.category;
import com.mostafazaghloul.eduplane.R;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;

public class categoryAdapter extends RecyclerView.Adapter<categoryAdapter.MyViewHolder> {

    private List<category> categoriesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public SliderLayout mDemoSlider;
        public RelativeLayout relativeLayout;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.statusItemCat);
            mDemoSlider = (SliderLayout) view.findViewById(R.id.slider);
            relativeLayout = (RelativeLayout)view.findViewById(R.id.layout);
        }
    }


    public categoryAdapter(List<category> courseModelList) {
        this.categoriesList = courseModelList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_for_category, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        category course = categoriesList.get(position);
        holder.title.setText(course.getName());
        HashMap<String,String> url_maps = new HashMap<String, String>();
        for(int j=0;j<course.getUrls().size();j++){
            url_maps.put("image"+String.valueOf(j),course.getUrls().get(j));
        }
        for (String name1 : url_maps.values()) {
            TextSliderView textSliderView = new TextSliderView(holder.mDemoSlider.getContext());
            // initialize a SliderLayout
            textSliderView
                    .description("image")
                    .image(name1)
                    .setScaleType(BaseSliderView.ScaleType.CenterCrop);
            holder.mDemoSlider.addSlider(textSliderView);
        }

        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context,CourseReservationActivity.class);
                intent.putExtra("position",position);
                intent.putExtra("type", categoryActivity.type);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoriesList.size();
    }
}