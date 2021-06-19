package com.iteratrlearning.shu_book.chapter_04.dms;

import com.iteratrlearning.shu_book.chapter_04.dms.exception.UnknownFileTypeException;
import com.iteratrlearning.shu_book.chapter_04.dms.importer.Attributes;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import static com.iteratrlearning.shu_book.chapter_04.dms.importer.Attributes.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DocumentManagementSystemTest {
    private static final String RESOURCES = "src" + File.separator + "test" + File.separator + "resources" + File.separator;
    private static final String LETTER = RESOURCES + "patient.letter";
    private static final String REPORT = RESOURCES + "patient.report";
    private static final String XRAY = RESOURCES + "xray.jpg";
    private static final String INVOICE = RESOURCES + "patient.invoice";

    final DocumentManagementSystem system = new DocumentManagementSystem();

    private Document onlyDocument() {
        List<Document> documents = system.contents();
        assertThat(documents, hasSize(1));
        return documents.get(0);
    }

    private void assertAttributeEquals(Document document, String attributeName, String expectedValue) {
        assertEquals(expectedValue, document.getAttribute(attributeName), "Document has the wrong value for ");
    }

    @Test
    public void shouldImportFile() throws Exception {
        // given
        system.importFile(LETTER);

        // when
        final Document document = onlyDocument();

        // then
        assertAttributeEquals(document, Attributes.PATH, LETTER);
    }

    @Test
    void shouldImportLetterAttributes() throws Exception {
        // given
        system.importFile(LETTER);

        // when
        final Document document = onlyDocument();

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

    @Test
    void shouldNotImportMissingFile() {
        assertThrows(FileNotFoundException.class, () ->
                system.importFile("gobbledygook.txt"));
    }

    @Test
    void shouldNotImportUnknownFile() {
        assertThrows(UnknownFileTypeException.class, () ->
                system.importFile(RESOURCES + "unknown.txt"));
    }

    /**
     * =================
     *  추가로 만든 테스트들
     * =================
     */

    @Test
    @DisplayName("importFile 동작 시 path 가 유효하지 않은 값일 경우 예외 발생")
    void importFile_whenDerivedException_invalidPathString() {
        // given
        String expectedMessage = "scam_path";

        // when
        Exception exception = assertThrows(FileNotFoundException.class, () -> system.importFile("scam_path"));
        String actualMessage = exception.getMessage();

        // then
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    @DisplayName("importFile 동작 시 처리할 수 없는 확장자일 경우 예외 발생 테스트")
    void importFile_whenDerivedException() {
        // given
        final String UNKNOWN = RESOURCES + "unknown.txt";
        String expectedMessage = "For file: " + UNKNOWN;

        // when
        Exception exception = assertThrows(UnknownFileTypeException.class, () -> system.importFile(UNKNOWN));
        String actualMessage = exception.getMessage();

        // then
        assertEquals(expectedMessage, actualMessage);
    }
}