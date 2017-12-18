
package com.example.asmsayem.gridviewsample.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PhotoInfoModel {

    @SerializedName("messageType")
    @Expose
    private String messageType;
    @SerializedName("userId")
    @Expose
    private String userId;
    @SerializedName("decodId")
    @Expose
    private String decodId;
    @SerializedName("FolderName")
    @Expose
    private String folderName;
    @SerializedName("FolderId")
    @Expose
    private String folderId;
    @SerializedName("ImageName")
    @Expose
    private String imageName;
    @SerializedName("IsResumeUpdate")
    @Expose
    private String isResumeUpdate;

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDecodId() {
        return decodId;
    }

    public void setDecodId(String decodId) {
        this.decodId = decodId;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public String getFolderId() {
        return folderId;
    }

    public void setFolderId(String folderId) {
        this.folderId = folderId;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getIsResumeUpdate() {
        return isResumeUpdate;
    }

    public void setIsResumeUpdate(String isResumeUpdate) {
        this.isResumeUpdate = isResumeUpdate;
    }

}
