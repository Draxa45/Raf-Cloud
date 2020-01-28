package raf.rs.cloud.model;

import lombok.Data;

@Data
public class LogInResponse {

    private  User user;
    private boolean found;

    public LogInResponse(User user, boolean found) {
        this.user = user;
        this.found = found;
    }

}
