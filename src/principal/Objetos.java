package principal;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.ImageIcon;

public class Objetos {

    private int x, y;
    private int dx, dy;
    private int altura, largura;
    int latatroca;
    int objeto = 0;
    private boolean isVisivel;
    private int vposicaoatual = 14;
    ImageIcon cenas[];	//VETOR DE IMAGENS

    Sprite objeto1 = new Sprite(4, 200, 300);

    private Image imagem;

    public Objetos() {

        objeto1.cenas[0] = new ImageIcon("src/jogo/objeto1.png");
        objeto1.cenas[1] = new ImageIcon("src/jogo/objeto2.png");
        objeto1.cenas[2] = new ImageIcon("src/jogo/objeto3.png");
        objeto1.cenas[3] = new ImageIcon("src/jogo/objeto4.png");

        ImageIcon referencia = new ImageIcon("src/jogo/objeto1.png");
        imagem = referencia.getImage();

        altura = 50;//imagem.getHeight(100);
        largura = 50;//imagem.getWidth(100);

        this.x = 0;
        this.y = 14;

    }

    public void mexer() {
        //instância um objeto da classe Random usando o construtor básico
        Random gerador = new Random();
        Random TrocaObj = new Random();
        // Random gerador2 = new Random();
        //Random gerador3 = new Random();

        //faz os objestos se movimentarem aleatoriamente utilizando random.   
        while (vposicaoatual != this.y) {
            this.y += 1;
        }

        if (this.y != 454) {
            vposicaoatual += 5;
        } else {
            //imprime sequência de 10 números inteiros aleatórios entre 0 e 450
            for (int i = 0; i < 10; i++) {
                this.x = gerador.nextInt(450);
                //xobj2 = gerador2.nextInt(450);
                //xobj3 = gerador3.nextInt(450);
            }

            this.y = 14;
            vposicaoatual = 14;
        }
        //se caso o objeto atingir o chao ele volta para cima e cai novamente.
        if (this.x == 450) {
            this.x = 0;
        }

       //Troca os objetos aleatoriamente 
        if (vposicaoatual == 14) {
            this.objeto = TrocaObj.nextInt(4);
        }

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getAltura() {
        return altura;
    }

    public int getLargura() {
        return largura;
    }

    public Image getImagem() {
        return imagem;
    }

    public boolean isVisivel() {
        return isVisivel;
    }

    public void setVisivel(boolean isVisivel) {
        this.isVisivel = isVisivel;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, largura, altura);
    }

    public void keyReleased(KeyEvent tecla) {

        int codigo = tecla.getKeyCode();

        if (codigo == KeyEvent.VK_LEFT) {
            dx = 0;
        }

        if (codigo == KeyEvent.VK_RIGHT) {
            dx = 0;
        }

    }

}
