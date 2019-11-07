package kz.example.jupiter.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientRecord {
    private String id;
    private String fio;
//    private String clientGroup;
}
