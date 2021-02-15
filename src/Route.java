import java.awt.*;
import java.util.ArrayList;
import java.util.Random;


/** Classe Route: assure la construction infinie de la route
 * */
public class Route {

    /**L'ensemble de points qui constitue la route*/
    private ArrayList<Point> ligneRoute;

    /**Distance entre les extrémités de la route et le centre*/
    int gap = 50;
    /**L'extremité gauche de la route*/
    private ArrayList<Point> ligneRouteG =new ArrayList<>();

    /**L'extremité droite de la route*/
    private ArrayList<Point> ligneRouteD =new ArrayList<>();

    public static Random rand = new Random();

    private static final int fuiteY = 70;


    public Route(){
        this.ligneRoute =new ArrayList<>();
        createRoute(); //création de la route
         }


    /** création de la route*/
    /*public void createRoute(){

        //Création de points qui dépassent l'horizon
        for(int i = Affichage.getHEIGHT(); i > 0; i-=50) {
            //Variation au niveau de l'abscisse de chaque point
            int abs = rand.nextInt((((Affichage.getWIDTH() / 2) + gap) - ((Affichage.getWIDTH()/ 2) - gap))) + ((Affichage.getWIDTH() / 2) - gap);
            ligneRoute.add(new Point(abs, i));
        }



        for( Point p: ligneRoute){
            /*calcule la déformation de la largeur de la piste à l'écran en fonction de la profondeur.
             * Ici la profondeur est représentée par p.y par une projection. */
           /* int coeffProfondeur = gap * ((p.y) / (Affichage.HEIGHT - Affichage.getHorizon()));
            ligneRouteG.add(new Point(p.x - gap , p.y));
            ligneRouteD.add(new Point(p.x + gap , p.y));
        }
    }*/


    /** création de la route*/

    public void createRoute(){


        ligneRoute.add(new Point(Affichage.WIDTH/2 , Affichage.HEIGHT));


        ligneRoute.add(new Point(Affichage.WIDTH/2 - Affichage.getLargeurMoto()/2, Affichage.HEIGHT - Affichage.getHauteurMoto()/2 - 20));
        ligneRoute.add(new Point(Affichage.WIDTH/2 + Affichage.WIDTH/10 - 10, Affichage.HEIGHT - Affichage.HEIGHT/3));
        ligneRoute.add(new Point(Affichage.WIDTH/ 2 - 60, Affichage.HEIGHT - Affichage.HEIGHT/2));



        ligneRoute.add(new Point(Affichage.WIDTH/ 2, Affichage.getHorizon()));

        for( Point p: ligneRoute){
            /*calcule la déformation de la largeur de la piste à l'écran en fonction de la profondeur.
             * Ici la profondeur est représentée par p.y par une projection. */
            int coeffProfondeur = gap * ((p.y) / (Affichage.HEIGHT - Affichage.getHorizon()));
            ligneRouteG.add(new Point(p.x - gap - coeffProfondeur, p.y));
            ligneRouteD.add(new Point(p.x + gap + coeffProfondeur, p.y));
        }
    }

    /** animation de la route*/
    public void updateRoute(){

        //Chaque points des lignes qui composent la route voient leur ordonée augmenter
        for (int i = 0; i < this.ligneRoute.size() ; i++){
            //int coeffProfondeur = gap * 2 * (this.ligneRoute.get(i).y / (Affichage.HEIGHT - this.ligneRoute.get(i).y));
            this.ligneRoute.get(i).setLocation(this.ligneRoute.get(i).x,this.ligneRoute.get(i).y + Affichage.getMove());
            this.ligneRouteD.get(i).setLocation(this.ligneRouteD.get(i).x  ,this.ligneRouteD.get(i).y + Affichage.getMove());
            this.ligneRouteG.get(i).setLocation(this.ligneRouteG.get(i).x  ,this.ligneRouteG.get(i).y + Affichage.getMove());
        }



        //Lorsque le dernier point de la route entre dans la fenetre, un nouveau point est créé
        if( this.ligneRoute.get((this.ligneRoute.size() -1)).y >= 0){
            int absL = rand.nextInt((((Affichage.getWIDTH() / 2) + gap) - ((Affichage.getWIDTH()/ 2) - gap))) + ((Affichage.getWIDTH() / 2) - gap);

            ligneRoute.add(new Point(absL, this.ligneRoute.get((this.ligneRoute.size() -1)).y - 50));
            ligneRouteG.add(new Point(absL - gap, this.ligneRoute.get((this.ligneRoute.size() -2)).y - 50)); //Indice -2 car on vient d'ajouter un point a la liste ligneRoute
            ligneRouteD.add(new Point(absL + gap, this.ligneRoute.get((this.ligneRoute.size() -2)).y - 50));
        }





    }

    /**---METHODES D'ACCES AUX VARIABLES DE LA CLASSE ROUTE---*/
    public ArrayList<Point> getLigneRoute() {
        return this.ligneRoute;
    }

    public int getGap() {
        return gap;
    }

    public ArrayList<Point> getLigneRouteG() {
        return ligneRouteG;
    }

    public ArrayList<Point> getLigneRouteD() {
        return ligneRouteD;
    }
}
