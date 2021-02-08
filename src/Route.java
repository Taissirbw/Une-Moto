import java.awt.*;
import java.util.ArrayList;


/** Classe Route: assure la construction infinie de la route
 * */
public class Route {

    /**L'ensemble de points qui constitue la route*/
    private ArrayList<Point> ligneRoute;

    /**Distance entre les extrémités de la route et le centre*/
    int gap = 100;
    /**L'extremité gauche de la route*/
    private ArrayList<Point> ligneRouteG =new ArrayList<>();

    /**L'extremité droite de la route*/
    private ArrayList<Point> ligneRouteD =new ArrayList<>();

    public Route(){
        this.ligneRoute =new ArrayList<>();
        createRoute(); //création de la route
         }

    /** création de la route*/
    public void createRoute(){


        ligneRoute.add(new Point(Affichage.WIDTH/2 , Affichage.HEIGHT));
        /*ligneRoute.add(new Point(Affichage.WIDTH/2 - Affichage.getLargeurMoto()/2, Affichage.HEIGHT - Affichage.getHauteurMoto()/2 - 20));
        ligneRoute.add(new Point(Affichage.WIDTH/2 + Affichage.WIDTH/10 - 10, Affichage.HEIGHT - Affichage.HEIGHT/2 + 10));
        ligneRoute.add(new Point(Affichage.WIDTH/ 2 - 60, Affichage.HEIGHT - Affichage.HEIGHT/4 - Affichage.HEIGHT/4));*/
        ligneRoute.add(new Point(Affichage.WIDTH/ 2, Affichage.getHorizon()));
        for( Point p: ligneRoute){
            ligneRouteG.add(new Point(p.x - gap, p.y));
            ligneRouteD.add(new Point(p.x + gap, p.y));
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
