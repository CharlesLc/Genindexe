package genindex;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BDD {

    public BDD() {
        String messages = "";
        try {
            messages=messages+"Chargement du driver...\n";
            Class.forName( "com.mysql.jdbc.Driver" );
            messages=messages+"Driver chargé !\n";
        } catch ( ClassNotFoundException e ) {
            messages=messages+"Erreur lors du chargement : le driver n'a pas été trouvé dans le classpath ! \n"
                    + e.getMessage() +"\n";
        }
        System.out.println(messages);
    }
}

