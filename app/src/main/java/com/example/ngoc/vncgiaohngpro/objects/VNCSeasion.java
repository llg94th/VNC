package com.example.ngoc.vncgiaohngpro.objects;

/**
 * Created by HUNGNTPH04073@FPT.EDU.VN on 6/20/2016.
 */
public class VNCSeasion {
    private String id;
    private String name;
    private long timeStarted;
    private long timeEnded;

    public VNCSeasion() {
    }

    public VNCSeasion(String id, String name, long timeStarted, long timeStopped) {
        this.id = id;
        this.name = name;
        this.timeStarted = timeStarted;
        this.timeEnded = timeStopped;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTimeStarted() {
        return timeStarted;
    }

    public void setTimeStarted(long timeStarted) {
        this.timeStarted = timeStarted;
    }

    public long getTimeStopped() {
        return timeEnded;
    }

    public void setTimeStopped(long timeEnded) {
        this.timeEnded = timeEnded;
    }
}
