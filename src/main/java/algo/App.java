package algo;

import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {


        Probleme p = new Probleme("partition6.txt");
        HillClimbing h = new HillClimbing(p);



        ArrayList<Integer> liste1Ex1 = new ArrayList<>();
        liste1Ex1.add(0);
        liste1Ex1.add(1);
        liste1Ex1.add(1);
        liste1Ex1.add(0);
        liste1Ex1.add(1);
        liste1Ex1.add(1);




        p.meilleurVoisin(liste1Ex1, false);
        p.calculValeur(liste1Ex1);

        h.Steepest_Hill_Climbing_redem(false);
        h.tabou(liste1Ex1, false);


       VoyageCommerxe v2 = new VoyageCommerxe("tsp101.txt");
       VoyageCommerceHillClimbing v1 = new VoyageCommerceHillClimbing(v2);

       ArrayList<Integer> liste1VC = new ArrayList<>();
        liste1VC.add(5);
        liste1VC.add(3);
        liste1VC.add(4);
        liste1VC.add(1);
        liste1VC.add(2);

        v1.Steepest_Hill_Climbing(liste1VC);
        v1.Steepest_Hill_Climbing_redem();
        v1.tabou(liste1VC);

        v2.meilleurVoison(liste1VC);
        v2.calculVal(liste1VC);




        int sommeHazard = 0;
        int sommeHillClimbing = 0;
        int sommeTabou = 0;


        for (int i = 0; i < 100 ; ++i) {
            sommeHazard += v2.calculVal(v2.solutionInitiale());
            sommeHillClimbing += v2.calculVal(v1.Steepest_Hill_Climbing_redem());
            sommeTabou += v2.calculVal(v1.tabou(v2.solutionInitiale()));
        }
        // moyenne
        sommeHazard = sommeHazard / 100;
        sommeHillClimbing = sommeHazard / 100;
        sommeTabou = sommeHazard / 100;

        System.out.println(sommeHazard);
        System.out.println(sommeHillClimbing);
        System.out.println(sommeTabou);






    }
}
