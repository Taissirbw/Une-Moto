import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;


/** Classe Route: assure la construction infinie de la route
 * */
public class Route {

    /** Liste des points qui constitue la route*/
    private ArrayList<Point> ligneRoute;

    /**Distance entre les bords de la route et le centre*/
    int gap = 50;
    /** Bord gauche de la route*/
    private ArrayList<Point> ligneRouteG =new ArrayList<>();
    /** Bord droite de la route*/
    private ArrayList<Point> ligneRouteD =new ArrayList<>();

    /** Les points de controle de la route */
    private ArrayList<Point> checkpoints =new ArrayList<>();
    /** Obstacle */
    private ArrayList<Obstacle> obstacles =new ArrayList<>();

    public static Random rand = new Random();


    /**
     * CONSTRUCTEUR DE LA CLASSE ROUTE
     *
     * */
    public Route() throws IOException {
        this.ligneRoute =new ArrayList<>();
        createRoute(); //création de la route
        for(int i =0; i < 3;i++){
            createObstacles();
        }
    }


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

        //Lorsque le deuxieme point de la route dépasse la fenetre en bas,
        if( this.ligneRoute.get(1).y > Affichage.getHEIGHT()){
            //on supprime le premier point de la route
            ligneRoute.remove(this.ligneRoute.get(0));
            ligneRouteG.remove(this.ligneRouteG.get(0));
            ligneRouteD.remove(this.ligneRouteD.get(0));
        }
    }

    /** --- POINTS DE CONTROLE DE LA ROUTE ---**/

    /** Création des points de controle*/
    public void createCheckpoint(){
        //La liste des points de controle n'est composée que de deux points
        final Point cp = new Point(this.ligneRouteG.get(5));
        final Point cp2 = new Point(this.ligneRouteD.get(5));
        checkpoints.add(cp);
        checkpoints.add(cp2);
    }

    /** Mise a jour des points de controle*/
    public void updateCheckpoint(){
        //Verifie que la liste n'est pas vide
        if(!this.checkpoints.isEmpty()) {
            //L'ordonnée des points se déplacent en meme temps que la route
            this.checkpoints.get(0).setLocation(this.checkpoints.get(0).x-10,this.checkpoints.get(0).y + Affichage.getMove());
            this.checkpoints.get(1).setLocation(this.checkpoints.get(1).x+10,this.checkpoints.get(1).y + Affichage.getMove());
            //La liste est vidée quand les points dépasse l'horizon
            if (this.checkpoints.get(0).y > Affichage.getHEIGHT())
                checkpoints.clear();
        }
    }

    /** --- OBSTACLES DE LA ROUTE ---**/

    /** Création d'un obstacle*/
    public void createObstacles() throws IOException {
        int ajouter = rand.nextInt(100); //on choisit un nombre aléatoire entre 0 et 100
        if(ajouter < 20) { //la probabilité de créer des obstacles
            int randX = rand.nextInt(Affichage.getWIDTH() - 10) + 10;
            int randY = rand.nextInt(Affichage.getHorizon() - 10) + 10;
            //Création de l'obstacle
            Obstacle o = new Obstacle(randX, randY);
            obstacles.add(o);
        }
    }

    /** Mise a jour des obstacles*/
    public void updateObstacles(){
        //Verifie que la liste n'est pas vide
        if(!this.obstacles.isEmpty()) {
            for(int i = 0; i<this.obstacles.size(); i++){
                //L'ordonnée des obstacles se déplacent en meme temps que la route
                this.obstacles.get(i).getPos().setLocation( this.obstacles.get(i).getPos().getX(),
                        this.obstacles.get(i).getPos().getY() + Affichage.getMove() );
                //L'obstacle est supprimé quand il sort de la fenetre
                if (this.obstacles.get(i).getPos().getY() > Affichage.getHEIGHT())
                    this.obstacles.remove(this.obstacles.get(i));
            }
        }
    }


    /**---METHODES D'ACCES AUX ATTRIBUTS DE LA CLASSE ROUTE---*/
    public ArrayList<Point> getLigneRoute() { return this.ligneRoute; }
    public int getGap() { return gap; }
    public ArrayList<Point> getLigneRouteG() { return ligneRouteG; }
    public ArrayList<Point> getLigneRouteD() { return ligneRouteD; }
    public ArrayList<Point> getCheckpoints() { return checkpoints; }
    public ArrayList<Obstacle> getObstacles() { return obstacles; }

}
