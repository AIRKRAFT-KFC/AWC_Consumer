package com.example.awc_consumer.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class WebSocketService {

    private final SimpMessagingTemplate messagingTemplate;
    private final ObjectMapper objectMapper;

    public void sendPirepData(Object pirepData) {
        try {
            log.info("🌐 Sending PIREP data via WebSocket to /topic/pirep");
            
            String pirepJson = objectMapper.writeValueAsString(pirepData);
            messagingTemplate.convertAndSend("/topic/pirep", pirepJson);
            
            log.info("✅ PIREP data sent successfully to WebSocket clients");
        } catch (Exception e) {
            log.error("❌ Failed to send PIREP data via WebSocket: {}", e.getMessage(), e);
        }
    }

    public void sendMetarData(Object metarData) {
        try {
            log.info("🌐 Sending METAR data via WebSocket to /topic/metar");
            
            String metarJson = objectMapper.writeValueAsString(metarData);
            messagingTemplate.convertAndSend("/topic/metar", metarJson);
            
            log.info("✅ METAR data sent successfully to WebSocket clients");
        } catch (Exception e) {
            log.error("❌ Failed to send METAR data via WebSocket: {}", e.getMessage(), e);
        }
    }
}
