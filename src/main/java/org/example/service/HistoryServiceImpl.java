package org.example.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.model.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class HistoryServiceImpl implements HistoryService {
    private CustomLinkedList<Task> history = new CustomLinkedList<>();
    private HashMap<Integer, Node> tasks = new HashMap<>();

    @Override
    public void add(Task task) {
        if (task == null) {
            return;
        }
        delete(task.getId());
        Node<Task> taskNode = history.addNode(task);
        tasks.put(task.getId(), taskNode);
    }

    @Override
    public void delete(int id) {
        if (tasks.containsKey(id)) {
            history.removeNode(tasks.get(id));
            tasks.remove(id);
        }
    }

    @Override
    public List<Task> getHistory() {
        return history.getAllList();
    }

    private static class CustomLinkedList<T> {
        private Node<T> header;
        private Node<T> tail;

        public Node<T> addNode(T data) {
            Node<T> node = new Node<>(data, null, null);
            if (header == null) {
                header = node;
                tail = node;
            } else {
                node.previous = tail;
                tail.next = node;
                tail = node;
            }
            return node;

        }

        public void removeNode(Node node) {
            if (node == null) {
                return;
            }
            if (header == node) {
                header = header.next;
                header.previous = null;
                return;
            }
            if (header == tail) {
                tail = tail.previous;
                tail.next = null;
                return;
            }
            node.previous.next = node.next;
            node.next.previous = node.previous;
        }

        public List<T> getAllList() {
            int count = 10;
            Node<T> current = tail;
            List<T> list = new ArrayList<>();
            while (count > 0 && current != null) {
                count--;
                list.add(current.data);
                current = current.previous;
            }
            return list;
        }

    }

    @AllArgsConstructor
    @Data
    private static class Node<T> {
        public T data;
        public Node<T> previous;
        public Node<T> next;

    }
}
