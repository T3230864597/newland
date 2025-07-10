package com.oa4.util;

import com.oa4.pojo.Emp;

import java.util.List;

public class PageData {
    private List<Emp> list;
    private int currentPage;
    private int total;

    public PageData() {}

    public PageData(List<Emp> list, int currentPage, int total) {
        this.list = list;
        this.currentPage = currentPage;
        this.total = total;
    }

    public List<Emp> getList() {
        return list;
    }

    public void setList(List<Emp> list) {
        this.list = list;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
