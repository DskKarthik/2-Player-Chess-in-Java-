package chess;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.border.Border;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;


public class Chess implements ActionListener{
    JFrame f;
    JPanel p1,p2,p3;
    JButton b[][],back,reset,selected;
    JLabel lab;
    String turn="white",selectedText;
    boolean crt,set=false,inBack=true,playCompleted;
    ArrayList<JButton> al;
    int a,c,x,y;
    Icon wh_pawn,bl_pawn,wh_king,bl_king,wh_horse,bl_horse,wh_elephant,bl_elephant,wh_camel,bl_camel,wh_queen,bl_queen;
    Icon selectedIcon;
    Border border;
    String audioFilePath = "C:\\Users\\dskk2\\Downloads\\Door Unlock-SoundBible.com-1558114225.wav";
    
    public static void main(String[] args) {
        
        new Chess();
    }
    
    Chess()
    {
        f=new JFrame("Chess");
        b=new JButton[8][8];
        for(int i=0;i<8;i++)
            for(int j=0;j<8;j++)
                b[i][j]=new JButton();
        
        for(int i=0;i<8;i++)
            for(int j=0;j<8;j++)
            {
                if((i+j)%2!=0)
                    b[i][j].setBackground(Color.WHITE);
                else
                    b[i][j].setBackground(Color.LIGHT_GRAY);
            }
        
        lab=new JLabel("WHITE's TURN");
        back=new JButton("Back");
        reset=new JButton("Reset");
        
        lab.setFont(new Font("Arial",Font.BOLD,15));
        //lab.setBackground(Color.BLACK);
        lab.setForeground(Color.RED);
        
        p1=new JPanel();
        p2=new JPanel();
        p3=new JPanel();
        
        p1.add(lab);
        p3.add(back);
        p3.add(reset);
        
        f.add(p1,BorderLayout.NORTH);
        
        p2.setLayout(new GridLayout(8,8));
        for(int i=0;i<8;i++)
            for(int j=0;j<8;j++)
                p2.add(b[i][j]);
        
        bl_pawn=new ImageIcon("C:\\Users\\dskk2\\Downloads\\icons8-pawn-52.png");
        bl_elephant=new ImageIcon("C:\\Users\\dskk2\\Downloads\\icons8-rook-52.png");
        bl_horse=new ImageIcon("C:\\Users\\dskk2\\Downloads\\icons8-knight-52.png");
        bl_camel=new ImageIcon("C:\\Users\\dskk2\\Downloads\\icons8-bishop-52.png");
        wh_pawn=new ImageIcon("C:\\Users\\dskk2\\Downloads\\icons8-pawn-50.png");
        bl_queen=new ImageIcon("C:\\Users\\dskk2\\Downloads\\icons8-queen-50.png");
        bl_king=new ImageIcon("C:\\Users\\dskk2\\Downloads\\icons8-king-50.png");
        wh_elephant=new ImageIcon("C:\\Users\\dskk2\\Downloads\\icons8-rook-50.png");
        wh_horse=new ImageIcon("C:\\Users\\dskk2\\Downloads\\icons8-knight-64.png");
        wh_camel=new ImageIcon("C:\\Users\\dskk2\\Downloads\\icons8-bishop-50.png");
        wh_queen=new ImageIcon("C:\\Users\\dskk2\\Downloads\\icons8-queen-50 (1).png");
        wh_king=new ImageIcon("C:\\Users\\dskk2\\Downloads\\icons8-king-50 (1).png");
        
        ImageIcon ii=new ImageIcon("C:\\Users\\dskk2\\Downloads\\icons8-knight-52.png");
        f.setIconImage(ii.getImage());
        
        for(int i=0;i<8;i++)
        {
                    b[1][i].setIcon(bl_pawn);
        }
        b[0][7].setIcon(bl_elephant);
        b[0][0].setIcon(bl_elephant);
        
        b[0][1].setIcon(bl_horse);
        b[0][6].setIcon(bl_horse);
        
        b[0][2].setIcon(bl_camel);
        b[0][5].setIcon(bl_camel);
        
        b[0][4].setIcon(bl_queen);
        b[0][3].setIcon(bl_king);
        
        
        for(int i=0;i<8;i++)
        {
            b[6][i].setIcon(wh_pawn);
        }
        
        b[7][0].setIcon(wh_elephant);
        b[7][7].setIcon(wh_elephant);
        
        b[7][1].setIcon(wh_horse);
        b[7][6].setIcon(wh_horse);
        
        b[7][2].setIcon(wh_camel);
        b[7][5].setIcon(wh_camel);
        
        b[7][4].setIcon(wh_queen);
        b[7][3].setIcon(wh_king);
        
        for(int i=0;i<8;i++)
            for(int j=0;j<8;j++)
                b[i][j].setFont(new Font("Araial",Font.PLAIN,1));
        
        f.add(p3,BorderLayout.SOUTH);
        f.add(p2,BorderLayout.CENTER);
        
        b[0][0].setText("blackrook");
        b[0][7].setText("blackrook");
        
        b[0][1].setText("blackhorse");
        b[0][6].setText("blackhorse");
        
        b[0][2].setText("blackcamel");
        b[0][5].setText("blackcamel");
        
        b[0][3].setText("blackking");
        b[0][4].setText("blackqueen");
        
        for(int i=0;i<8;i++)
            b[1][i].setText("blackpawn");
        
        for(int i=2;i<6;i++)
            for(int j=0;j<8;j++)
                b[i][j].setText("");
        
        b[7][0].setText("whiterook");
        b[7][7].setText("whiterook");
        
        b[7][1].setText("whitehorse");
        b[7][6].setText("whitehorse");
        
        b[7][2].setText("whitecamel");
        b[7][5].setText("whitecamel");
        
        b[7][3].setText("whiteking");
        b[7][4].setText("whitequeen");
        
        for(int i=0;i<8;i++)
            b[6][i].setText("whitepawn");
        
        for(int i=0;i<8;i++)
            for(int j=0;j<8;j++)
                b[i][j].addActionListener(this);
        back.addActionListener(this);
        reset.addActionListener(this);
        
        al=new ArrayList<>();
        border = BorderFactory.createLineBorder(Color.RED);
        //border.paintBorder(null, null, 0, 0, 20, 20);
        
        
        
        
        f.setSize(700,750);
        f.setVisible(true);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent ae) 
    {
        if(!set)
        {
          
        for(a=0;a<8;a++)
        {
            for(c=0;c<8;c++)
            {
                if(ae.getSource()==b[a][c])
                {
                    if(turn.equals("white"))
                    {
                        if(!isEmpty(b[a][c]))
                        {
                            if(isWhite(b[a][c]))
                            {
                                x=a; y=c;
                                activated(b[a][c]);
                                System.out.println(al.size());
                                b[a][c].setBorder(border);
                                break;
                            }
                        }
                    }
                    
                    if(turn.equals("black"))
                    {
                        if(!isEmpty(b[a][c]))
                        {
                            if(isBlack(b[a][c]))
                            {
                                x=a; y=c;
                                activated(b[a][c]);
                                System.out.println(al.size());
                                b[a][c].setBorder(border);
                                break;
                            }
                        }
                    }
                }
            }
        }
        }
        else
        {
            inBack=true;
            selected=(JButton)ae.getSource();
            selectedIcon=selected.getIcon();
            selectedText=selected.getText();
            if(b[x][y].getText().contains("pawn")){
                
            if(turn.equals("white"))
            {
                for(JButton bt: al)
                {
                    if(selected==bt)
                    {
                        System.out.println("set..");
                        
                        bt.setIcon(wh_pawn);
                        bt.setText("whitepawn");
                        b[x][y].setIcon(null);
                        b[x][y].setBorder(null);
                        b[x][y].setText("");
                        //play(audioFilePath);
                        turn="black";
                        lab.setText("BLACK's TURN");
                    }
                }
            }
            else
            {
                for(JButton bt: al)
                {
                    if(selected==bt)
                    {
                        System.out.println("set..");
                        
                        bt.setIcon(bl_pawn);
                        bt.setText("blackpawn");
                        b[x][y].setIcon(null);
                        b[x][y].setBorder(null);
                        b[x][y].setText("");
                        //play(audioFilePath);
                        turn="white";
                        lab.setText("WHITE's TURN");
                    }
                }
            }
            }
            if(b[x][y].getText().contains("king"))
            {
                
                if(turn.equals("white"))
                {
                    for(JButton bt: al)
                    {   
                        if(selected==bt)
                        {
                            System.out.println("set..");
                        
                            bt.setIcon(wh_king);
                            bt.setText("whiteking");
                            b[x][y].setIcon(null);
                            b[x][y].setBorder(null);
                            b[x][y].setText("");
                            turn="black";
                            lab.setText("BLACK's TURN");
                        }
                    }
                }
                else
                {
                    for(JButton bt: al)
                    {
                        if(selected==bt)
                        {
                            System.out.println("set..");
                        
                            bt.setIcon(bl_king);
                            bt.setText("blackking");
                            b[x][y].setIcon(null);
                            b[x][y].setBorder(null);
                            b[x][y].setText("");
                            //play(audioFilePath);
                            turn="white";
                            lab.setText("WHITE's TURN");
                        }
                    }
                }
            }
            
            if(b[x][y].getText().contains("horse"))
            {
                
                if(turn.equals("white"))
                {
                    for(JButton bt: al)
                    {   
                        if(selected==bt)
                        {
                            System.out.println("set..");
                        
                            bt.setIcon(wh_horse);
                            bt.setText("whitehorse");
                            b[x][y].setIcon(null);
                            b[x][y].setBorder(null);
                            b[x][y].setText("");
                            //play(audioFilePath);
                            turn="black";
                            lab.setText("BLACK's TURN");
                        }
                    }
                }
                else
                {
                    for(JButton bt: al)
                    {
                        if(selected==bt)
                        {
                            System.out.println("set..");
                        
                            bt.setIcon(bl_horse);
                            bt.setText("blackhorse");
                            b[x][y].setIcon(null);
                            b[x][y].setBorder(null);
                            b[x][y].setText("");
                            //play(audioFilePath);
                            turn="white";
                            lab.setText("WHITE's TURN");
                        }
                    }
                }
            }
            
            if(b[x][y].getText().contains("rook"))
            {
                
                if(turn.equals("white"))
                {
                    for(JButton bt: al)
                    {   
                        if(selected==bt)
                        {
                            System.out.println("set..");
                        
                            bt.setIcon(wh_elephant);
                            bt.setText("whiterook");
                            b[x][y].setIcon(null);
                            b[x][y].setBorder(null);
                            b[x][y].setText("");
                            //play(audioFilePath);
                            turn="black";
                            lab.setText("BLACK's TURN");
                        }
                    }
                }
                else
                {
                    for(JButton bt: al)
                    {
                        if(selected==bt)
                        {
                            System.out.println("set..");
                        
                            bt.setIcon(bl_elephant);
                            bt.setText("blackrook");
                            b[x][y].setIcon(null);
                            b[x][y].setBorder(null);
                            b[x][y].setText("");
                            //play(audioFilePath);
                            turn="white";
                            lab.setText("WHITE's TURN");
                        }
                    }
                }
            }
            
            if(b[x][y].getText().contains("camel"))
            {
                
                if(turn.equals("white"))
                {
                    for(JButton bt: al)
                    {   
                        if(selected==bt)
                        {
                            System.out.println("set..");
                        
                            bt.setIcon(wh_camel);
                            bt.setText("whitecamel");
                            b[x][y].setIcon(null);
                            b[x][y].setBorder(null);
                            b[x][y].setText("");
                            //play(audioFilePath);
                            turn="black";
                            lab.setText("BLACK's TURN");
                        }
                    }
                }
                else
                {
                    for(JButton bt: al)
                    {
                        if(selected==bt)
                        {
                            System.out.println("set..");
                        
                            bt.setIcon(bl_camel);
                            bt.setText("blackcamel");
                            b[x][y].setIcon(null);
                            b[x][y].setBorder(null);
                            b[x][y].setText("");
                            //play(audioFilePath);
                            turn="white";
                            lab.setText("WHITE's TURN");
                        }
                    }
                }
            }
            
            if(b[x][y].getText().contains("queen"))
            {
                
                if(turn.equals("white"))
                {
                    for(JButton bt: al)
                    {   
                        if(selected==bt)
                        {
                            System.out.println("set..");
                        
                            bt.setIcon(wh_queen);
                            bt.setText("whitequeen");
                            b[x][y].setIcon(null);
                            b[x][y].setBorder(null);
                            b[x][y].setText("");
                            //play(audioFilePath);
                            turn="black";
                            lab.setText("BLACK's TURN");
                        }
                    }
                }
                else
                {
                    for(JButton bt: al)
                    {
                        if(selected==bt)
                        {
                            System.out.println("set..");
                        
                            bt.setIcon(bl_queen);
                            bt.setText("blackqueen");
                            b[x][y].setIcon(null);
                            b[x][y].setBorder(null);
                            b[x][y].setText("");
                            //play(audioFilePath);
                            turn="white";
                            lab.setText("WHITE's TURN");
                        }
                    }
                }
            }
            
                set=false;
                b[x][y].setBorder(null);
                al.clear();
        }
        
        if(ae.getSource()==back)
        {
            moveBack();
        }
        
        if(ae.getSource()==reset)
        {
            if(JOptionPane.showConfirmDialog(null, "Are you sure?")==0)
            {
                f.dispose();
                new Chess();
            }
        }
    }
    
