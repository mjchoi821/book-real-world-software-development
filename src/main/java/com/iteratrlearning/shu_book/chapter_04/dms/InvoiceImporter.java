package com.iteratrlearning.shu_book.chapter_04.dms;

import com.iteratrlearning.shu_book.chapter_04.dms.importer.Importer;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static com.iteratrlearning.shu_book.chapter_04.dms.importer.Attributes.*;

/**
 * 청구서 임포터 구현
 */
public class InvoiceImporter implements Importer {
    private static final String PREFIX_NAME = "Dear ";
    private static final String PREFIX_AMOUNT = "Amount: ";

    @Override
    public Document importFile(File file) throws IOException {
        TextFile textFile = new TextFile(file);

        textFile.addLineSuffix(PREFIX_NAME, NAME);
        textFile.addLineSuffix(PREFIX_AMOUNT, AMOUNT);

        Map<String, String> attributes = textFile.getAttributes();
        attributes.put(TYPE, "INVOICE");
        return new Document(attributes);
    }
}
