package com.iteratrlearning.shu_book.chapter_03;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * BankStatement 검증자
 * - 적절한 수준의 예외 처리를 지향하자
 * - 과도하게 세밀하거나, 과도하게 덤덤한 예외 처리가 되지 않도록 주의한다
 */
public class BankStatementValidator {
    private static final int MAX_DESCRIPTION_LENGTH = 100;

    private String description;
    private String date;
    private String amount;

    public BankStatementValidator(String description, String date, String amount) {
        this.description = description;
        this.date = date;
        this.amount = amount;
    }

    public Notification validate() {

        final Notification notification = new Notification();
        if (this.description.length() > MAX_DESCRIPTION_LENGTH) {
            notification.addError("The description is too long");
        }

        final LocalDate parsedDate;
        try {
            parsedDate = LocalDate.parse(this.date);
            if (parsedDate.isAfter(LocalDate.now())) {
                notification.addError("date cannot be in future");
            }
        } catch (DateTimeParseException e) {
            notification.addError("Invalid format for date");
        }

        try {
            Double.parseDouble(this.amount);
        } catch (NumberFormatException e) {
            notification.addError("Invalid format for amount");
        }
        return notification;
    }
}
