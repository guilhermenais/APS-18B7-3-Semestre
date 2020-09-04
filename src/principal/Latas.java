package principal;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;


import javax.swing.ImageIcon;

public class Latas {

	private int x, y;
	private int dx, dy;
	private int altura, largura;
        int latatroca;
	private boolean isVisivel;
        ImageIcon cenas[];	//VETOR DE IMAGENS
        
        Sprite latas1 = new Sprite(4, 200, 300);

	private Image imagem;
	
	public Latas(){
            
               latas1.cenas[0] = new ImageIcon("src/jogo/lata1.png");
               latas1.cenas[1] = new ImageIcon("src/jogo/lata2.png");
               latas1.cenas[2] = new ImageIcon("src/jogo/lata3.png");
               latas1.cenas[3] = new ImageIcon("src/jogo/lata4.png");
		
		ImageIcon referencia = new ImageIcon("src/jogo/lata1.png");
		imagem = referencia.getImage();
		
		altura = 100;//imagem.getHeight(100);
		largura = 100;//imagem.getWidth(100);
		
		
		this.x = 210;
		this.y = 413;
		
	}
	
	public void mexer(){
            
            
            //TRATAMENTO PARA A LATA NÃO ULTRAPASSAR A BORDA.
		x += dx; // 1 e 462
        
		if(this.x < 1){
			x = 1;
		}
		
		if(this.x > 420){
			x = 410;
		}
			
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {	
		return y;
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
	
	

	public Rectangle getBounds(){
		return new Rectangle(x, y, largura, altura);
	}
	
	
	public void keyPressed(KeyEvent tecla){
		
		int codigo = tecla.getKeyCode();
			
            //SE A TECLA PRESSIONADA FOR LEFT = ESQUERDA xlata diminue 10    
		if(codigo == KeyEvent.VK_LEFT){
			dx -= 10;
		}
		
            //SE A TECLA PRESSIONADA FOR RIGHT = DIREITA xlata aumenta 10    
		if(codigo == KeyEvent.VK_RIGHT){
			dx += 10;
		}
                
        //SE A TECLA PRESSIONADA FOR UP = CIMA TROCA A LATA
        if(codigo == KeyEvent.VK_UP){
            latatroca += 1;
        }

        //SE A TECLA PRESSIONADA FOR DOWN = BAIXO TROCA A LATA
        if(codigo == KeyEvent.VK_DOWN){
           latatroca -= 1;
       }
       
        // SE A LATA TROCADA FOR A LATA 4 E O USUARIO PRESSIONAR CIMA VOLTA PARA A LATA 3 
        if (latatroca > 3) {
            latatroca = 0;
        }

        // SE A LATA TROCADA FOR A LATA 1 E O USUARIO PRESSIONAR BAIXO VOLTA PARA A LATA 1 
        if (latatroca < 0) {
            latatroca = 0;
        }
        
		
	}
	
	public void keyReleased(KeyEvent tecla){
		
		int codigo = tecla.getKeyCode();
		
		if(codigo == KeyEvent.VK_LEFT){
			dx = 0;
		}
		
		if(codigo == KeyEvent.VK_RIGHT){
			dx = 0;
		}
		
	}

   
	
	
}
