package io;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

/**
 *
 * Класс для чтения команд из скрипта.
 */
public class ScriptReader {
    private final Stack<Scanner> scanners = new Stack<>(); //LIFO
    private final Stack<String> fileNames = new Stack<>();

    public void pushFile(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        if (fileNames.contains(file.getAbsolutePath())) {
            throw new RuntimeException("Рекурсия в скриптах обнаружена!");
        }
        scanners.push(new Scanner(file));
        fileNames.push(file.getAbsolutePath());
    }

    public String readLine() {
        while (!scanners.isEmpty()) {
            Scanner s = scanners.peek();
            if (s.hasNextLine()) {
                return s.nextLine().trim();
            } else {
                scanners.pop().close();
                fileNames.pop();
            }
        }
        return null;
    }

    public boolean isEmpty() {
        return scanners.isEmpty();
    }
}
