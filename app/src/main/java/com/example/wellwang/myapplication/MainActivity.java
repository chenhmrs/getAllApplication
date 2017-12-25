package com.example.wellwang.myapplication;


import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    List<chatapp> chatappList;
    List<String> allApplication;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=(ListView)findViewById(R.id.list_view) ;
        toolbar=(Toolbar)findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_back);
        chatappList=getJsonData();
        allApplication=getAllApp();
        setAppList();
    }

    private void setAppList() {
        List<Map<String,Object>> list=new ArrayList<>();
        for (int i=0;i<allApplication.size();i++){
            HashMap<String,Object> hashMap=new HashMap<>();
            hashMap.put("getLogo",Utils.getLogo(MainActivity.this,allApplication.get(i)));
            hashMap.put("getIcon",Utils.getIcon(MainActivity.this,allApplication.get(i)));
            hashMap.put("getName",Utils.getName(MainActivity.this,allApplication.get(i)));
            list.add(hashMap);
        }

        SimpleAdapter adapter=new SimpleAdapter(MainActivity.this,list,R.layout.item_main,new String[]{"getLogo","getIcon","getName"},new int[]{R.id.logo,R.id.cion,R.id.name});
        listView.setAdapter(adapter);
        adapter.setViewBinder(new SimpleAdapter.ViewBinder() {
            @Override
            public boolean setViewValue(View view, Object data, String arg2) {
                if(view instanceof ImageView && data instanceof Drawable){
                    ImageView iv = (ImageView)view;
                    iv.setImageDrawable((Drawable)data);
                    return true;
                }else{
                    return false;
                }
            }
        });
    }

    public List<chatapp> getJsonData() {
        List<chatapp> list=new ArrayList<>();
        String text=readFromInputStrem();
        list=readFromGson(text);
        return list;
    }

    private List<chatapp> readFromGson(String text) {
        Gson gson=new Gson();
        Root1 root1=gson.fromJson(text,Root1.class);
       // List<chatapp> chatapps=root1.getList();
        for (int i=0;i<root1.getList().size();i++){
            Log.d("MainActivity",root1.getList().get(i).getLogo());
        }
        return root1.getList();
    }

    private String readFromInputStrem() {
        StringBuffer buffer=new StringBuffer();//这句必须放在try外面否则return不承认buffer变量
        try {
            InputStream is=getAssets().open("chat.json");
            InputStreamReader reader=new InputStreamReader(is,"UTF-8");
            BufferedReader bufferedReader=new BufferedReader(reader);
            String line;
            while ((line=bufferedReader.readLine())!=null){
                buffer.append(line);
                buffer.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
       // Log.d("MainActivity","ggg"+buffer.toString());
        return buffer.toString();
    }

    public List<String> getAllApp() {
        List<String> list=new ArrayList<>();
        PackageManager pm=getPackageManager();
        List<PackageInfo> list1=pm.getInstalledPackages(0);
        for (int i=0;i<list1.size();i++){
            //PackageInfo packageInfo = list1.get(i);
            //int flags = packageInfo.applicationInfo.flags;
           // if(flags & ApplicationInfo.FLAG_SYSTEM) != 0){
                //系统程序
         //   }
          //  if(flags & ApplicationInfo.FLAG_SYSTEM) <= 0){
                //非系统程序
                //   }
                //else if ((flags & ApplicationInfo.FLAG_UPDATED_SYSTEM_APP) != 0){
            //本来是系统程序，被用户手动更新后，该系统程序也成为第三方应用程序了
         //   }
           // if ((flags & ApplicationInfo.FLAG_EXTERNAL_STORAGE) != 0) {
                //安装在SDCard的应用程序
         //   }
            list.add(list1.get(i).packageName);
        }

        return list;
    }
}
