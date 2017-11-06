/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daef.analysinglogfiles;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author abj
 */
public class DynamicAnalyser {

    ArrayList<String> input;
    HashMap<Integer, LogEntry> logs;
    ArrayList<String> output;

    public DynamicAnalyser() {
        input = new ArrayList<>();
        logs = new HashMap<>();
        output = new ArrayList<>();
    }

    public static void main(String[] args) {

        DynamicAnalyser da = new DynamicAnalyser();

        da.addInstance(1000, 1, "A", System.currentTimeMillis() - 35000);
        da.addInstance(1000, 1, "B", System.currentTimeMillis() - 34000);
        da.addInstance(1000, 1, "C", System.currentTimeMillis() - 33000);
        da.addInstance(1000, 1, "D", System.currentTimeMillis() - 32000);
        da.addInstance(1000, 1, "F", System.currentTimeMillis() - 31000);
        da.addInstance(1000, 1, "F", System.currentTimeMillis() - 30500);
        da.addInstance(1000, 1, "G", System.currentTimeMillis() - 30200);

        da.addInstance(1000, 2, "A", System.currentTimeMillis() - 5000);
        da.addInstance(1000, 2, "B", System.currentTimeMillis() - 4000);
        da.addInstance(1000, 2, "C", System.currentTimeMillis() - 3000);
        da.addInstance(1000, 2, "B", System.currentTimeMillis() - 2000);
        da.addInstance(1000, 2, "E", System.currentTimeMillis() - 1000);

        da.addInstance(1000, 3, "A", System.currentTimeMillis() - 25000); //warning %%%
        da.addInstance(1000, 3, "B", System.currentTimeMillis() - 22000); //warning ###

        da.checkTimeStamp();
        da.printResult();
    }

    public void addInstance(int systemID, int instanceID, String actionID, long timeStamp) {
        Date d = new Date(timeStamp);
        input.add("SystemID: " + systemID + "  InstanceID: " + instanceID + "  ActionID: " + actionID + "  TimeStamp: " + d.toString());
        LogEntry log = logs.get(instanceID);

        if (log == null) {
            LogEntry tmpLog = new LogEntry(systemID, instanceID, actionID, timeStamp);
            tmpLog.setState(2);
            logs.put(tmpLog.getInstanceID(), tmpLog);
        } else {
            log.setActionID(actionID);
            log.setTimestamp(timeStamp);

            switch (log.getState()) {
                case 2:
                    if (log.getActionID() == "B" || log.getActionID() == "C") {
                        log.setLevel(LogEntry.Level.INFORMATION);
                    } else if (log.getActionID() == "D") {
                        log.setLevel(LogEntry.Level.INFORMATION);
                        log.setState(3);
                    } else if (log.getActionID() == "E") {
                        log.setLevel(LogEntry.Level.INFORMATION);
                        log.setState(4);
                    } else {
                        log.setLevel(LogEntry.Level.ERROR);
                    }
                    break;
                case 3:
                    if (log.getActionID() == "F") {
                        log.setLevel(LogEntry.Level.INFORMATION);
                    } else if (log.getActionID() == "G") {
                        log.setLevel(LogEntry.Level.INFORMATION);
                        log.setState(4);
                    } else {
                        log.setLevel(LogEntry.Level.ERROR);
                    }
                    break;
                case 4:
                    log.setLevel(LogEntry.Level.ERROR);
                    break;
            }

            logs.put(log.getInstanceID(), log);
            output.add("Level: " + log.getLevel() + "  SystemID: " + log.getSystemID() + "  InstanceID: " + log.getInstanceID() + "  ActionID: " + log.getActionID());
        }
    }

    public void checkTimeStamp() {
        for (Map.Entry<Integer, LogEntry> entry : logs.entrySet()) {
            LogEntry log = entry.getValue();
            if (log.getState() != 4) {
                if (log.getTimestamp() < System.currentTimeMillis() - 20000) {
                    log.setLevel(LogEntry.Level.WARNING);
                    logs.put(entry.getKey(), log);
                }
            }
        }
    }

    public void printResult() {
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
