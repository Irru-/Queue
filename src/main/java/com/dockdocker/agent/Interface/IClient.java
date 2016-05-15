package com.dockdocker.agent.Interface;

import com.github.dockerjava.api.model.Container;
import com.google.gson.JsonObject;

import java.util.List;

/**
 * Created by lars on 22-4-16.
 */
public interface IClient {

    JsonObject request(String command);

    void startProcess(ProcessBuilder pb);

    void start(String id);

    void stop(String id);

    void kill (String id);

    void restart(String id);

    void create(String name);

    void remove(String id);

    void rename(String id, String newName);

    void inspect(String id);

    void stats();

    void log(String id);

    List<Container> getListContainers();

    void setContainerId(String id);

    void getBuild();
}
