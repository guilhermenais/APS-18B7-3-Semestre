package principal;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

public class Principal extends JFrame implements KeyListener {

    BufferedImage backBuffer;
    int FPS = 30;
    int janelaW = 500;
    int janelaH = 550;

    int janelaX = 100;
    int janelaY = 200;
    int filhaX = 600;
    int filhaY = 200;
    

  
    //esse método vai desenhar na tela alguns possíveis cenários do nosso game
    //lá em Menu.java cenario foi definido como -1
    //se cenario == 0 muda a cor do fundo e mostra um texto
    //se cenario == 1 muda a cor do fundo e mostra um texto
    //se cenario == n muda a cor do fundo e mostra um texto...   
    //agora obter o nosso método desenharGraficos()
    public void cenarios() {
        Graphics bbg = backBuffer.getGraphics();
        bbg.setFont(new Font("Arial", Font.BOLD, 20));
        Game obj03 = new Game();
        obj03.setLocation(filhaX, filhaY);
        obj03.run();
        
        bbg.drawImage(backBuffer, 0, 0, this);
          
    }

    public void atualizar() {

    }

    public void desenharGraficos() {
        Graphics g = getGraphics();	//ISSO JÁ ESTAVA AQUI
        Graphics bbg = backBuffer.getGraphics();//ISSO TAMBÉM JÁ ESTAVA AQUI...
        bbg.setColor(Color.BLUE);
        bbg.fillRect(0, 0, janelaW, janelaH);	//PINTA O FUNDO COM UM QUADRADO BRANCO

        cenarios();//isso irá desenhar os cenários que escolhermos no menu
        //agora observe o método inicializar()

        //==================================================================================	
        g.drawImage(backBuffer, 0, 0, this);//OBS: ISSO DEVE FICAR SEMPRE NO FINAL!
    }

    public void inicializar() {
        backBuffer = new BufferedImage(janelaW, janelaH, BufferedImage.TYPE_INT_RGB);

        //AQUI ESTAMOS ADICIONANDO UM ESCUTADOR(OUVINTE) DE TECLAS
        addKeyListener(this);        
    }

    public void run() {
        inicializar();
        while (true) {
            try {
                atualizar();
                desenharGraficos();
                Thread.sleep(1000 / FPS);
            } catch (Exception e) {
                System.out.println("Thread principal interrompida!");
                break;
            }
        }
    }

    public static void main(String[] args) {
        Principal game = new Principal();
        game.run();
    }

    public void keyPressed(KeyEvent e) {
        //aqui, chamamos os métodos que irá controlar o menu pelo teclado
        //menuPrincipal.controlar(e);//esse controla o menu
        //menuPrincipal.voltarAoMenu(e);//esse faz voltar para o menu quando pressionarmos "Esc"
    }

    public void keyReleased(KeyEvent e) {

    }

    public void keyTyped(KeyEvent e) {

    }
}
