package Sources.TaskB;

import org.apache.hadoop.util.ToolRunner;

/**
 * Fonction Main du job de génération de fichier temporaire de la tâche B
 * @author Manon Lacombe
 */
public class TaskBTempMain 
{
    public static void main(String[] args) throws Exception 
    {
        int exitCode = ToolRunner.run(new TaskBTempDriver(), args);
        System.exit(exitCode);
    }
}
