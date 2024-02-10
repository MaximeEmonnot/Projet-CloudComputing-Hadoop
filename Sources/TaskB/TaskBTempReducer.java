package Sources.TaskB;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

import javax.naming.Context;

/**
 * Reducer du job de génération de fichier temporaire de la tâche B
 * @author Manon Lacombe
 */
public class TaskBTempReducer extends Reducer<Text, LongWritable, Text, Text> {

    public void reduce(Text key, Text values, Context context) throws IOException,
            InterruptedException {
        //Initialisation des variables 
        long count = 0;
        long sum=0;
        String nomUE = "";

        String[] keys = key.getString().split("/"); // k est de la forme CodeUE/Annee
        String codeUE = keys[0];

        //Boucle sur les différentes valeurs de values
        for (LongWritable v : values) {
            if(v.size()==1){  // v vaut soit 0, soit 1, soit le nom de l’UE. On ne traite que 0 et 1 dans cette condition.
                //Incrémentation de la variable count
                count++;
                //Ajout de la valeur v à la ariable sum
                sum+=v.toInt();
            }
            else{
                //Sinon codeUE est égale à v
                codeUE=v;
            }
        }
        //Calcul du taux de réussite
        long tauxReussite = sum/count;
        //Renvoye de la nouvelle clé de type codeUE;nomUE;tauxReussite associée à une valeur chaine vide
        context.write(new Text(codeUE + ";" + nomUE + ";" + tauxReussite), new Text(""));
    }
}
