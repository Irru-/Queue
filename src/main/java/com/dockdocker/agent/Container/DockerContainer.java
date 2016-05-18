package com.dockdocker.agent.Container;

import com.google.gson.JsonObject;

/**
 * Created by lars on 18-5-16.
 */
public class DockerContainer {

    private String id;
    private String image;

    public DockerContainer(JsonObject json){
        this.id = json.get("id").getAsString();
        this.image = json.get("image").getAsString();
    }

    public String getId() {
        return id;
    }

    public String getImage() {
        return image;
    }
}
