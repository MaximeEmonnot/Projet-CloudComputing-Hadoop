package Sources.TaskC;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
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
        InterruptedException {
        long count = 0;
        long sum=0;
        for (LongWritable note : values) {
            if(note > 10){
                sum+=note;
            }
            count++;
        }
        long taux = sum/count;
        context.write(key+" - "+taux, new Text(""));  // Nom UE - Taux de réussite
    }
    }    
}
