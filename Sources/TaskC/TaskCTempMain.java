package Sources.TaskC;

import org.apache.hadoop.util.ToolRunner;

/**
 * Fonction Main du job de génération de fichier temporaire
 * @author Maxime Emonnot
 */
public class TaskCTempMain 
{
    public static void main(String[] args) throws Exception
    {
        int exitCode = ToolRunner.run(new TaskCTempDriver(), args);
        System.exit(exitCode);
    }
}
