package algo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Math.random;

public class VoyageCommerxe {


    private ArrayList<Ville> listeVilles;
    private int n;


    public VoyageCommerxe(String fileName) {
        rechercheFichier(fileName);
    }

    public void rechercheFichier(String fileName){
        Scanner lecteur ;
        int x, y, num;
        File fichier = new File(fileName);
        try {
            lecteur = new Scanner(fichier);
            n = lecteur.nextInt();
            listeVilles = new ArrayList<>(n);
            while (lecteur.hasNextInt()) {
                num = lecteur.nextInt();
                x = lecteur.nextInt();
                y = lecteur.nextInt();
                listeVilles.add(new Ville(x, y, num));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }


    public ArrayList<Integer> solutionInitiale() {
        ArrayList<Ville> copy = new ArrayList<>(listeVilles);
        ArrayList<Integer> res = new ArrayList<>(n);
        int x = n;
        int v;
        for (int i =0; i < n; ++i) {
            v = (int) (random() * (x));
            res.add(copy.get(v).getNum());
            copy.remove(v);
            --x;
        }
        return res;
    }

    public double calculVal(ArrayList<Integer> v) {
        double distance = 0;
        int numPt1, numPt2;
        Ville villeDep = new Ville(0, 0, -1);

        distance += villeDep.distance(listeVilles.get(v.get(0) - 1));

        for (int i = 0; i < v.size() - 1; ++i) {
            numPt1 = v.get(i);
            numPt2 = v.get(i+1);
            distance += listeVilles.get(numPt1 - 1).distance(listeVilles.get(numPt2 - 1));
        }
        distance += listeVilles.get(v.get(v.size() - 1) - 1).distance(villeDep);
    return distance;
    }


    private ArrayList<Integer> permut(ArrayList<Integer> l, int ind1, int ind2){
        int i = l.get(ind1);
        l.set(ind1, l.get(ind2));
        l.set(ind2, i);
        return l;
    }


    public ArrayList<Integer> meilleurVoison(ArrayList<Integer> v){
        ArrayList<Integer> copy = new ArrayList<>(v);
        ArrayList<Integer> courant;
        ArrayList<Integer> meilleur = new ArrayList<>(permut(copy, 0, 1));
        double meilleurVal = calculVal(meilleur);
        double valCourante;

        for (int i = 0; i < v.size(); ++i) {

            for (int j = i + 1; j < v.size(); ++j) {
                copy = new ArrayList<>(v);
                courant = new ArrayList<>(permut(copy, i, j));
                valCourante  = calculVal(courant);
                if (valCourante < meilleurVal) {
                    meilleur = new ArrayList<>(courant);
                    meilleurVal = valCourante;
                }
            }

        }
        return meilleur;

    }




    public ArrayList<Integer> meilleur_voisin_non_Tabou(ArrayList<Integer> x, ArrayList<ArrayList<Integer>> tabou ) {
        double val;
        double courant;
        ArrayList<Integer> res = new ArrayList<>(x);
        ArrayList<Integer> x2;
        int i =0;
        int j = 1;
        do {
            x2 = new ArrayList<>(x);
            x2 = new ArrayList<>(permut(x2, i, j));
            val = calculVal(x2);
            res = new ArrayList<>(x2);
            ++j;
            if (j == x.size()) {
                ++i;
                j = i+1;
            }

        } while (tabou.contains(x2) && i < x.size() - 1);
        int k= i;
        for (i = k; i < x.size(); ++i) {
            for (j = i + 1; j < x.size(); ++j) {
                x2 = new ArrayList<>(x);
                x2 = new ArrayList<>(permut(x2, i, j));
                courant  = calculVal(x2);
                if (courant < val && !tabou.contains(x2)) {
                    res = new ArrayList<>(x2);
                    val = courant;
                }
            }
        }
        if (tabou.contains(res)) {
            return null;
        }
        return res;
    }


    private ArrayList<Integer> meilleurVoisinTest(ArrayList<Integer> x) {
        double val = 999999999;
        double courant;
        ArrayList<Integer> res = new ArrayList<>(x);
        ArrayList<Integer> x2;
        for (int i = 0; i < x.size(); ++i) {
            x2 = new ArrayList<>(x);

            for (int j = i + 2; j + 1 < x.size(); ++j) {

                if (listeVilles.get(x.get(i)).distance(listeVilles.get(x.get(i+1)))
                        + listeVilles.get(x.get(j)).distance(listeVilles.get(x.get(j+1))) >
                listeVilles.get(x.get(i)).distance(listeVilles.get(x.get(j)))
                        + listeVilles.get(x.get(i+1)).distance(listeVilles.get(x.get(j+1)))) {

                    int k  = j;
                    for (int h = i + 1; h != k; ++h) {
                        x2 = new ArrayList<>(permut(x2, j, i + 1));
                        --k;
                    }
                }

                courant  = calculVal(x2);
                if (courant < val) {
                    res = new ArrayList<>(x2);
                    val = courant;
                }

            }

        }
    return res;
    }

}
