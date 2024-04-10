package com.blissos.updatersdk;

public class UpdateItemStatus {
    public static final int UNKNOWN = 0;
    public static final int STARTING = 1;
    public static final int DOWNLOADING = 2;
    public static final int PAUSED = 3;
    public static final int PAUSED_ERROR = 4;
    public static final int DELETED = 5;
    public static final int VERIFYING = 6;
    public static final int VERIFIED = 7;
    public static final int VERIFICATION_FAILED = 8;
    public static final int INSTALLING = 9;
    public static final int INSTALLED = 10;
    public static final int INSTALLATION_FAILED = 11;
    public static final int INSTALLATION_CANCELLED = 12;
    public static final int INSTALLATION_SUSPENDED = 13;

    public static final class Persistent {
        public static final int UNKNOWN = 0;
        public static final int INCOMPLETE = 1;
        public static final int VERIFIED = 2;
    }
}
