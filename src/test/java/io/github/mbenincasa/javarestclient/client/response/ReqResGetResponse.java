package io.github.mbenincasa.javarestclient.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReqResGetResponse {
    private ReqResData data;
    private ReqResSupport support;

    public ReqResGetResponse() {
    }

    public ReqResGetResponse(ReqResData data, ReqResSupport support) {
        this.data = data;
        this.support = support;
    }

    public ReqResData getData() {
        return data;
    }

    public void setData(ReqResData data) {
        this.data = data;
    }

    public ReqResSupport getSupport() {
        return support;
    }

    public void setSupport(ReqResSupport support) {
        this.support = support;
    }

    @Override
    public String toString() {
        return "ReqResGetResponse{" +
                "data=" + data +
                ", support=" + support +
                '}';
    }

    public static class ReqResData {
        private Integer id;
        private String email;
        @JsonProperty("first_name")
        private String firstName;
        @JsonProperty("last_name")
        private String lastName;
        private String avatar;

        public ReqResData() {
        }

        public ReqResData(Integer id, String email, String firstName, String lastName, String avatar) {
            this.id = id;
            this.email = email;
            this.firstName = firstName;
            this.lastName = lastName;
            this.avatar = avatar;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        @Override
        public String toString() {
            return "ReqResData{" +
                    "id=" + id +
                    ", email='" + email + '\'' +
                    ", firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    ", avatar='" + avatar + '\'' +
                    '}';
        }
    }

    public static class ReqResSupport {
        private String url;
        private String text;

        public ReqResSupport() {
        }

        public ReqResSupport(String url, String text) {
            this.url = url;
            this.text = text;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return "ReqResSupport{" +
                    "url='" + url + '\'' +
                    ", text='" + text + '\'' +
                    '}';
        }
    }
}
