package com.company;

import java.io.File;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws Exception {

        String path_besoin = args[0];
        String path_stock = args[1];
        String path_achat = args[2];
        String path_resultat = args[3];


        ArrayList<String[]> besoin = new ArrayList<String[]>();
        Stock stock = new Stock();
        Achat achat = new Achat();

        Scanner scan1 = new Scanner(new File(path_besoin));
        while(scan1.hasNextLine()){
            String line = scan1.nextLine();
            besoin.add(line.split(";"));
        }

        Scanner scan2 = new Scanner(new File(path_stock));
        while(scan2.hasNextLine()){
            String line = scan2.nextLine();
            stock.add(line.split(";"));
        }

        Scanner scan3 = new Scanner(new File(path_achat));
        while(scan3.hasNextLine()){
            String line = scan3.nextLine();
            achat.add(line.split(";"));
        }

        PrintWriter pw = new PrintWriter(path_resultat);

        for (int i = 0 ; i<besoin.size() ; i++){
            String refBesoin = besoin.get(i)[1];
            int quantiteBesoin = Integer.parseInt(besoin.get(i)[2]);
            String dateBesoin = besoin.get(i)[0];
            int quantiteDansStock = stock.getByPieceRef(refBesoin);

            if (quantiteDansStock < quantiteBesoin ) {
                stock.updateElement(refBesoin , 0);
                int tmpQuantity = quantiteBesoin-quantiteDansStock;
                String[] ac = achat.getByPieceRef(refBesoin);

                String[] tmpdate = dateBesoin.split("/");
                int year = Integer.parseInt(tmpdate[0]);
                int month =Integer.parseInt(tmpdate[1]);
                int day =  Integer.parseInt(tmpdate[2]);

                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                LocalDate date = LocalDate.parse(dateBesoin,dtf).minusDays(Integer.parseInt(ac[1]));
                pw.write(
                        ac[0]+";"+
                            besoin.get(i)[1]+ ";"+
                            tmpQuantity+";"+
                            dtf.format(date)+"\n");

            }else{
                stock.updateElement(besoin.get(i)[0] , quantiteDansStock-Integer.parseInt(besoin.get(i)[1]));
            }
        }
        pw.close();

    }


    }