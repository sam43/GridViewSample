package com.example.asmsayem.gridviewsample.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by A.S.M Sayem on 12/18/2017.
 */

public class ResponsePathsPhoto {
    @SerializedName("messgae")
    @Expose
    private String messgae;
    @SerializedName("messageType")
    @Expose
    private String messageType;
    @SerializedName("path")
    @Expose
    private String path;

    public String getMessgae() {
        return messgae;
    }

    public void setMessgae(String messgae) {
        this.messgae = messgae;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
