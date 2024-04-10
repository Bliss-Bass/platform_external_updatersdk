package com.blissos.updatersdk;

import com.blissos.updatersdk.UpdateItemInfo;

interface IUpdaterCallback {
    void onUpdateCheckCompleted(boolean hasNewUpdates);

    void onStatusChange(in UpdateItemInfo update);
    void onDownloadProgressChange(in UpdateItemInfo update);
    void onInstallProgress(in UpdateItemInfo update);

    void onImportStarted();
    void onImportCompleted(in UpdateItemInfo update);
}
