package ru.softlynx.android.proxymitysmartcover;

import android.app.Activity;
import android.content.ComponentName;
import android.content.pm.PackageManager;
import android.os.Bundle;

/***
 * Actially empty activity to run it once from user and
 * hide the launcher icon and start the service
 */
public class SilentSetup extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ProxymityWakeLockService.start(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getPackageManager().setComponentEnabledSetting(
                new ComponentName(this, SilentSetup.class),
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);
        finish();
    }
}
