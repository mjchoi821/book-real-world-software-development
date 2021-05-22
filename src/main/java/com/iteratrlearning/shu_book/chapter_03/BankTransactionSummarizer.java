package com.iteratrlearning.shu_book.chapter_03;

/**
 * 예제 3-11 BankTransactionProcessor 클래스의 핵심 연산
 */
@FunctionalInterface
public interface BankTransactionSummarizer {
    double summarize(double accumulator, BankTransaction bankTransaction);
}
