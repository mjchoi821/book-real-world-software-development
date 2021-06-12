package com.iteratrlearning.shu_book.chapter_04.dms;

import com.iteratrlearning.shu_book.chapter_04.dms.importer.Importer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;

import static com.iteratrlearning.shu_book.chapter_04.dms.importer.Attributes.PATIENT;

/**
 * 레포트 임포터 구현
 * TODO: 본문 텍스트를 포함한다
 */
public class ReportImporter implements Importer {
    private static final String PREFIX_PATIENT = "Patient: ";

    List<String> lines;
    Map<String, String> attributes;

    @Override
    public Document importFile(File file) throws IOException {
        lines = Files.readAllLines(file.toPath());

        addLineSuffix(PREFIX_PATIENT, PATIENT);
        return new Document(attributes);
    }

    void addLineSuffix(String prefix, String attributeName) {
        for (String line: lines) {
            if (line.startsWith(prefix)) {
                attributes.put(attributeName, line.substring(prefix.length()));
                break;
            }
        }
    }
}
