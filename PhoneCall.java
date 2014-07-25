package nl.clicks.mywidget;

import android.app.Activity;
import android.app.KeyguardManager;
import android.app.admin.DevicePolicyManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.view.View.OnClickListener;

//public class PhoneCall
public class PhoneCall
{
    public static Intent newPhoneCallIntent(String phoneNumber){
        Log.i("PhoneCall", "newPhoneCallIntent  ");

        Intent callintent = new Intent(Intent.ACTION_CALL);
        callintent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        callintent.setData(Uri.parse("tel:" + phoneNumber));
        return callintent;
    }
}

