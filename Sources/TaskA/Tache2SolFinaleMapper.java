package Sources.TaskA;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

import javax.naming.Context;

public class Tache2SolFinaleMapper extends Mapper<LongWritable, Text, Text, DoubleWritable> 
{
    private String semestre = "";
    private String annee="";

    public void setup(Context context){
        semestre = context.getConfiguration().get("semestre");
        annee = contex.getConfiguration().get("Annee");
    }

    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException 
    {
        String[] values = value.getString().split(";"); // Année ; Semestre ; NumEtudiant ; Moyenne
        
        if(value[0].equals(annee)&&value[1].equals(semestre)){
            String newKey = values[0] + "/" + values[1];
            double moyenne = Double.parseDouble(value[3]);
            context.write(new Text(newKey), new DoubleWritable(Double.parseDouble(moyenne)));
        }
    }
}