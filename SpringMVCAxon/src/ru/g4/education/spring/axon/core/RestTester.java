package ru.g4.education.spring.axon.core;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import ru.g4.education.spring.axon.api.CreateTagCmd;
import ru.g4.education.spring.axon.api.NewTagValCmd;

public class RestTester {
  
  
  private static void getTagTest(String tagName) {
    RestTemplate client = new RestTemplate();
    client.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
    MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
    headers.add("Accept", "application/json");
    headers.add("Authorization", "Basic YXhvbjoxMjM=");
    
    // headers.add("Cookie", "JSESSIONID=062F33C975A2952D055E082141803619");
    HttpEntity<Object> requestEntity = new HttpEntity<Object>(headers);
    ResponseEntity<?> response =
        client.exchange("http://localhost:8080/SpringMVCAxon/tags/{tagName}", HttpMethod.GET, requestEntity,
                        String.class, tagName);
    System.out.println(response.getStatusCode());
    System.out.println(response.getBody());
    // Tag t = (Tag) response.getBody();
    // System.out.println(t);
  }
  
  private static void addTagTest(String tagName) {
    CreateTagCmd restTag = new CreateTagCmd(tagName);
    HttpHeaders requestHeaders = new HttpHeaders();
    requestHeaders.setContentType(new MediaType("application", "json"));
    HttpEntity<CreateTagCmd> requestEntity = new HttpEntity<CreateTagCmd>(restTag, requestHeaders);
    RestTemplate restTemplate = new RestTemplate();
    restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
    // Make the HTTP POST request, marshaling the request to JSON, and the response to a String
    ResponseEntity<String> resp =
        restTemplate.exchange("http://localhost:8080/SpringMVCHelloWold/tags", HttpMethod.PUT, requestEntity,
                              String.class);
    System.out.println(" " + resp.getStatusCode() + " " + resp.getBody());
  }
  
  private static void addTagValueTest(String tagName, String value) {
    NewTagValCmd tagCmd = new NewTagValCmd(tagName, value);
    HttpHeaders requestHeaders = new HttpHeaders();
    requestHeaders.setContentType(new MediaType("application", "json"));
    HttpEntity<NewTagValCmd> requestEntity = new HttpEntity<NewTagValCmd>(tagCmd, requestHeaders);
    RestTemplate restTemplate = new RestTemplate();
    restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
    // Make the HTTP POST request, marshaling the request to JSON, and the response to a String
    ResponseEntity<String> resp =
        restTemplate.exchange("http://localhost:8080/SpringMVCHelloWold/tags/{tagName}", HttpMethod.POST,
                              requestEntity, String.class, tagName);
    System.out.println(" " + resp.getStatusCode() + " " + resp.getBody());
  }
  
  
  public static void main(String[] args) {
    // addTagTest("tagfromRest2");
    // addTagValueTest("tagfromRest2", "testVal");
    getTagTest("SagaTestTag_1");
    // jaScadaTest();
  }
  
  static class BytesTagDescriptor {
    private String name;
    private String description;
    
    public BytesTagDescriptor(String name, String description) {
      super();
      this.name = name;
      this.description = description;
    }
    
    public BytesTagDescriptor() {
      super();
    }
    
    public String getName() {
      return name;
    }
    
    public String getDescription() {
      return description;
    }
    
    public void setName(String name) {
      this.name = name;
    }
    
    public void setDescription(String description) {
      this.description = description;
    }
  }
  
  private static void jaScadaTest() {
    BytesTagDescriptor tagCmd = new BytesTagDescriptor("bt1", "bytesTag1");
    HttpHeaders requestHeaders = new HttpHeaders();
    requestHeaders.setContentType(new MediaType("application", "json"));
    HttpEntity<BytesTagDescriptor> requestEntity = new HttpEntity<BytesTagDescriptor>(tagCmd, requestHeaders);
    RestTemplate restTemplate = new RestTemplate();
    restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
    // Make the HTTP POST request, marshaling the request to JSON, and the response to a String
    ResponseEntity<String> resp =
        restTemplate.exchange("http://10.18.32.44:8080/jascada/api/tags/bytes", HttpMethod.PUT, requestEntity,
                              String.class);
    System.out.println(" " + resp.getStatusCode() + " " + resp.getBody());
  }
  
}
