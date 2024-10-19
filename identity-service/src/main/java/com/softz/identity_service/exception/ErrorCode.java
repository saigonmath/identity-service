package com.softz.identity_service.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error",
            HttpStatus.INTERNAL_SERVER_ERROR)
    , INVALID_DATE_INPUT(9000, "Invalid date", HttpStatus.BAD_REQUEST)
    , USER_NOT_FOUND(404100, "User not found", HttpStatus.NOT_FOUND)
    , USER_ID_NOT_FOUND(404102, "User id %s not found", HttpStatus.NOT_FOUND)
    , USER_EXISTED(409100, "User existed", HttpStatus.CONFLICT)
    , PERMISSION_EXISTED(409105, "Permission existed", HttpStatus.CONFLICT)
    , INVALID_USERNAME(100100, "Username length must be between {min} and {max}", HttpStatus.BAD_REQUEST)
    , MISSING_MESSAGE_KEY(100101, "Invalid message key", HttpStatus.BAD_REQUEST)
    , INVALID_DATE_OF_BIRTH(100102, "User's age must be greater than or equal to {min}", HttpStatus.BAD_REQUEST)
    , INVALID_INPUT(100103, "Invalid input", HttpStatus.BAD_REQUEST)
    ;



    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    private final int code;
    private final String message;
    private final HttpStatusCode statusCode;
}
