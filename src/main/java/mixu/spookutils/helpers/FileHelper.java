package mixu.spookutils.helpers;

import mixu.spookutils.SpookUtils;
import org.apache.logging.log4j.Level;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileHelper {

    public static boolean createFile(String path) {
        File file = new File(path);
        if (file.exists()) {return true;}
        try {
            return file.createNewFile();
        } catch (IOException e) {
            return false;
        }

    }

    public static boolean writeFile(String path, String content) {
        File file = new File(path);
        if (!file.exists()) {
            SpookUtils.logger.log(Level.DEBUG, "File "+path+" not found");
            return false;
        }

        FileWriter fileWriter = null;
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
            } catch (IOException e) {
            }
        }
        return true;
    }

    public static String readFile(String path) {
        File file = new File(path);
        if (!file.exists()) {
            SpookUtils.logger.log(Level.DEBUG, "File "+path+" not found");
            return null;
        }
        try {
            SpookUtils.logger.log(Level.DEBUG, "Reading "+path);
            byte[] result = Files.readAllBytes(Paths.get(path));
            String stringResult = new String(result);
            SpookUtils.logger.log(Level.DEBUG, "Read "+result.length+" bytes, content is "+stringResult);
            return stringResult;
        } catch (IOException e) {
            SpookUtils.logger.log(Level.DEBUG, "Failed to read "+path);
            return null;
        }
    }
}
