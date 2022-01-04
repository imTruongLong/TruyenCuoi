package com.teckja.truyencuoi.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.teckja.truyencuoi.R;
import com.teckja.truyencuoi.view.model.StoryModel;

import java.util.List;

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.StoryHolder> {
    private final Context context;
    private final List<StoryModel> listStory;
    private final MutableLiveData<StoryModel> storyLD = new MutableLiveData<>();

    public StoryAdapter(Context context, List<StoryModel> listStory) {
        this.context = context;
        this.listStory = listStory;
    }

    public MutableLiveData<StoryModel> getStoryLD() {
        return storyLD;
    }

    @NonNull
    @Override
    public StoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_story, parent, false);
        return new StoryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StoryHolder holder, int position) {
        StoryModel data = listStory.get(position);
        holder.tvStoryName.setText(data.getName());
        holder.tvStoryName.setTag(data);
        holder.lnBG.setBackgroundResource(data.isSelected()?R.color.gray_light:R.color.white);
    }

    @Override
    public int getItemCount() {
        return listStory.size();
    }

    public class StoryHolder extends RecyclerView.ViewHolder {
        TextView tvStoryName;
        View lnBG;

        public StoryHolder(@NonNull View view) {
            super(view);
            tvStoryName = view.findViewById(R.id.tv_story_name);
            lnBG = view.findViewById(R.id.ln_bg);
            view.setOnClickListener(view1 -> {
                view1.startAnimation(AnimationUtils.loadAnimation(context,
                        androidx.appcompat.R.anim.abc_fade_in));
                clickItemStory((StoryModel) tvStoryName.getTag());
            });
        }

        private void clickItemStory(StoryModel story) {
            story.setSelected(true);
            if (storyLD.getValue() != null) {
                storyLD.getValue().setSelected(false);
            }
            storyLD.postValue(story);
            //refresh all items of RecyclerView
            notifyItemRangeChanged(0, listStory.size());
        }
    }
}
