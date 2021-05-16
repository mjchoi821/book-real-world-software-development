package com.iteratrlearning.shu_book.chapter_02;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

/**
 * 예제 2-14 CSV 파서의 유닛 테스트가 실패하는 경우
 */
public class BankStatementCSVParserTest {
    private final BankStatementParser statementParser = new BankStatementCSVParser();

    @Test
    public void shouldParseOneCorrectLine() throws Exception {
        fail("Not yet implemented");
    }
}