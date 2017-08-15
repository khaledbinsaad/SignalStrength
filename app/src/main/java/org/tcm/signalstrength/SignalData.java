package org.tcm.signalstrength;

import android.os.Environment;
import java.io.File;
import java.io.FileOutputStream;


/**
 * Created by TCM on 7/24/2017.
 */

public class SignalData {
    private String DeviceID;
    private String DeviceName;
    private long sysDateTime;
    private boolean isAvailable;
    private String CompanyName;
    private String ConnectionType;
    private String ConnectionSubType;
    private String ConnectionState;
    private String ConnectionReason;
    private String ConnectionExtra;
    private boolean isRoaming;
    private boolean isFailover;
    private int SignalStrength;
    private long TimeToLoad;
    private double LocationLatitude;
    private double LocationLongitude;
    private double LocationAltitude;
    private float LocationAccuracy;
    private float LocationSpeed;
    private float LocationBearing;
    private int BatteryPerc;
    private float BatteryTemperature;
    private int BatteryVoltage;

    public SignalData() {
    }

    public String getDeviceID() {
        return DeviceID;
    }

    public void setDeviceID(String deviceID) {
        DeviceID = deviceID;
    }

    public String getDeviceName() {
        return DeviceName;
    }

    public void setDeviceName(String deviceName) {
        DeviceName = deviceName;
    }

    public long getSysDateTime() {
        return sysDateTime;
    }

