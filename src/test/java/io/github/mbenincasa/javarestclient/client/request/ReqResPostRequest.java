package io.github.mbenincasa.javarestclient.client.request;

public class ReqResPostRequest {

    private String name;
    private String job;

    public ReqResPostRequest() {}

    public ReqResPostRequest(String name, String job) {
        this.name = name;
        this.job = job;
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

    @Override
    public String toString() {
        return "PostRequestExample{" +
                "name='" + name + '\'' +
                ", job='" + job + '\'' +
                '}';
    }
}
