package com.lesmtech.aidlserver;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.lesmtech.android.IRemoteService;

/**
 * @author Rindt
 * @version 0.1
 * @since 2/25/16
 */
public class AidlService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return bind;
    }

    IRemoteService.Stub bind = new IRemoteService.Stub() {
        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public int getPid() throws RemoteException {
            Log.e("AIDL_DEMO_SERVER", "Get Process Id");
            return Process.myPid();
        }
    };
}
