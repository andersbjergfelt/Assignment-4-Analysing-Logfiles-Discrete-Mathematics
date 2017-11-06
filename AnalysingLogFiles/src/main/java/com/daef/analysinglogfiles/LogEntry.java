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
    private int actionID;
    private Date timestamp;
    private Level level;
    
    public enum Level {
    WARNING, INFORMATION, ERROR
    }
    
    public enum States {
        UNREGISTERED, LOGGED_IN, COMMENT
    }

    public LogEntry(Level level, int systemID, int instanceID, int actionID, Date timestamp) {
        this.level = level;
        this.systemID = systemID;
        this.instanceID = instanceID;
        this.actionID = actionID;
        this.timestamp = timestamp;
    }
    
    
}



