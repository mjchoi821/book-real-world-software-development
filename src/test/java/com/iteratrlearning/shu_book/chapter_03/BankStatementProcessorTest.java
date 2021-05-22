package com.iteratrlearning.shu_book.chapter_03;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BankStatementProcessorTest {

    @Test
    public void searchCondition_Amount_GreaterThanEqual() {
        // given
        final int searchAmount = 6000;
        List<BankTransaction> expected = List.of(new BankTransaction(LocalDate.of(2017, Month.FEBRUARY, 1), 6000, "Salary"));
        List<String> lines = List.of("30-01-2017,-100,Deliveroo",
                "30-01-2017,-50,Tesco",
                "01-02-2017,6000,Salary",
                "02-02-2017,2000,Royalties",
                "02-02-2017,-4000,Rent",
                "03-02-2017,3000,Tesco",
                "05-02-2017,-30,Cinema");
        BankStatementParser statementParser = new BankStatementCSVParser();
        List<BankTransaction> bankTransactions = statementParser.parseLinesFrom(lines);
        BankStatementProcessor bankStatementProcessor = new BankStatementProcessor(bankTransactions);

        // when
        List<BankTransaction> result = bankStatementProcessor.findTransactions(bankTransaction ->
                bankTransaction.getAmount() == searchAmount);

        // then
        assertEquals(result.size(), 1);
        assertArrayEquals(expected.toArray(), result.toArray());
    }

    @Test
    public void searchCondition_Specific_Month() {
        // given
        final Month searchMonth = Month.JANUARY;
        List<BankTransaction> expected = List.of(
                new BankTransaction(LocalDate.of(2017, Month.JANUARY, 30), -100, "Deliveroo"),
                new BankTransaction(LocalDate.of(2017, Month.JANUARY, 30), -50, "Tesco"));
        List<String> lines = List.of("30-01-2017,-100,Deliveroo",
                "30-01-2017,-50,Tesco",
                "01-02-2017,6000,Salary",
                "02-02-2017,2000,Royalties",
                "02-02-2017,-4000,Rent",
                "03-02-2017,3000,Tesco",
                "05-02-2017,-30,Cinema");
        BankStatementParser statementParser = new BankStatementCSVParser();
        List<BankTransaction> bankTransactions = statementParser.parseLinesFrom(lines);
        BankStatementProcessor bankStatementProcessor = new BankStatementProcessor(bankTransactions);

        // when
        List<BankTransaction> result = bankStatementProcessor.findTransactions(bankTransaction ->
                bankTransaction.getDate().getMonth() == searchMonth);

        // then
        assertEquals(result.size(), 2);
        assertArrayEquals(expected.toArray(), result.toArray());
    }

    @Test
    public void searchCondition_Specific_Month_And_Amount_GreaterThanEqual() {
        // given
        BankTransactionFilter bankTransactionFilter = new BankTransactionIsFebruaryAndExpensive();
        List<BankTransaction> expected = List.of(
                new BankTransaction(LocalDate.of(2017, Month.FEBRUARY, 1), 6000, "Salary"),
                new BankTransaction(LocalDate.of(2017, Month.FEBRUARY, 2), 2000, "Royalties"),
                new BankTransaction(LocalDate.of(2017, Month.FEBRUARY, 3), 3000, "Tesco"));
        List<String> lines = List.of("30-01-2017,-100,Deliveroo",
                "30-01-2017,-50,Tesco",
                "01-02-2017,6000,Salary",
                "02-02-2017,2000,Royalties",
                "02-02-2017,-4000,Rent",
                "03-02-2017,3000,Tesco",
                "05-02-2017,-30,Cinema");
        BankStatementParser statementParser = new BankStatementCSVParser();
        List<BankTransaction> bankTransactions = statementParser.parseLinesFrom(lines);
        BankStatementProcessor bankStatementProcessor = new BankStatementProcessor(bankTransactions);

        // when
        List<BankTransaction> result = bankStatementProcessor.findTransactions(bankTransactionFilter);

        // then
        assertArrayEquals(expected.toArray(), result.toArray());
    }
}