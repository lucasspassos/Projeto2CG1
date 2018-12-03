package Proj;

import com.jogamp.opengl.util.Animator;
import com.jogamp.opengl.util.gl2.GLUT;
import java.awt.event.*;
import javax.swing.*;
import javax.media.opengl.*;
import static javax.media.opengl.GL.GL_TRIANGLES;
import javax.media.opengl.awt.GLJPanel;

public class ProjetoFinal implements GLEventListener,
        KeyListener {

    private GLUT glut;
    GLJPanel canvas = new GLJPanel();
    float pos[] = {0,0,0,1};
    private boolean up;
    private boolean down;
    private boolean fire1;
    private boolean fire2;
    private boolean fire3;
    private boolean fire4;
    
    
    public ProjetoFinal() {
        canvas.addGLEventListener(this);
        
        JFrame frame = new JFrame("Exemplo01");
        frame.setSize(700, 700);
        frame.getContentPane().add(canvas);
        frame.setVisible(true);
        frame.addKeyListener(this);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                new Thread(new Runnable() {
                    public void run() {
                        System.exit(0);
                    }
                }).start();
            }
        });
    }

    public static void main(String[] args) {
        new ProjetoFinal();
    }

    /*
 * Initialize material property, light source, lighting model, and depth
 * buffer.
     */
    @Override
    public void init(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();

        Animator ani = new Animator(drawable);
        ani.start();

        glut = new GLUT();

        //Habilita o teste de profundidade
        gl.glEnable(GL.GL_DEPTH_TEST);
        gl.glEnable(GL2.GL_LIGHTING);
        gl.glEnable(GL2.GL_LIGHT1);
        gl.glEnable(GL2.GL_COLOR_MATERIAL);

        float luzEspecular[] = {1,1,1,-1};
        float luzDifusa[]  ={1f,1f,1f,1.0f};
        float luzAmbiente[]  ={0.1f,0.1f,0.1f,1.0f};
        
        gl.glLightfv(GL2.GL_LIGHT1, 
                     GL2.GL_DIFFUSE, 
                     luzDifusa,0); 

        gl.glLightfv(GL2.GL_LIGHT1, 
                     GL2.GL_AMBIENT,
                     luzAmbiente,0); 
        
        
        gl.glLightfv(GL2.GL_LIGHT1, 
                     GL2.GL_SPECULAR,
                     luzEspecular,0);

    }

    double r = 0;
    float posZ = -10;
    float incZ = 0.1f;
    float pos2=0;
    float f1=0;
    float f2=0;
    float f3=0;
    float f4=0;   

    @Override
    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT
                | GL.GL_DEPTH_BUFFER_BIT);

        gl.glLoadIdentity(); 
        
        pos[2]=100;
                
        gl.glLightfv(GL2.GL_LIGHT1,GL2.GL_POSITION, pos,0);

        gl.glRotated(r+=0.1f,0,1,0);
        gl.glRotated(95,1,0,0);
        
        float matDifusa[]  ={0.9f,0,0,1.0f};
        float materialAmbiente[] ={0.25f,0,0,1};
        float materialEspecular[] ={0,0.3f,0.3f,1};
        float brilho = 40;
        
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK,
                        GL2.GL_DIFFUSE,
                        matDifusa,
                        0);
       
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK,
                        GL2.GL_AMBIENT,
                        materialAmbiente,
                        0); 
        
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK,
                        GL2.GL_SPECULAR,
                        materialEspecular,
                        0);
        
        gl.glMaterialf(GL.GL_FRONT_AND_BACK,
                        GL2.GL_SHININESS,
                        brilho
                        );
        
        
    //trem de pouso
    gl.glPushMatrix();
    
        
        gl.glPushMatrix();
            gl.glTranslated(0,0,pos2);
            gl.glColor3f(0.2f,0.2f,0.2f);
            gl.glTranslated(0,-0.3f,0.1f);
            glut.glutSolidCylinder(0.04f, 0.24f, 20, 10);
            gl.glRotated(90,1,0,0);
            gl.glRotated(90,0,1,0);
            gl.glColor3f(0,0,0.2f);
            gl.glTranslated(0,0.33,-0.07f);
            glut.glutSolidCylinder(0.1f, 0.1f, 20, 10);
            
        gl.glPopMatrix();
        
        gl.glPushMatrix();
            gl.glTranslated(0,0,pos2);
            gl.glColor3f(0.2f,0.2f,0.2f);
            gl.glTranslated(0.2,-1.2f,0.1f);
            glut.glutSolidCylinder(0.04f, 0.24f, 20, 10);
            gl.glRotated(90,1,0,0);
            gl.glRotated(90,0,1,0);
            gl.glColor3f(0,0,0.2f);
            gl.glTranslated(0,0.28,-0.05f);
            glut.glutSolidCylinder(0.1f, 0.1f, 20, 10);
        gl.glPopMatrix();
        
        gl.glPushMatrix();
            gl.glTranslated(0,0,pos2);
            gl.glColor3f(0.2f,0.2f,0.2f);
            gl.glTranslated(-0.2,-1.2f,0.1f);
            glut.glutSolidCylinder(0.04f, 0.24f, 20, 10);
            gl.glRotated(90,1,0,0);
            gl.glRotated(90,0,1,0);
            gl.glColor3f(0,0,0.2f);
            gl.glTranslated(0,0.28,-0.05f);
            glut.glutSolidCylinder(0.1f, 0.1f, 20, 10);
        gl.glPopMatrix();
        
    gl.glPopMatrix();
    //final trem de pouso
     
        
        gl.glPushMatrix();
            //corpo aviao
            gl.glPushMatrix();
            gl.glColor3f(0,0.4f,0);
            gl.glRotated(-90,1,0,0);
            glut.glutSolidCone(0.32f,0.7f, 20, 10);
            gl.glTranslated(0,0,-1.7f);
            glut.glutSolidCylinder(0.32f, 1.7f, 20, 10);
            gl.glTranslated(0,0.2f,1.1);
            gl.glColor3f(0.4f,0,0);
            glut.glutSolidSphere(0.30f, 10, 10);
            
            gl.glTranslated(-0.5f,-0.27f,0);
            missil(gl);

            gl.glPopMatrix();
            //asas
            gl.glPushMatrix();
            gl.glTranslated(0,-1.6,1);
            gl.glColor3f(0.30f,0.16f,0.1f);
            gl.glBegin(GL_TRIANGLES);
            gl.glVertex3f(0, 0, -1);
            gl.glVertex3f(1.2f, 0, -1);
            gl.glVertex3f(0, 1.85f, -1);
            gl.glEnd();
            gl.glPopMatrix();

            gl.glPushMatrix();
            gl.glTranslated(0,-1.6,1);
            gl.glRotated(90, 0, 0, 1);
            gl.glColor3f(0.30f,0.16f,0.1f);
            gl.glBegin(GL_TRIANGLES);
            gl.glVertex3f(0, 0, -1);
            gl.glVertex3f(1.85f, 0, -1);
            gl.glVertex3f(0, 1.2f, -1);
            gl.glEnd();
            gl.glPopMatrix();

            //leme
            gl.glPushMatrix();
            gl.glTranslated(1,-1.7f,0);
            gl.glRotated(90, 0, 1, 0);
            gl.glColor3f(0.30f,0.16f,0.1f);
            gl.glBegin(GL_TRIANGLES);
            gl.glVertex3f(0, 0, -1);
            gl.glVertex3f(0.9f, 0, -1);
            gl.glVertex3f(0, 0.7f, -1);
            gl.glEnd();
            gl.glPopMatrix();
        gl.glPopMatrix();
        
        
      
                
        
        
    //controles
        //Trem de pouso
        if(up && pos2>= -0.30f)
            pos2 -= 0.001f; 
        if(down && pos2 <=0.2f)
            pos2 += 0.001f; 
        
     //Misseis
        if(fire1){
            f1+=0.01f;
            if(f1>10){
                fire1=false;
                f1=0;
            }
                
        }
        if(fire2){
           f2+=0.01f;
           if(f2>10){
                fire2=false;
                f2=0;
            }
        }
        if(fire3){
            f3+=0.01f;
             if(f3>10){
                fire3=false;
                f3=0;
            }
        }
        if(fire4){
           f4+=0.01f;
            if(f4>10){
                fire4=false;
                f4=0;
            }
        }
        
    }
    
    public void missil(GL2 gl)
    {
        gl.glPushMatrix();

         //asa direita
            //missil maior
            
            gl.glPushMatrix();
                gl.glTranslated(0,0,f1);
                gl.glColor3f(0.4f,0,0);
                glut.glutSolidCone(0.06f,0.4f, 20, 10);
                gl.glTranslated(0,0,-1);
                gl.glColor3f(0.3f,0.3f,0.4f);
                glut.glutSolidCylinder(0.06f, 1, 20, 10);
            gl.glPopMatrix();
            
            //missil menor
            gl.glPushMatrix();
            
                gl.glTranslated(-0.4f, 0, -0.7f);
                gl.glTranslated(0,0,f2);
                gl.glColor3f(0.4f,0,0);
                glut.glutSolidCone(0.06f,0.4f, 20, 10);
                gl.glTranslated(0,0,-0.6f);
                gl.glColor3f(0.3f,0.3f,0.4f);
                glut.glutSolidCylinder(0.06f, 0.6f, 20, 10);
            gl.glPopMatrix();
            
        //asa esquerda
            //missil menor
             gl.glPushMatrix();
                gl.glTranslated(1.4f,0,-0.6f);
                gl.glTranslated(0,0,f3);
                gl.glColor3f(0.4f,0,0);
                glut.glutSolidCone(0.06f,0.4f, 20, 10);
                gl.glTranslated(0,0,-0.6f);
                gl.glColor3f(0.3f,0.3f,0.4f);
                glut.glutSolidCylinder(0.06f, 0.6f, 20, 10);
            gl.glPopMatrix();

            //missil maior
            gl.glPushMatrix();
                gl.glTranslated(1, 0, 0);
                gl.glTranslated(0,0,f4);
                gl.glColor3f(0.4f,0,0);
                glut.glutSolidCone(0.06f,0.4f, 20, 10);
                gl.glTranslated(0,0,-1f);
                gl.glColor3f(0.3f,0.3f,0.4f);
                glut.glutSolidCylinder(0.06f, 1, 20, 10);
            gl.glPopMatrix();
            
        gl.glPopMatrix();
    }
    
    public void reshape(GLAutoDrawable drawable, int x, int y, int w, int h) {
        GL2 gl = drawable.getGL().getGL2();

        gl.glViewport(0, 0, w, h);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        if (w <= h) {
            gl.glOrtho(-1.5, 1.5,
                    -1.5 * (float) h / (float) w,//
                    1.5 * (float) h / (float) w,//
                    -10.0, 10.0
            );
        } else {
            gl.glOrtho(-1.5 * (float) w / (float) h, //
                    1.5 * (float) w / (float) h, //
                    -1.5, 1.5, -10.0, 10.0
            );
        }
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            up = true;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            down = true;
        } else if (e.getKeyCode() == KeyEvent.VK_1) {
            fire1 = true;
        } else if (e.getKeyCode() == KeyEvent.VK_2) {
            fire2 = true;
        } else if (e.getKeyCode() == KeyEvent.VK_3) {
            fire3 = true;
        } else if (e.getKeyCode() == KeyEvent.VK_4) {
            fire4 = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            up = false;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            down = false;
        } 
    }

    @Override
    public void dispose(GLAutoDrawable glad) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}