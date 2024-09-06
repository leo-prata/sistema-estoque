package trabalhoPratico.persistence;

import java.io.*;

/**
 *
 * @author filip
 */
public class Archive {
    
    public static String read(String filePath)
    {
        StringBuilder content = new StringBuilder();
        
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath)))
        {
            String line;
            while((line = reader.readLine()) != null)
            {
                content.append(line).append("\n");
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        
        return content.toString();
    }
    
    public static void save(String filePath, String content)
    {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false)))
        {
            writer.write(content);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
