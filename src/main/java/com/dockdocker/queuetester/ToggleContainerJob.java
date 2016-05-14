/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dockdocker.queuetester;

import com.dockdocker.agent.Client.ClientHandlerFactory;
import com.dockdocker.agent.Interface.IClient;

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
        ClientHandlerFactory clientFactory = new ClientHandlerFactory();
        IClient docker = clientFactory.init("DOCKER", arg1);
        docker.setContainerId(docker.getListContainers().get(0).getId());
        Thread dockerTask = new Thread((Runnable) docker);
        dockerTask.start();
    }
    
}
