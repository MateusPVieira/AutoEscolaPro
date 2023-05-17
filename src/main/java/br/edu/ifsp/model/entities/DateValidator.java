package br.edu.ifsp.model.entities;

import java.time.LocalDateTime;
import java.util.Objects;

public class DateValidator {
        public Notification validate(LocalDateTime startDate, LocalDateTime endDate) {
            Notification notification = new Notification();
            if (startDate == null || endDate == null) {
                notification.addError("Date is null!");
            }

            if (Objects.requireNonNull(startDate).isAfter(endDate)) {
                notification.addError("Invalid Start Date!");
            }

            return notification;
        }
}
