package ObjectC;

/**
 * @Time 4/25/17.
 * @Author Lee
 */
public class Schedule {
    private String classid;
    private String classname;//课程名称
    private int beginweek;
    private int endweek;
    private String teachername;
    private int day;//星期几
    private int beginclass;//第几节开始
    private int endclass;//上几节课
    //private String schoolid;//学号
    private String beginyear;
    private String endyear;//学年,如2015-2016
    private int semester;//学期
    private String weeknum;//单双周
    private String place;

    public String getClassid() {
        return classid;
    }

    public void setClassid(String classid) {
        this.classid = classid;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getEndyear() {
        return endyear;
    }

    public void setEndyear(String endyear) {
        this.endyear = endyear;
    }
    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public int getBeginweek() {
        return beginweek;
    }

    public void setBeginweek(int beginweek) {
        this.beginweek = beginweek;
    }

    public int getEndweek() {
        return endweek;
    }

    public void setEndweek(int endweek) {
        this.endweek = endweek;
    }

    public String getTeachername() {
        return teachername;
    }

    public void setTeachername(String teachername) {
        this.teachername = teachername;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getBeginclass() {
        return beginclass;
    }

    public void setBeginclass(int beginclass) {
        this.beginclass = beginclass;
    }

    public int getEndclass() {
        return endclass;
    }

    public void setEndclass(int endclass) {
        this.endclass = endclass;
    }

    public String getBeginyear() {
        return beginyear;
    }

    public void setBeginyear(String beginyear) {
        this.beginyear = beginyear;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public String getWeeknum() {
        return weeknum;
    }

    public void setWeeknum(String weeknum) {
        this.weeknum = weeknum;
    }
}
