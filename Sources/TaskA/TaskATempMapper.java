package Sources.TaskA;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

import javax.naming.Context;

/**
 * Mapper du job de génération de fichier temporaire de la tâche A
 * @author Manon Lacombe
 */
public class TaskATempMapper extends Mapper<LongWritable, Text, Text, DoubleWritable> 
{

    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException 
    {
        //Condition 1er caractère de la ligne commençant par N
        if(value.getString().charAt(0).equals("N"))
        {
            //Division des éléments contenu dans value
            String[] values = value.getString().split(";"); // N ; CodeUE ; Année ; NumEtudiant ; Note
            //Création de la nouvelle clé de type Année;(3ers caractère)CodeUE;NumEtudiant
            String newKey = values[2] + ";" + values[1].substr(0, 3) + ";" + values[3];
            //renvoye de la nouvelle clé associée aux notes
            context.write(new Text(newKey), new DoubleWritable(Double.parseDouble(values[4])));
        }
    }
}