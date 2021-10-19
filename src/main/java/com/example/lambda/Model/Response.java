package com.example.lambda.Model;

import java.io.Serializable;

public class Response implements Serializable {

    public String sizes;

    public Response() {
        super();
    }

    public String getSizes() {
        return sizes;
    }

    public void setSizes(String sizes) {
        this.sizes = sizes;
    }

}
