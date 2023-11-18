package com.javaserver.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.fasterxml.jackson.databind.ObjectMapper;

public class BodyParser {
    public static <T> T parseJsonRequestBody(InputStream inputStream, Class<T> clazz) throws IOException {

        StringBuilder requestBodyStringBuilder = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = reader.readLine()) != null) {
            requestBodyStringBuilder.append(line);
        }
        reader.close();

        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(requestBodyStringBuilder.toString(), clazz);
    }
}