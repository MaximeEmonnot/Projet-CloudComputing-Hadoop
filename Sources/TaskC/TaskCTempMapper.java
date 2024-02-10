package Sources.TaskC;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * Mapper du job de génération de fichier temporaire
 * @author Maxime Emonnot
 */
public class TaskCTempMapper extends Mapper<LongWritable, Text, Text, Text> 
{
    @Override
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException
    {
        if(value.toString().charAt(0) == 'U')
        {
            String[] values = value.toString().split(";"); // U;Code UE;Année;Nom UE;Enseignant1,Enseignant2,Enseignant3,…
            for(String teacher : values[4].split(","))
            {
                String newKey = values[1] + "/" + values[2];
                String val    = values[3] + ";" + teacher;
                context.write(new Text(newKey), new Text(val)); 
            }
        }
        else if(value.toString().charAt(0) == 'N')
        {
            String[] values = value.toString().split(";"); // N;Code UE;Année;Num étudiant;Note
            String newKey = values[1] + "/" + values[2];
            context.write(new Text(newKey), new Text(Double.parseDouble(values[4]) >= 10 ? "1" : "0"));
        }
    }
}
