package kafka;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data@AllArgsConstructor
public class Owner {
    private String plateNumber;
    private String name;
    private String phoneNumber;
    }
