package Bean;

import ObjectC.Schedule;
import ObjectC.Schedules;
import Util.CalUtil;
import Util.MysqlUtil;
import Util.dbConnector;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.util.Hashtable;
import java.util.List;

/**
 * @Author lee
 * @Time 4/27/17.
 */
public class Entries {
    private Hashtable entries;
    private static final String[] time = {"第1节", "第2节","第3节","第4节","第5节","第6节",
            "第7节","第8节","第9节","第10节","第11节","第12节"};
    //TODO 加入星期行，显示一星期的课表
    private static  final String[] days = new String[] {"Monday", "Tuesday", "Wednesday",
            "Thursday", "Friday", "Saturday","Sunday"};
    private static final int rows=12;
    private String content="";
    private Connection connection=null;
    private static final String SPACE=" ";

    public Entries(){
        this.connection=dbConnector.getConnector();
        entries=new Hashtable(rows);
        for (int i=0;i<rows;i++){
            entries.put(time[i],new Entry(time[i]));
        }
    }

    public int getRows(){
        return rows;
    }

    /**
     *
     * @param index
     * @return
     */
    public Entry getEntry(int index){
        return (Entry)this.entries.get(time[index]);
    }

    /**
     *
     * @param tm
     * @return index
     */
    public int getIndex(String tm){
        for (int i=0;i<rows;i++){
            if (tm.equals(time[i])){
                return i;
            }
        }
        return  -1;
    }

    /*public void processRequest(HttpServletRequest request,String tm,String content){
        int index=getIndex(tm);
        if ((index>=0)){
            //todo get content from mysql
            //String content=request.getParameter("content");
            ((Entry)entries.get(time[index])).setContent(content);
        }
    }*/

    /**
     * 初始化课表
     */
    public  void processRequest(HttpServletRequest request,int dayR){
        String schoolid=request.getParameter("schoolid");
        MysqlUtil mysqlUtil=new MysqlUtil(connection);
        List<String>classList=mysqlUtil.getClassSelected(schoolid);
        CalUtil calUtil=new CalUtil();
        StringBuilder builder;
        for (int i = 0; i < classList.size(); i++) {
            //每个classid都有n个schedules对象,每个schedules有n个schedule对象.
           Schedules schedules=mysqlUtil.getSchedule(classList.get(i));
           List<Schedule>mschedules=schedules.getSchedules();
           for (int j=0;j<mschedules.size();j++){
               Schedule schedule=mschedules.get(j);
               if (schedule.getDay()==calUtil.getDayMon()) {
                   int beginclass = schedule.getBeginclass()-1;
                   int endclass = schedule.getEndclass()-1;
                   //String c1 = getClassTime(beginclass);
                   //String c2 = getClassTime(endclass);
                   //int index1 = getIndex(c1);
                   //int index2 = getIndex(c2);
                   builder=new StringBuilder();
                   builder.append(SPACE + schedule.getClassname());
                   builder.append(SPACE + schedule.getPlace());
                   //builder.append(SPACE+schedule.getTeachername());
                   content = builder.toString();
                   ((Entry) entries.get(time[beginclass])).setContent(content);
                   ((Entry) entries.get(time[endclass])).setContent(content);
                   if (endclass - beginclass == 2) {
                       ((Entry) entries.get(time[beginclass + 1])).setContent(content);
                   }
               }else {
                   //下一天或者前一天的课表
                   if (schedule.getDay()==dayR){
                       int beginclass = schedule.getBeginclass()-1;
                       int endclass = schedule.getEndclass()-1;
                       builder=new StringBuilder();
                       builder.append(SPACE + schedule.getClassname());
                       builder.append(SPACE + schedule.getPlace());
                       //builder.append(SPACE+schedule.getTeachername());
                       content = builder.toString();
                       ((Entry) entries.get(time[beginclass])).setContent(content);
                       ((Entry) entries.get(time[endclass])).setContent(content);
                       if (endclass - beginclass == 2) {
                           ((Entry) entries.get(time[beginclass + 1])).setContent(content);
                       }
                   }
               }
           }
        }

    }

    public String getClassTime(int nday){
        //"第2节","第3节","第4节","第5节","第6节",
          //      "第7节","第8节","第9节","第10节","第11节","第12节"
        String day="";
        switch (nday){
            case 1:
                day="第1节";
                break;
            case 2:
                day="第2节";
                break;
            case 3:
                day="第3节";
                break;
            case 4:
                day="第4节";
                break;
            case 5:
                day="第5节";
                break;
            case 6:
                day="第6节";
                break;
            case 7:
                day="第7节";
                break;
            case 8:
                day="第8节";
                break;
            case 9:
                day="第9节";
                break;
            case 10:
                day="第10节";
                break;
            case 11:
                day="第11节";
                break;
            case 12:
                day="第12节";
                break;
        }

        return day;
    }
}
