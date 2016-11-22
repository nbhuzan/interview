package com.huzan.interview.form;

import java.util.List;

/**
 * Created by huzan on 2016/11/22.
 */
public class ManageResultForm<T> extends ResultForm {
    private String title;
    private int size;
    private int page;
    private List<T> list;
    private List<String> tableRank;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public List<String> getTableRank() {
        return tableRank;
    }

    public void setTableRank(List<String> tableRank) {
        this.tableRank = tableRank;
    }
}
