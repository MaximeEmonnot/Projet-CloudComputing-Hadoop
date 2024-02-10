package Sources.TaskB;

import org.apache.hadoop.util.ToolRunner;

public class TaskBFinalMain {

    public static void main(String[] args) throws Exception {
        int exitCode = ToolRunner.run(new TaskBFinalDriver(), args);
        System.exit(exitCode);
    }
}
