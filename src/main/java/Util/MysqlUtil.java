package Util;


import ObjectC.UserInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by lee on 4/18/17.
 */
public class MysqlUtil {

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
        try {
            PreparedStatement prepare = connection
                    .prepareStatement("insert into users(openid,nickname,sex,schoolid) values (?, ?, ?, ? )");
            // Parameters start with 1
            prepare.setString(1,user.getOpenid());
            prepare.setString(2,user.getNickname());
            prepare.setString(3,user.getSex());
            if (user.getSchoolid()!=null) {
                prepare.setString(4, user.getSchoolid());
            }else {
                prepare.setString(4, "Default SchoolId");
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
        try {
            PreparedStatement prepare = connection
                    .prepareStatement("delete from users where openid=?");
            // Parameters start with 1
            prepare.setString(1,user.getOpenid());
            prepare.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void updateData(UserInfo user){

    }

    public boolean isUserInSql(String openid){
        boolean hasIN=false;
        try{
            PreparedStatement prepare = connection
                    .prepareStatement("select * from users where openid=?");
            prepare.setString(1,openid);
            ResultSet rs =prepare.executeQuery();
            if (rs.next()){
                hasIN=true;
            }else {
                hasIN=false;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return hasIN;
    }
}
