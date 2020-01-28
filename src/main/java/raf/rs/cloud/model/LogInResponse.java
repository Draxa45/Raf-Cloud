package raf.rs.cloud.model;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class LogInResponse {

    private  User user;
    private boolean found;
    private Token token;

    public LogInResponse(User user, boolean found) {
        this.user = user;
        this.found = found;
        if(this.found)
        this.token = new Token(Jwts.builder().setSubject(user.getUsername()).setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256, "123#&*zcvAWEE999").compact());
        System.out.println(token.getToken());
    }

}
