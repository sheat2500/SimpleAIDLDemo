package com.lesmtech.aidlclientdemo;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.lesmtech.android.IRemoteService;

public class MainActivity extends AppCompatActivity {

    IRemoteService remoteService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initService();
    }

    private void initService() {
        Intent intent = new Intent();
        // above LOLLIPOP, intent should spicfy package name
        intent.setAction("com.lesmtech.aidl.AIDL_SERVER");
        intent.setPackage("com.lesmtech.aidlserver");
        ServiceConnection conn = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                try {
                    remoteService = IRemoteService.Stub.asInterface(service);
                    Log.e("AIDL_DEMO", "Service Connected.");
                    Log.e("AIDL_DEMO", "RemoteService: " + remoteService.getPid());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                Log.e("AIDL_DEMO: ", "ProcessID:" + Process.myPid());
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };
        bindService(intent, conn, Context.BIND_AUTO_CREATE);
    }

}
