import java.awt.*;
import java.util.ArrayList;


/** Classe Route: assure la construction infinie de la route
 * */
public class Route {

    /**L'ensemble de points qui constitue le route*/
    private ArrayList<Point> ligneRoute;

    public Route(){
        this.ligneRoute =new ArrayList<>();
        createRoute(); //création de la route
         }

    /** création de la route*/
    public void createRoute(){
        ligneRoute.add(new Point(Affichage.WIDTH/2 - 50, Affichage.HEIGHT));
        ligneRoute.add(new Point(Affichage.WIDTH/2 - Affichage.getLargeurMoto()/2, Affichage.HEIGHT - Affichage.getHauteurMoto()/2 - 20));
        ligneRoute.add(new Point(Affichage.WIDTH/2 + Affichage.WIDTH/10 - 10, Affichage.HEIGHT - Affichage.HEIGHT/2 + 10));
        ligneRoute.add(new Point(Affichage.WIDTH/ 2 - 60, Affichage.HEIGHT - Affichage.HEIGHT/4 - Affichage.HEIGHT/4));
        ligneRoute.add(new Point(Affichage.WIDTH/ 2, Affichage.getHorizon()));
    }

    /**---METHODES D'ACCES AUX VARIABLES DE LA CLASSE ROUTE---*/
    public ArrayList<Point> getLigneRoute() {
        return this.ligneRoute;
    }
}
