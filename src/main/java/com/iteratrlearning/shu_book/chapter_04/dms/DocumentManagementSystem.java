package com.iteratrlearning.shu_book.chapter_04.dms;

import com.iteratrlearning.shu_book.chapter_04.dms.importer.Importer;

import java.util.HashMap;
import java.util.Map;

public class DocumentManagementSystem {
    private final Map<String, Importer> extensionToImporter = new HashMap<>();

    public DocumentManagementSystem() {
        //extensionToImporter.put("letter", new LetterImporter());
        //extensionToImporter.put("report", new ReportImporter());
        extensionToImporter.put("jpg", new ImageImporter());
    }
}
