package Sources.TaskA;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Reducer du job de génération de fichier temporaire de la tâche A
 * @author Manon Lacombe
 */
public class TaskATempReducer extends Reducer<Text, Text, Text, Text> 
{

    public void reduce(Text key, Iterable<Text> values, Context context) throws IOException,InterruptedException 
    {
        // Initialisation des variables
        double sum = 0;
        double num = 0;

        // Boucle sur les différentes valeurs de values
        for (Text note : values) 
        {
            // Incrémentation de la variable num
            num += 1;
            // Ajout de la valeur note à la variable sum
            sum += Double.parseDouble(note.toString());
        }
        // Calcul de la moyenne
        double moyenne = sum / num;
        // Renvoi de la nouvelle clé de type Année;(3ers caractère)CodeUE;NumEtudiant;moyenne associée à une valeur chaine vide
        context.write(new Text(key + ";" + String.valueOf(moyenne)), new Text(""));
    }
}
