package com.iteratrlearning.shu_book.chapter_04.dms.exception;

import java.io.IOException;

public class UnknownFileTypeException extends IOException {

    public UnknownFileTypeException(String msg) {
        super(msg);
    }
}
