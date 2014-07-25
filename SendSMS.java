package nl.clicks.mywidget;

import android.app.Activity;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Simon on 12-7-2014.
 */
public class SendSMS
{

    public void sendSMS()
    {
        try {
            // Get the default instance of the SmsManager
            SmsManager smsManager = SmsManager.getDefault();
            //smsManager.sendTextMessage("tel:+31620789755", null, "Test SMS", null, null);

            //Log.i("sendSMS", "Your sms has successfully sent!");
            //Toast.makeText(getApplicationContext(), "Your sms has successfully sent!", Toast.LENGTH_LONG).show();
        } catch (Exception ex) {
           // Toast.makeText(getApplicationContext(), "Your sms has failed...", Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }
    }
}
