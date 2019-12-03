package algo;

import java.util.ArrayList;


public class HillClimbing {

    private final int MAX_ESSAIE = 100;
    Probleme p;
    private final int MAX_DEPL = 1000;
    private final int TAILLE_TABOU = 100;

    public HillClimbing(Probleme p) {
        this.p = p;
    }

    public ArrayList<Integer> Steepest_Hill_Climbing(ArrayList<Integer> s, boolean contrainte){

        ArrayList<Integer> sPrime;
        int nb_depl = 0;
        boolean stop = false;


        do {
            sPrime = new ArrayList<>(p.meilleurVoisin(s, contrainte));
            if (p.calculValeur(sPrime) < p.calculValeur(s)) {
                s = new ArrayList<>(sPrime);
            } else {
                stop = true;
            }
            ++nb_depl;

        } while (nb_depl < MAX_DEPL && !stop);
        return s;

    }

    public ArrayList<Integer> Steepest_Hill_Climbing_redem(boolean contrainte){
        ArrayList<Integer> s;
        ArrayList<Integer> meilleur;
        ArrayList<Integer> courant;
        int val, valCourante;

        s = new ArrayList<Integer>(p.solutionInitiale());
        meilleur = new ArrayList<>(Steepest_Hill_Climbing(s, contrainte));
        val = p.calculValeur(meilleur);
        for (int i = 0; i < MAX_ESSAIE - 1; ++i) {

            s = new ArrayList<Integer>(p.solutionInitiale());

            courant = new ArrayList<>(Steepest_Hill_Climbing(s, contrainte));
            valCourante = p.calculValeur(s);


            if (valCourante < val) {
                meilleur = new ArrayList<>(courant);
                val = valCourante;
            }

        }
        return meilleur;

    }

    public ArrayList<Integer> tabou(ArrayList<Integer> s, boolean contrainte) {
        ArrayList<ArrayList<Integer>> tabou = new ArrayList<>(TAILLE_TABOU);
        int nb_depl = 0;
        boolean stop = false;
        ArrayList<Integer> sol = new ArrayList<>(s);
        ArrayList<Integer> sPrime = new ArrayList<>();;
        do {
            if (p.meilleur_voisin_non_Tabou(s, tabou, contrainte) != null) {
                sPrime = new ArrayList<>(p.meilleur_voisin_non_Tabou(s, tabou, contrainte));
               // System.out.println(tabou);
               // System.out.println(sPrime);
               // System.out.println(nb_depl);
            } else {
                stop = true;
            }
            if (tabou.size() < TAILLE_TABOU) {
                tabou.add(s);
            }

            if (p.calculValeur(sPrime) < p.calculValeur(sol)) {
                sol = new ArrayList<>(sPrime);
            }
            s = new ArrayList<>(sPrime);
            ++nb_depl;

        } while (nb_depl < MAX_DEPL && !stop);

        return sol;
    }


}
