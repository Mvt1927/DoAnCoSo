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
public class Reader {
    private String readerID, name, birth, sex, address, imageReader;

    public Reader(String ReaderID, String Name, String Birth, String Sex, String Address,String ImageReader) {
        this.readerID = ReaderID;
        this.name = Name;
        this.birth = Birth;
        this.sex = Sex;
        this.address = Address;
        this.imageReader = ImageReader;
    }

    // Get methods
    public String getReaderID() {
        return readerID;
    }

    public String getName() {
        return name;
    }

    public String getBirth() {
        return birth;
    }

    public String getSex() {
        return sex;
    }

    public String getAddress() {
        return address;
    }

    public String getImageReader() {
        return imageReader;
    }

    //Set methods
    public void setReaderID(String ReaderID) {
        this.readerID = ReaderID;
    }

    public void setName(String Name) {
        this.name = Name;
    }

    public void setBirth(String Birth) {
        this.birth = Birth;
    }

    public void setSex(String Sex) {
        this.sex = Sex;
    }

    public void setAddress(String Address) {
        this.address = Address;
    }

    public void setImageReader(String ImageReader) {
        this.imageReader = ImageReader;
    }

    //toString method
    @Override
    public String toString() {
        return "Book{" + "ReaderID=" + readerID + ", Name=" + name + ", DateOfBirth=" + birth + ", Sex=" + sex + "ImageReader=" + imageReader + '}';
    }
}
