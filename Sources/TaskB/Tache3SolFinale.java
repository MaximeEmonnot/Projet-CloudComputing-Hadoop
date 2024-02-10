package Sources.TaskB;

import org.apache.hadoop.util.ToolRunner;

public class Tache3SolFinale {

    public static void main(String[] args) throws Exception {
        int exitCode = ToolRunner.run(new Tache2SolFinaleDriver(), args);
        System.exit(exitCode);
    }
}
