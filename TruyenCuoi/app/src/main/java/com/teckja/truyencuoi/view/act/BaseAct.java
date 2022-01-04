package com.teckja.truyencuoi.view.act;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewbinding.ViewBinding;

public abstract class BaseAct<T extends ViewBinding, M extends ViewModel> extends AppCompatActivity implements View.OnClickListener {

    protected T binding;
    protected M viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = initViewBinding();
        setContentView(binding.getRoot());
        viewModel = new ViewModelProvider(this).get(getClassVM());
        initView();
    }

    protected abstract void initView();

    protected abstract Class<M> getClassVM();

    protected abstract T initViewBinding();

    @Override
    public final void onClick(View v) {
        v.startAnimation(AnimationUtils.loadAnimation(this, androidx.appcompat.R.anim.abc_fade_in));
        clickView(v);
    }

    protected void clickView(View v) {
        //do nothing
    }

    protected void showNotify(int msgId) {
        Toast.makeText(this, msgId, Toast.LENGTH_SHORT).show();
    }

    protected void showNotify(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
