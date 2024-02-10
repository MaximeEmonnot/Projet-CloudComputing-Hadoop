package Sources.TaskC;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**
 * Reducer du job de génération de fichier temporaire
 * @author Maxime Emonnot
 */
public class TaskCTempReducer extends Reducer<Text, Text, Text, Text> 
{
    @Override
    public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException
    {
        String notes               = "";
        ArrayList<String> teachers = new ArrayList<>();
        String            ueName   = "";
        String[]          keys     = key.toString().split("/"); // CodeUE/Année

        for(Text v : values)
        {
            if(v.toString().contains(";")) // v est de la forme NomUE;NomIntervenant
            {
                String[] val = v.toString().split(";");
                teachers.add(val[1]);
                ueName = val[0];
            }
            else
                notes += notes.isEmpty() ? "," : "" + v.toString(); 
        }

        for(String teacher : teachers)
            context.write(new Text(teacher + ";" + keys[0] + ";" + keys[1] + ";" + ueName + ";" + notes), new Text(""));
    }    
}
