package Util;


import ObjectC.UserInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
            connection.close();
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
}
