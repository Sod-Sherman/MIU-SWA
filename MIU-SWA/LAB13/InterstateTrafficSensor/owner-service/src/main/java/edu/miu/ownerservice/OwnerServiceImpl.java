package edu.miu.ownerservice;

import kafka.SpeedRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class OwnerServiceImpl {

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private FeeRecord feeRecord;

    @KafkaListener(topics = {"${app.topic.tofasttopic}"})
    public void cameraRecordReceiver(@Payload SpeedRecord speedRecord,
                                     @Headers MessageHeaders headers) {
        System.out.println("************ Received message ********====> " + speedRecord.toString());
        feeRecord.handleFeeRecord(speedRecord);

        /*
            headers.keySet().forEach(key ->
                System.out.println("****** headers ******** key: " + key + " ---------value: " + headers.get(key)));

        */
    }
}
