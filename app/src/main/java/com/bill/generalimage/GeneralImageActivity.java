package com.bill.generalimage;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.widget.ContentLoadingProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

public class GeneralImageActivity extends AppCompatActivity {

    private AppCompatImageView imageView;
    private ContentLoadingProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_image);
        imageView = findViewById(R.id.iv_image);
        progressBar = findViewById(R.id.pb);

    }

    public void handleClick(View view) {
        progressBar.setVisibility(View.VISIBLE);
        progressBar.show();

        final FrameLayout frameLayout = (FrameLayout) LayoutInflater.from(this).inflate(R.layout.layout_item, null);

        final AppCompatImageView contentIv = frameLayout.findViewById(R.id.iv_content);
        Glide.with(this)
                .load("https://rmrbcmsonline.peopleapp.com/upload/image/202006/202006280737042784.png?x-oss-process=image/resize,m_fill,h_170,w_226/quality,q_90/format,jpg")
                .asBitmap()
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        contentIv.setImageBitmap(resource);
                        Bitmap bitmap = GeneralImageUtil.getLayoutBitmap(frameLayout, getScreenWidth(), 0);
                        imageView.setImageBitmap(bitmap);
                        progressBar.hide();
                    }
                });

    }

    private int getScreenWidth() {
        WindowManager wm = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        return width;
    }

}
