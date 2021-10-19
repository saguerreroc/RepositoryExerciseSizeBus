package com.example.lambda.Model;

import java.io.Serializable;

public class Request implements Serializable {

    public String groups;

    public Request() {
        super();
    }

    public String getGroups() {
        return groups;
    }

    public void setGroups(String groups) {
        this.groups = groups;
    }
}
