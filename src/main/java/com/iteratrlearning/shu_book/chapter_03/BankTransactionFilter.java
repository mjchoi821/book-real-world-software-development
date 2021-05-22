package com.iteratrlearning.shu_book.chapter_03;

/**
 * 예제 3-4 BankTransactionFilter 인터페이스
 */
@FunctionalInterface
public interface BankTransactionFilter {
    boolean test(BankTransaction bankTransaction);
}
