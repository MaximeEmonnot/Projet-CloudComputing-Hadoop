package Sources.TaskA;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Mapper du job de la requête A
 * @author Manon Lacombe
 */
public class TaskAFinalMapper extends Mapper<LongWritable, Text, Text, Text> 
{
    //Initialisation des variables
    private String semestre = "";
    private String annee    = "";

    //Méthode pour récupérer les valeurs rentrées par l'utilisateur
    public void setup(Context context)
    {
        semestre = context.getConfiguration().get("semestre");
        annee    = context.getConfiguration().get("annee");
    }

    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException 
    {
        // Division des éléments contenu dans value
        String[] values = value.toString().split(";"); // Année ; Semestre ; NumEtudiant ; Moyenne
        
        // Condition si annee et semestre correspondent aux valeurs entrées par l'utilisateur
        if(values[0].equals(annee) 
        && values[1].equals(semestre))
        {
            // Création de la nouvelle clé de type Annee/Semestre
            String newKey = values[0] + "/" + values[1];
            // Conversion des moyennes en type double
            double moyenne = Double.parseDouble(values[3]);
            // Renvoi de la nouvelle clé associée aux moyennes
            context.write(new Text(newKey), new Text(String.valueOf(moyenne)));
        }
    }
}