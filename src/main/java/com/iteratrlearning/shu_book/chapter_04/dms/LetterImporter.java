package com.iteratrlearning.shu_book.chapter_04.dms;

import com.iteratrlearning.shu_book.chapter_04.dms.importer.Importer;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static com.iteratrlearning.shu_book.chapter_04.dms.importer.Attributes.*;

/**
 * 우편물 임포터 구현
 */
public class LetterImporter implements Importer {
    private static final String PREFIX_NAME = "Dear ";

    @Override
    public Document importFile(File file) throws IOException {
        TextFile textFile = new TextFile(file);

        textFile.addLineSuffix(PREFIX_NAME, PATIENT);

        int lineNumber = textFile.addLines(2, String::isEmpty, ADDRESS);
        textFile.addLines(lineNumber + 1, (line) -> line.startsWith("regards,"), BODY);

        Map<String, String> attributes = textFile.getAttributes();
        attributes.put(TYPE, "LETTER");
        return new Document(attributes);
    }
}
