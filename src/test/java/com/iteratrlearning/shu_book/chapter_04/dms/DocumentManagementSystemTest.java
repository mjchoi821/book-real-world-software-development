package com.iteratrlearning.shu_book.chapter_04.dms;

import com.iteratrlearning.shu_book.chapter_04.dms.exception.UnknownFileTypeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DocumentManagementSystemTest {

    @Test
    @DisplayName("importFile 동작 시 path 가 유효하지 않은 값일 경우 예외 발생")
    void importFile_whenDerivedException_invalidPathString() {
        // given
        DocumentManagementSystem dms = new DocumentManagementSystem();
        String expectedMessage = "scam_path";

        // when
        Exception exception = assertThrows(FileNotFoundException.class, () -> dms.importFile("scam_path"));
        String actualMessage = exception.getMessage();

        // then
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    @DisplayName("importFile 동작 시 처리할 수 없는 확장자일 경우 예외 발생 테스트")
    void importFile_whenDerivedException() {
        // given
        DocumentManagementSystem dms = new DocumentManagementSystem();
        String testImportPath = getClass().getClassLoader().getResource("patient.letter").getFile();
        String expectedMessage = "For file: " + testImportPath;

        // when
        Exception exception = assertThrows(UnknownFileTypeException.class, () -> dms.importFile(testImportPath));
        String actualMessage = exception.getMessage();

        // then
        assertEquals(expectedMessage, actualMessage);
    }
}