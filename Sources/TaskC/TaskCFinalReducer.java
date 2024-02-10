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
        String[] keys = key.toString().split(";");

        long count = 0;
        long sum   = 0;
        for(Text note : values) 
        {
            sum += (Double.parseDouble(note.toString()) >= 10) ? 1 : 0;
            count++;
        }
        double taux = sum / count;
        context.write(new Text(keys[1] + "/" + keys[2] + " - " + keys[3] + " - " + String.valueOf(taux)), new Text(""));  // Nom UE - Taux de réussite
    }    
}
