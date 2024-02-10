package Sources.TaskB;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Reducer du job de génération de fichier temporaire de la tâche B
 * @author Manon Lacombe
 */
public class TaskBTempReducer extends Reducer<Text, Text, Text, Text> 
{

    public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException 
    {
        // Initialisation des variables 
        long   count  = 0;
        long   sum    = 0;
        String nomUE  = "";
        String codeUE = key.toString().split("/")[0]; // key est de la forme CodeUE/Annee

        // Boucle sur les différentes valeurs de values
        for(Text v : values) 
        {
            if(v.toString().length() == 1) // v vaut soit 0, soit 1, soit le nom de l’UE. On ne traite que 0 et 1 dans cette condition.
            {   
                //Incrémentation de la variable count
                count++;
                //Ajout de la valeur v à la ariable sum
                sum += Integer.parseInt(v.toString());
            }
            else // Sinon codeUE est égale à v
            {
                
                codeUE = v.toString();
            }
        }
        // Calcul du taux de réussite
        double tauxReussite = sum / count;
        // Renvoi de la nouvelle clé de type codeUE;nomUE;tauxReussite associée à une valeur chaine vide
        context.write(new Text(codeUE + ";" + nomUE + ";" + String.valueOf(tauxReussite)), new Text(""));
    }
}
