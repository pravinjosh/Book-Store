package com.example.demoIntellij.responseDTO;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO {
    private int responseCode;
    private String message;
    private String status;
    private Object data;
}
