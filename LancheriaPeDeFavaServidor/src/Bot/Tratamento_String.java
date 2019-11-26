package Bot;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import org.apache.commons.lang.StringEscapeUtils;

public class Tratamento_String {

    //Variaveis que vão receber as diferentes partes da String maior
    private int update_id;
    private int user_id;
    private String nome;
    private String data;
    private String hora;
    private String mensagem;
    private String str;

    /**
     * Sobrecarca para criação de classe vazia
     */
    public Tratamento_String() {
    }

    /**
     * Recebe a String do Bot para separar o update_id, user_id, nome, data e a
     * menssagem
     *
     * @param str
     */
    public Tratamento_String(String str) {
        //Recebe a string inteira do bot
        this.str = str;
        //Faz a separação dos elementos encontrados na String
        json();
    }

    //Getters
    public int getUpdate_id() {
        return update_id;
    }

    public String getMensagem() {
        return mensagem;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getNome() {
        return nome;
    }

    public String getData() {
        return data;
    }

    public String getHora() {
        return hora;
    }

    /**
     * Metodo que faz o corte das Strings menores dentro da String maior
     */
    private void json() {
        //Corta o update_id de dentro da string total
        //Retorna um valor refenre a posicao do item pesquisado
        int pos1 = str.indexOf("\"update_id\":");
        //Copia um pedaco da String maior usando como referencia o valor pego anteriormente, e em seguida transforma em inteiro
        this.update_id = Integer.parseInt(str.substring(pos1 + 12, pos1 + 21));
        System.out.println("update_id: " + this.update_id);
        ////////////////////////////////////////////////////////////////////////
        //Corta o user_id de dentro da string total, utilizando o mesmo metodo anterior, porem com 2 referencias de posicoes
        pos1 = str.indexOf("\"from\":{\"id\":");
        int pos2 = str.indexOf(",\"is_bot\":");
        this.user_id = Integer.parseInt(str.substring(pos1 + 13, pos2));
        System.out.println("user_id: " + this.user_id);
        ////////////////////////////////////////////////////////////////////////
        //Corta a data de dentro da string total, utilizando o mesmo metodo anterior
        pos1 = str.indexOf(",\"date\":");
        pos2 = str.indexOf(",\"text\":\"");
        //Recorta a data informada na String maior que esta em formato Epoch time
        long dt = Integer.parseInt(str.substring(pos1 + 8, pos2));
        //O valor da data em epoch é multiplicado por 1000 para ser feito a converção
        Date date = new Date(dt * 1000);
        //Definição do formato que sera apresentada a data final
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy;HH:mm:ss");
        //Converção do horario obtido para o fuzo horario do Brasil
        format.setTimeZone(TimeZone.getTimeZone("America/Bahia"));
        //Formata a data para o horario Brasileiro
        String formatted = format.format(date);
        //Separa a data e a hora em Strings diferentes
        String[] dh = formatted.split(";");
        this.data = dh[0];
        this.hora = dh[1];
        System.out.println("Data: " + this.data + "\nHora: " + this.hora);
        ////////////////////////////////////////////////////////////////////////
        //Corta o nome de dentro da string total, utilizando o mesmo metodo anterior
        pos1 = str.indexOf("\"first_name\":\"");
        pos2 = str.indexOf("\",\"last_name\":\"");
        this.nome = str.substring(pos1 + 14, pos2);
        pos1 = str.indexOf("\",\"lang");
        this.nome += " " + str.substring(pos2 + 15, pos1);
        System.out.println("Nome: " + this.nome);
        ////////////////////////////////////////////////////////////////////
        //Corta a mensagem de dentro da string total, utilizando o mesmo metodo anterior
        pos1 = str.indexOf("\"text\":\"");
        if (str.contains("\"}}")) {
            pos2 = str.indexOf("\"}}");
        }else{
            pos2 = str.indexOf("\",\"ent");
        }
//        pos2 = str.indexOf("\",\"entities\":[{");
        this.mensagem = (str.substring(pos1 + 8, pos2));
        this.mensagem = StringEscapeUtils.unescapeJava(this.mensagem);
        this.mensagem = this.mensagem.toLowerCase();
        System.out.println("Mensagem: " + this.mensagem);


    }

}
