package nl.clicks.mywidget;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import java.util.Locale;

public class MyWidgetProvider extends AppWidgetProvider
{
    private int WIDGETHEIGHT = 150;

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds)
    {
        final int N = appWidgetIds.length;
        for (int i=0; i<N; i++) {
            // for every instance of the widget
            int appWidgetId = appWidgetIds[i];

            // set widget layout
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.my_widget_layout);

            // set the button intents
            remoteViews.setOnClickPendingIntent(R.id.ice_button, icePendingIntent(context));
            remoteViews.setOnClickPendingIntent(R.id.personal_info_button, personalInfoPendingIntent(context));
            remoteViews.setOnClickPendingIntent(R.id.important_people_button, importantPeoplePendingIntent(context));
            remoteViews.setOnClickPendingIntent(R.id.alarm_numbers_button, alarmNumbersPendingIntent(context));
            remoteViews.setOnClickPendingIntent(R.id.medical_button, medicalPendingIntent(context));

            // update te widget
            pushWidgetUpdate(context, remoteViews);
            onAppWidgetOptionsChanged(context, appWidgetManager, appWidgetId, null);
        }
    }

    public static PendingIntent icePendingIntent(Context context)
    {
        Intent intent = new Intent();
        intent.setAction("nl.clicks.intent.action.ICE_MAIN");
        return PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
   }


    public static PendingIntent personalInfoPendingIntent(Context context)
    {
        //Log.i("onUpdate", "personalInfoPendingIntent");
        Intent intent = new Intent();
        intent.setAction("nl.clicks.intent.action.PERSONAL_INFO");
        return PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    public static PendingIntent importantPeoplePendingIntent(Context context)
    {
        //Log.i("onUpdate", "importantPeoplePendingIntent");
        Intent intent = new Intent();
        intent.setAction("nl.clicks.intent.action.IMPOPRTANT_PEOPLE");
        return PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    public static PendingIntent alarmNumbersPendingIntent(Context context)
    {
        //Log.i("onUpdate", "alarmNumbersPendingIntent");
        Intent intent = new Intent();
        intent.setAction("nl.clicks.intent.action.ALARM_NUMBERS");
        return PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    public static PendingIntent medicalPendingIntent(Context context)
    {
        //Log.i("onUpdate", "medicalPendingIntent");
        Intent intent = new Intent();
        intent.setAction("nl.clicks.intent.action.MEDICAL");
        return PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    public static void pushWidgetUpdate(Context context, RemoteViews remoteViews)
    {
        ComponentName myWidget = new ComponentName(context, MyWidgetProvider.class);
        AppWidgetManager manager = AppWidgetManager.getInstance(context);
        manager.updateAppWidget(myWidget, remoteViews);
    }

    public void onAppWidgetOptionsChanged(Context context, AppWidgetManager mgr, int appWidgetId, Bundle newOptions)
    {
        /*
        *  todo     WIDGETHEIGHT will return 0 after onupdate()- should return 134
        * */

        RemoteViews updateViews = new RemoteViews(context.getPackageName(), R.layout.my_widget_layout);
        Bundle options = mgr.getAppWidgetOptions(appWidgetId);

        //Log.i("onAppWidgetOptionsChanged", "WIDGETHEIGHT 1 = " + WIDGETHEIGHT);
        int heigtOld = WIDGETHEIGHT;
        int heightNew = options.getInt(AppWidgetManager.OPTION_APPWIDGET_MIN_HEIGHT);
        //int maxHeight = options.getInt(AppWidgetManager.OPTION_APPWIDGET_MAX_HEIGHT);

        //Log.i("onAppWidgetOptionsChanged", "heigtOld = " + String.valueOf(heigtOld));
       // Log.i("onAppWidgetOptionsChanged", "heightNew = " + String.valueOf(heightNew));

        if (heigtOld < heightNew){
            //Log.i("onAppWidgetOptionsChanged", "minHeight < maxHeight");
            updateViews.setViewVisibility(R.id.ice_button, View.GONE);
            updateViews.setViewVisibility(R.id.ice_button_disabled, View.VISIBLE);

        }
        else  {
           // Log.i("onAppWidgetOptionsChanged", "minHeight !< maxHeight");
            updateViews.setViewVisibility(R.id.ice_button, View.VISIBLE);
            updateViews.setViewVisibility(R.id.ice_button_disabled, View.GONE);

        }

        this.WIDGETHEIGHT = heightNew;

        //Log.i("onAppWidgetOptionsChanged", "WIDGETHEIGHT 2 = " + WIDGETHEIGHT);

        mgr.updateAppWidget(appWidgetId, updateViews);
    }
}