package trabalhoPratico.persistence;

import java.io.*;

public class Archive {

    public static String read(String filePath) {
        StringBuilder content = new StringBuilder();

        File file = new File(filePath);
        if (!file.exists()) {
            System.err.println("File does not exist: " + filePath);
            return "";
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Reading file: " + filePath);
        return content.toString();
    }

    public static void save(String filePath, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false))) {
            writer.write(content);
            writer.flush();
            System.out.println("Saving file: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        
    }
}