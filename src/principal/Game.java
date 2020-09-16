package principal;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Game extends JFrame implements KeyListener {

    private Latas Latas;
    private Objetos Objetos;
    int cenario = -1;
    BufferedImage backBuffer;
    int FPS = 30;
    int janelaW = 500;
    int janelaH = 500;

    int vidas = 5;
    int noDelays = 0;
    int objtroca = 0;
    int pontos = 0;
    boolean ingame = true;

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
    ImageIcon fundo = new ImageIcon("src/jogo/fundomenu.png");
    ImageIcon fundojogo = new ImageIcon("src/jogo/fundojogo.png");

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

    public void desenharGraficos() {
        Graphics g = getGraphics();	//ISSO JÁ ESTAVA AQUI
        Graphics bbg = backBuffer.getGraphics();//ISSO TAMBÉM JÁ ESTAVA AQUI...
        bbg.setColor(Color.WHITE);
        bbg.drawImage(fundo.getImage(), 0, 0, this);

        menuPrincipal.desenharMenu();//isso desenhará nosso menu
        cenarios();//isso irá desenhar os cenários que escolhermos no menu
        //agora observe o método inicializar()

        //==================================================================================	
        g.drawImage(backBuffer, 0, 0, this);//OBS: ISSO DEVE FICAR SEMPRE NO FINAL!
    }

    public void jogar() {
        Graphics g = getGraphics();
        Graphics bbg = backBuffer.getGraphics();

        if (ingame == true) {

            bbg.setColor(Color.BLACK);	//muda a cor!
            bbg.setFont(new Font("Comic Sans MS", Font.BOLD, 20));// definimos a fonte, o estilo negrito(bold) e o tamanho
            bbg.fillRect(0, 0, janelaW, janelaH);

            bbg.drawImage(fundojogo.getImage(), 0, 0, this);

            bbg.drawString("PONTOS: " + pontos, 10, 50);
            bbg.drawString("VIDAS: " + vidas, 10, 90);
            
            TrocarObjetos();

        } else {
            pontos = 0;
            vidas = 5;
            ImageIcon fimJogo = new ImageIcon("src/jogo/gameover.png");

            bbg.drawImage(fimJogo.getImage(), 0, 0, null);
        }

    }

    public void inicializar() {
        ImageIcon imagemTituloJanela = new ImageIcon("src/jogo/icon.png");
        setIconImage(imagemTituloJanela.getImage());
        setTitle("Smash Trash");
        setSize(janelaW, janelaH);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);
        setVisible(true);
        backBuffer = new BufferedImage(janelaW, janelaH, BufferedImage.TYPE_INT_RGB);
        Latas = new Latas();
        Objetos = new Objetos();

        //AQUI ESTAMOS ADICIONANDO UM ESCUTADOR DE TECLAS
        addKeyListener(new TecladoAdapter());
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
    }

    public void run() {
        inicializar();
        while (true) {
            try {
                desenharGraficos();

                Thread.sleep(1000 / FPS);
            } catch (Exception e) {
                System.out.println("Thread interrompida!");
                break;
            }
        }
    }

    public void keyPressed(KeyEvent e) {
        Graphics bbg = backBuffer.getGraphics();
        //aqui, chamamos os métodos que irá controlar o menu pelo teclado
        menuPrincipal.controlar(e);//esse controla o menu
        menuPrincipal.voltarAoMenu(e);//esse faz voltar para o menu quando pressionarmos "Esc"
        ingame = true;
    }

    public void TrocarObjetos() {
        Graphics bbg = backBuffer.getGraphics();

        bbg.drawImage(Latas.latas1.cenas[Latas.latatroca].getImage(), Latas.getX(), Latas.getY(), Latas.getAltura(), Latas.getLargura(), this);
        Latas.mexer();

        bbg.drawImage(Objetos.objeto1.cenas[Objetos.objeto].getImage(), Objetos.getX(), Objetos.getY(), Objetos.getAltura(), Objetos.getLargura(), this);
        Objetos.mexer();

        checarColisoes();
    }

    public void checarColisoes() {
        Graphics g = getGraphics();
        Graphics bbg = backBuffer.getGraphics();

        Rectangle formaLata = Latas.getBounds();
        Rectangle formaObj = Objetos.getBounds();

        if (formaObj.intersects(formaLata)) {
            Objetos.Colisao = true;

            if (Latas.latatroca == Objetos.objeto) {
                pontos += 1;
            } else {
                vidas -= 1;
            }
        } else {
            Objetos.Colisao = false;
        }

        if (Objetos.getY() == 449) {
            vidas -= 1;
        }

        if (vidas == 0) {
            ingame = false;

            ImageIcon fimJogo = new ImageIcon("src/jogo/gameover.png");

            bbg.drawImage(fimJogo.getImage(), 0, 0, this);
        }

    }

    private class TecladoAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                Latas = new Latas();

            }

            Latas.keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            Latas.keyReleased(e);
        }

    }

    public void keyReleased(KeyEvent e) {

    }

    public void keyTyped(KeyEvent e) {

    }
}
