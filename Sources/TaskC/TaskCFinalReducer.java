package Sources.TaskC;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**
 * Reducer du job de la requête C
 * @author Maxime Emonnot
 */
public class TaskCFinalReducer extends Reducer<Text, Text, Text, Text> 
{
    @Override
    public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException
    {
        String[] keys = key.toString().split(";");                // key est de la forme Nom Intervenant;Code UE;Année;Nom UE

        long count = 0;
        long sum   = 0;
        for(Text note : values) 
        {
            sum += (Double.parseDouble(note.toString()) >= 10) ? 1 : 0; // On ajoute 1 si la note est bien supérieure à 10
            count++;
        }
        double taux = sum / count;                                      // Calcul du taux de réussite
        // On envoie les données sous la forme CodeUE/Année - Nom UE - Taux de réussite
        context.write(new Text(keys[1] + "/" + keys[2] + " - " + keys[3] + " - " + String.valueOf(taux)), new Text(""));  
    }    
}
