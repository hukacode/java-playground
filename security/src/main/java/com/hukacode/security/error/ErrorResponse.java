/*
 * Copyright 2020 hukacode
 */
package com.hukacode.security.error;

import java.time.LocalDateTime;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ErrorResponse {
  private final HttpStatus status;
  private final String message;
  private final ErrorCode errorCode;
  private final LocalDateTime timestamp;

  protected ErrorResponse(final String message, final ErrorCode errorCode, HttpStatus status) {
    this.message = message;
    this.errorCode = errorCode;
    this.status = status;
    this.timestamp = LocalDateTime.now();
  }

  public static ErrorResponse of(
      final String message, final ErrorCode errorCode, HttpStatus status) {
    return new ErrorResponse(message, errorCode, status);
  }
}
