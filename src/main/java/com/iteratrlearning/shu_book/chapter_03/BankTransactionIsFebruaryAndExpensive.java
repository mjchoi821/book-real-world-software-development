package com.iteratrlearning.shu_book.chapter_03;

import java.time.Month;

/**
 * 예제 3-6 BankTransactionFilter를 구현하는 클래스 선언
 */
public class BankTransactionIsFebruaryAndExpensive implements BankTransactionFilter {

    @Override
    public boolean test(BankTransaction bankTransaction) {
        return bankTransaction.getDate().getMonth() == Month.FEBRUARY
                && bankTransaction.getAmount() >= 1_000;
    }
}
