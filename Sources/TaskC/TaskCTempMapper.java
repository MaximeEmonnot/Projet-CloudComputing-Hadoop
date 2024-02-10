package Sources.TaskC;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * Mapper du job de génération de fichier temporaire
 * @author Maxime Emonnot
 */
public class TaskCTempMapper extends Mapper<LongWritable, Text, Text, Text> 
{
    @Override
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException
    {
        
    }
}