    void activated(JButton b)
    {
        if(b.getText().contains("pawn")) 
            activate_pawn();
        if(b.getText().contains("king")) 
            activate_king();
        if(b.getText().contains("horse")) 
            activate_horse();
        if(b.getText().contains("rook"))
            activate_rook();
        if(b.getText().contains("camel"))
            activate_camel();
        if(b.getText().contains("queen"))
            activate_queen();
    }
    
    boolean isEmpty(JButton b)
    {
        if(b.getIcon()==null)
            return true;
        else
            return false;
    }
    
    boolean isBlack(JButton b)
    {
        return b.getText().contains("black");
    }
    
    boolean isWhite(JButton b)
    {
        return b.getText().contains("white");
 
    }
    
    void activate_pawn()
    {
        if(turn.equals("white")){
        System.out.println("Pawn activated..");
        if(isEmpty(b[a-1][c]))
            al.add(b[a-1][c]);
        
        if(a==6 && isEmpty(b[a-2][c]))
            al.add(b[a-2][c]);
            
        if(c!=7)
            if(isBlack(b[a-1][c+1]))
                al.add(b[a-1][c+1]);
        if(c!=0)
            if(isBlack(b[a-1][c-1]))
                al.add(b[a-1][c-1]);
        set=true;
        }
        
        if(turn.equals("black"))
        {
            System.out.println("Pawn activated..");
        if(isEmpty(b[a+1][c]))
            al.add(b[a+1][c]);
        
        if(a==1 && isEmpty(b[a+2][c]))
            al.add(b[a+2][c]);
        
        if(c!=7)
            if(isWhite(b[a+1][c+1]))
                al.add(b[a+1][c+1]);
        if(c!=0)
            if(isWhite(b[a+1][c-1]))
                al.add(b[a+1][c-1]);
        set=true;
        }
    }
    
