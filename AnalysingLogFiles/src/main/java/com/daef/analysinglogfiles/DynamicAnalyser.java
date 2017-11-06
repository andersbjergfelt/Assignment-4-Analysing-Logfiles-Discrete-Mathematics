/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daef.analysinglogfiles;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author abj
 */
public class DynamicAnalyser {
    
    FSA fsa;
    
    public static void main(String[] args) {
        
        ArrayList<LogEntry> logs = new ArrayList();
        
        logs.add(new LogEntry(1000, 1, "A", System.currentTimeMillis()-6000, 1));
        logs.add(new LogEntry(1000, 1, "B", System.currentTimeMillis()-5000, 2));
        logs.add(new LogEntry(1000, 1, "C", System.currentTimeMillis()-4000, 2));
        logs.add(new LogEntry(1000, 1, "D", System.currentTimeMillis()-3000, 2));
        logs.add(new LogEntry(1000, 1, "F", System.currentTimeMillis()-1000, 3));
        logs.add(new LogEntry(1000, 1, "G", System.currentTimeMillis()-500, 3));
        
        fsa = new FSA(logs);
        
    }
}
