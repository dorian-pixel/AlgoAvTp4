package algo;

import java.util.ArrayList;

public class VoyageCommerceHillClimbing {



    VoyageCommerxe v;
    private  final int MAX_DEPL = 200;
    private  final int MAX_ESSAIE = 10;
    private final int TAILLE_TABOU = 100000;

    public VoyageCommerceHillClimbing(VoyageCommerxe x) {
        this.v = x;
    }



    public ArrayList<Integer> Steepest_Hill_Climbing(ArrayList<Integer> s){
        ArrayList<Integer> sPrime;
        int nb_depl = 0;
        boolean stop = false;
        do {
            sPrime = new ArrayList<>(v.meilleurVoison(s));
            if (v.calculVal(sPrime) < v.calculVal(s)) {
                s = new ArrayList<>(sPrime);
            } else {
                stop = true;
            }
            ++nb_depl;
        } while (nb_depl < MAX_DEPL && !stop);
        System.out.print(" /  dep =  " + nb_depl + "  / ");
        return s;
    }


    public ArrayList<Integer> Steepest_Hill_Climbing_redem(){
        ArrayList<Integer> s = new ArrayList<Integer>(v.solutionInitiale());
        ArrayList<Integer> meilleur;
        ArrayList<Integer> courant;
        double val, valCourante;
        System.out.print("Essaie 0  initial = " + s );
        meilleur = new ArrayList<>(Steepest_Hill_Climbing(s));
        val = v.calculVal(meilleur);
        System.out.println("arrivé = " + meilleur + "dist = " + val);
        for (int i = 0; i < MAX_ESSAIE - 1; ++i) {
            s = new ArrayList<Integer>(v.solutionInitiale());
            System.out.print("Essaie "+ String.valueOf(i+1) +"  initial = " + s );
            courant = new ArrayList<>(Steepest_Hill_Climbing(s));
            valCourante = v.calculVal(s);
            System.out.println("arrivé = " + courant + " dist = " + valCourante);
            if (valCourante < val) {
                meilleur = new ArrayList<>(courant);
                val = valCourante;
            }
        }
        return meilleur;
    }


    public ArrayList<Integer> tabou(ArrayList<Integer> s) {
        ArrayList<ArrayList<Integer>> tabou = new ArrayList<>(TAILLE_TABOU);
        int nb_depl = 0;
        boolean stop = false;
        ArrayList<Integer> sol = new ArrayList<>(s);
        ArrayList<Integer> sPrime = new ArrayList<>();
        // System.out.println("Initial = " + s);
        do {
            if (v.meilleur_voisin_non_Tabou(s, tabou) != null) {
                sPrime = new ArrayList<>(v.meilleur_voisin_non_Tabou(s, tabou));
            } else {
                stop = true;
            }
            if (tabou.size() < TAILLE_TABOU) {
                tabou.add(s);
            }

            if (v.calculVal(sPrime) < v.calculVal(sol)) {
                sol = new ArrayList<>(sPrime);
            }
            s = new ArrayList<>(sPrime);
            ++nb_depl;

        } while (nb_depl < MAX_DEPL && !stop);

        //System.out.println("arrivé = " + sol);
        System.out.println( "distance = " + v.calculVal(sol));
        System.out.println("nb dep = " + nb_depl);

        System.out.println(tabou.size());

        return sol;
    }

}
