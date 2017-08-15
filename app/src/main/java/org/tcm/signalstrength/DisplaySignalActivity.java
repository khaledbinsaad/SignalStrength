package org.tcm.signalstrength;


import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.BatteryManager;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import java.util.Timer;
import java.util.TimerTask;


public class DisplaySignalActivity extends AppCompatActivity {
    int signalStrengthValue;
    SignalData signalData;
    private String filename;
    TelephonyManager telephonyManager;
    MyPhoneStateListener2 psListener;
    private TextView txtSignalStr;
    private TextView textView2;
    private GPSTracker gpsTracker;
    private WebView mWebView;
    long startTime = 0;
    long timeToLoad =0;
    private long doneTime =0;
    String loadUrl = "https://www.google.com.sa/";// "https://www.google.com";

    public long getDoneTime() {
        return doneTime;
    }

    public void setDoneTime(long doneTime) {
        this.doneTime = doneTime;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getTimeToLoad() {
        return timeToLoad;
    }

    public void setTimeToLoad(long timeToLoad) {
        this.timeToLoad = timeToLoad;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_signal);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        filename = intent.getStringExtra(MainActivity.EXTRA_MESSAGE[0]);

        signalData = new SignalData();
        filename="Data_"+System.currentTimeMillis()+"_file.csv";
        signalData.writeToFile(filename, true);



        // Capture the layout's TextView and set the string as its text
        textView2 = (TextView) findViewById(R.id.textView2);

        mWebView = (WebView) findViewById(R.id.webView);
        psListener = new MyPhoneStateListener2();

        long interval = Integer.parseInt(intent.getStringExtra(MainActivity.EXTRA_MESSAGE[1]))*1000;
        textView2.setText(filename+"\nTime Interval: "+interval);

        new Timer().scheduleAtFixedRate(timerTask(), (long)0, interval);

    }


    public TimerTask timerTask() {
        TimerTask t = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    public void run() {
                        task();
                    }
                });
            }
        };
        return t;
    }


    public void task(){
        signalData = new SignalData();

        try {
            Context context= getApplicationContext();
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networks = connectivityManager.getActiveNetworkInfo();
            android.net.NetworkInfo mobile = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            txtSignalStr = (TextView)findViewById(R.id.textView3);

            telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
            telephonyManager.listen(psListener,PhoneStateListener.LISTEN_SIGNAL_STRENGTHS);

            String   myAndroidDeviceId = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
            String deviceName = Settings.System.getString(getContentResolver(), "device_name");

            if(networks.isConnected()){
                signalData.setDeviceID(myAndroidDeviceId);
                signalData.setDeviceName(deviceName);
                signalData.setSysDateTime(System.currentTimeMillis());
                signalData.setAvailable(mobile.isAvailable());
                signalData.setCompanyName(getNetworkOperatorName());
                signalData.setConnectionType(networks.getTypeName());
                signalData.setConnectionSubType(networks.getSubtypeName());
                signalData.setConnectionState(networks.getState().toString());
                signalData.setConnectionReason(networks.getReason());
                signalData.setConnectionExtra(networks.getExtraInfo());
                signalData.setRoaming(networks.isRoaming());
                signalData.setFailover(networks.isFailover());

                signalData.setBatteryPerc(getBatteryPercentage(this));
                signalData.setBatteryTemperature(batteryTemperature(this));
                signalData.setBatteryVoltage(getVoltage());

                // check if GPS enabled
                gpsTracker = new GPSTracker(this);

                if (gpsTracker.getIsGPSTrackingEnabled()){
                    signalData.setLocationLatitude(gpsTracker.latitude);
                    signalData.setLocationLongitude(gpsTracker.longitude);
                    signalData.setLocationAltitude(gpsTracker.altitude);
                    signalData.setLocationAccuracy(gpsTracker.accuracy);
                    signalData.setLocationSpeed(gpsTracker.speed);
                    signalData.setLocationBearing(gpsTracker.bearing);
                }
                else{
                    textView2.setText("Error GPS");
                }
                setStartTime(System.currentTimeMillis());
                mWebView.loadUrl(loadUrl);

                mWebView.setWebViewClient(new WebViewClient() {
                    public void onPageFinished(WebView view, String url) {
                        setDoneTime(System.currentTimeMillis());
                        setTimeToLoad((getDoneTime()- getStartTime()));
                        if (getTimeToLoad()>0){
                            signalData.setTimeToLoad(getTimeToLoad());
                            signalData.writeToFile(filename, false);
                        }
                    }
                });

            }else {
                textView2.setText("NO");
            }
        }catch (Exception e){
            textView2.setText("Exp "+ e.toString());
        }
    }


    public static int getBatteryPercentage(Context context) {
        IntentFilter iFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = context.registerReceiver(null, iFilter);
        int level = batteryStatus != null ? batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1) : -1;
        int scale = batteryStatus != null ? batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1) : -1;
        float batteryPct = level / (float) scale;
        return (int) (batteryPct * 100);
    }

    public static float batteryTemperature(Context context){
        Intent intent = context.registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        float  temp   = ((float) intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE,0)) / 10;
        return temp;
    }

    public int getVoltage(){
        IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent b = this.registerReceiver(null, ifilter);
        return b.getIntExtra(BatteryManager.EXTRA_VOLTAGE, -1);
    }


    public String getNetworkOperatorName(){
        TelephonyManager tm = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        return(tm.getNetworkOperatorName());
    }


    private class MyPhoneStateListener2 extends PhoneStateListener {
        public void onSignalStrengthsChanged(SignalStrength signalStrength) {
            super.onSignalStrengthsChanged(signalStrength);
            if (signalStrength.isGsm()) {
                if (signalStrength.getGsmSignalStrength() != 99)
                    signalStrengthValue = signalStrength.getGsmSignalStrength() * 2 - 113;
                else
                    signalStrengthValue = signalStrength.getGsmSignalStrength();
            } else {
                signalStrengthValue = signalStrength.getCdmaDbm();
            }
            txtSignalStr.setText("Signal Strength : " + signalStrengthValue+ "\nTimeToLoad:"+getTimeToLoad());
            signalData.setSignalStrength(signalStrengthValue);
        }
    }

}

