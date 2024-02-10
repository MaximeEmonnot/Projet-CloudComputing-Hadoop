package Sources.TaskA;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

import javax.naming.Context;

/**
 * Reducer du job de génération de fichier temporaire de la tâche A
 * @author Manon Lacombe
 */
public class TaskATempReducer extends Reducer<Text, LongWritable, Text, Text> {

    public void reduce(Text key, Iterable<LongWritable> values, Context context) throws IOException,
            InterruptedException {
        //Initialisation des variables
        long sum = 0;
        long num=0;

        //Boucle sur les différentes valeurs de values
        for (LongWritable note : values) {
            //Incrémentation de la variable num
            num++;
            //Ajout de la valeur note à la variable sum
            sum += note.get();
        }
        //Calcul de la moyenne
        long moyenne = sum/num;
        //Renvoye de la nouvelle clé de type  Année;(3ers caractère)CodeUE;NumEtudiant;moyenne associée à une valeur chaine vide
        context.write(key+";"+moyenne, new Text(""));
    }
}
