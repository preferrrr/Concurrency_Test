package com.example.concurrency_test.board;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;

    private String title;

    private String content;

    private int view;

    private Board(String title, String content, int view) {
        this.title = title;
        this.content = content;
        this.view = view;
    }

    public static Board create() {
        return new Board("title", "content", 0);
    }

    public void addView() {
        this.view++;
    }

}