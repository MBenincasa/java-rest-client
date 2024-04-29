package io.github.mbenincasa.javarestclient.client;

public class ReqResPatchRequest {

    private String name;
    private String job;

    public ReqResPatchRequest() {}

    public ReqResPatchRequest(String name, String job) {
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
        return "PutRequestExample{" +
                "name='" + name + '\'' +
                ", job='" + job + '\'' +
                '}';
    }
}
