package Sources.TaskB;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Reducer du job de la requête B
 * @author Manon Lacombe
 */
public class TaskBFinalReducer extends Reducer<Text, Text, Text, Text> 
{
    public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException 
    {
        // Initialisation des variables
        double count = 0;
        double sum   = 0;
        
        // Boucle sur les différentes valeurs de values
        for(Text taux : values) 
        {
            // Incrémentation de la variable count
            count += 1;
            // Ajout de la valeur de taux à la variable sum
            sum += Double.parseDouble(taux.toString());
        }
        // Calcul de la moyenne
        double moyenne = sum / count;
        // Renvoi de la nouvelle clé de type nomUE - moyenne associée à une valeur chaine vide
        context.write(new Text(key + " - " + String.valueOf(moyenne)), new Text(""));  // Nom UE - Moyenne
    }
}
