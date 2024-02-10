package Sources.TaskA;

import org.apache.hadoop.util.ToolRunner;

public class TaskAFinalMain {

    public static void main(String[] args) throws Exception {
        int exitCode = ToolRunner.run(new TaskAFinalDriver(), args);
        System.exit(exitCode);
    }
}
