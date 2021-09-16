package net.spookelton.spookutils.helpers;

import net.spookelton.spookutils.SpookUtils;
import org.apache.logging.log4j.Level;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileHelper {
    private static String FILE_NOT_FOUND = "File %s not found";
    private static String READ_FILE = "Read %d bytes, content is %s";

    public static boolean createFile(String path, String defaultContent) {
        File file = new File(path);
        if (file.exists()) { return true; }
        try {
            boolean success = file.createNewFile();
            writeFile(path, defaultContent);
            return success;
        } catch (IOException e) {
            return false;
        }

    }

    public static boolean writeFile(String path, String content) {
        File file = new File(path);
        if (!file.exists()) {
            SpookUtils.logger.log(Level.DEBUG, String.format(FILE_NOT_FOUND, path));
            return false;
        }

        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(file);
        } catch (IOException e) {
            return false;
        }
        try {
            fileWriter.write(content);
        } catch (IOException e) {
            return false;
        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException ignored) {
            }
        }
        return true;
    }

    public static String readFile(String path) {
        File file = new File(path);
        if (!file.exists()) {
            SpookUtils.logger.log(Level.DEBUG, String.format(FILE_NOT_FOUND, path));
            return null;
        }
        try {
            SpookUtils.logger.log(Level.DEBUG, "Reading "+path);
            byte[] result = Files.readAllBytes(Paths.get(path));
            String stringResult = new String(result);
            SpookUtils.logger.log(Level.DEBUG, String.format(READ_FILE, result.length, stringResult));
            return stringResult;
        } catch (IOException e) {
            SpookUtils.logger.log(Level.DEBUG, "Failed to read "+path);
            return null;
        }
    }
}
