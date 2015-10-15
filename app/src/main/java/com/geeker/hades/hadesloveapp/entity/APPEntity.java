package com.geeker.hades.hadesloveapp.entity;

import java.io.Serializable;

/**
 * Created by Hades on 10/12/15.
 */
public class APPEntity implements Serializable{

    private String appName;

    private int launchCount;

    private long usageTime;

    public APPEntity(String appName, int launchCount, long usageTime){
        this.appName = appName;
        this.launchCount = launchCount;
        this.usageTime = usageTime;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public int getLaunchCount() {
        return launchCount;
    }

    public void setLaunchCount(int launchCount) {
        this.launchCount = launchCount;
    }

    public long getUsageTime() {
        return usageTime;
    }

    public void setUsageTime(long usageTime) {
        this.usageTime = usageTime;
    }
}
