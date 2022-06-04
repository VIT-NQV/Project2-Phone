/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eProject2.dao;

import eProject2.Dbconnection.DbFactory;
import eProject2.Dbconnection.DbType;
import static eProject2.dao.AdminDAOImp.HashPass;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class AccountDAOImp implements AccountDAO {

    public DbType database = DbType.MYSQL;

    public Account check(String acc) {
        Account Acc = new Account();
        try (
                Connection conn = DbFactory.getConnection(database);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT `account`, `password`, `loginbyID`\n"
                        + "FROM admin\n"
                        + "WHERE `account` = '" + acc + "'")) {

            while (rs.next()) {
                Acc.setUserName(rs.getString("account"));
                Acc.setPassword(rs.getString("password"));
                Acc.setLogBy(rs.getInt("loginbyID"));

            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return Acc;
    }
    
    public Account insertNewMem(Account newMember){
        String sql = "INSERT INTO admin(account,password,name,loginbyID,numberphone,email) "
                + "VALUES (?,?,?,?,?,?)";
        ResultSet key = null;
       try (
                Connection conn = DbFactory.getConnection(database);
                PreparedStatement stmt
                = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            stmt.setString(1, newMember.getUserName());
            stmt.setString(2, HashPass(newMember.getPassword()));
            stmt.setString(3, newMember.getName());
            stmt.setInt(4, 2);
            stmt.setString(5, newMember.getNumberPhone());
            stmt.setString(6, newMember.getEmail());

            int rowInserted = stmt.executeUpdate();

            if (rowInserted == 1) {
                key = stmt.getGeneratedKeys();
                key.next();
                int newKey = key.getInt(1);
                newMember.setId(newKey);
                return newMember;

            } else {
                System.out.println("No member inserted");
                return null;
            }

        } catch (Exception e) {
            System.out.println(e);
            return null;
        } finally {
            if (key != null) {
                try {
                    key.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
    }

}
