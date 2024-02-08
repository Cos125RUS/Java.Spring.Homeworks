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

/**
 * Rest-контроллер трансферов
 */
@AllArgsConstructor
@RestController
@RequestMapping("/bank")
public class RestTransferController {

    private final TransferService transferService;

    /**
     * Перевод со счёта на счёт
     * @param request
     * @return
     */
    @PostMapping("/transfer")
    public boolean transferMoney(
            @RequestBody TransferRequest request) {
        return transferService.transferMoney(
                request.getSenderAccountId(),
                request.getReceiverAccountId(),
                request.getAmount());
    }

    /**
     * Подтверждение трансфера
     * @param senderAccountId
     * @param receiverAccountId
     * @param amount
     * @param episode
     * @param attributes
     * @return
     */
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

    /**
     * Список счетов (для проверки работы приложения)
     * @return
     */
    @GetMapping("/accounts")
    public List<Account> getAllAccounts() {
        return transferService.getAllAccounts();
    }
}
