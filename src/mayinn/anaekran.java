package mayinn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.util.TimerTask;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.Timer;

public class anaekran extends JFrame implements ActionListener {
    public static void main(String[] args) {
        new anaekran(9, 9);

    }
    Timer t;
    MouseListener listener;
   
    JLabel kalanmayin = new JLabel("Kalan Mayın Sayısı:");
    JTextField mayinlar = new JTextField("              ");
    JTextField jt = new JTextField("            ");
    JButton reset = new JButton("Sıfırla");
    Container Grid;
    JFrame frame;
    JPanel Panel;
    JMenuBar menu;
    JMenu game;
    JMenuItem cıkıs;
    JMenuItem baslangıc;
    JMenuItem orta;
    JMenuItem zor;
    JMenuItem ozel;
    int[][] counter;
    JButton[][] buttons;
    int cols, rows;
    int mayinSayisi;
       public void cascadeUtil(int count[][], int x, int y, int prevC, int newC, int cols, int rows) {

        if (x < 0 || x >= cols || y < 0 || y >= rows) {
            return;
        }
        if (count[x][y] != prevC) {
            return;
        }
        count[x][y] = newC;
        if (count[x][y] == newC) {
            buttons[x][y].setText("");
            buttons[x][y].setBackground(Color.WHITE);
            buttons[x][y].setForeground(Color.WHITE);
            buttons[x][y].setText("");
            buttons[x][y].setOpaque(true);
            buttons[x][y].setEnabled(false);

            try {
                buttons[x - 1][y - 1].setText(count[x - 1][y - 1] + "");
                buttons[x - 1][y - 1].setEnabled(false);

            } catch (Exception e) {
                System.out.println("indeks hatalı");
            }

            try {
                buttons[x + 1][y - 1].setText(count[x + 1][y - 1] + "");
                buttons[x + 1][y - 1].setEnabled(false);

            } catch (Exception e) {
                System.out.println("indeks hatalı");
            }

            try {
                buttons[x - 1][y + 1].setText(count[x - 1][y + 1] + "");
                buttons[x - 1][y + 1].setEnabled(false);

            } catch (Exception e) {
                System.out.println("indeks hatalı");
            }
            try {

                buttons[x + 1][y + 1].setText(count[x + 1][y + 1] + "");
                buttons[x + 1][y + 1].setEnabled(false);
            } catch (Exception e) {
                System.out.println("indeks hatalı");
            }
            try {

                buttons[x - 1][y].setText(count[x - 1][y] + "");
                buttons[x - 1][y].setEnabled(false);

            } catch (Exception e) {
                System.out.println("indeks hatalı");
            }
            try {
                buttons[x][y - 1].setText(count[x][y - 1] + "");
                buttons[x][y - 1].setEnabled(false);

            } catch (Exception e) {
                System.out.println("indeks hatalı");
            }
            try {
                buttons[x][y + 1].setText(count[x][y + 1] + "");
                buttons[x][y + 1].setEnabled(false);
            } catch (Exception e) {
                System.out.println("indeks hatalı");
            }

            try {
                buttons[x + 1][y].setText(count[x + 1][y] + "");
                buttons[x + 1][y].setEnabled(false);
            } catch (Exception e) {
                System.out.println("indeks hatalı");
            }

            cascadeUtil(count, x + 1, y, prevC, newC, cols, rows);
            cascadeUtil(count, x - 1, y, prevC, newC, cols, rows);
            cascadeUtil(count, x, y + 1, prevC, newC, cols, rows);
            cascadeUtil(count, x, y - 1, prevC, newC, cols, rows);

        }
    }


