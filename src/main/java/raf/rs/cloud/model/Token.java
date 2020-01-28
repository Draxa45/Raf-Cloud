package raf.rs.cloud.model;

import java.io.Serializable;

public class Token implements Serializable {

    private static final  long serialVersionUID = 1L;

    private String token;

    public Token(String token) {
        this.token = token;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
