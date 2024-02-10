package Sources.TaskA;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class Tache2SolFinaleReducer extends Reducer<Text, LongWritable, Text, Text> {

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
