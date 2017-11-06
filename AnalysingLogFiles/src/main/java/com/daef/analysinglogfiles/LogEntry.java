/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daef.analysinglogfiles;

import java.util.Date;

/**
 *
 * @author abj
 */
public class LogEntry {

    private int systemID;
    private int instanceID;
    private String actionID;
    private long timestamp;
    private Level level;
    private int state;

    public enum Level {
        WARNING, INFORMATION, ERROR
    }

    public LogEntry(int systemID, int instanceID, String actionID, long timestamp) {
        this.systemID = systemID;
        this.instanceID = instanceID;
        this.actionID = actionID;
        this.timestamp = timestamp;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getSystemID() {
        return systemID;
    }

    public void setSystemID(int systemID) {
        this.systemID = systemID;
    }

    public int getInstanceID() {
        return instanceID;
    }

    public void setInstanceID(int instanceID) {
        this.instanceID = instanceID;
    }

    public String getActionID() {
        return actionID;
    }

    public void setActionID(String actionID) {
        this.actionID = actionID;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

}
