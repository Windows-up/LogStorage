package ru.doctorixx.applogstorage.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Invalid report id")
public class ReportNotFoundException extends RuntimeException{
}
