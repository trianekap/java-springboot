package com.example.e_commerce.utilities;

import javax.persistence.AttributeConverter;
import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class ZonedDateTimeConverter implements AttributeConverter<ZonedDateTime, Timestamp> {
    @Override
    public Timestamp convertToDatabaseColumn(ZonedDateTime zonedDateTime) {
        return zonedDateTime != null ? Timestamp.valueOf(zonedDateTime.toLocalDateTime()) : null;
    }

    @Override
    public ZonedDateTime convertToEntityAttribute(Timestamp timestamp) {
        return timestamp != null ? timestamp.toLocalDateTime().atZone(ZoneId.of("+07:00")) : null;
    }
}
