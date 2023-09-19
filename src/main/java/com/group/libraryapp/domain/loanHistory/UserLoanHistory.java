package com.group.libraryapp.domain.loanHistory;

import com.group.libraryapp.domain.user.User;

import javax.persistence.*;

@Entity
public class UserLoanHistory {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id= null;

    protected UserLoanHistory(){}
    @ManyToOne
    private User user;
    private String bookName;
    private boolean isReturn;

    public UserLoanHistory(User user, String bookName) {
        this.user = user;
        this.bookName = bookName;
        this.isReturn=false;
    }
    public void doReturn(){
        this.isReturn=true;
    }
    public String getBookName(){
        return this.bookName;
    }
}
