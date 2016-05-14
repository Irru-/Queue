package com.dockdocker.agent.Client;

import com.dockdocker.agent.Interface.IClient;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.Container;
import com.github.dockerjava.core.DockerClientBuilder;

import java.security.SecureRandom;
import java.util.List;

/**
 * Created by lars on 22-4-16.
 */
public class DockerClientHandler implements
        IClient,
        Runnable {

    private DockerClient docker;
    private String command;
    private String containerId;

    DockerClientHandler(String command) {
        getBuild();
        this.command = command;
    }

    public void getBuild() {
        docker = DockerClientBuilder.getInstance("unix:///var/run/docker.sock").build();
    }

    public List<Container> getListContainers() {
        List<Container> containers = docker.listContainersCmd().withShowAll(true).exec();
        return containers;
    }

    public void setContainerId(String id) {
        this.containerId = id;
    }

    public void start(String id) {
//        docker.startContainerCmd(id).exec();
        System.out.println("start");
    }

    public void stop(String id) {
        docker.stopContainerCmd(id).withTimeout(2).exec();
    }

    public void kill(String id) {
        docker.killContainerCmd(id);
    }

    public void restart(String id) {
        docker.restartContainerCmd(id).withtTimeout(2).exec();
    }

    public void create(String name) {
        String containerName = "generated_" + new SecureRandom().nextInt();
        docker.createContainerCmd(name).withName(containerName).exec();
    }

    public void remove(String id) {
        docker.removeContainerCmd(id);
    }

    public void rename(String id, String newName) {
//        rename bestaat niet meer in library(?)
    }

    public void inspect(String id) {
        docker.inspectContainerCmd(id);
    }

    public void stats() {
        docker.statsCmd();
    }

    public void log(String id) {
        docker.logContainerCmd(id);
    }

    public void run() {
        switch (command) {
            case "STARTING":
                start(containerId);
                break;
            case "stop":
                stop(containerId);
                break;
            case "kill":
                kill(containerId);
                break;
            case "restart":
                restart(containerId);
                break;
            case "create":
                create(containerId);
                break;
            case "remove":
                remove(containerId);
                break;
//            case "rename":
//                rename(containerId);
//                break;
            case "inspect":
                inspect(containerId);
                break;
            case "stats":
                stats();
                break;
            case "log":
                log(containerId);
                break;
            default:
                throw new IllegalArgumentException("Invalid command: " + command);
        }
    }
}
