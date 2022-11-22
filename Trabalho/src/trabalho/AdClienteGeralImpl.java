package trabalho;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author berna-dev
 */
public class AdClienteGeralImpl extends UnicastRemoteObject implements AdClienteGeral, java.io.Serializable {

    PostgresConnector anuncios = null;
    Statement stmt;

    public AdClienteGeralImpl(PostgresConnector db) throws RemoteException, Exception {
        anuncios = db;
        stmt = anuncios.getStatement();
    }


    /*Registar novo anuncio do tipo oferta*/
    public void registerOffer(String tipo_alojamento, String detalhes, String zona,
            String genero, String preco, String anunciante, String contacto, String data) throws java.rmi.RemoteException {

        try {
            stmt.executeUpdate("insert into anuncios values('oferta' ,'" + tipo_alojamento + "','" + detalhes + "','" + zona + "','"
                    + genero + "','" + preco + "','" + anunciante + "','" + contacto + "','" + data + "', 'inativo' ," + Server.getAid() + ")");

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Problems on insert...");
        }
    }

    /*Registar novo anuncio do  tipo procura*/
    public void registerSearch(String tipo_alojamento, String detalhes, String zona,
            String genero, String preco, String anunciante, String contacto, String data) throws RemoteException {

        try {
            stmt.executeUpdate("insert into anuncios values('procura' ,'" + tipo_alojamento + "','" + detalhes + "','" + zona + "','"
                    + genero + "','" + preco + "','" + anunciante + "','" + contacto + "','" + data + "', 'inativo' ," + Server.getAid() + ")");

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Problems on insert...");
        }
    }

    /*Listar anuncios (com estado ativo) do tipo oferta*/
    public List<String> listOfOffers() throws RemoteException {
        List<String> offers = new LinkedList<>();
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM anuncios WHERE tipo_anuncio='oferta' AND estado='ativo'");
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
                int aid = rs.getInt("aid");

                offers.add(" Tipo de anuncio: " + tipo_anuncio + " Tipo de alojamento: " + tipo_alojamento + " Detalhes: " + detalhes
                        + " Zona: " + zona + " Genero: " + genero + " Preco: " + preco + " Anunciante: " + anunciante + " Contacto: " + contacto
                        + " Data: " + data + " Estado: " + estado + " Aid: " + aid);

            }

            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Problems retrieving data from db...");
        }
        
        return offers;

    }

    /*Listar anuncios (com estado ativo) do tipo procura*/
    public List<String> listOfSearch() throws RemoteException {
        List<String> searches = new LinkedList<>();
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM anuncios WHERE tipo_anuncio='procura' AND estado='ativo'");

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
                int aid = rs.getInt("aid");

                searches.add(" Tipo de anuncio: " + tipo_anuncio + " Tipo de alojamento: " + tipo_alojamento + " Detalhes: " + detalhes
                        + " Zona: " + zona + " Genero: " + genero + " Preco: " + preco + " Anunciante: " + anunciante + " Contacto: " + contacto
                        + " Data: " + data + " Estado: " + estado + " Aid: " + aid);

            }

            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Problems retrieving data from db...");
        }
        
        return searches;

    }

    /*Listar todos os anuncios de um anunciante*/
    public List<String> listByAdvertiser(String anunciante) throws RemoteException {
        
        List<String> ads= new LinkedList<>();
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM anuncios WHERE anunciante='" + anunciante +"'");

            //mostrar resultados
            while (rs.next()) {
                String tipo_anuncio = rs.getString("tipo_anuncio");
                String tipo_alojamento = rs.getString("tipo_alojamento");
                String detalhes = rs.getString("detalhes");
                String zona = rs.getString("zona");
                String genero = rs.getString("genero");
                String preco = rs.getString("preco");
                String contacto = rs.getString("contacto");
                java.sql.Date data = rs.getDate("data");
                String estado = rs.getString("estado");
                int aid = rs.getInt("aid");

                ads.add(" Tipo de anuncio: " + tipo_anuncio + " Tipo de alojamento: " + tipo_alojamento + " Detalhes: " + detalhes
                        + " Zona: " + zona + " Genero: " + genero + " Preco: " + preco + " Anunciante: " + anunciante + " Contacto: " + contacto
                        + " Data: " + data + " Estado: " + estado + " Aid: " + aid);

            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Problems retrieving data from db...");
        }
        
        return ads;

    }

    /*Obter todos os detalhes de um anuncio, dado o seu aid*/
    public String getDetails(String aid) throws RemoteException {
        String details = new String();
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM anuncios WHERE aid='" + aid +"'");

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

                details=" Tipo de anuncio: " + tipo_anuncio + " Tipo de alojamento: " + tipo_alojamento + " Detalhes: " + detalhes
                        + " Zona: " + zona + " Genero: " + genero + " Preco: " + preco + " Anunciante: " + anunciante + " Contacto: " + contacto
                        + " Data: " + data + " Estado: " + estado + " Aid: " + aid;

            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Problems retrieving data from db...");
        }
        
        return details;

    }

    /* Enviar nova mensagem ao anunciante de um anuncio, pelo aid*/
    public void sendMessage(String aid, String mensagem) throws RemoteException {

        try {
            stmt.executeUpdate("insert into mensagens values('" + mensagem + "','" + aid + "')");

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Problems on insert...");
        }

    }

    /*Consultar as mensagens inseridas para um determinado anúncio*/
    public List<String> consultMessages(String aid) throws RemoteException {
        
        List<String> messages= new LinkedList<>();

        try {
            ResultSet rs = stmt.executeQuery("SELECT mensagem FROM mensagens WHERE aid='" + aid + "'");

            //mostrar resultados
            while (rs.next()) {
                String mensagem = rs.getString("mensagem");

                messages.add("Mensagem: " + mensagem);

            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Problems retrieving data from db...");
        }
        
        return messages;
    }
}
