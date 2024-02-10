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
        // Définition des variables
        String            notes    = "";
        ArrayList<String> teachers = new ArrayList<>();
        String            ueName   = "";
        String[]          keys     = key.toString().split("/"); // key est de la forme CodeUE/Année

        for(Text v : values)
        {
            if(v.toString().contains(";"))                          // v est de la forme NomUE;NomIntervenant
            {
                String[] val = v.toString().split(";"); 
                teachers.add(val[1]);                                 // On ajoute l'intervenant dans la liste des intervenants 
                ueName = val[0];                                      // On enregistre le nom de l'UE
            }
            else                                                      // v est une note
                notes += (notes.isEmpty() ? "" : ",") + v.toString(); // On remplit la "liste" de notes (chaîne de charactère avec toutes les notes)
        }

        for(String teacher : teachers)                                // Pour tous les intervenant, on envoie une ligne "Intervenant;Code UE;Année;Nom UE;Liste de notes"
            context.write(new Text(teacher + ";" + keys[0] + ";" + keys[1] + ";" + ueName + ";" + notes), new Text(""));
    }    
}
