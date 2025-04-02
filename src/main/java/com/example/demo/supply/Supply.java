package com.example.demo.supply;

import lombok.*;
import org.springframework.format.annotation.*;

import java.time.*;
import java.util.regex.*;
// 사용자로 부터 값을 입력받는 객체(command 객체)
// 스프링에서 커맨더 객체는 반드시 기본생성자와 세터가 있어야한다


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Supply {
    private Integer sno;
    private  String name;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate regDate;
    private Integer price;
    private Integer count;

}
