package com.iteratrlearning.shu_book.chapter_03;

import java.io.IOException;

/**
 * 2-13 메인 응용프로그램
 * - argument : bank-data-simple.csv
 */
public class MainApplication {

    public static void main(String[] args) throws IOException {
        final BankStatementAnalyzer bankStatementAnalyzer = new BankStatementAnalyzer();
        final BankStatementParser bankStatementParser = new BankStatementCSVParser();
        bankStatementAnalyzer.analyze(args[0], bankStatementParser);
    }
}
