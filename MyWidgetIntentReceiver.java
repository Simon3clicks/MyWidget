package nl.clicks.mywidget;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.RemoteViews;

public class MyWidgetIntentReceiver extends BroadcastReceiver
{

    private static int clickCount = 0;

    @Override
    public void onReceive(Context context, Intent intent)
    {
        if (intent.getAction().equals("nl.clicks.intent.action.ICE_MAIN")) {
            Log.i("onReceive", "CHANGE_PICTURE");
            updateWidgetPictureAndButtonListener(context);

            startICE(context);
        }
        else if (intent.getAction().equals("nl.clicks.intent.action.PERSONAL_INFO")){
            Log.i("onReceive", "PERSONAL_INFO");
            openWidgetActivity(context, "nl.clicks.mywidget", "nl.clicks.mywidget.PersonalInfoWidgetActivity");
        }
        else if (intent.getAction().equals("nl.clicks.intent.action.IMPOPRTANT_PEOPLE"))
        {
            Log.i("onReceive", "IMPOPRTANT_PEOPLE");
            openWidgetActivity(context, "nl.clicks.mywidget", "nl.clicks.mywidget.PersonalInfoWidgetActivity");
        }
        else if (intent.getAction().equals("nl.clicks.intent.action.ALARM_NUMBERS"))
        {
            Log.i("onReceive", "ALARM_NUMBERS");
            openWidgetActivity(context, "nl.clicks.mywidget", "nl.clicks.mywidget.PersonalInfoWidgetActivity");
        }
        else if (intent.getAction().equals("nl.clicks.intent.action.MEDICAL"))
        {
            Log.i("onReceive", "MEDICAL");
            openWidgetActivity(context, "nl.clicks.mywidget", "nl.clicks.mywidget.PersonalInfoWidgetActivity");
        }
        else{}
    }



    private void startICE(Context context)
    {

        context.startActivity(PhoneCall.newPhoneCallIntent("+31620789755"));

/* working*/
        SendSMS sms = new SendSMS();
        sms.sendSMS();
    }

    private void openWidgetActivity(Context context, String string1, String string2)
    {
        Intent i = new Intent();
        i.setClassName(string1, string2);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }

    private void updateWidgetPictureAndButtonListener(Context context)
    {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.my_widget_layout);
        remoteViews.setImageViewResource(R.id.widget_image, getImageToSet());

        //REMEMBER TO ALWAYS REFRESH YOUR BUTTON CLICK LISTENERS!!!
        remoteViews.setOnClickPendingIntent(R.id.ice_button, MyWidgetProvider.icePendingIntent(context));

        MyWidgetProvider.pushWidgetUpdate(context.getApplicationContext(), remoteViews);
    }

    private int getImageToSet()
    {
        clickCount++;
        return clickCount % 2 == 0 ? R.drawable.wordpress_icon : R.drawable.me;
    }
}
