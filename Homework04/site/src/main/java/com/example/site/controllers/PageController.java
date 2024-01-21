package com.example.site.controllers;

import com.example.site.model.Node;
import com.example.site.services.DateService;
import com.example.site.services.NodeService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Контролёр в трамвае
 */
@AllArgsConstructor
@Controller
@Log
public class PageController {
    /**
     * Да-да сервис
     */
    private DateService dateService;
    /**
     * Not Bad сервис
     */
    private NodeService nodeService;

    /**
     * Главная страница
     * @return Ничего полезного
     */
    @GetMapping
    public String getHome() {
        return "index";
    }

    /**
     * Список всего, что нужно успеть сделать до смерти
     * @param model топ-модель
     * @return иногда они возвращаются
     */
    @GetMapping("/nodes")
    public String getNodes(Model model) {
        model.addAttribute("date", dateService.getNow());
        model.addAttribute("nodesList", nodeService.getAllNodes());
        return "nodes";
    }

    /**
     * Добавка пюрешки в столовке
     * @return пустая тарелка
     */
    @GetMapping("/add")
    public String getNewNode() {
        return "add";
    }

    /**
     * Ещё одна нода
     * @param node вот она
     * @return сдача
     */
    @PostMapping("/nodes")
    public String addNode(Node node) {
        nodeService.addNode(node);
        return "redirect:/nodes";
    }
}
