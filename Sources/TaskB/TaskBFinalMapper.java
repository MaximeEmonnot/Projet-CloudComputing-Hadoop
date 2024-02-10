package Sources.TaskB;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

import javax.naming.Context;

public class TaskBFinalMapper extends Mapper<LongWritable, Text, Text, Text> 
{
    private String codeUE = "";

    public void setup(Context context){
        codeUE = context.getConfiguration().get("codeUE");
    }

    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException 
    {
        String[] values = value.getString().split(";"); // CodeUE ; NomUE ; TauxRéussite
        
        if(values[0].equals(codeUE)){
            context.write(new Text(values[0]), new Text(values[2]));
        }
    }
}