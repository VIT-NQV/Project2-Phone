/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eProject2.dao;

/**
 *
 * @author Admin
 */
public interface AccountDAO {

    public Account check(String Acc);
    
    public Account insertNewMem(Account newMember);
}
