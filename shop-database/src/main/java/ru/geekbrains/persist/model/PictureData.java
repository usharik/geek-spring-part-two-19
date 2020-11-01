package ru.geekbrains.persist.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "pictures_data")
public class PictureData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Lob
    @Type(type="org.hibernate.type.BinaryType") // для правильной работы PostgreSQL
    @Column(name = "data", length = 33554430) // для правильной hibernate-валидации в MySQL
    private byte[] data;

    @Column
    private String fileName;

    public PictureData() {
    }

    public PictureData(byte[] data) {
        this.data = data;
    }

    public PictureData(String fileName) {
        this.fileName = fileName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
