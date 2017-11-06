/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daef.analysinglogfiles;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author abj
 */
public class FSA {
    
    
    public List<Integer> listOfInstances = new ArrayList<>();
    
    public HashMap<Integer, LogEntry> instances  = new HashMap();        
    
    //checks if instance is already defined
    private boolean checkForInstance(int instanceID){
        
        return instances.containsKey(instanceID);
    }
    
    private void addInstance(LogEntry entry){
        
        //not stuck at a non-final state
        
        instances.put(entry.getInstanceID(), entry);
        listOfInstances.add(entry.getInstanceID());
          
    }
    
    //Give a list of the running instances
    public List<Integer> getListOfInstances() {
        return listOfInstances;
    }
 
    
}
