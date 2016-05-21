package ru.softlynx.android.proxymitysmartcover;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.PowerManager;

/***
 * Background service holds the proximity wake lock and check it active
 * at any time it is called from application calls
 * (user start, receiver, device boot, phone state changes)
 */
public class ProxymityWakeLockService extends Service {
    PowerManager powerManager;
    PowerManager.WakeLock proxymityLock = null;

    public ProxymityWakeLockService() {
    }

    public static void start(Context context) {
        context.startService(new Intent(context.getApplicationContext(), ProxymityWakeLockService.class));
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new Binder();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        powerManager = (PowerManager) getSystemService(POWER_SERVICE);
        proxymityLock = powerManager.newWakeLock(PowerManager.PROXIMITY_SCREEN_OFF_WAKE_LOCK, "ProximityLock");

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (!proxymityLock.isHeld()) {
            proxymityLock.acquire();
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        proxymityLock.release();
        super.onDestroy();
    }
}