    void activate_king()
    {
        if(turn.equals("white"))
        {
            if(c!=7)
                if(isEmpty(b[a][c+1]) || isBlack(b[a][c+1]))
                    al.add(b[a][c+1]);
            if(c!=0)
                if(isEmpty(b[a][c-1]) || isBlack(b[a][c-1]))
                    al.add(b[a][c-1]);
            if(a!=7)
                if(isEmpty(b[a+1][c]) || isBlack(b[a+1][c]))
                    al.add(b[a+1][c]);
            if(a!=0)
                if(isEmpty(b[a-1][c]) || isBlack(b[a-1][c]))
                    al.add(b[a-1][c]);
            
            if(a!=7 && c!=0)
                if(isEmpty(b[a+1][c-1]) || isBlack(b[a+1][c-1]))
                    al.add(b[a+1][c-1]);
            if(a!=7 && c!=7)
                if(isEmpty(b[a+1][c+1]) || isBlack(b[a+1][c+1]))
                    al.add(b[a+1][c+1]);
            if(a!=0 && c!=0)
                if(isEmpty(b[a-1][c-1]) || isBlack(b[a-1][c-1]))
                    al.add(b[a-1][c-1]);
            if(a!=0 && c!=7)
                if(isEmpty(b[a-1][c+1]) || isBlack(b[a-1][c+1]))
                    al.add(b[a-1][c+1]);
            set=true;
        }
        if(turn.equals("black"))
        {
            if(c!=7)
                if(isEmpty(b[a][c+1]) || isWhite(b[a][c+1]))
                    al.add(b[a][c+1]);
            if(c!=0)
                if(isEmpty(b[a][c-1]) || isWhite(b[a][c-1]))
                    al.add(b[a][c-1]);
            if(a!=7)
                if(isEmpty(b[a+1][c]) || isWhite(b[a+1][c]))
                    al.add(b[a+1][c]);
            if(a!=0)
                if(isEmpty(b[a-1][c]) || isWhite(b[a-1][c]))
                    al.add(b[a-1][c]);
            
            if(a!=7 && c!=0)
                if(isEmpty(b[a+1][c-1]) || isWhite(b[a+1][c-1]))
                    al.add(b[a+1][c-1]);
            if(a!=7 && c!=7)
                if(isEmpty(b[a+1][c+1]) || isWhite(b[a+1][c+1]))
                    al.add(b[a+1][c+1]);
            if(a!=0 && c!=0)
                if(isEmpty(b[a-1][c-1]) || isWhite(b[a-1][c-1]))
                    al.add(b[a-1][c-1]);
            if(a!=0 && c!=7)
                if(isEmpty(b[a-1][c+1]) || isWhite(b[a-1][c+1]))
                    al.add(b[a-1][c+1]);
            
            set=true;
        }
    }
    
