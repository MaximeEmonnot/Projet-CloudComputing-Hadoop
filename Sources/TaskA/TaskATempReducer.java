package Sources.TaskA;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

import javax.naming.Context;

/**
 * Reducer du job de génération de fichier temporaire de la tâche A
 * @author Manon Lacombe
 */
public class TaskATempReducer extends Reducer<Text, LongWritable, Text, Text> {

    public void reduce(Text key, Iterable<LongWritable> values, Context context) throws IOException,
            InterruptedException {
        long sum = 0;
        long num=0;
        for (LongWritable notes : values) {
            num++;
            sum += notes.get();
        }
        long moyenne = sum/num;
        context.write(key+";"+moyenne, new Text(""));
    }
}
