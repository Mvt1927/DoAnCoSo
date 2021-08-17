/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LMP.Model;

/**
 *
 * @author DELL
 */
public class View {
    private String readerID, BookID, borr, Return;

    public View(String ReaderID, String BookID, String Borr, String Return) {
        this.readerID = ReaderID;
        this.BookID = BookID;
        this.borr = Borr;
        this.Return = Return;
    }

    // Get methods
    public String getReaderID() {
        return readerID;
    }
    public String getBookID() {
        return BookID;
    }
    public String getBorr() {
        return borr;
    }

    public String getReturn() {
        return Return;
    }

    //Set methods
    public void setReaderID(String ReaderID) {
        this.readerID = ReaderID;
    }
    public void setBookID(String bookID) {
        this.BookID = bookID;
    }
    public void setBorr(String Borr) {
        this.borr =Borr;
    }

    public void setReturn(String Return) {
        this.Return = Return;
    }

    //toString method
    @Override
    public String toString() {
        return "View{" + "ReaderID=" + readerID + ", BookID=" + BookID + ", Borrowed date=" + borr + ", Return date=" + Return + '}';
    }
}
