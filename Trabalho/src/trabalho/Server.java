package trabalho;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author berna-dev
 */
public class Server {

    public static int aid;

    public static int getAid() {
        aid++;
        return aid - 1;
    }

    public static void setAid(int aux_aid) {
        aid = aux_aid;
    }

    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        PostgresConnector BD = null;
        int regPort;
        String HOST;
        String DB;
        String USER;
        String PW;

        try ( InputStream input = new FileInputStream("src/trabalho/server.properties")) {
            Properties serverProps = new Properties();
            
            if (input == null) {
                System.out.println("Sorry, unable to find server.properties");
                return;
            }
            
            serverProps.load(input);
            
            regPort = Integer.parseInt(serverProps.getProperty("regPort")); // default RMIRegistry port
            HOST = serverProps.getProperty("host");
            DB = serverProps.getProperty("db");
            USER = serverProps.getProperty("user");
            PW = serverProps.getProperty("pw");

            BD = new PostgresConnector(HOST, DB, USER, PW);
            BD.connect();

            Statement stmt = BD.getStatement();

            //Obter o aid com maior numero!!!!
            try {

                int aid = 0;
                ResultSet rs = stmt.executeQuery("SELECT MAX(aid) FROM anuncios");

                if (rs.next()) {

                    if (rs.wasNull()) {
                        aid = 1;
                    } else {
                        aid = rs.getInt(1);
                    }
                }

                Server.setAid(aid);

            } catch (SQLException ignored) {

            }

            try {
                AdClienteGeral clienteGeral = new AdClienteGeralImpl(BD);
                AdClienteGestao clienteGestao = new AdClienteGestaoImpl(BD);

                java.rmi.registry.Registry registry = java.rmi.registry.LocateRegistry.getRegistry(regPort);

                registry.rebind("AdClienteGeral", clienteGeral);
                registry.rebind("AdClienteGestao", clienteGestao);

                System.out.println("RMI object bound to service in registry");

                System.out.println("servidor pronto");

            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception ignored) {

        }
        
        BD.disconnect();
    }
}
