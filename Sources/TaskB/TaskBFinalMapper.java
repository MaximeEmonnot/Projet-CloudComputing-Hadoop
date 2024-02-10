package Sources.TaskB;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

import javax.naming.Context;

/**
 * Mapper du job de la requête B
 * @author Manon Lacombe
 */
public class TaskBFinalMapper extends Mapper<LongWritable, Text, Text, Text> 
{
    //Initialisation des variables
    private String codeUE = "";

    //Méthode pour récupérer les valeurs rentrées par l'utilisateur
    public void setup(Context context){
        codeUE = context.getConfiguration().get("codeUE");
    }

    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException 
    {
        //Division des éléments contenu dans value
        String[] values = value.getString().split(";"); // CodeUE ; NomUE ; TauxRéussite
        
        //Condition si codeUE correspond à la valeur entrée par l'utilisateur
        if(values[0].equals(codeUE)){
            //renvoye du codeUE associé aux taux de réussite
            context.write(new Text(values[0]), new Text(values[2]));
        }
    }
}