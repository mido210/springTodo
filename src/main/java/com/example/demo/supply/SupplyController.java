package com.example.demo.supply;

import jakarta.annotation.*;
import org.springframework.cglib.core.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

import java.net.*;
import java.time.*;
import java.util.*;

@Controller
public class SupplyController {
    private List<Supply> supplies = new ArrayList<>();
    private int sno=4;

    @PostConstruct
    public void init(){
        supplies.add(new Supply(1,"펩시콜라",LocalDate.of(2025,3,22),3000,31));
        supplies.add(new Supply(2,"코카콜라",LocalDate.of(2025,3,22),3300,134));
        supplies.add(new Supply(3,"칠성사이다",LocalDate.of(2025,3,22),2900,12));

    }

    @GetMapping("/supply/add")
    public void supplyWrite(){
    }
    @PostMapping("/supply/add")
    public ModelAndView supplyList(@ModelAttribute Supply supply){
        supply.setSno(sno++);
        supplies.add(supply);
        return new ModelAndView("redirect:/supply/list");
    }
    @GetMapping("/supply/list")
    public ModelAndView supplyList(){
        return new ModelAndView("supply/list").addObject("supplies",supplies);
    }
    @PostMapping("/supply/delete")
    public ModelAndView supplyDelet(@RequestParam int sno){
        for(int i=supplies.size()-1;i>=0;i--){
            if(supplies.get(i).getSno()==sno){
                supplies.remove(i);
            }
        }
        return new ModelAndView("redirect:/supply/list");
    }
    @PostMapping("/supply/plus")
    // @ModelAttribute : 사용자 입력을 담은 객체를 생성
    // Supply 객체로 사용자 입력을 받아오는데 모든 값을 다 입력하는 건 아니다 -> 입력하지 않은 필드는 null이 들어간다(int->Integer)
    // @ModelAttribute는 기본 생성자로 객체를 생성한 다음, setter로 값을 집어넣은다
    public ModelAndView supplyPlus(@RequestParam int sno){
       for(int i=0;i<supplies.size();i++){
           if(supplies.get(i).getSno()==sno){
               supplies.get(i).setCount(supplies.get(i).getCount()+1);
               break;
           }
       }
        return new ModelAndView("redirect:/supply/list");
    }


    @PostMapping("/supply/minus")
    public ModelAndView supplyMinus(@RequestParam int sno){
        for(int i=supplies.size()-1;i>=0;i--){
            if(supplies.get(i).getSno()==sno){
                if(supplies.get(i).getCount()>0){
                    supplies.get(i).setCount(supplies.get(i).getCount()-1);
                    break;
                }
                else {
                    return new ModelAndView("redirect:/supply/list");
                }
            }
        }
        return new ModelAndView("redirect:/supply/list");
    }
//    public ModelAndView supplyPlus(@RequestParam Integer sno){
//
//        for(Supply supply:supplies){
//            if(supply.getSno().equals(sno)){
//                supply.setCount(supply.getCount()+1);
//            }
//        }
//        return new ModelAndView("redirect:/supply/list");
//    }
//    @PostMapping("/supply/minus")
//    public ModelAndView supplyMinus(@RequestParam Integer sno){
//        for(Supply supply:supplies){
//            if(supply.getSno().equals(sno)){
//                supply.setCount(supply.getCount()-1);
//            }
//        }
//        return new ModelAndView("redirect:/supply/list");
//    }


}
