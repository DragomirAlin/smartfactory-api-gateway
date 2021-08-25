package ro.dragomiralin.gateway.advice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {
    private final ObjectMapper mapper;

    @ExceptionHandler(FeignException.class)
    public final ResponseEntity<String> handleFeignException(FeignException fex) {
        log.error("Exception from downstream service call - ", fex);
        HttpStatus status = Optional.ofNullable(HttpStatus.resolve(fex.status()))
                .orElse(HttpStatus.INTERNAL_SERVER_ERROR);
        String body = fex.getMessage();
        return new ResponseEntity<>(
                body,
                getHeadersWithContentType(body),
                status
        );
    }

    private MultiValueMap<String, String> getHeadersWithContentType(String body) {
        HttpHeaders headers = new HttpHeaders();
        String contentType = isValidJSON(body) ? "application/json" : "text/plain";
        headers.add(HttpHeaders.CONTENT_TYPE, contentType);
        return headers;
    }

    private boolean isValidJSON(String body) {
        try {
            if (Strings.isNullOrEmpty(body)) return false;
            mapper.readTree(body);
            return true;
        } catch (JsonProcessingException e) {
            return false;
        }
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public void handleNotFound() {
        // Nothing to do
    }
}
