package br.com.foursys.fourcamp.fourbank.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerMessageResponseDTO {

    private String message;
}