package org.example.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@EqualsAndHashCode
public class Task {
    private int id;
    private String name;
    private String description;
    private TaskStatus taskStatus;
    private LocalDateTime timeStart;
    private LocalDateTime timeEnd;
    private long longTime;
}
