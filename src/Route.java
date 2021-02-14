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

    int routeWIDTH = 200;

    public Route(){
        this.ligneRoute =new ArrayList<>();
        createRoute(); //création de la route
         }

    /** création de la route*/
    public void createRoute(){

        for(int i = Affichage.getHEIGHT(); i > Affichage.getHorizon(); i-=50) {
            //Variation au niveau de l'abscisse de chaque point
            int abs = rand.nextInt((((Affichage.getWIDTH() / 2) + gap) - ((Affichage.getWIDTH()/ 2) - gap))) + ((Affichage.getWIDTH() / 2) - gap);
            ligneRoute.add(new Point(abs, i));
        }
        //Création du dernier point
        int absL = rand.nextInt((((Affichage.getWIDTH() / 2) + gap) - ((Affichage.getWIDTH()/ 2) - gap))) + ((Affichage.getWIDTH() / 2) - gap); //la valeur du random est bornée
        ligneRoute.add(new Point(absL, Affichage.getHorizon()));

        int coeff = (Affichage.getHEIGHT() - Affichage.getHorizon())/((Affichage.getWIDTH()/2 - routeWIDTH) - (Affichage.getWIDTH()/2));
        int coeffD = ( Affichage.getHorizon() - Affichage.getHEIGHT())/(Affichage.getWIDTH()/2 - (Affichage.getWIDTH()/ + routeWIDTH));
        //int coeffD = (Affichage.getHorizon() - Affichage.getHEIGHT())/( (Affichage.getWIDTH()/2) - (Affichage.getWIDTH()/2 + routeWIDTH));
        System.out.println(coeffD);
        for( Point p: ligneRoute){
            /*calcule la déformation de la largeur de la piste à l'écran en fonction de la profondeur.
            * Ici la profondeur est représentée par p.y par une projection. */

            //ligneRouteG.add(new Point((p.y - Affichage.getHEIGHT())/coeff + gap, p.y));
            //ligneRouteD.add(new Point((p.y - Affichage.getHEIGHT())/-coeffD, p.y));


            int coeffProfondeur = gap * 2 * (p.y / (Affichage.HEIGHT - Affichage.getHorizon()));
            ligneRouteG.add(new Point(p.x - gap , p.y));
            ligneRouteD.add(new Point(p.x + gap  , p.y));
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

        //Lorsque le dernier point de la route est visible en dépassant l'horizon, un nouveau point est créé
        if( this.ligneRoute.get((this.ligneRoute.size() -1)).y >= Affichage.getHorizon()){
            int absL = rand.nextInt((((Affichage.getWIDTH() / 2) + gap) - ((Affichage.getWIDTH()/ 2) - gap))) + ((Affichage.getWIDTH() / 2) - gap);
            ligneRoute.add(new Point(absL, Affichage.getHorizon() - 30));
            ligneRouteG.add(new Point(absL - gap, Affichage.getHorizon() - 30));
            ligneRouteD.add(new Point(absL + gap, Affichage.getHorizon() - 30));
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
