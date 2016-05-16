package com.dockdocker.agent.Client;

import com.dockdocker.agent.Interface.IClient;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.Container;
import com.github.dockerjava.core.DockerClientBuilder;
import com.google.gson.JsonObject;

import java.io.*;
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

    @Override
    public JsonObject request(String command) {
        ProcessBuilder pb = new ProcessBuilder("curl", "--unix-socket", "/var/run/docker.sock", command);
        try {
            pb.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void startProcess(ProcessBuilder pb) {
        try {
            pb.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start(String id) {
        ProcessBuilder pb = new ProcessBuilder("curl", "-X", "POST", "--unix-socket", "/var/run/docker.sock", "https:/containers/" + containerId + "/start");
        System.out.println(id + " is starting..");
        startProcess(pb);
    }

    public void stop(String id) {
        ProcessBuilder pb = new ProcessBuilder("curl", "-X", "POST", "--unix-socket", "/var/run/docker.sock", "https:/containers/" + containerId + "/stop");
        System.out.println(id + " is stopping..");
        startProcess(pb);
    }

    public void kill(String id) {
        ProcessBuilder pb = new ProcessBuilder("curl", "-X", "POST", "--unix-socket", "/var/run/docker.sock", "https:/containers/" + containerId + "/kill");
        startProcess(pb);
    }

    public void restart(String id) {
        ProcessBuilder pb = new ProcessBuilder("curl", "-X", "POST", "--unix-socket", "/var/run/docker.sock", "https:/containers/" + containerId + "/restart");
        startProcess(pb);
    }

    public void create(String name) {
        String containerName = "generated_" + new SecureRandom().nextInt();
        docker.createContainerCmd(name).withName(containerName).exec();
    }

    public void remove(String id) {
        ProcessBuilder pb = new ProcessBuilder("curl", "-X", "DELETE", "--unix-socket", "/var/run/docker.sock", "https:/containers/" + containerId);
        startProcess(pb);
    }

    public void rename(String id, String newName) {
        ProcessBuilder pb = new ProcessBuilder("curl", "-X", "POST", "--unix-socket", "/var/run/docker.sock", "https:/containers/" + containerId + "/rename?name" + newName);
        startProcess(pb);
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
            case "STOP":
                stop(containerId);
                break;
            case "KILL":
                kill(containerId);
                break;
            case "RESTART":
                restart(containerId);
                break;
            case "CREATE":
                create(containerId);
                break;
            case "REMOVE":
                remove(containerId);
                break;
//            case "RENAME":
//                rename(containerId);
//                break;
            case "INSPECT":
                inspect(containerId);
                break;
            case "STATS":
                stats();
                break;
            case "LOG":
                log(containerId);
                break;
            default:
                throw new IllegalArgumentException("Invalid command: " + command.toUpperCase());
        }
    }
}
