package com.iteratrlearning.shu_book.chapter_04.dms;

import com.iteratrlearning.shu_book.chapter_04.dms.importer.Importer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;

import static com.iteratrlearning.shu_book.chapter_04.dms.importer.Attributes.NAME;

/**
 * 우편물 임포터 구현
 * TODO: 주소와 본문 텍스트도 추출해야 한다
 */
public class LetterImporter implements Importer {
    private static final String PREFIX_NAME = "DEAR ";

    List<String> lines;
    Map<String, String> attributes;

    @Override
    public Document importFile(File file) throws IOException {
        lines = Files.readAllLines(file.toPath());

        addLineSuffix(PREFIX_NAME, NAME);
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
