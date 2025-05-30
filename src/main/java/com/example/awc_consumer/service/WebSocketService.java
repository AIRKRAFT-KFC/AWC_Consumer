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
            messagingTemplate.convertAndSend("/topic/pirep", pirepData);
        } catch (Exception e) {
            log.error("PIREP WebSocket 전송 실패: {}", e.getMessage());
        }
    }

    public void sendMetarData(Object metarData) {
        try {
            messagingTemplate.convertAndSend("/topic/metar", metarData);
        } catch (Exception e) {
            log.error("METAR WebSocket 전송 실패: {}", e.getMessage());
        }
    }
}
