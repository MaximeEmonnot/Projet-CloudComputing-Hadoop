package Sources.TaskA;

import org.apache.hadoop.util.ToolRunner;

public class Tache2SolFinale {

    public static void main(String[] args) throws Exception {
        int exitCode = ToolRunner.run(new Tache2SolFinaleDriver(), args);
        System.exit(exitCode);
    }
}
