package Sources.TaskA;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

import javax.naming.Context;

/**
 * Reducer du job de la requête A
 * @author Manon Lacombe
 */
public class TaskAFinalReducer extends Reducer<Text, LongWritable, Text, Text> {

    public void reduce(Text key, Iterable<LongWritable> values, Context context) throws IOException,
            InterruptedException {
        long totalNotes = 0;
        long notesValides=0;
        for (LongWritable note : values) {
            totalNotes++;
            if(note>=10){
                notesValides++;
            }
        }
        long taux = notesValides/totalNotes;
        context.write(key+" - "+taux, new Text(""));
    }
}
