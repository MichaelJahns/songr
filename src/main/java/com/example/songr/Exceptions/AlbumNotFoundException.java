package com.example.songr.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Album Not Found")
public class AlbumNotFoundException extends RuntimeException {
}
