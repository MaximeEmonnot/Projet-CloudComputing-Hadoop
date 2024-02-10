package Sources.TaskA;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Reducer du job de la requête A
 * @author Manon Lacombe
 */
public class TaskAFinalReducer extends Reducer<Text, Text, Text, Text> 
{
    public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException 
    {
        // Initialisation des variables
        long totalNotes   = 0;
        long notesValides = 0;

        // Boucle sur les différentes valeurs de values
        for(Text note : values) 
        {
            totalNotes++;                                 // Incrémentation de la variable totalNotes
            if(Double.parseDouble(note.toString()) >= 10) // Condition de réussite
                notesValides++;                           // Incrémentation de la variable notesValides
        }
        // Calcul du taux de réussite
        double taux = notesValides / totalNotes;
        // Renvoi de la nouvelle clé de type Annee/Semestre - taux associé à une valeur chaine vide
        context.write(new Text(key + " - " + String.valueOf(taux)), new Text(""));
    }
}
