package com.example.controllers;

import com.example.dto.TransferRequest;
import com.example.services.TransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpRequest;

@Controller
@RequiredArgsConstructor
public class PageController {
    private final TransferService transferService;


    @GetMapping("/confirm")
    public String transferMoney(@RequestParam("senderAccountId") long senderAccountId,
                                @RequestParam("receiverAccountId") long receiverAccountId,
                                @RequestParam("amount") BigDecimal amount,
                                @RequestParam("episode") String episode, Model model) {
        System.out.println(senderAccountId);
        System.out.println(receiverAccountId);
        System.out.println(amount);
        System.out.println(episode);
        String action = "confirm/" + senderAccountId + "/" + receiverAccountId + "/" +
                amount + "/" + episode;
//        model.addAttribute("senderAccountId", senderAccountId);
//        model.addAttribute("receiverAccountId", receiverAccountId);
        model.addAttribute("amount", amount);
        model.addAttribute("action", action);
        return "confirm";
    }


}
