package trabalho;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author berna-dev
 */
public class ClienteGeral {

    public static void main(String[] args) {

        Properties clientProps = new Properties();
        try ( InputStream properties = new FileInputStream("src/trabalho/cliente.properties")) {
            clientProps.load(properties);
        } catch (Exception ignored) {

        }

        String regHost = clientProps.getProperty("regHost");
        String regPort = clientProps.getProperty("regPort"); //porto do binder

        try {

            AdClienteGeral obj = (AdClienteGeral) java.rmi.Naming.lookup("rmi://" + regHost + ":" + regPort + "/AdClienteGeral");

            while (true) {
                System.out.println("Selecinar a opção:\n"
                        + "  1: Registar novo anuncio do tipo oferta\n"
                        + "  2: Registar novo anuncio do  tipo procura\n"
                        + "  3: Listar anuncios (com estado ativo) do tipo oferta\n"
                        + "  4: Listar anuncios (com estado ativo) do tipo procura\n"
                        + "  5: Listar todos os anuncios de um anunciante\n"
                        + "  6: Obter todos os detalhes de um anuncio, dado o seu aid\n"
                        + "  7: Enviar nova mensagem ao anunciante de um anuncio, pelo aid\n"
                        + "  8: Consultar as mensagens inseridas para um determinado anúncio\n"
                        + "  9: Sair\n"
                        + "Opção:");

                BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

                try {
                    int opcao = Integer.parseInt(input.readLine());

                    switch (opcao) {
                        case 1:
                            System.out.println("Introduza as caracteristicas do anuncio:");

                            System.out.print("Tipo de alojamento:");
                            String tipo_alojamento_O = input.readLine();

                            System.out.print("Detalhes");
                            String detalhes_O = input.readLine();

                            System.err.print("Zona:");
                            String zona_O = input.readLine();

                            System.out.print("Genero:");
                            String genero_O = input.readLine();

                            System.err.print("Preço:");
                            String preco_O = input.readLine();

                            System.out.print("Anunciante:");
                            String anunciante_O = input.readLine();

                            System.out.print("Contacto:");
                            String contacto_O = input.readLine();

                            System.out.print("Data:");
                            String data_O = input.readLine();

                            obj.registerOffer(tipo_alojamento_O, detalhes_O, zona_O,
                                    genero_O, preco_O, anunciante_O, contacto_O, data_O);
                            break;

                        case 2:
                            System.out.println("Introduza as caracteristicas do anuncio:");

                            System.out.print("Tipo de alojamento:");
                            String tipo_alojamento_P = input.readLine();

                            System.out.print("Detalhes");
                            String detalhes_P = input.readLine();

                            System.err.print("Zona:");
                            String zona_P = input.readLine();

                            System.out.print("Genero:");
                            String genero_P = input.readLine();

                            System.err.print("Preço:");
                            String preco_P = input.readLine();

                            System.out.print("Anunciante:");
                            String anunciante_P = input.readLine();

                            System.out.print("Contacto:");
                            String contacto_P = input.readLine();

                            System.out.print("Data:");
                            String data_P = input.readLine();

                            obj.registerSearch(tipo_alojamento_P, detalhes_P, zona_P,
                                    genero_P, preco_P, anunciante_P, contacto_P, data_P);
                            break;
                        case 3:
                            List<String> offers = obj.listOfOffers();
                            for (var s : offers) {
                                System.out.println(s);
                            }
                            break;
                        case 4:
                            List<String> searches = obj.listOfSearch();
                            for (var s : searches) {
                                System.out.println(s);
                            }
                            break;
                        case 5:
                            System.out.print("Anunciante:");
                            String anunciante = input.readLine();

                            List<String> adsByAdvertiser= obj.listByAdvertiser(anunciante);
                            
                            for(var s : adsByAdvertiser){
                                System.out.println(s);
                            }
                            break;
                        case 6:
                            System.out.print("Aid:");
                            String aid = input.readLine();

                            String details = obj.getDetails(aid);
                            
                            System.out.println(details);
                            break;
                        case 7:
                            System.out.print("Aid:");
                            String aid_M = input.readLine();

                            System.out.print("Mensagem:");
                            String mensagem = input.readLine();

                            obj.sendMessage(aid_M, mensagem);
                            break;
                        case 8:
                            System.out.print("Aid:");
                            String aid_C = input.readLine();

                            List<String> messages= obj.consultMessages(aid_C);
                            
                            for(var m : messages){
                                System.out.println(m);
                            }
                            break;
                        case 9:
                            return;
                        default:
                            System.out.println("Introduza um valor possivel (1-8).");
                            break;
                    }
                } catch (Exception e) {
                    System.out.println("Tem que introduzir um numero.");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        System.out.println("Concluido.");
    }

}
