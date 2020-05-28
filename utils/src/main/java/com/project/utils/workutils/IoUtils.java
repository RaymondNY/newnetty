package com.project.utils.workutils;

import java.io.Closeable;
import java.io.IOException;

public class IoUtils {

    public static void close(Closeable... closeableList) {
        for (Closeable closeable : closeableList) {
            try {
                if (closeable != null) {
                    closeable.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
