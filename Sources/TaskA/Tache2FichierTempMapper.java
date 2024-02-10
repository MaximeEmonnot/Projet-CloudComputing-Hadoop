package bdma.bigdata.mapreduce;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

import javax.naming.Context;

public class Tache2FichierTempMapper extends Mapper<LongWritable, Text, Text, DoubleWritable> 
{

    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException 
    {
        if(value.getString().charAt(0).equals("N"))
        {
            String[] values = value.getString().split(";"); // N ; CodeUE ; Année ; NumEtudiant ; Note
            String newKey = values[2] + ";" + values[1].substr(0, 3) + ";" + values[3];
            context.write(new Text(newKey), new DoubleWritable(Double.parseDouble(values[4])));
        }
    }
}