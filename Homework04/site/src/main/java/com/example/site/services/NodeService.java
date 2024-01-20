package com.example.site.services;

import com.example.site.model.Node;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NodeService {
    private List<Node> nodes;

    public NodeService() {
        nodes = new ArrayList<>();
    }

    public List<Node> getAllNodes() {
        return nodes;
    }

    public Node addNode(Node node) {
        nodes.add(node);
        return node;
    }
}
