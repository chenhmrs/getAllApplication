package com.example.wellwang.myapplication;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;

/**
 * Created by Well Wang on 2017/12/25.
 */

/**
 * PackageManager根据packageName得到特定的ApplicationInfo，以此加载出他的icon和name
 */
public class Utils {
    public static Drawable getLogo(Context context, String packageName){
        PackageManager pm=context.getPackageManager();
        ApplicationInfo ai= null;
        try {
            ai = pm.getApplicationInfo(packageName,0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return ai.loadLogo(pm);
    }

    public static Drawable getIcon(Context context, String packageName) {
        PackageManager pm=context.getPackageManager();
        ApplicationInfo ai= null;
        try {
            ai = pm.getApplicationInfo(packageName,0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return ai.loadIcon(pm);
    }
    public static String getName(Context context, String packageName) {
        PackageManager pm=context.getPackageManager();
        ApplicationInfo ai= null;
        try {
            ai = pm.getApplicationInfo(packageName,0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return ai.loadLabel(pm).toString();
    }
}
