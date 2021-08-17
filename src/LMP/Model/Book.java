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
public class Book {

    // Constructor methods
    private String bookID, title, author, category, imagebook;
    private int amount;

    public Book(String bookID, String title, String author, String category, int amount, String imagebook) {
        this.bookID = bookID;
        this.title = title;
        this.author = author;
        this.category = category;
        this.amount = amount;
        this.imagebook = imagebook;
    }

    // Get methods
    public String getBookID() {
        return bookID;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getCategory() {
        return category;
    }

    public int getAmount() {
        return amount;
    }

    public String getImagebook() {
        return imagebook;
    }

    //Set methods
    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setImagebook(String imagebook) {
        this.imagebook = imagebook;
    }

    //toString method
    @Override
    public String toString() {
        return "Book{" + "bookID=" + bookID + ", title=" + title + ", author=" + author + ", category=" + category + ", amount=" + amount + "imagebook=" + imagebook + '}';
    }
}
