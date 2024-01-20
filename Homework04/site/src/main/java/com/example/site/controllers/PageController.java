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

@AllArgsConstructor
@Controller
@Log
public class PageController {
    private DateService dateService;
    private NodeService nodeService;

    @GetMapping
    public String getHome() {
        return "index";
    }

    @GetMapping("/nodes")
    public String getNodes(Model model) {
        model.addAttribute("date", dateService.getNow());
        model.addAttribute("nodesList", nodeService.getAllNodes());
        return "nodes";
    }

    @GetMapping("/add")
    public String getNewNode() {
        return "add";
    }

    @PostMapping("/nodes")
    public String addNode(Node node) {
        nodeService.addNode(node);
        return "redirect:/nodes";
    }
}
