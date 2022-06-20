package br.com.foursys.fourcamp.fourbank.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
@Setter
public class ResponseModel {

    private HttpStatus status;
    private Integer statusCode;
    private String message;

}
