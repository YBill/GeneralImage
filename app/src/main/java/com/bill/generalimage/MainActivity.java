package com.bill.generalimage;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (PermissionUtils.hasNecessaryPermission(this)) {
            getPermissionSuccess();
        } else {
            requestPermissions();
        }
    }

    /**
     * 请求权限
     */
    private void requestPermissions() {
        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                1);
    }

    private void getPermissionSuccess() {
        startActivity(new Intent(this, GeneralImageActivity.class));
        finish();
    }

    private void getPermissionFail() {
        Toast.makeText(this, "请在设置页面手动设置SD卡读写权限", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            boolean isAllAgree = true;
            for (int grantResult : grantResults) {
                if (grantResult != PackageManager.PERMISSION_GRANTED) {
                    isAllAgree = false;
                    break;
                }
            }
            if (isAllAgree) {
                getPermissionSuccess();
            } else {
                getPermissionFail();
            }
        }
    }
}
