package com.teckja.truyencuoi.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.teckja.truyencuoi.R;
import com.teckja.truyencuoi.view.model.StoryModel;

import java.util.List;

public class DetailStoryAdapter extends PagerAdapter {
    private final Context context;
    private final List<StoryModel> listStory;

    public DetailStoryAdapter(Context context, List<StoryModel> listStory) {
        this.context = context;
        this.listStory = listStory;
    }

    @Override
    public int getCount() {
        return listStory.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup viewPager, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_detail_story,
                viewPager, false);
        StoryModel data = listStory.get(position);

        TextView tvName = view.findViewById(R.id.tv_name);
        TextView tvContent = view.findViewById(R.id.tv_content);
        tvName.setText(data.getName());
        tvContent.setText(data.getContent());

        viewPager.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup viewPager, int position,
                            @NonNull Object object) {
        viewPager.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }
}
