package com.novis.crypto.utils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class FormatUtils {
    // formatting utils for the crypto section

    public static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for(byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    // Convert a byte array to a Base64-encoded string
    public static String bytesToString(byte[] bytes) {
        if (bytes == null) return null;
        return Base64.getEncoder().encodeToString(bytes);
    }

    // Convert a Base64-encoded string back to a byte array
    public static byte[] stringToBytes(String base64String) {
        if (base64String == null) return null;
        return Base64.getDecoder().decode(base64String);
    }

    // Optional: convert raw string to bytes (e.g., UTF-8 encoding)
    public static byte[] utf8StringToBytes(String input) {
        if (input == null) return null;
        return input.getBytes(StandardCharsets.UTF_8);
    }

    // Optional: convert bytes to raw string (UTF-8 decoding)
    public static String bytesToUtf8String(byte[] bytes) {
        if (bytes == null) return null;
        return new String(bytes, StandardCharsets.UTF_8);
    }
}
