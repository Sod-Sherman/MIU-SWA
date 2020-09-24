package edu.miu.speedservice.service.impl;

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

    @Autowired(required = true)
    private KafkaTemplate<String, SensorRecord> kafkaTemplate;

    public void sendTest() {
        kafkaTemplate.send("cameratopic1", new SensorRecord("aa", 1, 1, 2));
    }

    public Double calculateSpeed(SensorRecord cam1, SensorRecord cam2) {
        System.out.println("cam1 = " + cam1);
        System.out.println("cam2 = " + cam2);
        return null;
    }

    @KafkaListener(topics = {"${app.topic.camera-topic1}", "${app.topic.camera-topic2}"})
    public void cameraRecordReceiver(@Payload SensorRecord sensorRecord,
                                     @Headers MessageHeaders headers) {
        System.out.println("************ Received message ********====> " + sensorRecord.toString());
        headers.keySet().forEach(key ->
                System.out.println("****** headers ******** key: " + key + " ---------value: " + headers.get(key)));
    }
}