    void activate_queen()
    {
        int i=a,j=c;
        if(turn.equals("white"))
        {
            if(i!=0)
            {
                while(i>0)
                {
                    if(isEmpty(b[i-1][j]))
                        al.add(b[i-1][j]);
                    else if(isBlack(b[i-1][j]))
                    {
                        al.add(b[i-1][j]);
                        break;
                    }
                    else
                        break;
                    i--;
                }
            }
            i=a; j=c;
            if(i!=7)
            {
                
                while(i<7)
                {
                    if(isEmpty(b[i+1][j]) || isBlack(b[i+1][j]))
                        al.add(b[i+1][j]);
                    else if(isBlack(b[i+1][j]))
                    {
                        al.add(b[i+1][j]);
                        break;
                    }
                    else
                        break;
                    
                    i++;
                }
                
            }
            i=a; j=c;
            if(j!=0)
            {
                //System.out.println("i: "+i+"   j: "+j);
                while(j>0)
                {
                    if(isEmpty(b[i][j-1]))
                    {
                        //System.out.println("Left: Is empty");
                        al.add(b[i][j-1]);
                    }
                    else if(isBlack(b[i][j-1]))
                    {
                        //System.out.println("Left: IsBlack");
                        al.add(b[i][j-1]);
                        break;
                    }
                    else
                        break;
                    
                    j--;
                }
            }
            i=a; j=c;
            if(j!=7)
            {
                //System.out.println("i: "+i+"   j: "+j);
                while(j<7)
                {
                    if(isEmpty(b[i][j+1]))
                    {
                        //System.out.println("Right: Is Empty");
                        al.add(b[i][j+1]);
                    }
                    else if(isBlack(b[i][j+1]))
                    {
                        //System.out.println("Right: Is black");
                        al.add(b[i][j+1]);
                        break;
                    }
                    else
                        break;
                    j++;
                }
            }
            
            i=a; j=c;
            if(i!=7 && j!=7)
            {
                while(i<7 && j<7)
                {
                    if(isEmpty(b[i+1][j+1]))
                        al.add(b[i+1][j+1]);
                    else if(isBlack(b[i+1][j+1]))
                    {
                        al.add(b[i+1][j+1]);
                        break;
                    }
                    else
                        break;
                
                    i++;
                    j++;
                }
            }
            i=a; j=c;
            
            if(i!=0 && j!=0)
            {
                while(i>0 && j>0)
                {
                    if(isEmpty(b[i-1][j-1]))
                        al.add(b[i-1][j-1]);
                    else if(isBlack(b[i-1][j-1]))
                    {
                        al.add(b[i-1][j-1]);
                        break;        
                    }
                    else
                        break;
                    
                    i--;
                    j--;
                }
            }
            i=a; j=c;
            
            if(i!=7 && j!=0)
            {
                while(i<7 && j>0)
                {
                    if(isEmpty(b[i+1][j-1]))
                        al.add(b[i+1][j-1]);
                    else if(isBlack(b[i+1][j-1]))
                    {
                        al.add(b[i+1][j-1]);
                        break;        
                    }
                    else
                        break;
                    
                    i++;
                    j--;
                }
            }
            
            i=a; j=c;
            
            if(i!=0 && j!=7)
            {
                while(i>0 && j<7)
                {
                    if(isEmpty(b[i-1][j+1]))
                        al.add(b[i-1][j+1]);
                    else if(isBlack(b[i-1][j+1]))
                    {
                        al.add(b[i-1][j+1]);
                        break;        
                    }
                    else
                        break;
                    
                    i--;
                    j++;
                }
            }
            
            set=true;
        }
        else
        {
            if(i!=0)
            {
                while(i>0)
                {
                    if(isEmpty(b[i-1][j]))
                        al.add(b[i-1][j]);
                    else if(isWhite(b[i-1][j]))
                    {
                        al.add(b[i-1][j]);
                        break;
                    }
                    else
                        break;
                    i--;
                }
            }
            i=a; j=c;
            if(i!=7)
            {
                
                while(i<7)
                {
                    if(isEmpty(b[i+1][j]))
                        al.add(b[i+1][j]);
                    else if(isWhite(b[i+1][j]))
                    {
                        al.add(b[i+1][j]);
                        break;
                    }
                    else
                        break;
                    
                    i++;
                }
                
            }
            i=a; j=c;
            if(j!=0)
            {
                //System.out.println("i: "+i+"   j: "+j);
                while(j>0)
                {
                    if(isEmpty(b[i][j-1]))
                    {
                        //System.out.println("Left: Is empty");
                        al.add(b[i][j-1]);
                    }
                    else if(isWhite(b[i][j-1]))
                    {
                        //System.out.println("Left: IsBlack");
                        al.add(b[i][j-1]);
                        break;
                    }
                    else
                        break;
                    
                    j--;
                }
            }
            i=a; j=c;
            if(j!=7)
            {
                //System.out.println("i: "+i+"   j: "+j);
                while(j<7)
                {
                    if(isEmpty(b[i][j+1]))
                    {
                        //System.out.println("Right: Is Empty");
                        al.add(b[i][j+1]);
                    }
                    else if(isWhite(b[i][j+1]))
                    {
                        //System.out.println("Right: Is black");
                        al.add(b[i][j+1]);
                        break;
                    }
                    else
                        break;
                    j++;
                }
            }
            
            i=a; j=c;
            if(i!=7 && j!=7)
            {
                while(i<7 && j<7)
                {
                    if(isEmpty(b[i+1][j+1]))
                        al.add(b[i+1][j+1]);
                    else if(isWhite(b[i+1][j+1]))
                    {
                        al.add(b[i+1][j+1]);
                        break;
                    }
                    else
                        break;
                
                    i++;
                    j++;
                }
            }
            i=a; j=c;
            
            if(i!=0 && j!=0)
            {
                while(i>0 && j>0)
                {
                    if(isEmpty(b[i-1][j-1]))
                        al.add(b[i-1][j-1]);
                    else if(isWhite(b[i-1][j-1]))
                    {
                        al.add(b[i-1][j-1]);
                        break;        
                    }
                    else
                        break;
                    
                    i--;
                    j--;
                }
            }
            i=a; j=c;
            
            if(i!=7 && j!=0)
            {
                while(i<7 && j>0)
                {
                    if(isEmpty(b[i+1][j-1]))
                        al.add(b[i+1][j-1]);
                    else if(isWhite(b[i+1][j-1]))
                    {
                        al.add(b[i+1][j-1]);
                        break;        
                    }
                    else
                        break;
                    
                    i++;
                    j--;
                }
            }
            
            i=a; j=c;
            
            if(i!=0 && j!=7)
            {
                while(i>0 && j<7)
                {
                    if(isEmpty(b[i-1][j+1]))
                        al.add(b[i-1][j+1]);
                    else if(isWhite(b[i-1][j+1]))
                    {
                        al.add(b[i-1][j+1]);
                        break;        
                    }
                    else
                        break;
                    
                    i--;
                    j++;
                }
            }
            
            set=true;
        }
    }
    
