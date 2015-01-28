package org.finra.datagenerator;

import java.io.InputStream;
import java.util.Scanner;

/**
 * Marshall Peters
 * Date: 1/27/15
 */
public class InputStreamReader {

    public static String readToCompletion(InputStream inputStream) {
        Scanner inputScanner = new Scanner(inputStream);

        StringBuilder completeInput = new StringBuilder();
        while (inputScanner.hasNextLine()) {
            completeInput.append(inputScanner.nextLine());
            completeInput.append("\n");
        }

        return completeInput.toString();
    }
}
