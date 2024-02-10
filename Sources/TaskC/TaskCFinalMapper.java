package Sources.TaskC;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * Mapper du job de la requête C
 * @author Maxime Emonnot
 */
public class TaskCFinalMapper extends Mapper<LongWritable, Text, Text, Text> 
{
    // Initialisation des variables
    private String teacher = "";

    // Méthode pour récupérer les valeurs rentrées par l'utilisateur
    public void setup(Context context)
    {
        teacher = context.getConfiguration().get("teacher");
    }

    @Override
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException
    {
        setup(context);

        String[] values = value.toString().split(";"); // Nom Intervenant ; Code UE ; Année ; Nom UE ; Notes

        if(values[0].equals(teacher))                        // On vérifie si l'intervenant est bien celui du paramètre
            for(String note : values[4].split(","))    // Pour toutes les notes dans la liste de notes, on renvoie le couple "Nom Intervenant;Code UE;Année;Nom UE" - "Note"
                context.write(new Text(values[0] + ";" + values[1] + ";" + values[2] + ";" + values[3]), new Text(note));
    }
}
