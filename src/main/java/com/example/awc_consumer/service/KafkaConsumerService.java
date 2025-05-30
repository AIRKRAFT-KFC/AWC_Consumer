package com.example.awc_consumer.service;

import com.example.awc_consumer.model.MetarData;
import com.example.awc_consumer.model.PirepData;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumerService {

    private final WebSocketService webSocketService;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "AWC-PIREP", groupId = "awc-consumer-group-realtime")
    public void consumePirep(String message) {
        try {
            System.out.println("🛩️  PIREP: " + message);
            
            PirepData pirepData = objectMapper.readValue(message, PirepData.class);
            webSocketService.sendPirepData(pirepData);
            
        } catch (Exception e) {
            log.error("PIREP 처리 오류: {}", e.getMessage());
        }
    }

    @KafkaListener(topics = "AWC-METAR", groupId = "awc-consumer-group-realtime")
    public void consumeMetar(String message) {
        try {
            System.out.println("🌤️  METAR: " + message);
            
            MetarData metarData = objectMapper.readValue(message, MetarData.class);
            webSocketService.sendMetarData(metarData);
            
        } catch (Exception e) {
            log.error("METAR 처리 오류: {}", e.getMessage());
        }
    }
}
