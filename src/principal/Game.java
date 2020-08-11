package principal;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Game extends JFrame implements KeyListener {
    int cenario = 0;
    BufferedImage backBuffer;
    int FPS = 30;
    int janelaW = 500;
    int janelaH = 500;
    int xlata   = 210;
    int ylata   = 420;
    int xobj    = 0;
    int xobj2   = 200;
    int xobj3   = 400;
    int yobj    = 14;
    int vidas   = 5;
    int noDelays = 0;
    int objtroca = 1;
    int contador = 0; 
    int vposicaoatual = 14;
    char teclaPressionada; 
    
    ImageIcon cenas[];	//VETOR DE IMAGENS,
	int x;					//AQUI É A COORDENADA X
	int y;					//AQUI É A COORDENADA Y
	int largura;			//LARGURA DA IMAGEM, CASO QUEIRA DEFINIR UMA
	int altura;				//ALTURA DA IMAGEM, CASO QUEIRA DEFINIR UMA
	int cena = 0;			//O INDICE DA CENA DA NOSSA SPRITE ANIMADA
	int controlaVelocidade = 0;
	int velocidade = 150;
    
    //AQUI DECLARAMOS O NOSSO MENU COM:
    //4 itens, coordenadas x e y = 100, a ativo = true
    //agora olhe esse método abaixo cenarios()
    Menu menuPrincipal = new Menu(4, 100, 100, true);
    ImageIcon fundo = new ImageIcon("src/jogo/Fundo.png");
    ImageIcon lata4 = new ImageIcon("src/jogo/lata4.png");
  
    
    
    //ESSA É A NOSSA SPRITE!
    //VERIFIQUE AGORA O MÉTODO inicializar()
    //LÁ VAMOS INICIAR AS IMAGENS QUE VAMOS USAR NESSA SPRITE!
    //DEPOIS VERIFIQUE O MÉTODO desenharGraficos()
    //VEJA QUE ESSA SPRITE TEM 3 CENAS!!!
    Sprite objeto1 = new Sprite(4, 200, 300);
    Sprite objeto2 = new Sprite(4, 200, 300);
    Sprite objeto3 = new Sprite(4, 200, 300);
    
   
    //esse método vai desenhar na tela alguns possíveis cenários do nosso game
    //lá em Menu.java cenario foi definido como -1
    //se cenario == 0 muda a cor do fundo e mostra um texto
    //se cenario == 1 muda a cor do fundo e mostra um texto
    //se cenario == n muda a cor do fundo e mostra um texto...
    //agora obser o nosso método desenharGraficos()
   public void cenarios() {
        Graphics bbg = backBuffer.getGraphics();
        bbg.setFont(new Font("Arial", Font.BOLD, 20));
        
        //aqui você pode escolher o que irá aparecer caso o usuario escolha essa item do menu!
        if (menuPrincipal.cenario == 0) {
            jogar();
        }
        if (menuPrincipal.cenario == 1) {
            bbg.setColor(new Color(100, 255, 100));
            bbg.fillRect(0, 0, janelaW, janelaH);
            bbg.setColor(Color.BLACK);
            bbg.drawString("Você escolheu Opções", 100, 200);
        }
        if (menuPrincipal.cenario == 2) {
            bbg.setColor(new Color(100, 100, 255));
            bbg.fillRect(0, 0, janelaW, janelaH);
            bbg.setColor(Color.BLACK);
            bbg.drawString("Você escolheu Ajuda", 100, 200);
        }
        if (menuPrincipal.cenario == 3) {
            System.exit(0);//esse comando fecha o nosso game!
        }
    }

    public void atualizar() {

    }

    public void desenharGraficos() {
        Graphics g = getGraphics();	//ISSO JÁ ESTAVA AQUI
        Graphics bbg = backBuffer.getGraphics();//ISSO TAMBÉM JÁ ESTAVA AQUI...
        bbg.setColor(Color.WHITE);
        bbg.fillRect(0, 0, janelaW, janelaH);	//PINTA O FUNDO COM UM QUADRADO BRANCO

        menuPrincipal.desenharMenu();//isso desenhará nosso menu
        cenarios();//isso irá desenhar os cenários que escolhermos no menu
        //agora observe o método inicializar()

        
        
        //==================================================================================	
        g.drawImage(backBuffer, 0, 0, this);//OBS: ISSO DEVE FICAR SEMPRE NO FINAL!
    }
    
    public void jogar() {
       Graphics bbg = backBuffer.getGraphics(); 
       bbg.setColor(Color.BLACK);	//muda a cor!
       bbg.setFont(new Font("Comic Sans MS", Font.BOLD, 20));// definimos a fonte, o estilo negrito(bold) e o tamanho
       bbg.fillRect(0, 0, janelaW, janelaH);
       bbg.drawImage(fundo.getImage(), 0, 0, this);

   
      
       bbg.drawString("PONTOS: "+yobj, 10, 50);
       bbg.drawString("VIDAS: "+vidas, 10, 90);
       bbg.drawString("Contador: "+contador, 10, 140);
       
       
       bbg.drawImage(lata4.getImage(), xlata, ylata, 80, 80, this);
       
       
   
      TrocarObjetos(); 
    }

    public void inicializar() {
        setTitle("Smash Trash");
        setSize(janelaW, janelaH);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);
        setVisible(true);
        backBuffer = new BufferedImage(janelaW, janelaH, BufferedImage.TYPE_INT_RGB);

        //AQUI ESTAMOS ADICIONANDO UM ESCUTADOR DE TECLAS
        addKeyListener(this);

        //aqui definimos o texto de cada item do nosso menu
        menuPrincipal.itens[0] = "Jogar";
        menuPrincipal.itens[1] = "Opções";
        menuPrincipal.itens[2] = "Ajuda";
        menuPrincipal.itens[3] = "Sair";
        //aqui fazemos o método desenhaMenu() que fica lá em Menu.java
        //desenhar no nosso buffer
        //.. agora para finalizar observe o método de evento keyPressed() mais abaixo...
        menuPrincipal.bbg = backBuffer.getGraphics();
        
         //QUI CARREGAMOS AS IMAGENS DE NOSSA SPIRTE!!!!!!!
        //PARA O VETOR DE ImageIcon[] !!!
        objeto1.cenas[0] = new ImageIcon("src/jogo/objeto1.png");
        objeto1.cenas[1] = new ImageIcon("src/jogo/objeto2.png");
        objeto1.cenas[2] = new ImageIcon("src/jogo/objeto3.png");
        objeto1.cenas[3] = new ImageIcon("src/jogo/objeto4.png");
        objeto1.largura = 30;	//LARGURA DO VILÃO
        objeto1.altura = 30;	//ALTURA DO VILÃO , mas não vou usar isso agora.
        
        objeto2.cenas[0] = new ImageIcon("src/jogo/objeto1.png");
        objeto2.cenas[1] = new ImageIcon("src/jogo/objeto2.png");
        objeto2.cenas[2] = new ImageIcon("src/jogo/objeto3.png");
        objeto2.cenas[3] = new ImageIcon("src/jogo/objeto4.png");
        objeto2.largura = 30;	//LARGURA DO VILÃO
        objeto1.altura = 30;	//ALTURA DO VILÃO , mas não vou usar isso agora.
        
        objeto3.cenas[0] = new ImageIcon("src/jogo/objeto1.png");
        objeto3.cenas[1] = new ImageIcon("src/jogo/objeto2.png");
        objeto3.cenas[2] = new ImageIcon("src/jogo/objeto3.png");
        objeto3.cenas[3] = new ImageIcon("src/jogo/objeto4.png");
        objeto3.largura = 30;	//LARGURA DO VILÃO
        objeto3.altura = 30;	//ALTURA DO VILÃO , mas não vou usar isso agora.
    }

    public void run() {
        inicializar();
        while (true) {
            try {
                atualizar();
                desenharGraficos();

                Thread.sleep(1000 / FPS);
            } catch (Exception e) {
                System.out.println("Thread interrompida!");
                break;
            }
        }
    }

    public void keyPressed(KeyEvent e) {
        //aqui, chamamos os métodos que irá controlar o menu pelo teclado
        menuPrincipal.controlar(e);//esse controla o menu
        menuPrincipal.voltarAoMenu(e);//esse faz voltar para o menu quando pressionarmos "Esc"
        
        
        //SE A TECLA PRESSIONADA FOR LEFT = ESQUERDA xlata diminue 10
        if (e.getKeyCode() == e.VK_LEFT) {
            xlata -= 10;
        }
        //SE A TECLA PRESSIONADA FOR RIGHT = DIREITA xlata aumenta 10
        if (e.getKeyCode() == e.VK_RIGHT) {
            xlata += 10;
        }
        
        //TRATAMENTO PARA A LATA NÃO ULTRAPASSAR A BORDA.
        if (xlata == 430) {
            xlata = 420;   
        }
       
        if (xlata == -20) {
            xlata = -10;   
        } 
    }
    
    public void TrocarObjetos() {
    Graphics bbg = backBuffer.getGraphics();
       
    //AQUI TO DESENHANDO A O NOSSO PERSONAGEM
    //VEJA QUE NOSSO vilão tem tudo que agente precisa!
    //SUAS COORDENADAS, LARGURA, ALTURA, E AS IMAGENS!!!
    bbg.drawImage(objeto1.cenas[objeto1.cena].getImage(), xobj, yobj, 50, 50, this);
    objeto1.animarMaisLento(); //AQUI CHAMEI O MÉTODO ANIMAR MAIS LENTO
    
    bbg.drawImage(objeto2.cenas[objeto1.cena].getImage(), xobj2, yobj, 50, 50, this);
    objeto2.animarMaisLento(); //AQUI CHAMEI O MÉTODO ANIMAR MAIS LENTO
    
    bbg.drawImage(objeto3.cenas[objeto1.cena].getImage(), xobj3, yobj, 50, 50, this);
    objeto3.animarMaisLento(); //AQUI CHAMEI O MÉTODO ANIMAR MAIS LENTO    
     CairObjetos();  
    }
    
        
    public void CairObjetos() {
       
      if (contador == 4) {
          objtroca += 1;       
      } 
      
       //instância um objeto da classe Random usando o construtor básico
        Random gerador = new Random();
        Random gerador2 = new Random();
        Random gerador3 = new Random();
      
   
    //faz os objestos se movimentarem aleatoriamente utilizandon   
    while (vposicaoatual != yobj) {
       yobj += 1;
       }   
     
    if (yobj != 454) {
       vposicaoatual += 5;     
       }
    else
       {
       //imprime sequência de 10 números inteiros aleatórios entre 0 e 450
        for (int i = 0; i < 10; i++) {
            xobj  =  gerador.nextInt(450);
            xobj2 =  gerador2.nextInt(450);  
            xobj3 =  gerador3.nextInt(450);           
         }
        
       yobj = 14;   
       vposicaoatual = 14;  
       }  
    
    //se caso o objeto atingir o chao ele volta para cima e cai novamente.
    if (xobj == 450)  {
       xobj = 0;   
    }   
    
    //se algum dos objetos atingir o chao perde uma vida.
    if (yobj == 449) {
       vidas -= 1; 
       contador += 1; 
    }
    
    
    //Troca de objeto a cada 10 segundos 
    
   // for (int i = 0; i < 10; i++) {
     //  TrocarObjeto("objeto"+Integer.toString(i));    
    //}
    
    
    
    }
    
    public void keyReleased(KeyEvent e) {

    }

    public void keyTyped(KeyEvent e) {

    }
}