    void activate_rook()
    {
        int i=a,j=c;
        if(turn.equals("white"))
        {
            if(i!=0)
            {
                while(i>0)
                {
                    if(isEmpty(b[i-1][j]))
                        al.add(b[i-1][j]);
                    else if(isBlack(b[i-1][j]))
                    {
                        al.add(b[i-1][j]);
                        break;
                    }
                    else
                        break;
                    i--;
                }
            }
            i=a; j=c;
            if(i!=7)
            {
                
                while(i<7)
                {
                    if(isEmpty(b[i+1][j]) || isBlack(b[i+1][j]))
                        al.add(b[i+1][j]);
                    else if(isBlack(b[i+1][j]))
                    {
                        al.add(b[i+1][j]);
                        break;
                    }
                    else
                        break;
                    
                    i++;
                }
                
            }
            i=a; j=c;
            if(j!=0)
            {
                //System.out.println("i: "+i+"   j: "+j);
                while(j>0)
                {
                    if(isEmpty(b[i][j-1]))
                    {
                        //System.out.println("Left: Is empty");
                        al.add(b[i][j-1]);
                    }
                    else if(isBlack(b[i][j-1]))
                    {
                        //System.out.println("Left: IsBlack");
                        al.add(b[i][j-1]);
                        break;
                    }
                    else
                        break;
                    
                    j--;
                }
            }
            i=a; j=c;
            if(j!=7)
            {
                //System.out.println("i: "+i+"   j: "+j);
                while(j<7)
                {
                    if(isEmpty(b[i][j+1]))
                    {
                        //System.out.println("Right: Is Empty");
                        al.add(b[i][j+1]);
                    }
                    else if(isBlack(b[i][j+1]))
                    {
                        //System.out.println("Right: Is black");
                        al.add(b[i][j+1]);
                        break;
                    }
                    else
                        break;
                    j++;
                }
            }
            
            
            set=true;
        }
        else
        {
            if(i!=0)
            {
                while(i>0)
                {
                    if(isEmpty(b[i-1][j]))
                        al.add(b[i-1][j]);
                    else if(isWhite(b[i-1][j]))
                    {
                        al.add(b[i-1][j]);
                        break;
                    }
                    else
                        break;
                    i--;
                }
            }
            i=a; j=c;
            if(i!=7)
            {
                
                while(i<7)
                {
                    if(isEmpty(b[i+1][j]))
                        al.add(b[i+1][j]);
                    else if(isWhite(b[i+1][j]))
                    {
                        al.add(b[i+1][j]);
                        break;
                    }
                    else
                        break;
                    
                    i++;
                }
                
            }
            i=a; j=c;
            if(j!=0)
            {
                //System.out.println("i: "+i+"   j: "+j);
                while(j>0)
                {
                    if(isEmpty(b[i][j-1]))
                    {
                        //System.out.println("Left: Is empty");
                        al.add(b[i][j-1]);
                    }
                    else if(isWhite(b[i][j-1]))
                    {
                        //System.out.println("Left: IsBlack");
                        al.add(b[i][j-1]);
                        break;
                    }
                    else
                        break;
                    
                    j--;
                }
            }
            i=a; j=c;
            if(j!=7)
            {
                //System.out.println("i: "+i+"   j: "+j);
                while(j<7)
                {
                    if(isEmpty(b[i][j+1]))
                    {
                        //System.out.println("Right: Is Empty");
                        al.add(b[i][j+1]);
                    }
                    else if(isWhite(b[i][j+1]))
                    {
                        //System.out.println("Right: Is black");
                        al.add(b[i][j+1]);
                        break;
                    }
                    else
                        break;
                    j++;
                }
            }
            
            set=true;
        }
    }
    
