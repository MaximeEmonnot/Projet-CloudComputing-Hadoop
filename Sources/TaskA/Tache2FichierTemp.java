package bdma.bigdata.mapreduce;

import org.apache.hadoop.util.ToolRunner;

public class Tache2FichierTemp {

    public static void main(String[] args) throws Exception {
        int exitCode = ToolRunner.run(new Tache2FichierTempDriver(), args);
        System.exit(exitCode);
    }
}
