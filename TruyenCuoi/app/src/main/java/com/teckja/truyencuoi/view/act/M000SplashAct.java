package com.teckja.truyencuoi.view.act;

import android.content.Intent;
import android.os.Handler;

import com.teckja.truyencuoi.databinding.M000ActSplashBinding;
import com.teckja.truyencuoi.viewmodel.CommonVM;

public class M000SplashAct extends BaseAct<M000ActSplashBinding, CommonVM> {
    @Override
    protected void initView() {
        new Handler().postDelayed(this::gotoMainScreen, 2000);
    }

    private void gotoMainScreen() {
        startActivity(new Intent(this, M002MainAct.class));
    }

    @Override
    protected Class<CommonVM> getClassVM() {
        return CommonVM.class;
    }

    @Override
    protected M000ActSplashBinding initViewBinding() {
        return M000ActSplashBinding.inflate(getLayoutInflater());
    }
}
