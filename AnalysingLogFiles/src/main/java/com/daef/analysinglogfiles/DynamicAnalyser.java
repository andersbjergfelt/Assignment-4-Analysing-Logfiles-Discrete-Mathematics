/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daef.analysinglogfiles;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author abj
 */
public class DynamicAnalyser {
    
    ArrayList<String> input;
    HashMap<Integer,LogEntry> logs;
    ArrayList<String> output;
    public DynamicAnalyser(){
        input = new ArrayList<>();
        logs = new HashMap<>();
        output = new ArrayList<>();
    }
    
    public static void main(String[] args) {
        
        DynamicAnalyser da = new DynamicAnalyser();
        
        da.addInstance(new LogEntry(1000, 1, "A", System.currentTimeMillis()));
        da.addInstance(new LogEntry(1000, 1, "B", System.currentTimeMillis()));
        da.addInstance(new LogEntry(1000, 1, "C", System.currentTimeMillis()));
        da.addInstance(new LogEntry(1000, 1, "D", System.currentTimeMillis()));
        da.addInstance(new LogEntry(1000, 1, "A", System.currentTimeMillis()));
        
        da.checkLevel();
        da.printResult();
    }
    
    public void addInstance(LogEntry log){
        Date d = new Date(log.getTimestamp());
        input.add("SystemID: " + log.getSystemID() + "  InstanceID: " + log.getInstanceID() + "  ActionID: " + log.getActionID() + "  TimeStamp: " + d.toString());
        LogEntry l = logs.get(log.getInstanceID());
        
        if(l == null){
            log.setState(2);
            logs.put(log.getInstanceID(), log);
        } else {
            switch(l.getState()){
                case 2:
                    if(l.getActionID() == "B" || l.getActionID() == "C"){
                        l.setLevel(LogEntry.Level.INFORMATION);
                    } else if(l.getActionID() == "D") {
                        l.setLevel(LogEntry.Level.INFORMATION);
                        l.setState(3);
                    } else if(l.getActionID() == "E") {
                        l.setLevel(LogEntry.Level.INFORMATION);
                        l.setState(4);
                    } else {
                        l.setLevel(LogEntry.Level.ERROR);
                    }
                    break;
                case 3:
                    if(l.getActionID() == "F"){
                        l.setLevel(LogEntry.Level.INFORMATION);
                    } else if(l.getActionID() == "G") {
                        l.setLevel(LogEntry.Level.INFORMATION);
                        l.setState(4);
                    } else {
                        l.setLevel(LogEntry.Level.ERROR);
                    }
                    break;
                case 4:
                    l.setLevel(LogEntry.Level.ERROR);
                    break;
            }
            logs.put(l.getInstanceID(), l);
            output.add("Level: " + l.getLevel() + "  SystemID: " + l.getSystemID() + "  InstanceID: " + l.getInstanceID() + "  ActionID: " + l.getActionID());
        }
    }
    
    public void checkLevel(){
        for (int i = 0; i < logs.size(); i++) {
            LogEntry log = logs.get(i);
            
//            switch(log.getState()){
//                case 1:
//                    if(!log.getActionID().equals("A")){
//                        log.setLevel(LogEntry.Level.ERROR);
//                    } else {
//                        log.setLevel(LogEntry.Level.INFORMATION);
//                    }
//                    break;
//                case 2:
//                    if(!(log.getActionID().equals("B") || log.getActionID().equals("C") || log.getActionID().equals("D") || log.getActionID().equals("E"))){
//                        log.setLevel(LogEntry.Level.ERROR);
//                    } else {
//                        log.setLevel(LogEntry.Level.INFORMATION);
//                    }
//                    break;
//                case 3:
//                    if(!(log.getActionID().equals("F") || log.getActionID().equals("G"))){
//                        log.setLevel(LogEntry.Level.ERROR);
//                    } else {
//                        log.setLevel(LogEntry.Level.INFORMATION);
//                    }
//                    break;
//                case 4:
//                    log.setLevel(LogEntry.Level.ERROR);
//                    break;
//            }
        }
    }
    
    public void printResult(){
        System.out.println("----- LOG INPUT -----");
        for (int i = 0; i < input.size(); i++) {
            System.out.println(input.get(i));
        }
        
        System.out.println("");
        System.out.println("----- LOG OUTPUT -----");
        for (int i = 0; i < output.size(); i++) {
            System.out.println(output.get(i));
        }
    }
}
