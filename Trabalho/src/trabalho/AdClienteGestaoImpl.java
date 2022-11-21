package trabalho;

import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author berna-dev
 */
public class AdClienteGestaoImpl extends UnicastRemoteObject implements AdClienteGestao, java.io.Serializable {

    PostgresConnector anuncios = null;
    Statement stmt;

    public AdClienteGestaoImpl(PostgresConnector db) throws RemoteException, Exception {
        anuncios = db;
        anuncios.connect();
        stmt = anuncios.getStatement();
    }

    /*Listar anuncios por estado*/
    public void listAdsByState(String estado) throws RemoteException {

        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM anuncios WHERE estado=" + estado);

            //mostrar resultados
            while (rs.next()) {
                String tipo_anuncio = rs.getString("tipo_anuncio");
                String tipo_alojamento = rs.getString("tipo_alojamento");
                String detalhes = rs.getString("detalhes");
                String zona = rs.getString("zona");
                String genero = rs.getString("genero");
                String preco = rs.getString("preco");
                String anunciante = rs.getString("anunciante");
                String contacto = rs.getString("contacto");
                java.sql.Date data = rs.getDate("data");
                String aid = rs.getString("aid");

                System.out.println(" Tipo de anuncio: " + tipo_anuncio + " Tipo de alojamento: " + tipo_alojamento + " Detalhes: " + detalhes
                        + " Zona: " + zona + " Genero: " + genero + " Preco: " + preco + " Anunciante: " + anunciante + " Contacto: " + contacto
                        + " Data: " + data + " Estado: " + estado + " Aid: " + aid);

            }

            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Problems retrieving data from db...");
        }

    }

    /*Obter detalhes de um anuncio*/
    public void getDetails(String aid) throws RemoteException {
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM anuncios WHERE aid=" + aid);

            //mostrar resultados
            while (rs.next()) {
                String tipo_anuncio = rs.getString("tipo_anuncio");
                String tipo_alojamento = rs.getString("tipo_alojamento");
                String detalhes = rs.getString("detalhes");
                String zona = rs.getString("zona");
                String genero = rs.getString("genero");
                String preco = rs.getString("preco");
                String anunciante = rs.getString("anunciante");
                String contacto = rs.getString("contacto");
                java.sql.Date data = rs.getDate("data");
                String estado = rs.getString("estado");

                System.out.println(" Tipo de anuncio: " + tipo_anuncio + " Tipo de alojamento: " + tipo_alojamento + " Detalhes: " + detalhes
                        + " Zona: " + zona + " Genero: " + genero + " Preco: " + preco + " Anunciante: " + anunciante + " Contacto: " + contacto
                        + " Data: " + data + " Estado: " + estado + " Aid: " + aid);

            }

            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Problems retrieving data from db...");
        }

    }

    /*Aprovar um anuncio, alterando o estado do mesmo para ativo*/
    public void aprouveAd(String aid) throws RemoteException {

        try {
            stmt.executeUpdate("update anuncios set estado='ativo' where aid=" + aid);

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Problems on insert...");
        }
    }

    /*Alterar o estado de um anuncio*/
    public void modifyAd(String estado, String aid) throws RemoteException {
        try {
            stmt.executeUpdate("update anuncios set estado=" + estado + " where aid=" + aid);

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Problems on insert...");
        }
    }
}
