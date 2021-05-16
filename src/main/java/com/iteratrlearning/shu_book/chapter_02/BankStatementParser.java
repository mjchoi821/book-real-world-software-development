package com.iteratrlearning.shu_book.chapter_02;

import java.util.List;

/**
 * 예제 2-11 입출금 내역을 파싱하는 인터페이스 정의
 */
public interface BankStatementParser {
    BankTransaction parseFrom(String line);
    List<BankTransaction> parseLinesFrom(List<String> lines);
}
