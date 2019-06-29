package com.kshitizbali.twineup.utilities;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.UnderlineSpan;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.stetho.Stetho;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.kshitizbali.twineup.R;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class Utils {

    /**
     * Initialize stetho test lib.
     */
    public static void initializeStetho(Context context) {

        Stetho.initialize(Stetho.newInitializerBuilder(context)
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(context))
                .build());
    }

    /*
     * Checks if WiFi or 3G is enabled or not. server
     */
    public static boolean isInternetAvailable(Context context) {
        return isWiFiAvailable(context) || isMobileDateAvailable(context);
    }

    /**
     * Checks if the WiFi is enabled on user's device
     */
    public static boolean isWiFiAvailable(Context context) {
        // ConnectivityManager is used to check available wifi network.
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo network_info = connectivityManager.getActiveNetworkInfo();
        // Wifi network is available.
        return network_info != null
                && network_info.getType() == ConnectivityManager.TYPE_WIFI;
    }

    /**
     * Checks if the mobile data is enabled on user's device
     */
    public static boolean isMobileDateAvailable(Context context) {
        // ConnectivityManager is used to check available 3G network.
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        // 3G network is available.
        return networkInfo != null
                && networkInfo.getType() == ConnectivityManager.TYPE_MOBILE;
    }


    public static void setAvatar(Context context, String data, ImageView target) {
        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
        builder.build().load(data)
                .placeholder((R.drawable.placeholder))
                .error(R.drawable.placeholder_error)
                .into(target);
    }

    public static void setTextStyle(TextView textview) {

        textview.setSelected(true);
        textview.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        textview.setHorizontallyScrolling(true);
        textview.setSingleLine(true);
        textview.setLines(1);
    }

    public static String getCorrectDate(String date) {
        //String date="2019-06-04";
        /*String date="Mar 10, 2016 6:30:00 PM";*/
        SimpleDateFormat spf = new SimpleDateFormat("yyyy-MM-DD", Locale.getDefault());
        /*SimpleDateFormat spf=new SimpleDateFormat("MMM dd, yyyy hh:mm:ss aaa");*/
        Date newDate = null;
        try {
            newDate = spf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        spf = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
        date = spf.format(newDate);
        return date;
    }


    public static String getCorrectTime(String time) {
        /*"Mar 10, 2016 6:30:00 PM"*/
        SimpleDateFormat spf = new SimpleDateFormat("hh:mm:ss", Locale.getDefault());
        /*SimpleDateFormat spf=new SimpleDateFormat("MMM dd, yyyy hh:mm:ss aaa");*/
        Date newDate = null;
        try {
            newDate = spf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        spf = new SimpleDateFormat("hh:mm aa", Locale.getDefault());
        time = spf.format(newDate);
        return time;
    }

    public static String getDateForAPI(String date) {
        //String date="2019-06-04";
        /*String date="Mar 10, 2016 6:30:00 PM";*/
        SimpleDateFormat spf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        /*SimpleDateFormat spf=new SimpleDateFormat("MMM dd, yyyy hh:mm:ss aaa");*/
        Date newDate = null;
        try {
            newDate = spf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        spf = new SimpleDateFormat("MMddyyyy", Locale.getDefault());
        date = spf.format(newDate);
        return date;
    }

    public static String getTodaysDate() {
        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        SimpleDateFormat df = new SimpleDateFormat("MMddyyyy", Locale.getDefault());
        /*String formattedDate = df.format(c);*/
        return df.format(c);
    }

    public static String getTomorrowsDate() {
        Calendar calendar = Calendar.getInstance();
        Date c = calendar.getTime();

        SimpleDateFormat df = new SimpleDateFormat("MMddyyyy", Locale.getDefault());

        calendar.add(Calendar.DAY_OF_YEAR, 1);
        c = calendar.getTime();
        // String tomorrow_Date = df.format(c);
        /*String formattedDate = df.format(c);*/
        return df.format(c);
    }


    public static void setTextViewUnderline(TextView textViewUnderline, String text) {
        SpannableString spanStr = new SpannableString(text);
        spanStr.setSpan(new UnderlineSpan(), 0, spanStr.length(), 0);

        textViewUnderline.setText(spanStr);
    }


    public static void setActivityLabelColor(View view) {
        int actionBarTitleId = Resources.getSystem().getIdentifier("action_bar_title", "id", "android");
        if (actionBarTitleId > 0) {
            TextView title = (TextView) view.findViewById(actionBarTitleId);
            if (title != null) {
                title.setTextColor(Color.BLACK);
            }
        }
    }


    public static List<String> getActivityList() {
        List<String> list = new ArrayList<>();
        list.add("Lucie");
        list.add("James");
        list.add("Lucie");
        list.add("James");
        list.add("Lucie");
        list.add("James");

        return list;
    }


}
