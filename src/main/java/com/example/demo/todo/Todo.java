package com.example.demo.todo;

import lombok.*;
import org.springframework.format.annotation.*;

import java.time.*;

@Getter
public class Todo {
    private int tno;
    private String title;
//    @DateTimeFormat(pattern = "yyyy-MM-dd")// 날짜의 입력 포맷
//    private LocalDate writeDay;
    private final LocalDate regDate = LocalDate.now();
    @Setter
    private  boolean finish =false;
    public Todo(int tno, String title){
        this.tno=tno;
        this.title=title;
    }
}