    public anaekran(int COLS, int ROWS) {
        t = new Timer(1000, new ActionListener() {
            int k = 0;
            @Override
            public void actionPerformed(ActionEvent e) {
                jt.setText(String.valueOf(k));
                k++;
            }
        });
        t.start();
        this.cols = COLS;
        this.rows = ROWS;
        this.mayinSayisi = 11;
        frame = new JFrame("Mayin");
        frame.setBackground(Color.CYAN);
        frame.setSize(700, 500);
        frame.setLayout(new BorderLayout());
        menu = new JMenuBar();
        game = new JMenu("AYARLAR");
        cıkıs = new JMenuItem("Kapat");
        baslangıc = new JMenuItem("Kolay");
        orta = new JMenuItem("Orta");
        zor = new JMenuItem("Zor");
        ozel = new JMenuItem("Yapılandır");
        cıkıs.addActionListener(this);
        baslangıc.addActionListener(this);
        orta.addActionListener(this);
        zor.addActionListener(this);
        ozel.addActionListener(this);
        game.add(cıkıs);
        game.add(baslangıc);
        game.add(orta);
        game.add(zor);
        game.add(ozel);
        menu.add(game);
        frame.setJMenuBar(menu);
        Panel = new JPanel();
        reset.addActionListener(this);
        Panel.add(reset);
        jt.setEditable(false);
        mayinlar.setText(""+10);
        Panel.add(jt);
  
        mayinlar.setEditable(false);
        Panel.add(mayinlar);
        frame.add(Panel, BorderLayout.NORTH);
        Grid = new Container();
        buttons = new JButton[COLS][ROWS];
        
        Grid.setLayout(new GridLayout(COLS, ROWS));
    int a=0;
    while(a<COLS){
        int b =0;
        while(b<ROWS){
                            buttons[a][b] = new JButton();
                buttons[a][b].setSize(100, 100);
                buttons[a][b].addActionListener(this);
                buttons[a][b].setFocusPainted(false);
                Grid.add(buttons[a][b]);
                b++;
        }
        a++;
    }
        frame.add(Grid, BorderLayout.CENTER);
        mayinata(COLS, ROWS, mayinSayisi);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == reset) {
          int i =0;
          while(i<cols){
              int j = 0;
              while(j<rows){
                      buttons[i][j].setEnabled(true);
                    buttons[i][j].setOpaque(false);
                    buttons[i][j].setText("");
                    j++;
              }
              i++;
          }
            mayinata(cols, rows, mayinSayisi);
        } else if (e.getSource() == cıkıs) {
            System.exit(0);
        } else if (e.getSource() == ozel) {
            t = new Timer(1000, new ActionListener() {
                int k = 0;

                @Override
                public void actionPerformed(ActionEvent e) {
                    jt.setText(String.valueOf(k));
                    k++;
                }
            });
            t.start();
            String sat = JOptionPane.showInputDialog("Satır sayısı");
            int satır = Integer.parseInt(sat);
            String sut = JOptionPane.showInputDialog("Sütun sayısı");
            int sutun = Integer.parseInt(sut);
            String may = JOptionPane.showInputDialog("MAYIN sayısı");
            int mayın = Integer.parseInt(may);
            frame.remove(Grid);
            frame.repaint();
            mayinSayisi = 11;
            mayinlar.setText("" + mayın);
            Grid = new Container();
            buttons = new JButton[satır][sutun];
            Grid.setLayout(new GridLayout(satır, sutun));
            for (int a = 0; a < satır; a++) {
                for (int b = 0; b < sutun; b++) {
                    buttons[a][b] = new JButton();
                    buttons[a][b].addActionListener(this);
                    buttons[a][b].setFocusPainted(false);
                    Grid.add(buttons[a][b]);
                }
            }
            mayinata(satır, sutun, mayın);
            cols = satır;
            rows = sutun;
            frame.add(Grid, BorderLayout.CENTER);
            frame.validate();

        } else if (e.getSource() == baslangıc) {
            t = new Timer(1000, new ActionListener() {
                int k = 0;

                @Override
                public void actionPerformed(ActionEvent e) {
                    jt.setText(String.valueOf(k));
                    k++;
                }
            });
            mayinlar.setText("" + 11);
            t.start();
            frame.remove(Grid);
            frame.repaint();
            mayinSayisi = 11;
            Grid = new Container();
            buttons = new JButton[9][9];
            Grid.setLayout(new GridLayout(9, 9));
            for (int a = 0; a < 9; a++) {
                for (int b = 0; b < 9; b++) {
                    buttons[a][b] = new JButton();
                    buttons[a][b].addActionListener(this);
                    buttons[a][b].setFocusPainted(false);
                    Grid.add(buttons[a][b]);
                }
            }
            mayinata(9, 9, mayinSayisi);
            cols = 9;
            rows = 9;
            frame.add(Grid, BorderLayout.CENTER);
            frame.validate();
        } else if (e.getSource() == orta) {
            t = new Timer(1000, new ActionListener() {
                int k = 0;

                @Override
                public void actionPerformed(ActionEvent e) {
                    jt.setText(String.valueOf(k));
                    k++;
                }
            });
            mayinlar.setText("" + 40);
            t.start();
            frame.remove(Grid);
            frame.repaint();
            mayinSayisi = 40;
            Grid = new Container();

            buttons = new JButton[16][16];

            Grid.setLayout(new GridLayout(16, 16));
            for (int a = 0; a < 16; a++) {
                for (int b = 0; b < 16; b++) {
                    buttons[a][b] = new JButton();
                    buttons[a][b].addActionListener(this);
                    buttons[a][b].setFocusPainted(false);
                    Grid.add(buttons[a][b]);
                }
            }
            mayinata(16, 16, mayinSayisi);
            cols = 16;
            rows = 16;

            frame.add(Grid, BorderLayout.CENTER);
            frame.validate();
        } else if (e.getSource() == zor) {
            t = new Timer(1000, new ActionListener() {
                int k = 0;

                @Override
                public void actionPerformed(ActionEvent e) {
                    jt.setText(String.valueOf(k));
                    k++;
                }
            });
            t.start();
            frame.remove(Grid);
            frame.repaint();
            mayinSayisi = 99;
            Grid = new Container();
            buttons = new JButton[16][30];

            Grid.setLayout(new GridLayout(16, 30));
            for (int a = 0; a < 16; a++) {
                for (int b = 0; b < 30; b++) {
                    buttons[a][b] = new JButton();
                    buttons[a][b].addActionListener(this);
                    buttons[a][b].setFocusPainted(false);
                    Grid.add(buttons[a][b]);
                }
            }
            mayinlar.setText("" + 99);
            mayinata(16, 30, mayinSayisi);
            cols = 16;
            rows = 30;
            frame.add(Grid, BorderLayout.CENTER);
            frame.validate();
        } else {
            for (int a = 0; a < cols; a++) {
                for (int b = 0; b < rows; b++) {
                    sagtık(buttons[a][b]);
                    if (e.getSource() == buttons[a][b]) {
                        if (counter[a][b] == -1) {
                            buttons[a][b].setText("M");
                            t.stop();
                            buttons[a][b].setEnabled(false);
                            oyunsonlandır();
                        } else if (counter[a][b] > 0) {
                            buttons[a][b].setText(counter[a][b] + "");
                            buttons[a][b].setEnabled(false);
                        } else {
                            cascadeUtil(counter, a, b, 0, 0, cols, rows);
                        }
                    }

                }
            }
        }
    }
        public void oyunsonlandır() {
            int i=0;
            while(i<cols){
                int j =0;
                while(j<rows){
                if (counter[i][j] < 0) {
                    buttons[i][j].setText("MAYIN");
                    buttons[i][j].setOpaque(true);
                    buttons[i][j].setBackground(Color.RED);
                } else if (counter[i][j] == 0) {
                    buttons[i][j].setText(" ");
                    buttons[i][j].setOpaque(true);
                    buttons[i][j].setBackground(Color.WHITE);
                } else {
                    buttons[i][j].setText("" + counter[i][j]);
                    if (counter[i][j] == 1) {
                        buttons[i][j].setText("" + counter[i][j]);
                        buttons[i][j].setOpaque(true);
                        buttons[i][j].setBackground(Color.darkGray);
                    }
                    if (counter[i][j] == 2) {
                        buttons[i][j].setText("" + counter[i][j]);
                        buttons[i][j].setOpaque(true);
                        buttons[i][j].setBackground(Color.yellow);
                    }
                    if (counter[i][j] == 3) {
                        buttons[i][j].setText("" + counter[i][j]);
                        buttons[i][j].setOpaque(true);
                        buttons[i][j].setBackground(Color.magenta);
                    }
                    if (counter[i][j] > 3) {
                        buttons[i][j].setText("" + counter[i][j]);
                        buttons[i][j].setOpaque(true);
                        buttons[i][j].setBackground(Color.PINK);
                    }
                }
                j++;
            }
            i++;
        }
        JOptionPane.showMessageDialog(null, "Kaybettiniz", "Mayin", JOptionPane.INFORMATION_MESSAGE);

    }

    public void sagtik(JButton buton[][], int a, int b) {
        MouseListener mouseListener = new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
            

                int modifiers = e.getModifiers();
                if ((modifiers & InputEvent.BUTTON1_MASK) == InputEvent.BUTTON1_MASK) {

                }
                if ((modifiers & InputEvent.BUTTON3_MASK) == InputEvent.BUTTON3_MASK) {
                    System.err.println("");
                    buton[a][b].setText("var");
                }
            }
        };
    }

    public void sagtık(JButton buton) {
        buton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == 3) {
                    buton.setText("İŞARETLENDİ");
                }
            }
        });
    }



    public void mayinata(int cols, int rows, int seviye) { 
        counter = new int[cols][rows];
        Random rand = new Random();
        for (int i = 1; i < seviye; i++) {
            int x = rand.nextInt(cols);
            int y = rand.nextInt(rows);
            if (counter[x][y] != -1) {
                counter[x][y] = -1;
            }
        }
        int a =0;
        while(a<cols){
           int b =0;
           while (b<rows){
                         if (counter[a][b] != -1) {
                    int sayac = 0;
                    if (a > 0 && b > 0 && counter[a - 1][b - 1] == -1) {  
                        sayac++;
                    }
                    if (a < cols - 1 && b > 0 && counter[a + 1][b - 1] == -1) 
                    {
                        sayac++;
                    }
                    if (a > 0 && b < cols - 1 && counter[a - 1][b + 1] == -1)
                    {
                        sayac++;
                    }
                    if (a < cols - 1 && b < cols - 1 && counter[a + 1][b + 1] == -1) 
                    {
                        sayac++;
                    }
                    if (a > 0 && counter[a - 1][b] == -1) 
                    {
                        sayac++;
                    }
                    if (a < cols - 1 && counter[a + 1][b] == -1) 
                    {
                        sayac++;
                    }
                    if (b > 0 && counter[a][b - 1] == -1) 
                    {
                        sayac++;
                    }
                    if (b < cols - 1 && counter[a][b + 1] == -1) 
                    {
                        sayac++;
                    }
                    counter[a][b] = sayac;
                } else {
                    if (counter[a][b] != -1) {
                        counter[a][b] = 0;
                    }
                }
             b++;
            }
           a++;
        }
    }

 

}
