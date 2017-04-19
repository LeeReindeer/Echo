package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by lee on 4/18/17.
 */
public class dbConnector {

    private static Connection connection=null;
    private final static String url = "jdbc:mysql://120.24.73.230:3306/UserDB";
    private final static String user="root";
    private final static String passworld="Aa1314bbbfgh@";
    public static Connection getConnector()  {
        if (connection!=null){
            return connection;
        }else {
            try{
                connection = DriverManager.getConnection(url, user, passworld);
            }catch (SQLException e){
                e.printStackTrace();
            }catch (Exception e){
                e.printStackTrace();
            }
            return connection;
        }
    }
}
