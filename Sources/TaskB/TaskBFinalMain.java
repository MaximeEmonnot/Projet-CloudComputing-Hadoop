package Sources.TaskB;

import org.apache.hadoop.util.ToolRunner;

/**
 * Fonction Main du job de la requête B
 * @author Manon Lacombe
 */
public class TaskBFinalMain {

    public static void main(String[] args) throws Exception {
        int exitCode = ToolRunner.run(new TaskBFinalDriver(), args);
        System.exit(exitCode);
    }
}
