/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LMP.Control;

import LMP.Model.View;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

/**
 *
 * @author DELL
 */
public class ViewAccess {

    Connection conn;
    ResultSet rs;
    Statement stmt;
    String sql;
    PreparedStatement pstmt;
    int rc;

    public View getView(String aReaderID, String aBookID) {
        View b = null;
        try {
            conn = ConnectData.getConnection();
            sql = "Select * from view where (ReaderID='" + aReaderID + "' and '" + aBookID + "')";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                String readerID = rs.getString(1);
                String bookID = rs.getString(2);
                String Borr = rs.getString(3);
                String Return = rs.getString(4);
                b = new View(readerID, bookID, Borr, Return);
            }
            conn.close();
            System.out.println("Get View successfully!");
        } catch (Exception ex) {
            System.out.println("Get View failure!");
            System.out.println(stmt);
        }
        return b;
    }

//Get all of books -> Vector
    public Vector<View> getAllView() {
        Vector allView = new Vector();
        try {
            conn = ConnectData.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("Select * from view");
            while (rs.next()) {
                Vector<String> v = new Vector<>();
                v.add(rs.getString(1));
                v.add(rs.getString(2));
                v.add(rs.getString(3));
                v.add(rs.getString(4));
                allView.add(v);
            }
            conn.close();
            System.out.println("Get all of View successfully!");
        } catch (Exception ex) {
            System.out.println("Get all of View failure!");
            System.out.println(stmt);
        }
        return allView;
    }

    public String GetImageBook(String aBookID) {
        String ImageBook = null;
        try {
            conn = ConnectData.getConnection();
            sql = "Select ImageBook from books where BookID='" + aBookID + "'";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                ImageBook = rs.getString(1);
            }
            conn.close();
            System.out.println("Get ImageBooks successfully!");
        } catch (Exception e) {
            System.out.println("Get ImageBook failure!");
            System.out.println(stmt);
        }
        return ImageBook;
    }

    public String GetTitleBook(String aBookID) {
        String TitleBook = null;
        try {
            conn = ConnectData.getConnection();
            sql = "Select Title from books where BookID='" + aBookID + "'";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                TitleBook = rs.getString(1);
            }
            conn.close();
            System.out.println("Get TitleBook successfully!");
        } catch (Exception e) {
            System.out.println("Get TitleBook failure!");
            System.out.println(stmt);
        }
        return TitleBook;
    }

    //get ImageBook
    public String GetImageReader(String aReaderID) {
        String ImageReader = null;
        
        try {
            conn = ConnectData.getConnection();
            sql = "Select ImageReader from reader where ReaderID='" + aReaderID + "'";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
              
                ImageReader = rs.getString(1);
            }
            conn.close();
            System.out.println("Get ImageReader successfully!");
        } catch (Exception e) {
            System.out.println("Get ImageReader failure!");
            System.out.println(stmt);
        }
        return ImageReader;
    }
    public String GetNameReader(String aReaderID) {
        String NameReader = null;
        try {
            conn = ConnectData.getConnection();
            sql = "Select Name from reader where ReaderID='" + aReaderID + "'";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                NameReader = rs.getString(1);
            }
            conn.close();
            System.out.println("Get Name successfully!");
        } catch (Exception e) {
            System.out.println("Get Name failure!");
            System.out.println(stmt);
        }
        return NameReader;

    }
//Add a new book

    public int addNewView(String aReaderID, String aBookID, String aBorr, String aReturn) {
        rc = 0;
        try {
            conn = ConnectData.getConnection();
            sql = "INSERT INTO view(ReaderID, BookID, Borrowed, Returndate) VALUES(?,?,?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, aReaderID);
            pstmt.setString(2, aBookID);
            pstmt.setString(3, aBorr);
            pstmt.setString(4, aReturn);
            rc = pstmt.executeUpdate();
            conn.close();
            System.out.println("Add View successfully!");
        } catch (Exception ex) {
            System.out.println("Add View failure!");
            System.out.println(pstmt);
        }
        return rc;
    }
