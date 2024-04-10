package com.blissos.updatersdk;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


public class UpdaterManager {
    /**
     * Callback interface when service is connected/disconnected.
     */
    public interface ServiceConnectionListener {
        /**
         * Called when service is connected.
         */
        void onServiceConnected();

        /**
         * Called when service is disconnected.
         */
        void onServiceDisconnected();
    }

    private static final String TAG = "UpdaterManager";
    private static final boolean DEBUG = false;

    private IUpdater sService;
    private Context mContext;
    private final Intent mServiceIntent;

    private ServiceConnectionListener mConnectionListener;
    private ServiceConnection mServiceConnection;


    public UpdaterManager(Context context, ServiceConnectionListener listener) {
        Context appContext = context.getApplicationContext();
        mContext = appContext == null ? context : appContext;
        mConnectionListener = listener;
        mServiceIntent = new Intent("UpdaterPublicService");
        mServiceIntent.setPackage("com.blissroms.updater");
        mServiceConnection = createServiceConnection();
    }

    /**
     * Start the controller.
     */
    public void start() {
        mContext.bindServiceAsUser(mServiceIntent, mServiceConnection, Context.BIND_AUTO_CREATE,
                android.os.Process.myUserHandle());
    }

    /**
     * Stop the controller.
     */
    public void stop() {
        if (sService != null) {
            sService = null;
            mContext.unbindService(mServiceConnection);
        }
    }

    /**
     * @return true if service is valid
     */
    private boolean checkService() {
        if (sService == null) {
            Log.w(TAG, "not connected to Updater service");
            return false;
        }
        return true;
    }

    public void setCallback(IUpdaterCallback cb) {
        try {
            if (checkService())
                sService.setCallback(cb);
        } catch (RemoteException e) {
            Log.e(TAG, e.getLocalizedMessage(), e);
        }
    }

    public void checkForUpdates() {
        try {
            if (checkService())
                sService.checkForUpdates();
        } catch (RemoteException e) {
            Log.e(TAG, e.getLocalizedMessage(), e);
        }
    }

    public List<UpdateItemInfo> getAvaliableUpdates() {
        try {
            if (checkService())
                return sService.getAvaliableUpdates();
        } catch (RemoteException e) {
            Log.e(TAG, e.getLocalizedMessage(), e);
        }

        return new ArrayList<>();
    }

    public void downloadUpdate(String id) {
        try {
            if (checkService())
                sService.downloadUpdate(id);
        } catch (RemoteException e) {
            Log.e(TAG, e.getLocalizedMessage(), e);
        }
    }

    public void pauseDownload(String id) {
        try {
            if (checkService())
                sService.pauseDownload(id);
        } catch (RemoteException e) {
            Log.e(TAG, e.getLocalizedMessage(), e);
        }
    }

    public void resumeDownload(String id) {
        try {
            if (checkService())
                sService.resumeDownload(id);
        } catch (RemoteException e) {
            Log.e(TAG, e.getLocalizedMessage(), e);
        }
    }

    public void installUpdate(String id) {
        try {
            if (checkService())
                sService.installUpdate(id);
        } catch (RemoteException e) {
            Log.e(TAG, e.getLocalizedMessage(), e);
        }
    }

    public void cancelUpdate() {
        try {
            if (checkService())
                sService.cancelUpdate();
        } catch (RemoteException e) {
            Log.e(TAG, e.getLocalizedMessage(), e);
        }
    }

    public void suspendUpdate() {
        try {
            if (checkService())
                sService.suspendUpdate();
        } catch (RemoteException e) {
            Log.e(TAG, e.getLocalizedMessage(), e);
        }
    }

    public void resumeUpdate() {
        try {
            if (checkService())
                sService.resumeUpdate();
        } catch (RemoteException e) {
            Log.e(TAG, e.getLocalizedMessage(), e);
        }
    }

    public void importUpdate(ParcelFileDescriptor pfd) {
        try {
            if (checkService())
                sService.importUpdate(pfd);
        } catch (RemoteException e) {
            Log.e(TAG, e.getLocalizedMessage(), e);
        }
    }

    /**
     * Create a new {@link ServiceConnection} object to handle service connect/disconnect event.
     */
    private ServiceConnection createServiceConnection() {
        return new ServiceConnection() {

            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                if (DEBUG) {
                    Log.d(TAG, "Service is connected");
                }
                sService = IUpdater.Stub.asInterface(service);
                if (mConnectionListener != null) {
                    mConnectionListener.onServiceConnected();
                }
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                if (mConnectionListener != null) {
                    sService = null;
                    mConnectionListener.onServiceDisconnected();
                }
            }
        };
    }
}
