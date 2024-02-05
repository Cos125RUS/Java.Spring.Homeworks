package com.example.controllers;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import com.example.dto.TransferRequest;
import com.example.model.Account;
import com.example.services.TransferService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@AllArgsConstructor
@RestController
public class AccountController {

    private final TransferService transferService;

    @PostMapping("/transfer")
    public boolean transferMoney(
            @RequestBody TransferRequest request) {
        return transferService.transferMoney(
                request.getSenderAccountId(),
                request.getReceiverAccountId(),
                request.getAmount());
    }

    @PostMapping("/confirm/{senderAccountId}/{receiverAccountId}/{amount}/{episode}")
    public RedirectView confirm(@PathVariable long senderAccountId,
                                @PathVariable long receiverAccountId,
                                @PathVariable BigDecimal amount,
                                @PathVariable String episode,
                                RedirectAttributes attributes) {
        attributes.addAttribute("result",
                transferService.transferMoney(senderAccountId, receiverAccountId, amount));
        attributes.addAttribute("senderAccountId", senderAccountId);
        attributes.addAttribute("episode", episode);
        return new RedirectView("http://localhost:8080/confirm-transfer");
    }

    @GetMapping("/accounts")
    public List<Account> getAllAccounts() {
        return transferService.getAllAccounts();
    }
}
