package kafka;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data@AllArgsConstructor
public class SpeedRecord {
    private String licensePlate;
    private int speed;


}
