package Jogo;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Game extends JFrame implements KeyListener{

    BufferedImage backBuffer;
    int FPS = 30;
    int janelaW = 500;
    int janelaH = 500;
    
    principal.Menu menuPrincipal = new principal.Menu(4, 100, 100, true);

    //ImageIcon lata = new ImageIcon("src/jogo/Lata1.png");
    ImageIcon fundo = new ImageIcon("src/jogo/fundo.png");

    public void atualizar() {

    }

    public void desenharGraficos() {
        Graphics g = getGraphics();	//ISSO JÁ ESTAVA AQUI
        Graphics bbg = backBuffer.getGraphics();//ISSO TAMBÉM JÁ ESTAVA AQUI...
        //AQUI VAMOS MANDAR DESENHAR ALGUNS IMAGENS NA TELA
        bbg.drawImage(fundo.getImage(), 0, 0, this);//QUI DESENHAMOS O FUNDO
        //AS DIMENSÕES ORIGINAIS DO FUNDO SÃO: 500X500 QUE É O TAMANHO DA NOSSA TELA!

        //DESENHANDO A ABELHA NAS COORDENADAS X=200 e Y=200
       // bbg.drawImage(lata.getImage(), 200, 200, this); //aqui fica com as dimensões originais!
        //AQUI DEFINI A LARGURA E ALTURA DA IMAGEM
       // bbg.drawImage(lata.getImage(), 100, 100, 40, 40, this); //aqui não fica com as dimensões originais!

        //AQUI VAMOS ROTACIONAR A IMAGEM EM 45º
        //PARA ISSO VAMOS USAR OUTRA VARIAVEL DO TIPO Graphics2D
        //SE NÃO usarmos outra variável tudo que tá no buffer será rotacionado também!
        Graphics2D bbg2d = (Graphics2D) backBuffer.getGraphics();//AQIU É NOSSA OUTRA VÁRIAVEL!
        //para rotacionar primeiro define o eixo (isso desloca a imagem, então depois temos que desfazer)
        //vou por o exito no meio, então é só dividir a altura e largura da imagem por 2
       // bbg2d.translate((lata.getIconWidth() / 2) + 300, (lata.getIconHeight() / 2) + 300); //tem que desfazer isso depois que a imagem será deslocada!
        bbg2d.rotate(45 * (Math.PI / 180)); //vamos calcular 45º
        //bbg2d.translate(-((lata.getIconWidth() / 2) + 300), -((lata.getIconHeight() / 2) + 300));// agora desfaz o négocio do exito!!!
        //veja que eu passei os mesmos valores só que negativos, ovserve o sinal de "-" menos
        //bbg2d.drawImage(lata.getImage(), 300, 300, this); //desenha a imagem

        //==================================================================================	
        g.drawImage(backBuffer, 0, 0, this);//OBS: ISSO DEVE FICAR SEMPRE NO FINAL!
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
        
        //bbg2d = backBuffer.getGraphics();
    }

    public void run() {
        inicializar();
        while (true) {
            try {
                atualizar();
                desenharGraficos();

                Thread.sleep(1000 / FPS);
            } catch (Exception e) {
                System.out.println("Parou aqui!");
                break;
            }
        }
    }
 public void keyPressed(KeyEvent e) {
        //aqui, chamamos os métodos que irá controlar o menu pelo teclado
        menuPrincipal.controlar(e);//esse controla o menu
        menuPrincipal.voltarAoMenu(e);//esse faz voltar para o menu quando pressionarmos "Esc"
    }

    public void keyReleased(KeyEvent e) {

    }

    public void keyTyped(KeyEvent e) {

    }    

    public void setLocation(int i, int i0, int janelaW, int janelaH) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
