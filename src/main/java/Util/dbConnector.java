package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by lee on 4/18/17.
 */
public class dbConnector {

    private static Connection connection=null;
    private final static String url = "jdbc:mysql://127.0.0.1:3306/UserDB";
    private final static String user="lee";
    //private final static String user="root";
    //todo remember to change pswd
    //private final static String password="Aa1314bbbfgh@";
    private final static String password="987654321.";
    private final static String drive="com.mysql.jdbc.Driver";

    public static Connection getConnector()  {
        if (connection!=null){
            return connection;
        }else {
            try{
                Class.forName(drive);
                connection = DriverManager.getConnection(url, user, password);
                System.out.println("connect success!");
            }catch (SQLException e){
                e.printStackTrace();
                System.out.println("connect failed!");
            }catch (Exception e){
                e.printStackTrace();
            }
            return connection;
        }
    }
}
