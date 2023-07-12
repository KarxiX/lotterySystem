package com.cssl.status;


import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class Status {
    private String status;
    private String message;
    private List<String> winners;
    private List<String> winnerNames;
}
