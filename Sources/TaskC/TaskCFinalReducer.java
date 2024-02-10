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

    }    
}
