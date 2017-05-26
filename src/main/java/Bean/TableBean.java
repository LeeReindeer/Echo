package Bean;

import ObjectC.Schedule;
import Util.MysqlUtil;
import Util.dbConnector;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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

    public String[][] processRequest(HttpServletRequest request) throws UnsupportedEncodingException {
        String schoolid=request.getParameter("schoolid");
        MysqlUtil mysqlUtil=new MysqlUtil(connection);
        String WEEK=request.getParameter("week");
        if (WEEK!=null) {
            int week = Integer.parseInt(WEEK);
        }
        Cookie[]cookies=null;
        cookies=request.getCookies();
        if (cookies!=null){
            for (Cookie temp:cookies)
                if (temp.getName().equals("schoolid")){
                    schoolid=URLDecoder.decode(temp.getValue(),"UTF-8");
                }
        }
        List<String> classList=mysqlUtil.getClassSelected(schoolid);
        for (int i=0;i<classList.size();i++){
            //得到所有课程对象的列表
            List<Schedule>mSchedules=mysqlUtil.getSchedule(classList.get(i));
            for (int j=0;j<mSchedules.size();j++){
                Schedule schedule=mSchedules.get(j);
                setclass(schedule);
            }

        }
        return scheduleArray;
    }

    /**
     * 将课表信息存在数组中
     * @param schedule
     */
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
