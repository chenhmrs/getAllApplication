package com.example.wellwang.myapplication;

/**
 * Created by Well Wang on 2017/12/25.
 */

/**
 * 每个app的mould
 */
public class chatapp {
    String name;
    String pagename;
    String logo;
    String homepage;


    public void setLogo(String logo) {
        this.logo = logo;
    }
    public String getLogo() {
        return logo;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setPagename(String pagename) {
        this.pagename = pagename;
    }
    public String getPagename() {
        return pagename;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getHomepage() {
        return homepage;
    }
}
