package Sources.TaskA;

import org.apache.hadoop.util.ToolRunner;

/**
 * Fonction Main du job de génération de fichier temporaire de la tâche A
 * @author Manon Lacombe
 */
public class TaskATempMain {

    public static void main(String[] args) throws Exception {
        int exitCode = ToolRunner.run(new TaskATempDriver(), args);
        System.exit(exitCode);
    }
}
