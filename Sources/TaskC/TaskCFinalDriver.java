package Sources.TaskC;

import java.util.Scanner;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

/**
 * Driver du job de la requête C
 * @author Maxime Emonnot
 */
public class TaskCFinalDriver extends Configured implements Tool
{
    @Override
    public int run(String[] args) throws Exception 
    {

        // Vérification du nombre d'arguments utilisés dans la commande Hadoop
        if(args.length != 2) 
        {
            System.out.printf("Usage: %s <INPUT> <OUTPUT>\n", getClass().getSimpleName());
            ToolRunner.printGenericCommandUsage(System.out);
            return -1;
        }

        Scanner scanner = new Scanner(System.in);
        //Interaction utilisateur  pour obtenir le codeUE
        String teacher = "";
        System.out.print("Veuillez entrer une valeur pour l'intervenant: ");
        teacher = scanner.nextLine();      
        scanner.close();

        // Instanciation du job
        Job job = Job.getInstance();
        job.setJarByClass(TaskCFinalDriver.class);
        job.setJobName("Task C Final Query");

        // Définition des chemins d'entrée et de sortie
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        // Définition du Mapper et du Reducer
        job.setMapperClass(TaskCFinalMapper.class);
        job.setReducerClass(TaskCFinalReducer.class);

        // Définition des types d'entrée et de sortie
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        // Définition des paramètres
        job.getConfiguration().set("teacher", teacher);

        // Exécution du job
        return job.waitForCompletion(true) ? 0 : 1;
    }
}