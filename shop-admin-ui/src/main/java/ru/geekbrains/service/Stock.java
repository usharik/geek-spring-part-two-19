package ru.geekbrains.service;

public class Stock {

    private Long id;

    private Integer count;

    public Stock() {
    }

    public Stock(Long id, Integer count) {
        this.id = id;
        this.count = count;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