    void activate_camel()
    {
        if(turn.equals("white"))
        {
            int i=a, j=c;
            if(i!=7 && j!=7)
            {
                while(i<7 && j<7)
                {
                    if(isEmpty(b[i+1][j+1]))
                        al.add(b[i+1][j+1]);
                    else if(isBlack(b[i+1][j+1]))
                    {
                        al.add(b[i+1][j+1]);
                        break;
                    }
                    else
                        break;
                
                    i++;
                    j++;
                }
            }
            i=a; j=c;
            
            if(i!=0 && j!=0)
            {
                while(i>0 && j>0)
                {
                    if(isEmpty(b[i-1][j-1]))
                        al.add(b[i-1][j-1]);
                    else if(isBlack(b[i-1][j-1]))
                    {
                        al.add(b[i-1][j-1]);
                        break;        
                    }
                    else
                        break;
                    
                    i--;
                    j--;
                }
            }
            i=a; j=c;
            
            if(i!=7 && j!=0)
            {
                while(i<7 && j>0)
                {
                    if(isEmpty(b[i+1][j-1]))
                        al.add(b[i+1][j-1]);
                    else if(isBlack(b[i+1][j-1]))
                    {
                        al.add(b[i+1][j-1]);
                        break;        
                    }
                    else
                        break;
                    
                    i++;
                    j--;
                }
            }
            
            i=a; j=c;
            
            if(i!=0 && j!=7)
            {
                while(i>0 && j<7)
                {
                    if(isEmpty(b[i-1][j+1]))
                        al.add(b[i-1][j+1]);
                    else if(isBlack(b[i-1][j+1]))
                    {
                        al.add(b[i-1][j+1]);
                        break;        
                    }
                    else
                        break;
                    
                    i--;
                    j++;
                }
            }//
            
            set=true;
        }
        else
        {
            int i=a, j=c;
            if(i!=7 && j!=7)
            {
                while(i<7 && j<7)
                {
                    if(isEmpty(b[i+1][j+1]))
                        al.add(b[i+1][j+1]);
                    else if(isWhite(b[i+1][j+1]))
                    {
                        al.add(b[i+1][j+1]);
                        break;
                    }
                    else
                        break;
                
                    i++;
                    j++;
                }
            }
            i=a; j=c;
            
            if(i!=0 && j!=0)
            {
                while(i>0 && j>0)
                {
                    if(isEmpty(b[i-1][j-1]))
                        al.add(b[i-1][j-1]);
                    else if(isWhite(b[i-1][j-1]))
                    {
                        al.add(b[i-1][j-1]);
                        break;        
                    }
                    else
                        break;
                    
                    i--;
                    j--;
                }
            }
            i=a; j=c;
            
            if(i!=7 && j!=0)
            {
                while(i<7 && j>0)
                {
                    if(isEmpty(b[i+1][j-1]))
                        al.add(b[i+1][j-1]);
                    else if(isWhite(b[i+1][j-1]))
                    {
                        al.add(b[i+1][j-1]);
                        break;        
                    }
                    else
                        break;
                    
                    i++;
                    j--;
                }
            }
            
            i=a; j=c;
            
            if(i!=0 && j!=7)
            {
                while(i>0 && j<7)
                {
                    if(isEmpty(b[i-1][j+1]))
                        al.add(b[i-1][j+1]);
                    else if(isWhite(b[i-1][j+1]))
                    {
                        al.add(b[i-1][j+1]);
                        break;        
                    }
                    else
                        break;
                    
                    i--;
                    j++;
                }
            }//
            
            set=true;
        }
    }
    