//Update a book

    public int updateView(String aReaderID, String aBookID, String aBorr, String aReturn) {
        rc = 0;
        try {
            conn = ConnectData.getConnection();
            sql = "UPDATE `view` SET `Borrowed` = ?, `Returndate` = ? WHERE (`ReaderID` = ?) and (`BookID` = ?);";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, aBorr);
            pstmt.setString(2, aReturn);
            pstmt.setString(3, aReaderID);
            pstmt.setString(4, aBookID);
            rc = pstmt.executeUpdate();
            conn.close();
            System.out.println("Update View successfully!");
            //System.out.println(pstmt);
        } catch (Exception ex) {
            System.out.println("Update View failure!");
            System.out.println(pstmt+"  "+ex);
        }
        return rc;
    }
//Delete a book

    public int deleteView(String aReaderID, String aBoookID) {
        rc = 0;
        try {
            conn = ConnectData.getConnection();
            sql = "Delete from view where ReaderID='" + aReaderID + "' and BookID='" + aBoookID + "'";
            stmt = conn.createStatement();
            rc = stmt.executeUpdate(sql);
            conn.close();
            System.out.println("Delete View successfully!");
        } catch (Exception ex) {
            System.out.println("Delete View failure!");
            System.out.println(stmt);
        }
        return rc;
    }
//Search books by Title -> Vector

    public Vector<View> searchByReaderID(String aReaderID) {
        Vector sReader = new Vector();
        try {
            conn = ConnectData.getConnection();
            stmt = conn.createStatement();
            sql = "Select * from view where ReaderID Like '" + aReaderID + "'";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Vector<String> v = new Vector<>();
                v.add(rs.getString(1));
                v.add(rs.getString(2));
                v.add(rs.getString(3));
                v.add(rs.getString(4));
                sReader.add(v);
            }
            conn.close();
            System.out.println("Get View by ReaderID successfully!");
        } catch (Exception ex) {
            System.out.println("Get View by ReaderID failure!");
            System.out.println(stmt);
        }
        return sReader;
    }

    public Vector<View> searchByBookID(String aBookID) {
        Vector sReader = new Vector();
        try {
            conn = ConnectData.getConnection();
            stmt = conn.createStatement();
            sql = "Select * from view where BookID Like '" + aBookID + "'";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Vector<String> v = new Vector<>();
                v.add(rs.getString(1));
                v.add(rs.getString(2));
                v.add(rs.getString(3));
                v.add(rs.getString(4));
                sReader.add(v);
            }
            conn.close();
            System.out.println("Get View by BookID successfully!");
        } catch (Exception ex) {
            System.out.println("Get View by BookID failure!");
            System.out.println(stmt);
        }
        return sReader;
    }
//Search books by Author -> Vector

    public Vector<View> searchByBorr(String aBorr) {
        Vector sReader = new Vector();
        try {
            conn = ConnectData.getConnection();
            stmt = conn.createStatement();
            sql = "Select * from view where Borrowed Like '" + aBorr + "'";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Vector<String> v = new Vector<>();
                v.add(rs.getString(1));
                v.add(rs.getString(2));
                v.add(rs.getString(3));
                v.add(rs.getString(4));
                sReader.add(v);
            }
            conn.close();
            System.out.println("Get View by Borrowed successfully!");
        } catch (Exception ex) {
            System.out.println("Get View by Borrowed failure!");
            System.out.println(stmt);
        }
        return sReader;
    }

//Search books by Category -> Vector
    public Vector<View> searchByReturn(String aReturn) {
        Vector sReader = new Vector();
        try {
            conn = ConnectData.getConnection();
            stmt = conn.createStatement();
            sql = "Select * from view where Returndate Like '" + aReturn + "'";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Vector<String> v = new Vector<>();
                v.add(rs.getString(1));
                v.add(rs.getString(2));
                v.add(rs.getString(3));
                v.add(rs.getString(4));
                sReader.add(v);
            }
            conn.close();
            System.out.println("Get view by Return date successfully!");
        } catch (Exception ex) {
            System.out.println("Get View by Return date failure!");
            System.out.println(stmt);
        }
        return sReader;
    }

}
