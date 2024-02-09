package org.document.common.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
public class ErrorDetails {
    private Date timestamp;
    private String message;

    public ErrorDetails(String message){
        timestamp = Date.from(Instant.now());
        this.message = message;
    }
}
