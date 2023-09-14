package com.group.libraryapp.domain.loanHistory;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserLoanHistory {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id= null;

    protected UserLoanHistory(){}
    private long userId;
    private String bookName;
    private boolean isReturn;

    public UserLoanHistory(long userId, String bookName) {
        this.userId = userId;
        this.bookName = bookName;
        this.isReturn=false;
    }
}
