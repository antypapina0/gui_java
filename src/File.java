import java.io.FileWriter;
import java.io.IOException;

public class File {
    String pathToFile;

    public File(String pathToFile)
    {
        this.pathToFile = pathToFile;
    }

    public void saveToFile(Object[][] tableSave)
    {
        try
        {
            FileWriter csvWriter = new FileWriter(pathToFile);
            for (int i = 0; i < 5; i++)
            {
                for (int j = 0; j < 5; j++)
                {
                    csvWriter.append(tableSave[i][j].toString());
                    csvWriter.append(",");
                }
                csvWriter.append("\n");
            }
            csvWriter.flush();
            csvWriter.close();
        }
        catch (IOException e)
        {
            System.out.println("Wystąpił błąd podczas zapisywania tabeli do pliku.");
        }
    }
}
