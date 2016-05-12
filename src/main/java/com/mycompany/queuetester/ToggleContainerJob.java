/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.queuetester;

/**
 *
 * @author nick
 */
public class ToggleContainerJob implements Runnable {
    final String arg1;
    
    public ToggleContainerJob(String arg1) {
        this.arg1 = arg1;
    }

    @Override
    public void run() {
        System.out.println("New status of container:" + arg1);
    }
    
}
