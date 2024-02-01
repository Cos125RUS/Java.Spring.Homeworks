//package com.example.client.controller;
//
//import com.nimbusds.oauth2.sdk.Message;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.reactive.function.client.WebClient;
//
//import java.util.List;
//
//import static org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction.clientRegistrationId;
//
//@Controller
//@RequiredArgsConstructor
//public class MessagesController {
//
//    private final WebClient webClient;
//
////    public MessagesController(WebClient webClient) {
////        this.webClient = webClient;
////    }
//
////    @GetMapping("/messages")
////    public ResponseEntity<List<Message>> messages() {
////        ResponseEntity<List<Message>> messageList = this.webClient.get()
////                .uri("http://localhost:18080/messages")
////                .attributes(clientRegistrationId("oidc-client"))
////                .retrieve()
////                .toEntityList(Message.class)
////                .block();
////        System.out.println(messageList);
////        return messageList;
////    }
//
//    @GetMapping
//    public ResponseEntity<String> home() {
//        return webClient.get()
//                .uri("http://localhost:18080/")
//                .attributes(clientRegistrationId("oidc-client"))
//                .retrieve()
//                .toEntity(String.class)
//                .block();
//    }
//
//    @PostMapping("/done")
//    public String post() {
//        System.out.println("POST");
//        return "POST";
//    }
//
//    @GetMapping("/done")
//    public String get() {
//        System.out.println("GET");
//        return "GET";
//    }
//}
