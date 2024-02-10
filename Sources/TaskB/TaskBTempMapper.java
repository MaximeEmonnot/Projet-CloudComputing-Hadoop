package Sources.TaskB;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Mapper du job de génération de fichier temporaire de la tâche B
 * @author Manon Lacombe
 */
public class TaskBTempMapper extends Mapper<LongWritable, Text, Text, Text> 
{
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException 
    {
        // Condition 1er caractère de la ligne commençant par U
        if(value.toString().charAt(0) == 'U')
        {
            // Division des éléments contenu dans value
            String[] values = value.toString().split(";"); // U;Code UE;Année;Nom UE;Enseignant1,Enseignant2,Enseignant3,…
            // Création de la nouvelle clé de type CodeUE/Annee
            String newK = values[1] + "/" + values[2];
            // Renvoi de la nouvelle clé associée au nomUE
            context.write(new Text(newK), new Text(values[3]));
        }
        //Condition 1er caractère de la ligne commençant par N
        else if(value.toString().charAt(0) == 'N')
        {
            // Division des éléments contenu dans value
            String[] values = value.toString().split(";"); // N;Code UE;Année;Num étudiant;Note
            // Création de la nouvelle clé de type CodeUE/Année
            String newK = values[1] + "/" + values[2];
            // Renvoi de la nouvelle clé associée à une chaine de caractère indiquant la réussite ou non 
            context.write(new Text(newK), new Text(Double.parseDouble(values[4]) >= 10 ? "1" : "0"));
        }
    }
}