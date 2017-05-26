package Util;

import java.util.Calendar;
import java.util.Date;

/**
 * @Author lee
 * @Time  4/27/17.
 */
public class CalUtil {
    Calendar calendar=null;
    Date currentDate;
    public CalUtil(){
        calendar=Calendar.getInstance();
        Date trialTime=new Date();
    }

    public int getYear(){
        return calendar.get(Calendar.YEAR);
    }
    public String getMonth() {
        int m = getMonthInt();
        String[] months = new String [] { "January", "February", "March",
                "April", "May", "June",
                "July", "August", "September",
                "October", "November", "December" };
        if (m > 12)
            return "Unknown to Man";

        return months[m - 1];

    }

    /**
     * 从星期一开始
     * @return
     */
    public  int getDayMon() {
        Calendar calendar=Calendar.getInstance();
        int dayofweeksun=calendar.get(Calendar.DAY_OF_WEEK);
        int day=0;
        if (dayofweeksun==1){
            day=7;
        }else {
            day=dayofweeksun-1;
        }
        return day;
    }

    public  int getNextDay(){
        int nextday=0;
        //todo fuckkkkkkkkkkk to get next day
        //calendar.set(Calendar.DAY_OF_WEEK,getDayMon()+1);
        /*if (day<7&&day>0) {
            nextday= getDayMon() + 1;
        }else if (day==7){
            nextday=1;
        }*/
        return getDayMon();
    }

    public  int getPrevtDay(){
        int prevday=0;
        //calendar.set(Calendar.DAY_OF_WEEK,getDayMon()-1);
        /*if (day!=1) {
            prevday= getDayMon()-1;
        }else if (day==1){
            prevday=7;
        }*/
        return getDayMon();
    }

    public String getDay() {
        int x = getDayOfWeek();
        String[] days = new String[] {"Sunday", "Monday", "Tuesday", "Wednesday",
                "Thursday", "Friday", "Saturday"};

        if (x > 7)
            return "Unknown to Man";

        return days[x - 1];

    }

    public int getMonthInt() {
        return 1 + calendar.get(Calendar.MONTH);
    }

    public String getDate() {
        return getMonthInt() + "/" + getDayOfMonth() + "/" +  getYear();
    }

    public String getCurrentDate() {
        Date dt = new Date ();
        calendar.setTime (dt);
        return getMonthInt() + "/" + getDayOfMonth() + "/" +  getYear();

    }

    public String getNextDate() {
        calendar.set (Calendar.DAY_OF_MONTH, getDayOfMonth() + 1);
        return getDate ();
    }

    public String getPrevDate() {
        calendar.set (Calendar.DAY_OF_MONTH, getDayOfMonth() - 1);
        return getDate ();
    }

    public String getTime() {
        return getHour() + ":" + getMinute() + ":" + getSecond();
    }

    public int getDayOfMonth() {
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public int getDayOfYear() {
        return calendar.get(Calendar.DAY_OF_YEAR);
    }

    public int getWeekOfYear() {
        return calendar.get(Calendar.WEEK_OF_YEAR);
    }

    public int getWeekOfMonth() {
        return calendar.get(Calendar.WEEK_OF_MONTH);
    }

    public int getDayOfWeek() {
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    public int getHour() {
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    public int getMinute() {
        return calendar.get(Calendar.MINUTE);
    }


    public int getSecond() {
        return calendar.get(Calendar.SECOND);
    }


    public int getEra() {
        return calendar.get(Calendar.ERA);
    }

    public String getUSTimeZone() {
        String[] zones = new String[] {"Hawaii", "Alaskan", "Pacific",
                "Mountain", "Central", "Eastern"};

        return zones[10 + getZoneOffset()];
    }

    public int getZoneOffset() {
        return calendar.get(Calendar.ZONE_OFFSET)/(60*60*1000);
    }


    public int getDSTOffset() {
        return calendar.get(Calendar.DST_OFFSET)/(60*60*1000);
    }


    public int getAMPM() {
        return calendar.get(Calendar.AM_PM);
    }
}
