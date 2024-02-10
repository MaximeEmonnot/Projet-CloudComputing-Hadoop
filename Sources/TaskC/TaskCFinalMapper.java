package Sources.TaskC;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * Mapper du job de la requête C
 * @author Maxime Emonnot
 */
public class TaskCFinalMapper extends Mapper<LongWritable, Text, Text, Text> 
{
    @Override
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException
    {
        String[] values = value.getString().split(";");
        String[] notes = values[-1].split(",");

        notes.forEach(note -> context.write(new Text(values[0]), new Text(note)));

    }
}
