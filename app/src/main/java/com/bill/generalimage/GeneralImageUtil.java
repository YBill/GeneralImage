package com.bill.generalimage;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.View;
import android.view.ViewGroup;

public class GeneralImageUtil {

    public static Bitmap getLayoutBitmap(ViewGroup viewGroup, int width, int height) {
        boolean isNeedMeasureWidth = false;
        boolean isNeedMeasureHeight = false;

        if (width > 0) {
            width = View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY);
        } else
            isNeedMeasureWidth = true;

        if (height > 0) {
            height = View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.EXACTLY);
        } else
            isNeedMeasureHeight = true;

        // get width or height
        if (isNeedMeasureWidth || isNeedMeasureHeight) {
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                viewGroup.getChildAt(i).measure(0, 0); // UNSPECIFIED

                if (isNeedMeasureWidth)
                    width += viewGroup.getChildAt(i).getMeasuredWidth();
                if (isNeedMeasureHeight)
                    height += viewGroup.getChildAt(i).getMeasuredHeight();
            }
        }

        return generalBitmap(viewGroup, width, height);
    }

    private static Bitmap generalBitmap(ViewGroup viewGroup, int width, int height) {
        // width and height must >0
        if (width <= 0 || height <= 0) {
            return null;
        }

        viewGroup.measure(width, height);
        viewGroup.layout(0, 0, viewGroup.getMeasuredWidth(), viewGroup.getMeasuredHeight());
        Bitmap bitmap = Bitmap.createBitmap(viewGroup.getWidth(), viewGroup.getHeight(), Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(bitmap);
        viewGroup.draw(canvas);

        return bitmap;
    }

    @Deprecated
    public static Bitmap getCacheBitmap(View view) {
        view.setDrawingCacheEnabled(true); //设置能否缓存图片信息（drawing cache）
        view.buildDrawingCache(); //如果能够缓存图片，则创建图片缓存
        Bitmap bitmap = view.getDrawingCache(); //如果图片已经缓存，返回一个bitmap
        view.destroyDrawingCache(); //释放缓存占用的资源
        return bitmap;
    }

}
