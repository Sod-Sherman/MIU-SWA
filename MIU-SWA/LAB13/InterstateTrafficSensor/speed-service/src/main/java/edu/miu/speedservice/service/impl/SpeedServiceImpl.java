package edu.miu.speedservice.service.impl;

import edu.miu.speedservice.service.SpeedCalculator;
import edu.miu.speedservice.service.SpeedService;
import kafka.SensorRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class SpeedServiceImpl {

    @Autowired
    private SpeedCalculator speedCalculator;

    @KafkaListener(topics = {"${app.topic.camera-topic1}", "${app.topic.camera-topic2}"})
    public void cameraRecordReceiver(@Payload SensorRecord sensorRecord,
                                     @Headers MessageHeaders headers) {
        System.out.println("************ Received message ********====> " + sensorRecord.toString());
       speedCalculator.handleRecord(sensorRecord);

        /*
            headers.keySet().forEach(key ->
                System.out.println("****** headers ******** key: " + key + " ---------value: " + headers.get(key)));

        */
    }
}
