package com.example.concurrency_test.optimistic;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor
public class OptimisticBoard {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;

    private String title;

    private String content;

    @Version
    private Long version;

    private int view;

    private OptimisticBoard(String title, String content, int view) {
        this.title = title;
        this.content = content;
        this.view = view;
    }

    public static OptimisticBoard create() {
        return new OptimisticBoard("title", "content", 0);
    }
    public void addView() {
        this.view++;
    }
}
