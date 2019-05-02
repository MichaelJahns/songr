package com.example.songr.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Song Not Found")
public class SongNotFoundException extends RuntimeException {
}
