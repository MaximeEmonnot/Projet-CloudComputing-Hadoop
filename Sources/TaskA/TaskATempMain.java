package Sources.TaskA;

import org.apache.hadoop.util.ToolRunner;

public class TaskATempMain {

    public static void main(String[] args) throws Exception {
        int exitCode = ToolRunner.run(new TaskATempDriver(), args);
        System.exit(exitCode);
    }
}
