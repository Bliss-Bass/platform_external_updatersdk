package com.blissos.updatersdk;

import com.blissos.updatersdk.UpdateItemInfo;
import com.blissos.updatersdk.IUpdaterCallback;

interface IUpdater {
    void setCallback(IUpdaterCallback cb);
    void checkForUpdates();
    List<UpdateItemInfo> getAvaliableUpdates();
    void downloadUpdate(String id);
    void pauseDownload(String id);
    void resumeDownload(String id);
    void installUpdate(String id);
    void cancelUpdate();
    void suspendUpdate();
    void resumeUpdate();

    void importUpdate(in ParcelFileDescriptor pfd);
}
