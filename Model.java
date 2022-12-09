package rubicboard;

import javax.swing.*;
import java.awt.*;

public class Model {

    private final int size;
    private JButton[][] table;
    private int counter = 0;


    public Model(int size, JButton[][] table) {
        this.size = size;
        this.table = table;
    }

    public JButton[][] move(int i, int j, Point firstpoint, Point secondpoint){

         if(Math.abs(secondpoint.getX() - firstpoint.getX()) == 0 && Math.abs(secondpoint.getY() - firstpoint.getY()) == 0){
             return table;
         }else if(Math.abs(secondpoint.getX() - firstpoint.getX()) > Math.abs(secondpoint.getY() - firstpoint.getY())){
             counter++;
             if(secondpoint.getX() > firstpoint.getX()){
                 moveclockwiserowwise(i);
             }else {
                 movecounterclockwiserowwise(i);
             }
        }else {
             counter++;
             if (secondpoint.getY() > firstpoint.getY()) {
                 moveclockwisecolumnwise(j);
             } else {
                 movecounterclockwisecolumnwise(j);
             }

         }

         return table;
    }

    public void movecounterclockwiserowwise(int i){

        Color[] colors = new Color[size];
        for(int j = 1; j < size; j++){
            colors[j] = table[i][j].getBackground();
        }

        table[i][size-1].setBackground(table[i][0].getBackground());
        for(int j = 0; j < size-1; j++){
            table[i][j].setBackground(colors[j+1]);
        }
    }

    public void moveclockwiserowwise(int i){

        Color[] colors = new Color[size];
        for(int j = 0; j < size-1; j++){
            colors[j] = table[i][j].getBackground();
        }

        table[i][0].setBackground(table[i][size-1].getBackground());
        for(int j = 1; j < size; j++){
            table[i][j].setBackground(colors[j-1]);
        }



    }

    public void moveclockwisecolumnwise(int j){

        Color[] colors = new Color[size];
        for(int i = 0; i < size-1; i++){
            colors[i] = table[i][j].getBackground();
        }
        table[0][j].setBackground(table[size-1][j].getBackground());

        for(int i = 1; i< size; i++){
            table[i][j].setBackground(colors[i-1]);
        }
    }

    public void movecounterclockwisecolumnwise(int j){

        Color[] colors = new Color[size];
        for(int i = 1; i < size; i++){
            colors[i] = table[i][j].getBackground();
        }

        table[size-1][j].setBackground(table[0][j].getBackground());
        for(int i = 0; i < size-1; i++){
            table[i][j].setBackground(colors[i+1]);
        }
    }

    public boolean check(){
        boolean allsamerow = true;
          for(int i = 0; i < size; i++){
              for(int j = 0; j < size; j++){
                  allsamerow = allsamerow && (table[i][j].getBackground() == table[i][0].getBackground());
              }
          }

boolean allsamecolumn = true;
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                allsamecolumn = allsamecolumn && (table[i][j].getBackground() == table[0][j].getBackground());
            }
        }
        if(allsamecolumn || allsamerow){
            return true;
        }
        return false;
    }
    public int getCounter(){
        return counter;
    }





}
