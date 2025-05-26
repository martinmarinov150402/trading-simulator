package com.tradinsimulator.tradingsimulator;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tradinsimulator.tradingsimulator.records.Currency;
import com.tradinsimulator.tradingsimulator.records.Update;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WebSocketHandler extends TextWebSocketHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Map<String, Currency> currencyMap = new HashMap<>();
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        Map<String, Object> payload = new HashMap<>();
        payload.put("method", "subscribe");

        Map<String, Object> params = new HashMap<>();
        params.put("channel", "ticker");
        params.put("symbol", List.of(
                "XBT/USD", "USDT/USD", "ETH/USD", "XRP/USD", "USDC/USD",
                "DOGE/USD", "ADA/USD", "SOL/USD", "DOT/USD", "LTC/USD",
                "BCH/USD", "LINK/USD", "XLM/USD", "TRX/USD", "EOS/USD", "ETC/USD"
        ));

        payload.put("params", params);

        // Convert to JSON
        String jsonMessage = objectMapper.writeValueAsString(payload);
        session.sendMessage(new TextMessage(jsonMessage));
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        if(message.getPayload().contains("ticker") && !message.getPayload().contains("subscribe")) {
            System.out.println(message.getPayload());
            Update data = objectMapper.readValue(message.getPayload(), Update.class);
            currencyMap.put(data.data().getFirst().symbol(), data.data().getFirst());
            //System.out.println(data);
        }

    }

    public Currency getData(String currencyCode) {
        return currencyMap.get(currencyCode);
    }
}