    public void setSysDateTime(long sysDateTime) {
        this.sysDateTime = sysDateTime;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    public String getConnectionType() {
        return ConnectionType;
    }

    public void setConnectionType(String connectionType) {
        ConnectionType = connectionType;
    }

    public String getConnectionSubType() {
        return ConnectionSubType;
    }

    public void setConnectionSubType(String connectionSubType) {
        ConnectionSubType = connectionSubType;
    }

    public String getConnectionState() {
        return ConnectionState;
    }

    public void setConnectionState(String connectionState) {
        ConnectionState = connectionState;
    }

    public String getConnectionReason() {
        return ConnectionReason;
    }

    public void setConnectionReason(String connectionReason) {
        ConnectionReason = connectionReason;
    }

    public String getConnectionExtra() {
        return ConnectionExtra;
    }

    public void setConnectionExtra(String connectionExtra) {
        ConnectionExtra = connectionExtra;
    }

    public boolean isRoaming() {
        return isRoaming;
    }

    public void setRoaming(boolean roaming) {
        isRoaming = roaming;
    }

    public boolean isFailover() {
        return isFailover;
    }

    public void setFailover(boolean failover) {
        isFailover = failover;
    }

    public int getSignalStrength() {
        return SignalStrength;
    }

    public void setSignalStrength(int signalStrength) {
        SignalStrength = signalStrength;
    }

    public long getTimeToLoad() {
        return TimeToLoad;
    }

    public void setTimeToLoad(long timeToLoad) {
        TimeToLoad = timeToLoad;
    }

    public double getLocationLatitude() {
        return LocationLatitude;
    }

    public void setLocationLatitude(double locationLatitude) {
        LocationLatitude = locationLatitude;
    }

    public double getLocationLongitude() {
        return LocationLongitude;
    }

    public void setLocationLongitude(double locationLongitude) {
        LocationLongitude = locationLongitude;
    }

    public double getLocationAltitude() {
        return LocationAltitude;
    }

    public void setLocationAltitude(double locationAltitude) {
        LocationAltitude = locationAltitude;
    }

    public float getLocationAccuracy() {
        return LocationAccuracy;
    }

    public void setLocationAccuracy(float locationAccuracy) {
        LocationAccuracy = locationAccuracy;
    }

    public float getLocationSpeed() {
        return LocationSpeed;
    }

    public void setLocationSpeed(float locationSpeed) {
        LocationSpeed = locationSpeed;
    }

    public float getLocationBearing() {
        return LocationBearing;
    }

    public void setLocationBearing(float locationBearing) {
        LocationBearing = locationBearing;
    }

    public int getBatteryPerc() {
        return BatteryPerc;
    }

    public void setBatteryPerc(int batteryPerc) {
        BatteryPerc = batteryPerc;
    }

    public float getBatteryTemperature() {
        return BatteryTemperature;
    }

    public void setBatteryTemperature(float batteryTemperature) {
        BatteryTemperature = batteryTemperature;
    }

    public int getBatteryVoltage() {
        return BatteryVoltage;
    }

    public void setBatteryVoltage(int batteryVoltage) {
        BatteryVoltage = batteryVoltage;
    }

    @Override
    public String toString() {
        return "SignalData{" +
                "DeviceID='" + DeviceID + '\'' +
                ", DeviceName='" + DeviceName + '\'' +
                ", sysDateTime=" + sysDateTime +
                ", isAvailable=" + isAvailable +
                ", CompanyName='" + CompanyName + '\'' +
                ", ConnectionType='" + ConnectionType + '\'' +
                ", ConnectionSubType='" + ConnectionSubType + '\'' +
                ", ConnectionState='" + ConnectionState + '\'' +
                ", ConnectionReason='" + ConnectionReason + '\'' +
                ", ConnectionExtra='" + ConnectionExtra + '\'' +
                ", isRoaming=" + isRoaming +
                ", isFailover=" + isFailover +
                ", SignalStrength=" + SignalStrength +
                ", TimeToLoad=" + TimeToLoad +
                ", LocationLatitude=" + LocationLatitude +
                ", LocationLongitude=" + LocationLongitude +
                ", LocationAltitude=" + LocationAltitude +
                ", LocationAccuracy=" + LocationAccuracy +
                ", LocationSpeed=" + LocationSpeed +
                ", LocationBearing=" + LocationBearing +
                ", BatteryPerc=" + BatteryPerc +
                ", BatteryTemperature=" + BatteryTemperature +
                ", BatteryVoltage=" + BatteryVoltage +
                '}';
    }

    public String HeadString() {
        return "DeviceID" +
                "; DeviceName" +
                "; sysDateTime" +
                "; isAvailable" +
                "; CompanyName" +
                "; ConnectionType" +
                "; ConnectionSubType" +
                "; ConnectionState" +
                "; ConnectionReason" +
                "; ConnectionExtra" +
                "; isRoaming" +
                "; isFailover" +
                "; SignalStrength" +
                "; TimeToLoad" +
                "; LocationLatitude" +
                "; LocationLongitude" +
                "; LocationAltitude" +
                "; LocationAccuracy" +
                "; LocationSpeed" +
                "; LocationBearing" +
                "; BatteryPerc" +
                "; BatteryTemperature" +
                "; BatteryVoltage" +
                "\n";
    }

    public String toCSV() {
        return DeviceID +
                ";" + DeviceName +
                ";" + sysDateTime +
                ";" + isAvailable +
                ";" + CompanyName +
                ";" + ConnectionType +
                ";" + ConnectionSubType +
                ";" + ConnectionState +
                ";" + ConnectionReason +
                ";" + ConnectionExtra +
                ";" + isRoaming +
                ";" + isFailover +
                ";" + SignalStrength +
                ";" + TimeToLoad +
                ";" + LocationLatitude +
                ";" + LocationLongitude +
                ";" + LocationAltitude +
                ";" + LocationAccuracy +
                ";" + LocationSpeed +
                ";" + LocationBearing +
                ";" + BatteryPerc +
                ";" + BatteryTemperature +
                ";" + BatteryVoltage +
                "\n";
    }

    public void writeToFile(String filename, boolean chk) {
        try {
            File mydir = new File(Environment.getExternalStorageDirectory(), "SignalStrength");
            if (!mydir.exists()) {
                mydir.mkdirs();
            }
            File fileMyDir = new File(mydir, filename);
            FileOutputStream outputStream;
            if (chk){
                outputStream = new FileOutputStream(fileMyDir);
                outputStream.write(HeadString().getBytes());
            }else {
                outputStream = new FileOutputStream(fileMyDir,  true);
                outputStream.write(toCSV().getBytes());
            }

            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
