package com.group.libraryapp.controller.calculator;

import com.group.libraryapp.dto.calculator.request.CalculatorAddRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController //API 만들때 필요
public class CalculatorController {

//    @GetMapping("/add") //method//path
//    public int addTwoNumbers(@RequestParam int number1,
//                             @RequestParam int number2){
//        return number1+number2;
//    }
    @GetMapping("/add") //method//path
    public int addTwoNumbers(CalculatorAddRequest request){
        return request.getNumber1()+ request.getNumber2();
    }
}
