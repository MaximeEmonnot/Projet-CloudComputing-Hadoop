package Sources.TaskB;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Reducer du job de la requête B
 * @author Manon Lacombe
 */
public class TaskBFinalReducer extends Reducer<Text, LongWritable, Text, Text> {

    public void reduce(Text key, Iterable<LongWritable> values, Context context) throws IOException,
            InterruptedException {
        //Initialisation des variables
        long count = 0;
        long sum=0;
        
        //Boucle sur les différentes valeurs de values
        for (LongWritable taux : values) {
            //Incrémentation de la variable count
            count++;
            //Ajout de la valeur de taux à la variable sum
            sum+=taux;
        }
        //Calcul de la moyenne
        long moyenne = sum/count;
        //Renvoye de la nouvelle clé de type codeUE - moyenne associée à une valeur chaine vide
        context.write(key+" - "+moyenne, new Text(""));  // Nom UE - Moyenne
    }
}
