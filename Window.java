package rubicboard;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.util.*;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Window extends BaseWindow{

    private final int size;
    private final JLabel label;
  private JButton[][] table;
    private final ArrayList<Color> colors = new ArrayList<>(Arrays.asList(Color.red, Color.blue, Color.yellow, Color.green,Color.orange, Color.white));
    private List<Color> colorsforsize ;
    int[] occ;

    public Model model;
    private final MainWindow mainWindow;
    
    public Window(int size, MainWindow mainWindow) {
        this.size = size;
        colorsforsize = colors.subList(0, size);
        this.mainWindow = mainWindow;
        table = new JButton[size][size];
        mainWindow.getWindowList().add(this);
        occ = new int[size];
       for(int i = 0; i< size; i++){
           occ[i] = 0;
       }


        JPanel top = new JPanel();
        
        label = new JLabel();

        
        JButton newGameButton = new JButton();
        newGameButton.setText("New game");
        newGameButton.addActionListener(e -> newGame());

        top.add(label);
        top.add(newGameButton);


        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(size, size));


        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                addButton(mainPanel, i, j);
            }
        }

        model = new Model(size, table);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(top, BorderLayout.NORTH);
        getContentPane().add(mainPanel, BorderLayout.CENTER);
    }



    private int randomizer(){

        int index = (int)(Math.random() * colorsforsize.size());

        while(occ[index] >= size){
            index = (int)(Math.random() * colorsforsize.size());
        }
        occ[index]++;
        return index;
    }


    private void addButton(JPanel panel, final int i,
            final int j) {
            table[i][j] = new JButton();
            JButton button = table[i][j];

            button.setBackground(colors.get(randomizer()));

            panel.add(button);


        button.addMouseListener(new MouseAdapter() {

            Point startingpoint;
            Point endingpoint;
            @Override
            public void mousePressed(MouseEvent e) {
              startingpoint =  e.getPoint();
               ;
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                endingpoint = e.getPoint();
               table = model.move(i, j, startingpoint, endingpoint);
               updatetable(table);
               if(model.check()){
                  showGameOverMessage();

               };
               updateLabelText(model.getCounter());
            }

        });



    }

    private void showGameOverMessage() {
        JOptionPane.showMessageDialog(this,
                "Game is over. You Won!");
        newGame();
    }
    private void updatetable(JButton[][] table){
               this.table = table;
    }



    private void updateLabelText(int counter) {
    label.setText("Counter : " + counter);
    }
    private void newGame() {
        Window newWindow = new Window(size, mainWindow);
        newWindow.setVisible(true);
        
        this.dispose();
        mainWindow.getWindowList().remove(this);
    }
    /*
    private void updateLabelText() {
        label.setText("Current player: "
                + model.getActualPlayer().name());
    }
*/
    @Override
    protected void doUponExit() {
        super.doUponExit();
        mainWindow.getWindowList().remove(this);
    }


}
