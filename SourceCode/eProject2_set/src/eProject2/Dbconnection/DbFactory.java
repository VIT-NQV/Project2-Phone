/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eProject2.Dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Admin
 */
public class DbFactory {
    private static final String MYSQL_USERNAME = "root";
    private static final String MYSQL_PASSWORD = "";
    private static final String MYSQL_CONN_STRING = "jdbc:mysql://localhost/eproject2";
//    private static final String SQLITE_CONN_STRING = "jdbc:sqlite:eproject2.db";
    
    public static Connection getConnectionMYSQL() throws SQLException{
        return DriverManager.getConnection(MYSQL_CONN_STRING, MYSQL_USERNAME, MYSQL_PASSWORD);
    }
    
//    public static Connection getConnectionSQLITE() throws SQLException{
//        return DriverManager.getConnection(SQLITE_CONN_STRING);
//    }
    
    public static Connection getConnection(DbType dbt) throws SQLException {
        switch (dbt) {
            case SQLITE:
//                return getConnectionSQLITE();
                break;
            case MYSQL:
            default:
                return getConnectionMYSQL();
        }
        return getConnectionMYSQL();
    }
}
