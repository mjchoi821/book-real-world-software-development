package com.iteratrlearning.shu_book.chapter_03;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * 예제 2-3 파싱 로직을 추출해 한 클래스로 만듦
 * 예제 2-11 입출금 내역을 파싱하는 인터페이스 정의를 구현
 */
public class BankStatementCSVParser implements BankStatementParser {
    public static final DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    private static final int EXPECTED_ATTRIBUTED_LENGTH = 3;

    private BankTransaction parseFromCSV(final String line) {
        final String[] columns = line.split(",");

        if (columns.length < EXPECTED_ATTRIBUTED_LENGTH) {
            throw new CSVSyntaxException();
        }

        final LocalDate date = LocalDate.parse(columns[0], DATE_PATTERN);
        final double amount = Double.parseDouble(columns[1]);
        final String description = columns[2];

        return new BankTransaction(date, amount, description);
    }

    private List<BankTransaction> parseLinesFromCSV(final List<String> lines) {
        final List<BankTransaction> bankTransactions = new ArrayList<>();
        for(final String line: lines) {
            bankTransactions.add(parseFromCSV(line));
        }
        return bankTransactions;
    }

    @Override
    public BankTransaction parseFrom(String line) {
        return parseFromCSV(line);
    }

    @Override
    public List<BankTransaction> parseLinesFrom(List<String> lines) {
        return parseLinesFromCSV(lines);
    }
}
