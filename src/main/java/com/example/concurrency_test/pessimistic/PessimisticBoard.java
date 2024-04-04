package com.example.concurrency_test.pessimistic;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class PessimisticBoard {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;

    private String title;

    private String content;

    private int view;

    private PessimisticBoard(String title, String content, int view) {
        this.title = title;
        this.content = content;
        this.view = view;
    }

    public static PessimisticBoard create() {
        return new PessimisticBoard("title", "content", 0);
    }
    public void addView() {
        this.view++;
    }
}
