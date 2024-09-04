/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import dbcontext.DBContext;
import entities.food;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author truon
 */
public class dbDAO {

    public static boolean checkLogin(String id, String password) {
//                String query1 = ("select email from tblUsers where email='" + email + "'");
//                String query2 = ("select password from tblUsers where password='" + pass + "'");
//                String query3 = ("select * from tblUsers where email='" + email + "'and password = '" + pass + "'");
        boolean isValid = false;
        try {
            String query = ("select * from tblUsers where userID=? and password=?");
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, id);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                isValid = true;
            } else {
                isValid = false;
            }
        } catch (Exception e) {
        }
        return isValid;
    }

    public static boolean checkRole(String id) {
        boolean isValid = false;
        try {
            String query = ("select * from tblUsers where userID=? and roleID='AD'");
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                isValid = true;
            } else {
                isValid = false;
            }
        } catch (Exception e) {
        }
        return isValid;
    }

    public static String getNameofUser(String userid) {
        try {
            String query = ("select fullName from tblUsers where userID=?");
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, userid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String name = rs.getString(1);
                return name;
            }
        } catch (Exception e) {
        }
        return null;
    }

    public static void insert(String id, String name, String desp, float price, int time, int status) {
        try {
            String query = ("insert into tblFoods values (?,?,?,?,?,?);");
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, id);
            ps.setString(2, name);
            ps.setString(3, desp);
            ps.setFloat(4, price);
            ps.setInt(5, time);
            ps.setInt(6, status);
            ResultSet rs = ps.executeQuery();
        } catch (Exception e) {
        }
    }

    public List<food> getListFoods() {
        try {
            String query = "select * from tblFoods";
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            List<food> list = new ArrayList<>();
            while (rs.next()) {
                food fd = new food(rs.getString(1), rs.getString(2), rs.getString(3), rs.getFloat(4), rs.getInt(5), rs.getInt(6));
                list.add(fd);
            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }

    public static boolean checkFood(String id) {
        boolean isValid = false;
        try {
            String query = ("select * from tblFoods where id=?");
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                isValid = true;
            } else {
                isValid = false;
            }
        } catch (Exception e) {
        }
        return isValid;
    }
}
