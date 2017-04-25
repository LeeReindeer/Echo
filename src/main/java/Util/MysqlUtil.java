package Util;


import ObjectC.SchoolName;
import ObjectC.UserInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Author lee
 * @Time 18/4
 */
public class MysqlUtil {

    public static final int OPRNID=1;
    public static final int SCHOOLID=2;
    private Connection connection=null;

    public MysqlUtil(Connection connection) {
        this.connection = dbConnector.getConnector();
    }

    public UserInfo getData(){
        UserInfo user=new UserInfo();

        return user;
    }

    /**
     * 添加用户数据
     * @param user
     */
    public void addUser(UserInfo user){
        String school=user.getSchool();
        String schoolname= SchoolName.getSchoolId(school);
        try {
            PreparedStatement prepare = connection
                    .prepareStatement("insert into "+schoolname+"(openid,nickname,sex,schoolid) values (?, ?, ?, ? )");
            // Parameters start with 1
            prepare.setString(1,user.getOpenid());
            prepare.setString(2,user.getNickname());
            prepare.setString(3,user.getSex());
            if (user.getSchoolid()!=null) {
                prepare.setString(4, user.getSchoolid());
            }else {
                prepare.setString(4, "Default SchoolName");
            }
            prepare.executeUpdate();
            System.out.println("write success!!");
            //connection.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * 删除用户数据
     * @param user
     */
    public void deleteUser(UserInfo user){
        String school=user.getSchool();
        String schoolname= SchoolName.getSchoolId(school);
        try {
            PreparedStatement prepare = connection
                    .prepareStatement("delete from "+schoolname+" where openid=?");
            // Parameters start with 1
            prepare.setString(1,user.getOpenid());
            prepare.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * 更新数据
     * @param userInfo
     * @param updateKey
     */
    public void updateUser(UserInfo userInfo,int updateKey) {
        String school=userInfo.getSchool();
        String schoolname= SchoolName.getSchoolId(school);
        switch (updateKey){
            //update openid
            case OPRNID:
                try {
                        PreparedStatement prepare = connection
                                .prepareStatement("update "+schoolname+" set openid=?" + "where schoolid=?");
                        prepare.setString(1, userInfo.getOpenid());
                        prepare.setString(2, userInfo.getSchoolid());
                        prepare.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                break;
            case SCHOOLID:
                break;
            default:
                break;
        }
    }

    /**
     *
     * @param user
     * @param key
     * @return isIn
     */
    public boolean isUserInSql(UserInfo user,int key){
        boolean isIn=false;
        String openid=user.getOpenid();
        String schoolid=user.getSchoolid();
        String schoolname=SchoolName.getSchoolId(schoolid);
        switch (key) {
            //check openid
            case OPRNID:
                try {
                    PreparedStatement prepare = connection
                            .prepareStatement("select * from "+schoolname+" where openid=?");
                    prepare.setString(1, openid);
                    ResultSet rs = prepare.executeQuery();
                    if (rs.next()) {
                        isIn = true;
                    } else {
                        isIn = false;
                    }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
            break;
                //check schoolid
            case SCHOOLID:
                try {
                    PreparedStatement prepare = connection
                            .prepareStatement("select * from "+schoolname+" where schoolid=?");
                    prepare.setString(1, schoolid);
                    ResultSet rs = prepare.executeQuery();
                    if (rs.next()) {
                        isIn = true;
                    } else {
                        isIn = false;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
        return isIn;
    }

    /**
     * 判断登录的密码，账号是否正确
     * @param user
     * @return
     */
    public boolean checkUser(UserInfo user){
        boolean isIn=false;
        String schoolid=user.getSchoolid();
        String password=user.getPassword();
        String school=user.getSchool();
        //数据库table以学校命名
        String schoolname= SchoolName.getSchoolId(school);
        try {
            if (schoolid!=null&&password!=null) {
                PreparedStatement prepare= connection.
                        prepareStatement("select * from "+schoolname +" where schoolid=? and password=?");
                prepare.setString(1, schoolid);
                prepare.setString(2,password);
                //prepare.setString(2, password);
                ResultSet rs = prepare.executeQuery();

                if (rs.next()) {
                    isIn = true;
                } else {
                    isIn = false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isIn;
    }

    public ResultSet checkResult(UserInfo user){
        ResultSet rs=null;
        String schoolid=user.getSchoolid();
        String password=user.getPassword();
        String school=user.getSchool();
        String schoolname= SchoolName.getSchoolId(school);
        try {
            PreparedStatement prepare = connection
                    .prepareStatement("select * from "+schoolname +" where schoolid=?");
            prepare.setString(1, schoolid);
            //prepare.setString(2,password);
            rs = prepare.executeQuery();
            if (rs.next()) {
                return  rs;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
}
