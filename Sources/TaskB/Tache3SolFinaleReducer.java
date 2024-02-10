package bdma.bigdata.mapreduce;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class Tache2SolFinaleReducer extends Reducer<Text, LongWritable, Text, Text> {

    public void reduce(Text key, Iterable<LongWritable> values, Context context) throws IOException,
            InterruptedException {
        long count = 0;
        long sum=0;
        for (LongWritable taux : values) {
            count++;
            sum+=taux;
        }
        long moyenne = sum/count;
        context.write(key+" - "+moyenne, new Text(""));  // Nom UE - Moyenne
    }
}
