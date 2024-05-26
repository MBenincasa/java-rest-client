package io.github.mbenincasa.javarestclient.client.response.reqres;

import java.util.Date;

public class ReqResPutResponse {

    private String name;
    private String job;
    private Date updatedAt;

    public ReqResPutResponse() {
    }

    public ReqResPutResponse(String name, String job, Date updatedAt) {
        this.name = name;
        this.job = job;
        this.updatedAt = updatedAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "ReqResPutResponse{" +
                "name='" + name + '\'' +
                ", job='" + job + '\'' +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
