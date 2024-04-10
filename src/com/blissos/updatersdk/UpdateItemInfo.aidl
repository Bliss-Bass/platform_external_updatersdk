package com.blissos.updatersdk;

parcelable UpdateItemInfo {
    String name;
    String downloadUrl;
    String downloadId;
    long timestamp;
    String version;
    long fileSize;

    int status;
    int persistentStatus;
    int progress;
    long eta;
    long speed;
    int installProgress;
    boolean availableOnline;
    boolean finalizing;
}
