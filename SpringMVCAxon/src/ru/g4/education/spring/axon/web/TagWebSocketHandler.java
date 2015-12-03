package ru.g4.education.spring.axon.web;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import ru.g4.education.spring.axon.ReadModelDAO;
import ru.g4.education.spring.axon.Tag;

import com.fasterxml.jackson.databind.ObjectMapper;


public class TagWebSocketHandler extends TextWebSocketHandler {
  
  ObjectMapper mapper = new ObjectMapper();
  
  WebSocketSession session;
  
  @Autowired
  private ReadModelDAO dao;
  
  private SendThread sendThr;
  
  public TagWebSocketHandler() {
  }
  
  @Override
  public void afterConnectionEstablished(WebSocketSession session) throws Exception {
    this.session = session;
    System.out.println("Открыли сессию " + session.getUri().getPath());
    sendThr = new SendThread();
    sendThr.start();
  }
  
  @Override
  protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
    super.handleTextMessage(session, message);
    TransportedTag tag = mapper.readValue(message.getPayload(), TransportedTag.class);
    System.out.println("TAG пришёл " + tag);
  }
  
  @Override
  public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
    super.handleTransportError(session, exception);
    
  }
  
  @Override
  public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
    super.afterConnectionClosed(session, status);
    if (sendThr != null)
      sendThr.interrupt();
  }
  
  private class SendThread extends Thread {
    private int i = 0;
    
    @Override
    public void run() {
      while (!Thread.currentThread().isInterrupted()) {
        Tag t = dao.getTag("asyncTestTag_1");
        TransportedTag transportTag = new TransportedTag(i++, t);
        try {
          session.sendMessage(new TextMessage(mapper.writeValueAsString(transportTag)));
        } catch (IOException e) {
          e.printStackTrace();
        }
        try {
          sleep(5000);
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
        }
      }
    }
  }
  
  private class TransportedTag {
    private int count;
    
    private Tag tag;
    
    public int getCount() {
      return count;
    }
    
    public void setCount(int count) {
      this.count = count;
    }
    
    public Tag getTag() {
      return tag;
    }
    
    public void setTag(Tag tag) {
      this.tag = tag;
    }
    
    public TransportedTag() {
    }
    
    public TransportedTag(int count, Tag tag) {
      super();
      this.count = count;
      this.tag = tag;
    }
    
    @Override
    public String toString() {
      return "TransportedTag [count=" + count + ", tag=" + tag + "]";
    }
    
    
  }
  
}
