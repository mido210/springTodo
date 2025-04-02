package com.example.demo.todo;

import lombok.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.support.*;
import org.springframework.web.servlet.*;

import java.util.*;

@Controller
public class TodoController {
    private final StandardServletMultipartResolver standardServletMultipartResolver;
    private List<Todo> todos = new ArrayList<>();
    private int tno =1;


    public TodoController(StandardServletMultipartResolver standardServletMultipartResolver) {
        this.standardServletMultipartResolver = standardServletMultipartResolver;
    }
    // C -Get, Post

    @GetMapping("/todo/write")
    public void write(){

    }
    //<input name='title'>에 요청 객체에 담겨 백엔드로 전달되면 같은 이름의 파라미터에 담긴다
    // @RequestParam : 요청 객체에서 이름이 같은 데이터를 추출해 파라미터에 담아라
    //                 스프링은 그 어떤 데이터라도 파라미터로 변환할 수 있을까?
    //                 <input type='date'> ->2020-11-20 -> 2020년 11월 20일 객체 변환이 될까? 스프링은 안됨
    @PostMapping("/todo/write")
    public ModelAndView write(@RequestParam  String title){
        Todo todo = new Todo(tno++,title);
        todos.add(todo);
        return new ModelAndView("redirect:/todo/list");
    }
    @GetMapping("/todo/list")
        // R - 목록
    public ModelAndView list(){
        return new ModelAndView("todo/list").addObject("todos",todos);
    }

    @PostMapping("/todo/finish")
    public ModelAndView finish(@RequestParam int tno){
        for(Todo todo:todos){
            if(todo.getTno()==tno){
                todo.setFinish(true);
                break;
            }
        }
        return new ModelAndView("redirect:/todo/list");
    }

    @PostMapping("/todo/delete")
    public ModelAndView delete(@RequestParam int tno){
        //0번째 원소부터 차례대로 찾으면서 삭제 할 경우, 접근하지 않고 통과하는 원소가 생김
        //index : 0 1 2 3
        // 값     A B C D
        // B가 지워지는 경우 -> B가 지워지는 순간에 C가 첫번째, D가 두번째 원소가 된다
//                           -> 인덱스는 1에서2로 증가 -> C를 건너뛰고 D로 접근 -> 자바가 용납안한다

        for(int i=todos.size()-1;i>=0;i--){
            if(todos.get(i).getTno()==tno){
                todos.remove(i);
                break;
            }
        }
        return new ModelAndView("redirect:/todo/list");
    }

}
