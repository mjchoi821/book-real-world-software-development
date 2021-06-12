package com.iteratrlearning.shu_book.chapter_04.dms;

import com.iteratrlearning.shu_book.chapter_04.dms.importer.Importer;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static com.iteratrlearning.shu_book.chapter_04.dms.importer.Attributes.*;

/**
 * 레포트 임포터 구현
 */
public class ReportImporter implements Importer {
    private static final String PREFIX_PATIENT = "Patient: ";

    @Override
    public Document importFile(File file) throws IOException {
        TextFile textFile = new TextFile(file);

        textFile.addLineSuffix(PREFIX_PATIENT, PATIENT);
        textFile.addLines(2, String::isEmpty, BODY);

        Map<String, String> attributes = textFile.getAttributes();
        attributes.put(TYPE, "REPORT");
        return new Document(attributes);
    }
}
