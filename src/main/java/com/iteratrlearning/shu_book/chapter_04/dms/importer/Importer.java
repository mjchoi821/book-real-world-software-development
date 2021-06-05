package com.iteratrlearning.shu_book.chapter_04.dms.importer;

import com.iteratrlearning.shu_book.chapter_04.dms.Document;

import java.io.File;
import java.io.IOException;

/**
 * Import 인터페이스
 */
public interface Importer {
    Document importFile(File file) throws IOException;
}
