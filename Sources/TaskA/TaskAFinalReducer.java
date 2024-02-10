package Sources.TaskA;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

import javax.naming.Context;

/**
 * Reducer du job de la requête A
 * @author Manon Lacombe
 */
public class TaskAFinalReducer extends Reducer<Text, LongWritable, Text, Text> {

    public void reduce(Text key, Iterable<LongWritable> values, Context context) throws IOException,
            InterruptedException {
        //Initialisation des variables
        long totalNotes = 0;
        long notesValides=0;

        //Boucle sur les différentes valeurs de values
        for (LongWritable note : values) {
            //Incrémentation de la variable totalNotes
            totalNotes++;
            //Condition de réussite
            if(note>=10){
                //Incrémentation de la variable notesValides
                notesValides++;
            }
        }
        //Calcul du taux de réussite
        long taux = notesValides/totalNotes;
        //Renvoye de la nouvelle clé de type Annee/Semestre - taux associé à une valeur chaine vide
        context.write(key+" - "+taux, new Text(""));
    }
}
