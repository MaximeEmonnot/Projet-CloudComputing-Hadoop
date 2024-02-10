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
        long count = 0;
        long sum=0;
        String nomUE = "";

        String[] keys = key.getString().split("/"); // k est de la forme CodeUE/Annee
        String codeUE = keys[0];

        for (LongWritable v : values) {
            if(v.size()==1){  // v vaut soit 0, soit 1, soit le nom de l’UE. On ne traite que 0 et 1 dans cette condition.
                count++;
                sum+=v.toInt();
            }
            else{
                codeUE=v;
            }
        }
        long tauxReussite = sum/count;
        context.write(new Text(codeUE + ";" + nomUE + ";" + tauxReussite), new Text(""));
    }
}
