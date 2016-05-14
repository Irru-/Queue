package com.dockdocker.agent.Interface;

import com.github.dockerjava.api.model.Container;

import java.util.List;

/**
 * Created by lars on 22-4-16.
 */
public interface IClient {

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

    void getBuild();

    List<Container> getListContainers();

    void setContainerId(String id);
}
