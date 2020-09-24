package edu.miu.speedservice.service;

import kafka.SensorRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;

public interface SpeedService {
    public Double calculateSpeed(SensorRecord cam1, SensorRecord cam2);

    @KafkaListener(topics = "cameratopic1")
    public void cameraRecordReceiver(@Payload SensorRecord sensorRecord, @Headers MessageHeaders headers);
}
