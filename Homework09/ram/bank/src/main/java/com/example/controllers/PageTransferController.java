package com.example.controllers;

import com.example.services.TransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * Контроллер страниц трансферов
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/bank")
public class PageTransferController {
    private final TransferService transferService;

    /**
     * Страница подтверждения платежа
     * @param senderAccountId
     * @param receiverAccountId
     * @param amount
     * @param episode
     * @param model
     * @return
     */
    @GetMapping("/confirm")
    public String transferMoney(@RequestParam("senderAccountId") long senderAccountId,
                                @RequestParam("receiverAccountId") long receiverAccountId,
                                @RequestParam("amount") BigDecimal amount,
                                @RequestParam("episode") String episode, Model model) {
        String action = "confirm/" + senderAccountId + "/" + receiverAccountId + "/" +
                amount + "/" + episode;
        model.addAttribute("amount", amount);
        model.addAttribute("action", action);
        return "confirm";
    }


}
