package com.iteratrlearning.shu_book.chapter_04.dms;

import com.iteratrlearning.shu_book.chapter_04.dms.importer.Importer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;

import static com.iteratrlearning.shu_book.chapter_04.dms.importer.Attributes.AMOUNT;

/**
 * 청구서 임포터 구현
 */
public class InvoiceImporter implements Importer {
    private static final String PREFIX_AMOUNT = "Amount: ";

    List<String> lines;
    Map<String, String> attributes;

    @Override
    public Document importFile(File file) throws IOException {
        lines = Files.readAllLines(file.toPath());

        addLineSuffix(PREFIX_AMOUNT, AMOUNT);
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
