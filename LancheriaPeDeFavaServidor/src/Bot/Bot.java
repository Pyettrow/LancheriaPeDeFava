package Bot;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Bot {

    private String token;//Variavel que tera o Token do bot

    public Bot(String token) {
        this.token = token;// Inicialização da variavel Token que vai receber por parametro o Token do Bot
    }
    
    /**
     * Metodo que recebe a String gerada pelo bot após a mensagem ser escrita no Telegran.
     * Faz a chamada da classe para recortar a String inteira.
     * Faz a chamada do Metodo que responde a mensagem.
     */
    public Tratamento_String recebeMensagem() {
        //Criação do link de recepção das mensagens do Telegran
        String url = String.format("https://api.telegram.org/bot%s/getUpdates", token);
        //Criação da classe resposnavel por selecionar as partes da String inteira
        Tratamento_String t;

        try {
            //Conecção com o link do Telegran
            URL telegram = new URL(url);
            URLConnection con = telegram.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            
            //Leitura de uma String de mensagem por vez
            String linha = in.readLine() + in.readLine();
            
            //If responsavel por executar ações somente quando tem mensagens
            if (!linha.equals("{\"ok\":true,\"result\":[]}null")) {
                //Alocação da classe e envio da String para o tratamento
                t = new Tratamento_String(linha);
                
                //Coneção com o site do Telegran novamente para apagar a mensagem que ja foi executada
                url = String.format("https://api.telegram.org/bot%s/getUpdates?offset=%s", token, t.getUpdate_id() + 1);
                telegram = new URL(url);
                con = telegram.openConnection();
                in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                
                //Retorna o tratamento de String
                return t;
                
            }

        } catch (MalformedURLException ex) {
            Logger.getLogger(Bot.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Bot.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Caso não tenha recebido novas mensagens retorna null
        return null;
    }

    /**
     * Metodo utilizado para enviar uma mensagem passada por parametro para o 
     * id de usuario que tembem é passado por parametro.
     * @param idDest
     * @param msg 
     */
    public void enviarMensagem(String idDest, String msg) {
        //Criação do link de envio da mensagem
        String s = String.format("https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s", token, idDest, msg);

        try {
            //Conecção com o link do Telegran
            URL telegram = new URL(s);
            URLConnection tc = telegram.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(tc.getInputStream()));

        } catch (MalformedURLException ex) {
            Logger.getLogger(Bot.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Bot.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
