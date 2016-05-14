package com.dockdocker.agent.Client;

import com.dockdocker.agent.Interface.IClient;

/**
 * Created by lars on 22-4-16.
 */
public class ClientHandlerFactory {
    public IClient init(String clientType, String command) {
        if (clientType == null)
            return null;

        if (clientType.equalsIgnoreCase("DOCKER"))
            return new DockerClientHandler(command);

        return null;
    }
}
