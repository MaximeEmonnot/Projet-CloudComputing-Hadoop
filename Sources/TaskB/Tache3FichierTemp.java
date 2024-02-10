package bdma.bigdata.mapreduce;

import org.apache.hadoop.util.ToolRunner;

public class Tache3FichierTemp {

    public static void main(String[] args) throws Exception {
        int exitCode = ToolRunner.run(new Tache3FichierTempDriver(), args);
        System.exit(exitCode);
    }
}
