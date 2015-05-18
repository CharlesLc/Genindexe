package genindex;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 *
 * @author Luc
 */
public class Exemple_Use_Bdd {
    
    /* 
    La classe BDD renvoie un objet connexion de sorte à se connecter qu'une fois (cf. BDD et main)
    Il faut donc passer l'objet connexion en paramètre dans toutes les classes reliées avec
    */
    
    private String nomToto;
    private String nomTata;
    
    /* BIEN SUR, CE CODE NE MARCHE PAS, C'est du code à lire on va dire ;) */
    
    public String test_bdd(Connection connexion){/*Hyper important le paramètre*/
        
        String cequetuveux = ""; /*RETURN, donc pas forcément un string*/
        
        String messages ="";
        Statement statement = null;
        ResultSet resultat = null;
        try {
            /* Création de l'objet gérant les requêtes */
            statement = connexion.createStatement();
            messages=messages+"Objet requête créé !\n";

            /* --> Exécution d'une requête de lecture */
                /* BIEN SUR, ON PEUT METTRE NOS VARIABLES DANS LA REQUETE (+) */
                resultat = statement.executeQuery( "SELECT id, email, mot_de_passe, nom FROM Utilisateur;" );
                messages=messages+"Requête \"SELECT id, email, mot_de_passe, nom FROM Utilisateur;\" effectuée !\n";
            
 
                /* Récupération des données du résultat de la requête de lecture */
                while ( resultat.next() ) {
                    int idUtilisateur = resultat.getInt( "id" );
                    String emailUtilisateur = resultat.getString( "email" );
                    String motDePasseUtilisateur = resultat.getString( "mot_de_passe" );
                    String nomUtilisateur = resultat.getString( "nom" );
                    /* Formatage des données pour affichage*/
                    messages=messages+"Données retournées par la requête : id = " + idUtilisateur + ", email = " + emailUtilisateur
                            + ", motdepasse = "
                            + motDePasseUtilisateur + ", nom = " + nomUtilisateur + ".\n";
                }
                
            /* --> OU Exécution d'une requête de modification */
                /* Exécution d'une requête d'écriture */
                int statut = statement.executeUpdate( "INSERT INTO Utilisateur (email, mot_de_passe, nom, date_inscription) VALUES ('jmarc@mail.fr', MD5('lavieestbelle78'), 'jean-marc', NOW());" );

                /* Formatage pour affichage dans la JSP finale. */
                messages=messages+"Résultat de la requête d'insertion : " + statut + ".\n";
                /* statut : 1 = OK / 0 = ERROR */
                
            /* --> OU Exemple de requête préparée */
                /* ? remplacé plus tard ;) */
                /* Création de l'objet gérant les requêtes préparées */
                    PreparedStatement preparedStatement = connexion.prepareStatement( "INSERT INTO Utilisateur (email, mot_de_passe, nom, date_inscription) VALUES(?, MD5(?), ?, NOW());" );
                    messages=messages+"Requête préparée créée !\n";

                /* Récupération des paramètres d'URL saisis par l'utilisateur */
                String paramEmail = "bite@youhou.com";
                String paramMotDePasse = "beute";
                String paramNom = "Charlot";

                /*
                 * Remplissage des paramètres de la requête grâce aux méthodes
                 * setXXX() mises à disposition par l'objet PreparedStatement.
                 * Remplacement des 
                 */
                preparedStatement.setString( 1, paramEmail );
                preparedStatement.setString( 2, paramMotDePasse );
                preparedStatement.setString( 3, paramNom );

                /* Exécution de la requête */
                statut = preparedStatement.executeUpdate();

                /* Formatage pour affichage dans la JSP finale. */
                messages=messages+"Résultat de la requête d'insertion préparée : " + statut + ".\n";
                
            /* FIN DIFFERENT EXEMPLE */
               
        } catch ( SQLException e ) {
            messages=messages+"Erreur lors de la connexion : "
                + e.getMessage()+"\n";
        } finally {
            messages=messages+"Fermeture de l'objet ResultSet.\n";
            if ( resultat != null ) {
                try {
                    resultat.close();
                } catch ( SQLException ignore ) {
                }
            }
            messages=messages+"Fermeture de l'objet Statement.\n";
            if ( statement != null ) {
                try {
                    statement.close();
                } catch ( SQLException ignore ) {
                }
            }
        }
        System.out.println(messages);
        return cequetuveux;
    }
}
