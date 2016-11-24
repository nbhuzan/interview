package com.huzan.interview.bean;

/**
 * Created by huzan on 2016/11/23.
 */
public class ExaminationBean extends SubjectBean {
    private int id;
    private int paperId;
    private String respond;
    private int subjectId;
    private double score;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPaperId() {
        return paperId;
    }

    public void setPaperId(int paperId) {
        this.paperId = paperId;
    }

    public String getRespond() {
        return respond;
    }

    public void setRespond(String respond) {
        this.respond = respond;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
