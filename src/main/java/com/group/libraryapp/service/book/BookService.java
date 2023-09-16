package com.group.libraryapp.service.book;

import com.group.libraryapp.domain.book.Book;
import com.group.libraryapp.domain.book.BookRepository;
import com.group.libraryapp.domain.loanHistory.UserLoanHistory;
import com.group.libraryapp.domain.loanHistory.UserLoanHistoryRepository;
import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.domain.user.UserRepository;
import com.group.libraryapp.dto.book.request.BookCreateRequest;
import com.group.libraryapp.dto.book.request.BookLoanRequest;
import com.group.libraryapp.dto.book.request.BookReturnRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
@Service
public class BookService {
    private final BookRepository bookRepository;
    private final UserLoanHistoryRepository userLoanHistoryRepository;
    private final UserRepository userRepository;

    public BookService(BookRepository bookRepository,UserLoanHistoryRepository userLoanHistoryRepository,UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userLoanHistoryRepository=userLoanHistoryRepository;
        this.userRepository=userRepository;
    }
    @Transactional
    public void saveBook(BookCreateRequest request){
        bookRepository.save(new Book(request.getName()));
    }
    @Transactional
    public void loanBook(BookLoanRequest request){
        //1. 책 정보를 가져온다
       Book book= bookRepository.findByName(request.getBookName()).orElseThrow(IllegalArgumentException::new);
        //2. 대출정보를 확인한다
        //3. 대출중이면 예외를 발생시킨다.
        if(userLoanHistoryRepository.existByBookNameAndIsReturn(book.getName(), false))
            throw new IllegalArgumentException("대출중 입니다 ");
        //4. 유저정보를 가져와 저장한다.
        User user= userRepository.findByName(request.getUserName()).orElseThrow(IllegalArgumentException::new);

        userLoanHistoryRepository.save(new UserLoanHistory(user, book.getName()));

    }

    @Transactional
    public void returnBook(BookReturnRequest request){
        User user= userRepository.findByName(request.getUserName()).orElseThrow(IllegalArgumentException::new);
        UserLoanHistory history=userLoanHistoryRepository.findByUserIdAndBookName(user.getId(), request.getBookName()).orElseThrow(IllegalArgumentException::new);
        history.doReturn();
        //영속성은 변경감지가 있으므로 변경을 자동으로 업데이트
    }
}
