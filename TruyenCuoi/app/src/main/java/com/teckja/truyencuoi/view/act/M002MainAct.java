package com.teckja.truyencuoi.view.act;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.view.GravityCompat;

import com.teckja.truyencuoi.App;
import com.teckja.truyencuoi.R;
import com.teckja.truyencuoi.databinding.M002ActMainBinding;
import com.teckja.truyencuoi.view.adapter.StoryAdapter;
import com.teckja.truyencuoi.view.model.StoryModel;
import com.teckja.truyencuoi.viewmodel.M002MainVM;

import java.io.InputStream;

public class M002MainAct extends BaseAct<M002ActMainBinding, M002MainVM> {

    @Override
    protected void initView() {
        initPhotoTopics();
        binding.includeContent.ivMenu.setOnClickListener(v -> openMenu());
        showListStory();
    }

    private void openMenu() {
        binding.drawer.openDrawer(GravityCompat.START);
    }

    @Override
    protected Class<M002MainVM> getClassVM() {
        return M002MainVM.class;
    }

    @Override
    protected M002ActMainBinding initViewBinding() {
        return M002ActMainBinding.inflate(getLayoutInflater());
    }

    private void initPhotoTopics() {
        AssetManager assetMgr = getAssets();
        try {
            String[] listFileName = assetMgr.list("photo/");
            binding.include.lnTopic.removeAllViews();

            for (String photoPath : listFileName) {
                View itemView = LayoutInflater.from(this).inflate(R.layout.item_topic, null);
                TextView tvName = itemView.findViewById(R.id.tv_photo);
                ImageView ivPhoto = itemView.findViewById(R.id.iv_photo);

                InputStream in = assetMgr.open("photo/" + photoPath);
                Bitmap img = BitmapFactory.decodeStream(in);
                ivPhoto.setImageBitmap(img);
                tvName.setText(photoPath.replace(".png", ""));

                itemView.setTag(tvName.getText().toString());
                itemView.setOnClickListener(this::openTopic);
                binding.include.lnTopic.addView(itemView);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void openTopic(View viewTopic) {
        viewTopic.startAnimation(AnimationUtils.loadAnimation(this,
                androidx.appcompat.R.anim.abc_fade_in));
        binding.drawer.closeDrawers();

        viewModel.setTopicName(viewTopic.getTag().toString());
        binding.includeContent.tvTopic.setText(viewModel.getTopicName());
        showListStory();
    }

    private void showListStory() {
        viewModel.readStoryFile(getAssets());
        StoryAdapter adapter = new StoryAdapter(this, viewModel.getListStory());
        adapter.getStoryLD().observe(this, storyModel -> {
            if (storyModel == null) return;
            openStoryDetail(storyModel);
        });
        binding.rvStory.setAdapter(adapter);
    }

    private void openStoryDetail(StoryModel storyModel) {
        //showNotify(storyModel.getName() + "\n\n" + storyModel.getContent());
        App.getInstance().getStorage().listStory = viewModel.getListStory();
        App.getInstance().getStorage().storyModel = storyModel;
        App.getInstance().getStorage().topicName = viewModel.getTopicName();

        startActivity(new Intent(this, M003DetailStoryAct.class));
    }
}