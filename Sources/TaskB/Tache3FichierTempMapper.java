package Sources.TaskB;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

import javax.naming.Context;

public class Tache3FichierTempMapper extends Mapper<LongWritable, Text, Text, Text> 
{
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException 
    {
        if(value.getString().charAt(0).equals("U"))
        {
            String[] values = value.getString().split(";"); // U;Code UE;Année;Nom UE;Enseignant1,Enseignant2,Enseignant3,…
            String newK = values[1] + "/" + values[2];
            context.write(new Text(newK), new Text(line));
        }
        else if(value.getString().charAt(0).equals("N")){
            String[] values = value.getString().split(";"); // N;Code UE;Année;Num étudiant;Note
            String newK = values[1] + "/" + values[2];
            context.write(new Text(newK), new Text(note > 10 ? "1" : "0"));
        }
    }
}