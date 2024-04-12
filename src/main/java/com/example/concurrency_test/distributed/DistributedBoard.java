package com.example.concurrency_test.distributed;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class DistributedBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;

    private String title;

    private String content;

    private int view;

    private DistributedBoard(String title, String content, int view) {
        this.title = title;
        this.content = content;
        this.view = view;
    }

    public static DistributedBoard create() {
        return new DistributedBoard("title", "content", 0);
    }
    public void addView() {
        this.view++;
    }
}
