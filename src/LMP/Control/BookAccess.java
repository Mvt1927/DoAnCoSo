/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LMP.Control;

import LMP.Model.Book;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

/**
 *
 * @author DELL
 */
public class BookAccess {

    Connection conn;
    ResultSet rs;
    Statement stmt;
    String sql;
    PreparedStatement pstmt;
    int rc;
//Get Book by BookID 

    public Book getBook(String aBookID) {
        Book b = null;
        try {
            conn = ConnectData.getConnection();
            sql = "Select * from books where BookID='" + aBookID + "'";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                String bookID = rs.getString(1);
                String title = rs.getString(2);
                String author = rs.getString(3);
                String category = rs.getString(4);
                int amount = rs.getInt(5);
                String imagebook = rs.getString(6);
                b = new Book(bookID, title, author, category, amount, imagebook);
            }
            conn.close();
            System.out.println("Get Book successfully!");
        } catch (Exception ex) {
            System.out.println("Get Book failure!");
            System.out.println(stmt);
        }
        return b;
    }

//Get all of books -> Vector
    public Vector<Book> getAllBooks() {
        Vector allBooks = new Vector();
        try {
            conn = ConnectData.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("Select * from books");
            while (rs.next()) {
                Vector<String> v = new Vector<>();
                v.add(rs.getString(1));
                v.add(rs.getString(2));
                v.add(rs.getString(3));
                v.add(rs.getString(4));
                v.add(rs.getInt(5) + "");
                v.add(rs.getString(6));
                allBooks.add(v);
            }
            conn.close();
            System.out.println("Get all of Books successfully!");
        } catch (Exception ex) {
            System.out.println("Get all of Books failure!");
            System.out.println(stmt);
        }
        return allBooks;
    }

    //get ImageBook
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
//Add a new book

    public int addNewBook(String aBookID, String aTitle, String aAuthor, String aCategory, int aAmont, String aImagebook) {
        rc = 0;
        try {
            conn = ConnectData.getConnection();
            sql = "INSERT INTO books(BookID, Title, Author, Category, Amount, Imagebook) VALUES(?,?,?,?,?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, aBookID);
            pstmt.setString(2, aTitle);
            pstmt.setString(3, aAuthor);
            pstmt.setString(4, aCategory);
            pstmt.setInt(5, aAmont);
            pstmt.setString(6, aImagebook);
            rc = pstmt.executeUpdate();
            conn.close();
            System.out.println("Add Book successfully!");
        } catch (Exception ex) {
            System.out.println("Add Book failure!");
            System.out.println(pstmt);
        }
        return rc;
    }
//Update a book

    public int updateBook(String aBookID, String aTitle, String aAuthor, String aCategory, int aAmount, String aImagebook) {
        rc = 0;
        try {
            conn = ConnectData.getConnection();
            sql = "Update books set Title=?,Author=?,Category=?,Amount=?,Imagebook=? where BookID=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, aTitle);
            pstmt.setString(2, aAuthor);
            pstmt.setString(3, aCategory);
            pstmt.setInt(4, aAmount);
            pstmt.setString(5, aImagebook);
            pstmt.setString(6, aBookID);
            rc = pstmt.executeUpdate();
            conn.close();
            System.out.println("Update Book successfully!");
        } catch (Exception ex) {
            System.out.println("Update Book failure!");
            System.out.println(pstmt);
        }
        return rc;
    }
//Delete a book

    public int deleteBook(String aBookID) {
        rc = 0;
        try {
            conn = ConnectData.getConnection();
            sql = "Delete from books where BookID='" + aBookID + "'";
            stmt = conn.createStatement();
            rc = stmt.executeUpdate(sql);
            conn.close();
            System.out.println("Delete Book successfully!");
        } catch (Exception ex) {
            System.out.println("Delete Book failure!");
            System.out.println(stmt);
        }
        return rc;
    }
//Search books by Title -> Vector

    public Vector<Book> searchByTitle(String aTitle) {
        Vector sBooks = new Vector();
        try {
            conn = ConnectData.getConnection();
            stmt = conn.createStatement();
            sql = "Select * from Books where Title Like '" + aTitle + "'";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Vector<String> v = new Vector<>();
                v.add(rs.getString(1));
                v.add(rs.getString(2));
                v.add(rs.getString(3));
                v.add(rs.getString(4));
                v.add(rs.getInt(5) + "");
                v.add(rs.getString(6));
                sBooks.add(v);
            }
            conn.close();
            System.out.println("Get Books by Tittle successfully!");
        } catch (Exception ex) {
            System.out.println("Get Books by Title failure!");
            System.out.println(stmt);
        }
        return sBooks;
    }

//Search books by Author -> Vector
    public Vector<Book> searchByAuthor(String aAuthor) {
        Vector sBooks = new Vector();
        try {
            conn = ConnectData.getConnection();
            stmt = conn.createStatement();
            sql = "Select * from Books where Author Like '" + aAuthor + "'";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Vector<String> v = new Vector<>();
                v.add(rs.getString(1));
                v.add(rs.getString(2));
                v.add(rs.getString(3));
                v.add(rs.getString(4));
                v.add(rs.getInt(5) + "");
                v.add(rs.getString(6));
                sBooks.add(v);
            }
            conn.close();
            System.out.println("Get Books by Author successfully!");
        } catch (Exception ex) {
            System.out.println("Get Books by Author failure!");
            System.out.println(stmt);
        }
        return sBooks;
    }

//Search books by Category -> Vector
    public Vector<Book> searchByCategory(String aCategory) {
        Vector sBooks = new Vector();
        try {
            conn = ConnectData.getConnection();
            stmt = conn.createStatement();
            sql = "Select * from Books where Category Like '" + aCategory + "'";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Vector<String> v = new Vector<>();
                v.add(rs.getString(1));
                v.add(rs.getString(2));
                v.add(rs.getString(3));
                v.add(rs.getString(4));
                v.add(rs.getInt(5) + "");
                v.add(rs.getString(6));
                sBooks.add(v);
            }
            conn.close();
            System.out.println("Get Books by Category  successfully!");
        } catch (Exception ex) {
            System.out.println("Get Books by Category failure!");
            System.out.println(stmt);
        }
        return sBooks;
    }

//Search books by Amount -> Vector
    public Vector<Book> searchByAmount(int aAmount) {
        Vector sBooks = new Vector();
        try {
            conn = ConnectData.getConnection();
            stmt = conn.createStatement();
            sql = "Select * from books where Amount Like '" + aAmount + "'";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Vector<String> v = new Vector<>();
                v.add(rs.getString(1));
                v.add(rs.getString(2));
                v.add(rs.getString(3));
                v.add(rs.getString(4));
                v.add(rs.getInt(5) + "");
                v.add(rs.getString(6));
                sBooks.add(v);
            }
            conn.close();
            System.out.println("Get Books by Amount  successfully!");
        } catch (Exception ex) {
            System.out.println("Get Books by Amount failure!");
            System.out.println(stmt);
        }
        return sBooks;
    }
}
