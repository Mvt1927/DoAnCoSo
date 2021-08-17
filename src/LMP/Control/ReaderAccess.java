/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LMP.Control;

import LMP.Model.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

/**
 *
 * @author DELL
 */
public class ReaderAccess {
    Connection conn;
    ResultSet rs;
    Statement stmt;
    String sql;
    PreparedStatement pstmt;
    int rc;
//Get Book by BookID 

    public Reader getReader(String aReaderID) {
        Reader b = null;
        try {
            conn = ConnectData.getConnection();
            sql = "Select * from reader where ReaderID='" + aReaderID + "'";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                String readerID = rs.getString(1);
                String name = rs.getString(2);
                String birth = rs.getString(3);
                String sex = rs.getString(4);
                String address = rs.getString(5);
                String imagebook = rs.getString(6);
                b = new Reader(readerID, name, birth, sex, address, imagebook);
            }
            conn.close();
            System.out.println("Get Reader successfully!");
        } catch (Exception ex) {
            System.out.println("Get Reader failure!");
            System.out.println(stmt);
        }
        return b;
    }

//Get all of books -> Vector
    public Vector<Reader> getAllReader() {
        Vector allReader = new Vector();
        try {
            conn = ConnectData.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("Select * from reader");
            while (rs.next()) {
                Vector<String> v = new Vector<>();
                v.add(rs.getString(1));
                v.add(rs.getString(2));
                v.add(rs.getString(3));
                v.add(rs.getString(4));
                v.add(rs.getString(5));
                v.add(rs.getString(6));
                allReader.add(v);
            }
            conn.close();
            System.out.println("Get all of Reader successfully!");
        } catch (Exception ex) {
            System.out.println("Get all of Reader failure!");
            System.out.println(stmt);
        }
        return allReader;
    }

    //get ImageBook
    public String GetImageReader(String aReaderID) {
        String ImageBook = null;
        try {
            conn = ConnectData.getConnection();
            sql = "Select ImageReader from reader where ReaderID='" + aReaderID + "'";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                ImageBook = rs.getString(1);
            }
            conn.close();
            System.out.println("Get ImageReader successfully!");
        } catch (Exception e) {
            System.out.println("Get ImageReader failure!");
            System.out.println(stmt);
        }

        return ImageBook;

    }
//Add a new book

    public int addNewReader(String aReaderID, String aName, String aBirth, String aSex, String aAddress, String aImageReader) {
        rc = 0;
        try {
            conn = ConnectData.getConnection();
            sql = "INSERT INTO reader(ReaderID, Name, Birth, Sex, Address, ImageReader) VALUES(?,?,?,?,?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, aReaderID);
            pstmt.setString(2, aName);
            pstmt.setString(3, aBirth);
            pstmt.setString(4, aSex);
            pstmt.setString(5, aAddress);
            pstmt.setString(6, aImageReader);
            rc = pstmt.executeUpdate();
            conn.close();
            System.out.println("Add Reader successfully!");
        } catch (Exception ex) {
            System.out.println("Add Reader failure!");
            System.out.println(pstmt);
        }
        return rc;
    }
//Update a book

    public int updateReader(String aReaderID, String aName, String aBirth, String aSex, String aAddress, String aImageReader) {
        rc = 0;
        try {
            conn = ConnectData.getConnection();
            sql = "Update reader set Name=?,Birth=?,Sex=?,Address=?,ImageReader=? where ReaderID=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, aName);
            pstmt.setString(2, aBirth);
            pstmt.setString(3, aSex);
            pstmt.setString(4, aAddress);
            pstmt.setString(5, aImageReader);
            pstmt.setString(6, aReaderID);
            rc = pstmt.executeUpdate();
            conn.close();
            System.out.println("Update Reader successfully!");
            //System.out.println(pstmt);
        } catch (Exception ex) {
            System.out.println("Update Reader failure!");
            System.out.println(pstmt);
        }
        return rc;
    }
//Delete a book

    public int deleteReader(String aReaderID) {
        rc = 0;
        try {
            conn = ConnectData.getConnection();
            sql = "Delete from reader where ReaderID='" + aReaderID + "'";
            stmt = conn.createStatement();
            rc = stmt.executeUpdate(sql);
            conn.close();
            System.out.println("Delete Reader successfully!");
        } catch (Exception ex) {
            System.out.println("Delete Reader failure!");
            System.out.println(stmt);
        }
        return rc;
    }
//Search books by Title -> Vector

    public Vector<Reader> searchByName(String aName) {
        Vector sReader = new Vector();
        try {
            conn = ConnectData.getConnection();
            stmt = conn.createStatement();
            sql = "Select * from reader where name Like '" + aName + "'";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Vector<String> v = new Vector<>();
                v.add(rs.getString(1));
                v.add(rs.getString(2));
                v.add(rs.getString(3));
                v.add(rs.getString(4));
                v.add(rs.getString(5));
                v.add(rs.getString(6));
                sReader.add(v);
            }
            conn.close();
            System.out.println("Get Reader by Name successfully!");
        } catch (Exception ex) {
            System.out.println("Get Reader by Name failure!");
            System.out.println(stmt);
        }
        return sReader;
    }

//Search books by Author -> Vector
    public Vector<Reader> searchByBirth(String aBirth) {
        Vector sReader = new Vector();
        try {
            conn = ConnectData.getConnection();
            stmt = conn.createStatement();
            sql = "Select * from reader where Reader Like '" + aBirth + "'";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Vector<String> v = new Vector<>();
                v.add(rs.getString(1));
                v.add(rs.getString(2));
                v.add(rs.getString(3));
                v.add(rs.getString(4));
                v.add(rs.getString(5));
                v.add(rs.getString(6));
                sReader.add(v);
            }
            conn.close();
            System.out.println("Get Reader by Birth successfully!");
        } catch (Exception ex) {
            System.out.println("Get Reader by Birth failure!");
            System.out.println(stmt);
        }
        return sReader;
    }

//Search books by Category -> Vector
    public Vector<Reader> searchBySex(String aSex) {
        Vector sReader = new Vector();
        try {
            conn = ConnectData.getConnection();
            stmt = conn.createStatement();
            sql = "Select * from reader where Sex Like '" + aSex + "'";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Vector<String> v = new Vector<>();
                v.add(rs.getString(1));
                v.add(rs.getString(2));
                v.add(rs.getString(3));
                v.add(rs.getString(4));
                v.add(rs.getString(5));
                v.add(rs.getString(6));
                sReader.add(v);
            }
            conn.close();
            System.out.println("Get Reader by Sex  successfully!");
        } catch (Exception ex) {
            System.out.println("Get Reader by Sex failure!");
            System.out.println(stmt);
        }
        return sReader;
    }

//Search books by Amount -> Vector
    public Vector<Reader> searchByAddress(String aAddress) {
        Vector sReader = new Vector();
        try {
            conn = ConnectData.getConnection();
            stmt = conn.createStatement();
            sql = "Select * from reader where Address Like '" + aAddress + "'";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Vector<String> v = new Vector<>();
                v.add(rs.getString(1));
                v.add(rs.getString(2));
                v.add(rs.getString(3));
                v.add(rs.getString(4));
                v.add(rs.getString(5));
                v.add(rs.getString(6));
                sReader.add(v);
            }
            conn.close();
            System.out.println("Get Reader by Address successfully!");
        } catch (Exception ex) {
            System.out.println("Get Reader by Address failure!");
            System.out.println(stmt);
        }
        return sReader;
    }
}
