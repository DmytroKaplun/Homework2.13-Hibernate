package org.example.app.util;

import lombok.experimental.UtilityClass;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
@UtilityClass
public class RequestMapper {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

    public static <T> T mapToEntity(Map<String, String[]> params, Class<T> clazz) {
        try {
            T entity = clazz.getDeclaredConstructor().newInstance();

            for (Field field : clazz.getDeclaredFields()) {
                field.setAccessible(true);
                String[] value = params.get(field.getName());
                if (value != null && value.length > 0) {
                    if (field.getType().equals(LocalDateTime.class)) {
                        field.set(entity, LocalDateTime.parse(value[0], DATE_TIME_FORMATTER));
                    } else if (field.getType().isEnum()) {
                        field.set(entity, convertToEnum(field.getType(), value[0]));
                    } else {
                        field.set(entity, convertType(value[0], field.getType()));
                    }
                }
                field.setAccessible(false);
            }
            return entity;
        } catch (Exception e) {
            throw new RuntimeException("Error mapping request parameters to entity", e);
        }
    }

    private static Object convertType(String value, Class<?> type) {
        if (type.equals(Long.class) || type.equals(long.class)) {
            return Long.parseLong(value);
        } else if (type.equals(Integer.class) || type.equals(int.class)) {
            return Integer.parseInt(value);
        } else if (type.equals(Double.class) || type.equals(double.class)) {
            return Double.parseDouble(value);
        } else if (type.equals(Float.class) || type.equals(float.class)) {
            return Float.parseFloat(value);
        } else if (type.equals(Boolean.class) || type.equals(boolean.class)) {
            return Boolean.parseBoolean(value);
        } else {
            return value;
        }
    }

    private static <T extends Enum<T>> T convertToEnum(Class<?> enumType, String value) {
        try {
            return Enum.valueOf((Class<T>) enumType, value);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid value for enum: " + value, e);
        }
    }
}
