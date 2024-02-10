package Sources.TaskA;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Driver du job de génération de la tâche A
 * @author Manon Lacombe
 */
public class TaskAFinalDriver extends Configured implements Tool 
{

    public int run(String[] args) throws Exception 
    {
        // Vérification du nombre d'arguments utilisés dans la commande Hadoop
        if (args.length != 2) 
        {
            System.out.printf("Usage: %s <INPUT> <OUTPUT>\n", getClass().getSimpleName());
            ToolRunner.printGenericCommandUsage(System.out);
            return -1;
        }

        Scanner scanner = new Scanner(System.in);
        // Interacton utilisateur pour obtenir le semestre
        String semestre = "";
        do 
        {
            System.out.print("Veuillez entrer une valeur pour le semestre (sous la forme Sxx avec x le numéro du semestre): ");
            semestre = scanner.nextLine();
        } 
        while (!isValidSemestre(semestre));

        // Interacton utilisateur pour obtenir l'année
        String annee = "";
        do 
        {
            System.out.print("Veuillez entrer une valeur pour l'année (4 chiffres): ");
            annee = scanner.nextLine();
        } 
        while (!isValidAnnee(annee)); 
        scanner.close();      

        // Instanciation du job
        Job job = Job.getInstance();
        job.setJarByClass(TaskAFinalDriver.class);
        job.setJobName("Tache A Solution Finale");

        // Définition des chemins d'entrée et de sortie
        FileInputFormat .addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        
        // Définition du Mapper et du Reducer
        job.setMapperClass(TaskAFinalMapper.class);
        job.setReducerClass(TaskAFinalReducer.class);
        
        // Définition des types d'entrée et de sortie
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        // Définition des paramètres
        job.getConfiguration().set("semestre", semestre);
        job.getConfiguration().set("annee", annee);

        // Exécution du job
        return job.waitForCompletion(true) ? 0 : 1;
    }

    // Méthode de validation du format du semestre
    private boolean isValidSemestre(String semestre) 
    {
        String regex    = "^S0\\d{1}$"; 
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(semestre);
        return matcher.matches();
    }

    // Méthode de validation du format du semestre
    private boolean isValidAnnee(String Annee) 
    {
        String regex    = "^\\d{4}$"; 
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(Annee);
        return matcher.matches();
    }
}
