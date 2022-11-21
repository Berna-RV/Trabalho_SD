package trabalho;

/**
 *
 * @author berna-dev
 */
public interface AdClienteGeral extends java.rmi.Remote {
    
    /* Methods to be used by Cliente Geral*/

    /*Registar novo anuncio do tipo oferta*/
    public void registerOffer(String tipo_alojamento, String detalhes, String zona,
            String genero, String preco, String anunciante, String contacto, String data) throws java.rmi.RemoteException;

    /*Registar novo anuncio do  tipo procura*/
    public void registerSearch(String tipo_alojamento, String detalhes, String zona,
            String genero, String preco, String anunciante, String contacto, String data) throws java.rmi.RemoteException;

    /*Listar anuncio (com estado ativo) do tipo oferta*/
    public void listOfOffers() throws java.rmi.RemoteException;

    /*Listar anuncio (com estado ativo) do tipo procura*/
    public void listOfSearch() throws java.rmi.RemoteException;

    /*Listar todos os anuncios de um anunciante*/
    public void listByAdvertiser(String anunciante) throws java.rmi.RemoteException;
    
    /*Obter todos os detalhes de um anuncio, dado o seu aid*/
    public void getDetails(String aid) throws java.rmi.RemoteException;

    /* Enviar nova mensagem ao anunciante de um anuncio, pelo aid*/
    public void sendMessage(String aid, String mensagem) throws java.rmi.RemoteException;
    
    /*Consultar as mensagens inseridas para um determinado anúncio*/
    public void consultMessages(String aid) throws java.rmi.RemoteException; 

}
