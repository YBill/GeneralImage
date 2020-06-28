package com.bill.generalimage;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.core.content.ContextCompat;

public class PermissionUtils {

    public static boolean hasSelfPermission(Context context, String permission) {
        try {
            return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
        } catch (Exception e) {
            // java.lang.RuntimeException: Unknown exception code: 1 msg null
            e.printStackTrace();
        }
        return false;
    }

    public static boolean hasNecessaryPermission(Context context) {
        if (PermissionUtils.hasSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                && PermissionUtils.hasSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            return true;
        }
        return false;
    }

}
