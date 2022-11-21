package trabalho;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 *
 * @author berna-dev
 */
public class ClienteGestao {

    public static void main(String[] args) {

        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String clientConfigPath = rootPath + "cliente.properties";

        Properties clientProps = new Properties();
        try {
            clientProps.load(new FileInputStream(clientConfigPath));

        } catch (Exception ignored) {

        }

        String regHost = clientProps.getProperty("regHost");
        String regPort = clientProps.getProperty("regPort"); //porto do binder

        try {
            AdClienteGestao obj = (AdClienteGestao) java.rmi.Naming.lookup("rmi://" + regHost + ":" + regPort + "/AdClienteGestao");
            while (true) {

                System.out.println("Selecinar a opção:\n"
                        + "  1: Listar anuncios por estado\n"
                        + "  2: Obter detalhes de um anuncio\n"
                        + "  3: Aprovar um anuncio, alterando o estado do mesmo para ativo\n"
                        + "  4: Alterar o estado de um anuncio\n"
                        + "  5: Sair\n"
                        + "Opcão:");

                BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

                try {
                    int opcao = Integer.parseInt(input.readLine());

                    switch (opcao) {
                        case 1:
                            System.out.println("Estado:");
                            String estado1 = input.readLine();
                            obj.listAdsByState(estado1);
                        case 2:
                            System.out.println("Aid:");
                            String aid2 = input.readLine();
                            obj.getDetails(aid2);
                        case 3:
                            System.out.println("Aid:");
                            String aid3 = input.readLine();
                            obj.aprouveAd(aid3);
                        case 4:
                            System.out.println("Estado:");
                            String estado4 = input.readLine();
                            System.out.println("Aid:");
                            String aid4 = input.readLine();

                            obj.modifyAd(estado4, aid4);
                        case 5:
                            break;
                        default:
                            System.out.println("Introduzir um número válido.");

                    }
                } catch (Exception e) {
                    System.out.println("Tem que introduzir um numero.");
                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
