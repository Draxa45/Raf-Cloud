package raf.rs.cloud.config;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(
        value = HttpStatus.NO_CONTENT
)
public class CrosException extends RuntimeException {
}
