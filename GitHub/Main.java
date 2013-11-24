import java.util.*;

public class Main {
  // Hash utilizado para armazenar os pares de chave valor, sendo a chave a palavra e o valor o n�mero da palavra no
  // dicionario
  public static Hashtable <String, Integer> dicionarioHashtable; 
  
  public static void main (String args[])
  {
    Scanner scanner = new Scanner(System.in);
    
    // Cria hash com as palavras do dicion�rio
    dicionarioHashtable = new Hashtable<String, Integer>();
     
      while (scanner.hasNext()) 
      {
        int numeroDePalavrasDicionario = 0;
        String linha = scanner.nextLine();
        
        if (linha.equals("#"))
        {
          String textoCifrado = scanner.nextLine();
          decriptaTextoPorForcaBruta(textoCifrado);
          break;
        }
        else 
        {
          // Etapa 1 - Adiciona palavras no dicion�rio
          dicionarioHashtable.put(linha, numeroDePalavrasDicionario);
          numeroDePalavrasDicionario++;
        }
      }
    }
  
  // Etapa 2 - M�todo que tenta por for�a bruta a descoberta da chave
  public static void decriptaTextoPorForcaBruta (String textoCifrado)
  {
    int encontrouMaisPalavrasNoDicionario = 0;
    int nrPalavrasEncontradasNoDicionario = 0;
    String textoDecriptado = "";
    String textoClaro = "";
    // M�todo que testa todas as chaves poss�veis (1 a 26)
    int chave = 26;
    
    while(chave > 0)
    {
      nrPalavrasEncontradasNoDicionario = 0;
      // Chama m�todo que vai obter o texto claro usando a chave passada como par�metro
      textoClaro = decriptaTexto(textoCifrado, chave);
      
      String[] palavras = textoClaro.split(" ");
      
      // Conta quantas coincid�ncias no dicion�rio o texto que foi decriptografado cont�m
      for (int i = 0; i < palavras.length; i++) 
      {
        if (dicionarioHashtable.containsKey(palavras[i]))
        {
          nrPalavrasEncontradasNoDicionario++;
        }
      }
      
      // Se for a melhor op��o at� o momento considera o texto como o decriptado
      if (nrPalavrasEncontradasNoDicionario > encontrouMaisPalavrasNoDicionario)
      {
        textoDecriptado = textoClaro;
      }      
      chave--;
    }
    
    imprimeTextoDecriptado(textoDecriptado);
  }
  
  // M�todo que implementa o algoritmo de decriptografia utilizando o texto cifrado e a chave
  public static String decriptaTexto (String textoCifrado, int chave)
  {
    String textoDecifrado = "";
    char letra;
    
    for (int i = 0; i < textoCifrado.length(); i++) 
    {
      int caractere = -1;
      
      // Consideramos que o caractere em branco tenha valor 64
      if (Character.toString(textoCifrado.charAt(i)).equals(" "))
      {
        caractere = 64;
      }
      else 
        caractere = textoCifrado.charAt(i);
      
      // Defini��o de resto para o Java � diferente da defini��o matem�tica
      int charToInt = (caractere - 'A' + 1 - chave)%27;
      
      // Se a % b for negativo, e se b for positivo, ent�o use para o valor do resto b + a % b
      if (charToInt < 0)
        charToInt = 27 + charToInt;
      
      // Transforma para char de volta 
      int intToChar = (charToInt - 1 + 'A');
      
      // Se valor for 64, ent�o o caractere � um espa�o
      if (intToChar == 64)
      {
        letra = ' ';
      }
      else
      {
        letra = (char) (intToChar);
      }
      textoDecifrado = textoDecifrado + Character.toString(letra);
    }
    
    return textoDecifrado;
  }
  
  // Etapa 3 - Imprime cada linha com no m�ximo 60 caracteres e sem espa�os brancos no come�o e no final da linha 
  public static void imprimeTextoDecriptado (String textoDecriptado)
  {
    String linha = "";
    String[] palavras = textoDecriptado.split(" ");
    
    for (int k = 0; k < palavras.length ; k++) 
    {
      String linhaComMaisUmaPalavra = "";
      
      // Tenta adicionar mais uma palavra na linha
      linhaComMaisUmaPalavra = linha + " " + palavras[k];    
      
      // Olha se n�o ultrapassa o tamanho m�ximo
      if (linhaComMaisUmaPalavra.length() <= 60) 
      {
        linha = linhaComMaisUmaPalavra;
      }
      // Se ultrapassar, imprime o que j� tinha retirando os espa�os em branco 
      // e grava a nova linha como sendo a palavra (se ela n�o for espa�o em branco)
      else 
      {
        System.out.println (linha.trim());
        if (!palavras[k].equals(" "))
        {
          linha = palavras[k];
        }
        continue;
      }
    }
    // Imprime a linha final (se ela tiver menos que 60 caracteres
    System.out.println (linha.trim()); 
} 
  
}