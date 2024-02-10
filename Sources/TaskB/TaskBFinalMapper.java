package Sources.TaskB;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Mapper du job de la requête B
 * @author Manon Lacombe
 */
public class TaskBFinalMapper extends Mapper<LongWritable, Text, Text, Text> 
{
    // Initialisation des variables
    private String codeUE = "";

    // Méthode pour récupérer les valeurs rentrées par l'utilisateur
    public void setup(Context context)
    {
        codeUE = context.getConfiguration().get("codeUE");
    }

    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException 
    {
        setup(context);

        // Division des éléments contenus dans value
        String[] values = value.toString().split(";"); // CodeUE ; NomUE ; TauxRéussite
        
        // Condition si codeUE correspond à la valeur entrée par l'utilisateur
        if(values[0].equals(codeUE))
        {
            // Renvoi du nomUE associé aux taux de réussite
            context.write(new Text(values[1]), new Text(values[2]));
        }
    }
}