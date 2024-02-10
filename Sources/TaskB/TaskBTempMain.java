package Sources.TaskB;

import org.apache.hadoop.util.ToolRunner;

public class TaskBTempMain {

    public static void main(String[] args) throws Exception {
        int exitCode = ToolRunner.run(new TaskBTempDriver(), args);
        System.exit(exitCode);
    }
}
