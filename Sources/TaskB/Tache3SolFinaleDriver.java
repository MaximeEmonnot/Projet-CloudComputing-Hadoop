package Sources.TaskB;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tache3SolFinaleDriver extends Configured implements Tool {

    public int run(String[] args) throws Exception {
        if (args.length != 2) {
            System.out.printf("Usage: %s <INPUT> <OUTPUT>\n", getClass().getSimpleName());
            ToolRunner.printGenericCommandUsage(System.out);
            return -1;
        }

        Scanner scanner = new Scanner(System.in);

        do {
            System.out.print("Veuillez entrer une valeur pour le code de l'UE (sous la forme S0x avec x un chiffre): ");
            semestre = scanner.nextLine();
        } while (!isValidCodeUE(codeUE));       

        Job job = Job.getInstance();
        job.setJarByClass(Tache3SolFinaleDriver.class);
        job.setJobName("Tache 3 Solution Finale");
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        job.setMapperClass(Tache3SolFinaleMapper.class);
        job.setReducerClass(Tache3SolFinaleReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        job.getConfiguration().set("codeUE", codeUE);

        return job.waitForCompletion(true) ? 0 : 1;
    }

    private boolean isValidCodeUE(String codeUE) {
        String regex = "^S0\\d{1}[A-B]\d{3}$"; 
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(userValue);
        return matcher.matches();
    }
}
