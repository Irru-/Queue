/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dockdocker.queuetester;

import com.dockdocker.agent.Client.ClientHandlerFactory;
import com.dockdocker.agent.Configuration.DockDockerGetProperties;
import com.dockdocker.agent.Interface.IClient;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
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
        IClient docker = clientFactory.init("DOCKER", "STOP");
        docker.setContainerId(docker.getListContainers().get(0).getId());
        Thread dockerTask = new Thread((Runnable) docker);
        dockerTask.start();

        /**
         * Following code needs to be implemented correctly when the web interface supports
         * multiple containers commands to be send simultaneously
         * This function could be useful when you can filter all, for example, Nginx containers and
         * start them all together with 1 button
         */
//        DockDockerGetProperties properties = new DockDockerGetProperties();
//        int threadValue = 1;
//        try {
//            threadValue = Integer.parseInt(properties.getProperty("thread_maximum"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        ExecutorService executor = Executors.newFixedThreadPool(threadValue);
//
//        for (int i = 0; i < 20; i++) {
//            docker.setContainerId(docker.getListContainers().get(i).getId());
//            executor.submit((Runnable) docker);
//        }
//
//        executor.shutdown();
//        System.out.println("[" + new Date(System.currentTimeMillis()) + "]: All start commands submitted..");
//
//        try {
//            executor.awaitTermination(1, TimeUnit.DAYS);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println("[" + new Date(System.currentTimeMillis()) + "]: All start commands completed!");
    }

}
