package trabalho;
/**
 *
 * @author berna-dev
 */
public interface AdClienteGestao extends java.rmi.Remote {
    
    /*Listar anuncios por estado*/
    public void listAdsByState(String estado) throws java.rmi.RemoteException;
    
    /*Obter detalhes de um anuncio*/
    public void getDetails(String aid) throws java.rmi.RemoteException;
    
    /*Aprovar um anuncio, alterando o estado do mesmo para ativo*/
    public void aprouveAd(String aid) throws java.rmi.RemoteException;
    
    /*Alterar o estado de um anuncio*/
    public void modifyAd(String estado , String aid) throws java.rmi.RemoteException;  
}
