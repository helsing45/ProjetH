package TP1;/* INF4230 - Intelligence artificielle
 * UQAM / Département d'informatique
 * Hiver 2017
 */

import java.awt.geom.Point2D;

import static TP1.Etat.*;

public class Route {


    public Route(Emplacement origine, Emplacement destination) {
        this.origine = origine;
        this.destination = destination;
    }

    public double getLongueur() {
        return origine.positionGeographique.distance(destination.positionGeographique);
    }

    public String getAction() {
        return getAction(origine.getPositionGeographique(), destination.getPositionGeographique());
    }

    public static String getAction(Point2D origine, Point2D destination) {
        double origineX = origine.getX(),
                origineY = origine.getY(),
                destinationX = destination.getX(),
                destinationY = destination.getY();
        if (origineX == destinationX && origineY == destinationY) return ""; //Il n'a pas bouger

        if (origineX == destinationX) {
            //Mouvement vertical
            return origineY > destinationY ? ACTION_NORD : ACTION_SUD;
        }
        return destinationX > origineX ? ACTION_EST : ACTION_OUEST;

    }

    public double obtenirCoutRoute(double vitesse) {
        double coutDeplacementNormal = origine.positionGeographique.distance(destination.positionGeographique) * vitesse;
        return coutDeplacementNormal + destination.getPenalité();
    }

    protected Emplacement origine;
    protected Emplacement destination;


}
