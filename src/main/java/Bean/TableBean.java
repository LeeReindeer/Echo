package Bean;

import ObjectC.Schedule;
import Util.MysqlUtil;
import Util.dbConnector;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.util.List;

/**
 * @Author lee
 * @Time 5/12/17.
 */
public class TableBean {
    private Connection connection;
    //todo 可以改为对象数组
    private String [][] scheduleArray =new String[13][8];
    public TableBean() {
        this.connection= dbConnector.getConnector();
    }

    public String[][] processRequest(HttpServletRequest request){
        String schoolid=request.getParameter("schoolid");
        MysqlUtil mysqlUtil=new MysqlUtil(connection);
        String WEEK=request.getParameter("week");
        if (WEEK!=null) {
            int week = Integer.parseInt(WEEK);
        }
        List<String> classList=mysqlUtil.getClassSelected("160410218");
        for (int i=0;i<classList.size();i++){
            //得到课程对象的列表
            List<Schedule>mSchedules=mysqlUtil.getSchedule(classList.get(i)).getSchedules();
            for (int j=0;j<mSchedules.size();j++){
                Schedule schedule=mSchedules.get(j);
                setclass(schedule);
            }

        }
        return scheduleArray;
    }

    public  void setclass(Schedule schedule){
        int day=schedule.getDay();
        int beginclass=schedule.getBeginclass();
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 7; j++) {
                if (beginclass==i&&day==j){
                    scheduleArray[i][j]=schedule.getClassname();
                    System.out.println("第"+i+"节"+"星期"+j+ scheduleArray[i][j]);
                }
            }
        }

       // return scheduleArray;
    }

}
