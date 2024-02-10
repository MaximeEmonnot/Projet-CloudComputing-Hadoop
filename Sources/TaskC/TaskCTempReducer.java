package Sources.TaskC;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**
 * Reducer du job de génération de fichier temporaire
 * @author Maxime Emonnot
 */
public class TaskCTempReducer extends Reducer<Text, Text, Text, Text> 
{
    @Override
    public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException
    {

    }    
}
