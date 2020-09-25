package edu.miu.ownerservice;

import kafka.FeeInfo;
import kafka.Owner;
import kafka.SpeedRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;

@Service
public class FeeRecord {

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private Sender sender;

    Map<String, Owner> ownerMap;

    @Value("${app.topic.ownertopic}")
    private String ownertopic;

    FeeRecord(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @PostConstruct
    public void init() {
        this.ownerMap = ownerRepository.getOwnerMap();
    }


    /*
    SpeedRecord
        private String licensePlate;
        private int speed;
     */
    public void handleFeeRecord(SpeedRecord speedRecord) {
        FeeInfo
                feeInfo = new FeeInfo(
                speedRecord.getLicensePlate(),
                findOwner(speedRecord.getLicensePlate()),
                speedRecord.getSpeed(),
                findFee(speedRecord.getSpeed())
        );

//        feeInfo = FeeInfo.builder()
//                .ownerInfo(findOwner(speedRecord.getLicensePlate()).toString())
//                .speed(speedRecord.getSpeed())
//                .fee(findFee(speedRecord.getSpeed()))
//                .build();


        System.out.println("Sending feeInfo to OWNER-TOPIC = " + feeInfo);
        sender.send(ownertopic, feeInfo);

    }

    /*
        72 – 77 miles/hour = $ 25
        77 - 82 miles/hour = $ 45
        82 – 90 miles/hour = $ 80
           > 90 miles/hour = $ 125
     */

    public int findFee(int speed) {
        if (speed < 72) return 0;
        if (speed < 77) return 25;
        if (speed < 82) return 45;
        if (speed < 90) return 80;
        return 125;
    }

    public Owner findOwner(String licensePlate) {

        return ownerMap.getOrDefault(licensePlate, new Owner(licensePlate, "No Registered Vehicle", "-"));

    }

}
