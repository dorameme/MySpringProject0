package com.group.libraryapp.domain.loanHistory;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserLoanHistoryRepository  extends JpaRepository<UserLoanHistory,Long> {
    boolean existByBookNameAndIsReturn(String name,boolean isReturn);
}
