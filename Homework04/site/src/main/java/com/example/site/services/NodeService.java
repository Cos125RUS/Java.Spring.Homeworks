package com.example.site.services;

import com.example.site.model.Node;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Сервисное обслуживание ваших заметок
 */
@Service
public class NodeService {
    /**
     * Заметка списков
     */
    private List<Node> nodes;

    /**
     * Чистый и непорочный конструктор
     */
    public NodeService() {
        nodes = new ArrayList<>();
    }

    /**
     * Мне нужны твоя одежда и твои заметки
     * @return возврат товара
     */
    public List<Node> getAllNodes() {
        return nodes;
    }

    /**
     * Шо, опять?
     * @param node протеста
     * @return reborn
     */
    public Node addNode(Node node) {
        nodes.add(node);
        return node;
    }
}
