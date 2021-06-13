package com.iteratrlearning.shu_book.chapter_04.dms;

import com.iteratrlearning.shu_book.chapter_04.dms.exception.UnknownFileTypeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.iteratrlearning.shu_book.chapter_04.dms.importer.Attributes.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DocumentManagementSystemTest {
    private static final String RESOURCES = "target/test-classes/";

    @Test
    void shouldImportLetterAttributes() throws Exception {
        // given
        final String fileName = "patient.letter";
        final Path path = Paths.get(RESOURCES + fileName);
        DocumentManagementSystem system = new DocumentManagementSystem();

        // when
        system.importFile(path.toAbsolutePath().toString());
        final Document document = system.contents().get(0);

        // then
        assertAttributeEquals(document, PATIENT, "Joe Bloggs");
        assertAttributeEquals(document, ADDRESS,
                "123 Fake Street\n" +
                "Westminster\n" +
                "London\n" +
                "United Kingdom");
        assertAttributeEquals(document, BODY,
                "We are writing to you to confirm the re-scheduling of your appointment\n" +
                "with Dr. Avaj from 29th December 2016 to 5th January 2017.");
        assertEquals("LETTER", document.getAttribute(TYPE));
    }

    private void assertAttributeEquals(Document document, String attributeName, String expectedValue) {
        assertEquals(expectedValue, document.getAttribute(attributeName), "Document has the wrong value for ");
    }

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