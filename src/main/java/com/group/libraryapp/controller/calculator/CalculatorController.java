package com.group.libraryapp.controller.calculator;

import com.group.libraryapp.dto.calculator.request.CalculatorAddRequest;
import com.group.libraryapp.dto.calculator.request.CalculatorMutiplyRequest;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/multiply")
    public int multiplyTwoNumbers(@RequestBody CalculatorMutiplyRequest request){
        //@RequestBody로 바디에 제이슨 삽입
        return request.getNumber1()*request.getNumber2();
    }
}
