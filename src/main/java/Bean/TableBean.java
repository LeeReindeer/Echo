package Bean;

import Util.CalUtil;
import Util.dbConnector;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.util.Hashtable;

/**
 * @Time 4/25/17.
 * @Author Lee
 */
public class TableBean {

    private Connection connection=null;
    Hashtable table;
    CalUtil calUtil;
    String date;
    int day;
    Entries entries;
    boolean processError=false;
    public TableBean(Connection connection) {
        this.connection = dbConnector.getConnector();
    }
    //todo get class schedule from mysql.

    public TableBean(){
        this.table=new Hashtable(10);
        this.calUtil=new CalUtil();
        this.date=calUtil.getCurrentDate();
        this.day=calUtil.getDayMon();
    }

    public String getDate () {
        return this.date;
    }

    public int getDay() {
        return day;
    }

    public Entries getEntries () {
        return this.entries;
    }

    public void processRequest (HttpServletRequest request) {
        String dateR = request.getParameter ("date");
        //get date
        //int dayR=day;
        if (dateR == null) {date = calUtil.getCurrentDate ();
        }else if (dateR.equalsIgnoreCase("next")) {
            date = calUtil.getNextDate ();
            day=calUtil.getNextDay();
            //todo wrong

        }else if (dateR.equalsIgnoreCase("prev")) {
            date = calUtil.getPrevDate ();
            day=calUtil.getPrevtDay();

        }
        entries = (Entries) table.get (date);
        if (entries == null) {
            entries = new Entries ();
            table.put (date, entries);
        }

        String schoolid=request.getParameter("schoolid");
        if (schoolid!=null){
            entries.processRequest(request,day);
        }
    }

    public boolean isProcessError() {
        return processError;
    }
}
