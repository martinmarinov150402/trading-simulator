package com.tradinsimulator.tradingsimulator.config;

import com.tradinsimulator.tradingsimulator.WebSocketHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.client.WebSocketConnectionManager;

@Configuration
public class WebSocketClientConfig {

    @Bean
    public WebSocketHandler webSocketHandler() {
        return new WebSocketHandler();
    }

    @Bean
    public WebSocketConnectionManager wsConnectionManager() {

        StandardWebSocketClient client = new StandardWebSocketClient();
        WebSocketConnectionManager manager =
                new WebSocketConnectionManager(client, webSocketHandler(), "wss://ws.kraken.com/v2");
        manager.setAutoStartup(true);
        return manager;
    }
}