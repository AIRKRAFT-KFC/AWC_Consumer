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

    @KafkaListener(topics = "AWC-PIREP", groupId = "awc-consumer-group-new")
    public void consumePirep(String message) {
        try {
            log.info("=== PIREP MESSAGE RECEIVED ===");
            log.info("Raw message: {}", message);
            System.out.println("=== PIREP MESSAGE RECEIVED ===");
            System.out.println("Raw message: " + message);
            
            PirepData pirepData = objectMapper.readValue(message, PirepData.class);
            
            log.info("✈️  PIREP Details:");
            log.info("PIREP ID: {}", pirepData.getPirepId());
            log.info("Aircraft: {} ({})", pirepData.getAcType(), pirepData.getIcaoId());
            log.info("Position: {}, {}", pirepData.getLat(), pirepData.getLon());
            log.info("Flight Level: {} ({})", pirepData.getFltLvl(), pirepData.getFltLvlType());
            log.info("Temperature: {}°C", pirepData.getTemp());
            log.info("Wind: {}° / {} kt", pirepData.getWdir(), pirepData.getWspd());
            log.info("Receipt Time: {}", pirepData.getReceiptTime());
            
            webSocketService.sendPirepData(pirepData);
            log.info("✅ PIREP data processed and sent via WebSocket");
            
        } catch (Exception e) {
            log.error("❌ Error processing PIREP message: {}", e.getMessage(), e);
            System.err.println("Error in PIREP consumer: " + e.getMessage());
        }
    }

    @KafkaListener(topics = "AWC-METAR", groupId = "awc-consumer-group-new")
    public void consumeMetar(String message) {
        try {
            log.info("=== METAR MESSAGE RECEIVED ===");
            log.info("Raw message: {}", message);
            System.out.println("=== METAR MESSAGE RECEIVED ===");
            System.out.println("Raw message: " + message);
            
            MetarData metarData = objectMapper.readValue(message, MetarData.class);
            
            log.info("🌤️  METAR Details:");
            log.info("Station: {} ({})", metarData.getName(), metarData.getIcaoCode());
            log.info("Position: {}, {}", metarData.getLat(), metarData.getLon());
            log.info("Temperature: {}°C, Dewpoint: {}°C", metarData.getTemp(), metarData.getDewp());
            log.info("Wind: {}° / {} kt", metarData.getWdir(), metarData.getWspd());
            log.info("Visibility: {}", metarData.getVisib());
            log.info("Altimeter: {} hPa", metarData.getAltim());
            log.info("Observation Time: {}", metarData.getObservationTime());
            
            webSocketService.sendMetarData(metarData);
            log.info("✅ METAR data processed and sent via WebSocket");
            
        } catch (Exception e) {
            log.error("❌ Error processing METAR message: {}", e.getMessage(), e);
            System.err.println("Error in METAR consumer: " + e.getMessage());
        }
    }
}
