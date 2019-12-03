package algo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import static java.lang.Math.random;

public class Probleme {

    private int n;
    private int p;
    private ArrayList<Integer> q;



    public Probleme(String fileName) {
        rechercheFichier(fileName);

    }

    public void rechercheFichier(String fileName){

        Scanner lecteur ;

        File fichier = new File(fileName);
        try {
            lecteur = new Scanner(fichier);

            n = lecteur.nextInt();
            p = lecteur.nextInt();
            q = new ArrayList<>(n * n);

            while (lecteur.hasNextInt()) {
                q.add(lecteur.nextInt());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(-1);
        }



    }

    public ArrayList<Integer> solutionInitiale(){
        ArrayList<Integer> x2 = new ArrayList<>(n);
        for (int i = 0; i < n; ++i) {
            int v = (int) (random() * (2));
            x2.add(v);
        }
        return x2;
    }

    public int calculValeur(ArrayList<Integer> x){
        int res = 0;
        for (int i = 0; i < n; ++i) {

            for (int j = 0; j < n; ++j) {
                res += q.get(i * n + j) * x.get(i) * x.get(j);
            }
        }
        return res;
    }

    public ArrayList<Integer> meilleurVoisin(ArrayList<Integer> x, boolean contrainte){
        int val;
        int courant;
        ArrayList<Integer> res = new ArrayList<>(x);
        ArrayList<Integer> x2;

        x2 = new ArrayList<>(x);

        x2.set(0, 1 - x2.get(0));
        val = calculValeur(x2);
        res = new ArrayList<>(x2);

        for (int i = 1; i < x.size(); ++i) {
            x2 = new ArrayList<>(x);
            x2.set(i, 1 - x2.get(i));
            courant = calculValeur(x2);

            if (courant < val && (!contrainte || sum(x2) > p)) {
                res = new ArrayList<>(x2);
                val = courant;

            }
        }
        return res;
    }

    public ArrayList<Integer> meilleur_voisin_non_Tabou(ArrayList<Integer> x, ArrayList<ArrayList<Integer>> tabou, boolean contrainte ) {
        int val;
        int courant;
        ArrayList<Integer> res = new ArrayList<>(x);
        ArrayList<Integer> x2;
        int j = 0;
        do {
            x2 = new ArrayList<>(x);
            x2.set(j, 1 - x2.get(j));
            val = calculValeur(x2);
            res = new ArrayList<>(x2);
            ++j;
        } while (tabou.contains(x2) && j < n);


        for (int i = j; i < x.size(); ++i) {
            x2 = new ArrayList<>(x);
            x2.set(i, 1 - x2.get(i));
            courant = calculValeur(x2);
            if (courant < val && !tabou.contains(x2) && (!contrainte || sum(x2) > p)) {
                res = new ArrayList<>(x2);
                val = courant;
            }
        }
        if (tabou.contains(res)) {
            return null;
        }
        return res;
    }


    private int sum(ArrayList<Integer> l) {
        int sum = 0;
        for (Integer i : l) {
            sum += i;
        }
        return sum;
    }


}


