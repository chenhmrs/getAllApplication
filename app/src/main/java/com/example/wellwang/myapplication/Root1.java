package com.example.wellwang.myapplication;

import java.util.List;

/**
 * Created by Well Wang on 2017/12/25.
 */

/**
 * 将String格式转为Json格式后的对象
 */
public class Root1 {
    List<chatapp> chatapp;
    public  List<chatapp> getList(){
        return this.chatapp;
    }

    public void setChatapp(List<com.example.wellwang.myapplication.chatapp> chatapp) {
        this.chatapp = chatapp;
    }
}
