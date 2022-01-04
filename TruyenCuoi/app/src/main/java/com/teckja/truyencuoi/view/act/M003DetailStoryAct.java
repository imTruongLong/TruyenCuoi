package com.teckja.truyencuoi.view.act;

import android.view.View;
import android.view.animation.AnimationUtils;

import androidx.viewpager.widget.ViewPager;

import com.teckja.truyencuoi.App;
import com.teckja.truyencuoi.databinding.M003ActDetailBinding;
import com.teckja.truyencuoi.view.adapter.DetailStoryAdapter;
import com.teckja.truyencuoi.view.model.StoryModel;
import com.teckja.truyencuoi.viewmodel.CommonVM;

import java.util.List;

public class M003DetailStoryAct extends BaseAct<M003ActDetailBinding, CommonVM> {
    @Override
    protected void initView() {
        List<StoryModel> listData = App.getInstance().getStorage().listStory;
        DetailStoryAdapter adapter = new DetailStoryAdapter(this, listData);
        binding.vpStory.setAdapter(adapter);

        binding.include.ivMenu.setVisibility(View.GONE);
        binding.include.ivBack.setVisibility(View.VISIBLE);
        binding.include.ivBack.setOnClickListener(this::backToM002);
        binding.include.tvTopic.setText(App.getInstance().getStorage().topicName);

        //goto selected story
        int selectedIndex = listData.indexOf(App.getInstance().getStorage().storyModel);
        binding.vpStory.setCurrentItem(selectedIndex, true);

        binding.include.tvIndex.setVisibility(View.VISIBLE);
        binding.include.tvIndex.setText(String.format("%s%s/%s",
                (selectedIndex < 10 ? "0" : ""), selectedIndex, listData.size()));

        //listen index change
        binding.vpStory.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                binding.include.tvIndex.setText(String.format("%s%s/%s",
                        position < 10 ? "0" : "", position, listData.size()));
            }
        });
    }

    private void backToM002(View v) {
        v.startAnimation(AnimationUtils.loadAnimation(this,
                androidx.appcompat.R.anim.abc_fade_in));
        finish();
    }

    @Override
    protected Class<CommonVM> getClassVM() {
        return CommonVM.class;
    }

    @Override
    protected M003ActDetailBinding initViewBinding() {
        return M003ActDetailBinding.inflate(getLayoutInflater());
    }
}
