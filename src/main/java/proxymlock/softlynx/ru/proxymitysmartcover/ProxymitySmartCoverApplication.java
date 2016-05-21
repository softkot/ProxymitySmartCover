package proxymlock.softlynx.ru.proxymitysmartcover;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;

/***
 * Main application class ontercepted to register some runtime intents ( screen on/off)
 * and ask to package manager to do not kill the service holding the proximity wake lock
 */
public class ProxymitySmartCoverApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                ProxymityWakeLockService.start(context);
            }
        }, new IntentFilter(Intent.ACTION_SCREEN_ON));

        getPackageManager().setComponentEnabledSetting(
                new ComponentName(this, ProxymityWakeLockService.class),
                PackageManager.COMPONENT_ENABLED_STATE_DEFAULT,
                PackageManager.DONT_KILL_APP);

        ProxymityWakeLockService.start(this);
    }
}
