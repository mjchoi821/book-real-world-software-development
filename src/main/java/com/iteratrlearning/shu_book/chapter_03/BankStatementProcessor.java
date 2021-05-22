package com.iteratrlearning.shu_book.chapter_03;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

/**
 * 예제 2-7 BankStatementProcessor 클래스의 계산 연산 그룹화
 * 예제 3-1 특정 금액 이상의 은행 거래 내역 찾기
 * 예제 3-2 특정 월의 입출금 내역 찾기
 * 예제 3-3 특정 월이나 금액으로 입출금 내역 검색하기
 * 예제 3-5 개방/폐쇄 원칙을 적용한 후 유연해진 findTransactions() 메서드
 * 예제 3-11 BankTransactionProcessor 클래스의 핵심 연산 함수형 인터페이스 적용
 */
public class BankStatementProcessor {
    private final List<BankTransaction> bankTransactions;

    public BankStatementProcessor(final List<BankTransaction> bankTransactions) {
        this.bankTransactions = bankTransactions;
    }

    private double summarizeTransactions(final BankTransactionSummarizer bankTransactionSummarizer) {
        double result = 0;
        for (final BankTransaction bankTransaction: bankTransactions) {
            result = bankTransactionSummarizer.summarize(result, bankTransaction);
        }
        return result;
    }

    public double calculateTotalAmount() {
        return summarizeTransactions((acc, bankTransaction) -> acc + bankTransaction.getAmount());
    }

    public double calculateTotalInMonth(final Month month) {
        return summarizeTransactions((acc, bankTransaction) ->
                bankTransaction.getDate().getMonth() == month ? acc + bankTransaction.getAmount() : acc);
    }

    public double calculateTotalForCategory(final String category) {
        return summarizeTransactions((acc, bankTransaction) ->
                bankTransaction.getDescription().equals(category) ? acc + bankTransaction.getAmount() : acc);
    }

    public List<BankTransaction> findTransactions(final BankTransactionFilter bankTransactionFilter) {
        final List<BankTransaction> result = new ArrayList<>();
        for (final BankTransaction bankTransaction: bankTransactions) {
            if (bankTransactionFilter.test(bankTransaction)) {
                result.add(bankTransaction);
            }
        }
        return result;
    }

    public List<BankTransaction> findTransactionsGreaterThanEqual(final int amount) {
        return findTransactions(bankTransaction -> bankTransaction.getAmount() >= amount);
    }
}
