package com.company;

import java.util.ArrayList;

public class Stock extends ArrayList<String[]>{


    // s : reference d'un produit
    // tmp : quantite dans le stock
    public int getByPieceRef(String s){

        int tmp = 0;
        for(int i = 0 ; i<this.size() ; i++ ){

            if (this.get(i)[0].equals(s)){
                tmp = Integer.parseInt(this.get(i)[1]);
                break;
            }
        }
        return tmp;
    }

    public void updateElement(String ref, int newValue){
        for(int i = 0 ; i<this.size() ; i++ ){
            if (this.get(i)[0].equals(ref)){
                this.set(i, new String[] {this.get(i)[1],String.valueOf(newValue)});
                break;
            }
        }

    }


}
