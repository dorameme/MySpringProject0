package com.group.libraryapp.dto.calculator.request;

import org.springframework.web.bind.annotation.RestController;

public class CalculatorAddRequest {//dto 데잍터 전달 객체

    private final int number1;
    private final int number2;

    public CalculatorAddRequest(int number1, int number2) {
        this.number1 = number1;
        this.number2 = number2;
    }

    public int getNumber1() {
        return number1;
    }

    public int getNumber2() {
        return number2;
    }

}
