package airticketmanagementsystem;

import java.io.*;
import java.util.*;

public class FileHandler {
    private static final String BASE_DIR = "database/";
    
    static {
        // Create data directory if it doesn't exist
        new File(BASE_DIR).mkdirs();
    }
    
    public static void saveData(String filename, String data) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(BASE_DIR + filename, true))) {
            writer.println(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static List<String> readData(String filename) {
        List<String> data = new ArrayList<>();
        File file = new File(BASE_DIR + filename);
        
        if (!file.exists()) {
            return data;
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                data.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
    
    public static void deleteRecord(String filename, String key, int keyPosition) {
        List<String> records = readData(filename);
        List<String> updatedRecords = new ArrayList<>();
        
        for (String record : records) {
            String[] fields = record.split(",");
            if (fields.length > keyPosition && !fields[keyPosition].equals(key)) {
                updatedRecords.add(record);
            }
        }
        
        // Rewrite the file without the deleted record
        overwriteData(filename, updatedRecords);
    }
    
    public static String findRecord(String filename, String key, int keyPosition) {
        List<String> records = readData(filename);
        
        for (String record : records) {
            String[] fields = record.split(",");
            if (fields.length > keyPosition && fields[keyPosition].equals(key)) {
                return record;
            }
        }
        return null;
    }
    
    public static List<String> findRecords(String filename, String key, int keyPosition) {
        List<String> records = readData(filename);
        List<String> result = new ArrayList<>();
        
        for (String record : records) {
            String[] fields = record.split(",");
            if (fields.length > keyPosition && fields[keyPosition].equals(key)) {
                result.add(record);
            }
        }
        return result;
    }

    // ✅ New method: overwrite file with updated data
    public static void overwriteData(String filename, List<String> lines) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(BASE_DIR + filename, false))) {
            for (String line : lines) {
                writer.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ✅ New method: update a user’s password
    public static boolean updatePassword(String filename, String username, String newPassword) {
        List<String> records = readData(filename);
        boolean updated = false;
        List<String> updatedRecords = new ArrayList<>();

        for (String record : records) {
            String[] fields = record.split(",");
            if (fields.length >= 2 && fields[0].equals(username)) {
                updatedRecords.add(username + "," + newPassword);
                updated = true;
            } else {
                updatedRecords.add(record);
            }
        }

        if (updated) {
            overwriteData(filename, updatedRecords);
        }
        return updated;
    }
}
