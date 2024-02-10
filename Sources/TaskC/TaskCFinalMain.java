package Sources.TaskC;

import org.apache.hadoop.util.ToolRunner;

/**
 * Fonction Main du job de la requête C
 * @author Maxime Emonnot
 */
public class TaskCFinalMain 
{
    public static void main(String[] args) throws Exception
    {
        int exitCode = ToolRunner.run(new TaskCFinalDriver(), args);
        System.exit(exitCode);
    }
}
