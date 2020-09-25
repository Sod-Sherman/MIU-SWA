package kafka;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data@NoArgsConstructor
public class FeeInfo {
    /*
    license plate, owner info, speed, amount of the fee
     */
    private String licensePlate;
    private String ownerInfo;
    private int speed;
    private int fee;

    public FeeInfo(String licensePlate, Owner owner, int speed, int fee) {
        this.licensePlate = licensePlate;
        ownerInfo = owner.getName() + " " + owner.getPhoneNumber();
        this.speed = speed;
        this.fee = fee;

    }

}
