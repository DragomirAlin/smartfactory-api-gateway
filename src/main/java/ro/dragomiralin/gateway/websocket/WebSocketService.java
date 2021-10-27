package ro.dragomiralin.gateway.websocket;


import ro.dragomiralin.gateway.common.Data;

public interface WebSocketService {
    void notify(Data data);
}
