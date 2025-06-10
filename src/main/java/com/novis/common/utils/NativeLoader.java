package com.novis.common.utils;

import com.sun.jna.NativeLibrary;

import java.io.*;
import java.nio.file.*;

public class NativeLoader {

    public static void loadLibsodiumFromResources() throws IOException {
        String libName = "libsodium";
        String fileName = "libsodium.dll"; // Or .so/.dylib depending on platform
        String resourcePath = "/native/" + fileName;

        InputStream in = NativeLoader.class.getResourceAsStream(resourcePath);
        if (in == null) {
            throw new FileNotFoundException("Couldn't find " + resourcePath + " in resources.");
        }

        Path tempDir = Files.createTempDirectory("nativeLibs");
        Path libFile = tempDir.resolve(fileName);
        Files.copy(in, libFile, StandardCopyOption.REPLACE_EXISTING);
        libFile.toFile().deleteOnExit();

        NativeLibrary.addSearchPath(libName, libFile.getParent().toString());
        NativeLibrary.getInstance(libName);
    }
}