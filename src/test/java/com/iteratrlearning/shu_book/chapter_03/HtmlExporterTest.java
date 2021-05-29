package com.iteratrlearning.shu_book.chapter_03;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HtmlExporterTest {

    @Test
    void export() {
        // given
        final double sum = 10;
        final double average = 4;
        final double min = 1;
        final double max = 5;
        String expected = "<!doctype html>";
        expected += "<html lang='en'>";
        expected += "<head><title>Bank Transaction Report</title></head>";
        expected += "<body>";
        expected += "<ul>";
        expected += "<li><strong>The sum is</strong>: " + sum + "</li>";
        expected += "<li><strong>The average is</strong>: " + average + "</li>";
        expected += "<li><strong>The max is</strong>: " + max + "</li>";
        expected += "<li><strong>The min is</strong>: " + min + "</li>";
        expected += "</ul>";
        expected += "</body>";
        expected += "</html>";
        SummaryStatistics summaryStatistics = new SummaryStatistics(sum, max, min, average);
        Exporter exporter = new HtmlExporter();

        // when
        String result = exporter.export(summaryStatistics);

        // then
        assertEquals(expected, result);
    }
}