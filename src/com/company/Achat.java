package com.company;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Achat extends ArrayList<String[]>{



    // s : reference d'un produit
    // tmp[0] : numero de fournisseur
    // tmp[1] : delai maximun de livraison
    public String[] getByPieceRef(String s) throws Exception {
        String[] tmp = new String[2];
        for(int i = 0 ; i<this.size() ; i++ ){
            if (this.get(i)[1].equals(s)){
                tmp[0] = this.get(i)[0];
                tmp[1] = this.get(i)[2];
                return tmp;
            }
        }
        throw (new Exception("la référence : " + s + " rentrée n'est pas recencé dans la base de donnée de gestion d'achat")) ;

    }

}