    void activate_horse()
    {
        if(turn.equals("white"))
        {
            if(a>=2 && c!=7)
                if(isEmpty(b[a-2][c+1]) || isBlack(b[a-2][c+1]))
                    al.add(b[a-2][c+1]);
            if(a>=2 && c!=0)
                if(isEmpty(b[a-2][c-1]) || isBlack(b[a-2][c-1]))
                    al.add(b[a-2][c-1]);
            if(a!=7 && c>=2)
                if(isEmpty(b[a+1][c-2]) || isBlack(b[a+1][c-2]))
                    al.add(b[a+1][c-2]);
            if(a!=7 && c<6)
                if(isEmpty(b[a+1][c+2]) || isBlack(b[a+1][c+2]))
                    al.add(b[a+1][c+2]);
            if(a<6 && c!=0)
                if(isEmpty(b[a+2][c-1]) || isBlack(b[a+2][c-1]))
                    al.add(b[a+2][c-1]);
            if(a<6 && c!=7)
                if(isEmpty(b[a+2][c+1]) || isBlack(b[a+2][c+1]))
                    al.add(b[a+2][c+1]);
            if(a!=0 && c<6)
                if(isEmpty(b[a-1][c+2]) || isBlack(b[a-1][c+2]))
                    al.add(b[a-1][c+2]);
            if(a!=0 && c>=2)
                if(isEmpty(b[a-1][c-2]) || isBlack(b[a-1][c-2]))
                    al.add(b[a-1][c-2]);
                
            
            set=true;
        }
        else
        {
            if(a>=2 && c!=7)
                if(isEmpty(b[a-2][c+1]) || isWhite(b[a-2][c+1]))
                    al.add(b[a-2][c+1]);
            if(a>=2 && c!=0)
                if(isEmpty(b[a-2][c-1]) || isWhite(b[a-2][c-1]))
                    al.add(b[a-2][c-1]);
            if(a!=7 && c>=2)
                if(isEmpty(b[a+1][c-2]) || isWhite(b[a+1][c-2]))
                    al.add(b[a+1][c-2]);
            if(a!=7 && c<6)
                if(isEmpty(b[a+1][c+2]) || isWhite(b[a+1][c+2]))
                    al.add(b[a+1][c+2]);
            if(a<6 && c!=0)
                if(isEmpty(b[a+2][c-1]) || isWhite(b[a+2][c-1]))
                    al.add(b[a+2][c-1]);
            if(a<6 && c!=7)
                if(isEmpty(b[a+2][c+1]) || isWhite(b[a+2][c+1]))
                    al.add(b[a+2][c+1]);
            if(a!=0 && c<6)
                if(isEmpty(b[a-1][c+2]) || isWhite(b[a-1][c+2]))
                    al.add(b[a-1][c+2]);
            if(a!=0 && c>=2)
                if(isEmpty(b[a-1][c-2]) || isWhite(b[a-1][c-2]))
                    al.add(b[a-1][c-2]);
            
            set=true;
        }
    }
    
    void moveBack()
    {
        if(inBack){
        String s=selected.getText();
        Icon i=selected.getIcon();
        b[x][y].setIcon(i);
        selected.setIcon(selectedIcon);
        selected.setText(selectedText);
        if(turn.equals("black"))
        {
            b[x][y].setText(s);
            turn="white";
            lab.setText("WHITE's TURN");
        }
        else
        {
            b[x][y].setText(s);
            turn="black";
            lab.setText("BLACK's TURN");
        }
        inBack=false;
        }
        
    }
    
    void play(String audioFilePath) {
        File audioFile = new File(audioFilePath);
 
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
 
            AudioFormat format = audioStream.getFormat();
 
            DataLine.Info info = new DataLine.Info(Clip.class, format);
 
            Clip audioClip = (Clip) AudioSystem.getLine(info);
 
            //audioClip.addLineListener(this);
 
            audioClip.open(audioStream);
             
            audioClip.start();

                try 
                {
                    Thread.sleep(500);
                } 
                catch (InterruptedException ex) 
                {}
            audioClip.close();
             
        } catch (Exception ex) {
            ex.printStackTrace();
        } 
         
    }
    
}
