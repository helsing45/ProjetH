package TP1;/* INF4230 - Intelligence artificielle
 * UQAM / Département d'informatique
 * Hiver 2017
 */

import TP1.heuristique.Heuristique;

import java.util.*;

public class AStar {
    private static TreeSet<Etat> open, closed;
    private static Etat current;

    public static List<String> genererPlan(Etat etatInitial, But but, Heuristique heuristique) {
        List<String> plan = new ArrayList<>();
        plan.addAll(generatePath(etatInitial, etatInitial.getEmplacementBombe(0), heuristique));
        current.nbbombesCharges++;
        plan.add("Charger(r0,B)");
        plan.addAll(generatePath(current, etatInitial.planete.getSortie(), heuristique));
        plan.add("Decharger(r0,B)");
        return plan;
    }

    private static List<String> generatePath(Etat etatInitial, Emplacement but, Heuristique heuristique) {
        List<String> plan = new ArrayList<>();
        etatInitial.clear();
        open = new TreeSet<Etat>();
        closed = new TreeSet<Etat>();

        open.add(etatInitial);

        while (!open.isEmpty()) {
            current = open.pollFirst();
            LinkedList<Successeur> successeurs = current.genererSuccesseurs(heuristique, but);
            closed.add(current);

            if (current.emplacementHtepien == but) break;
            open.add(successeurs.getFirst().etat);
        }

        NavigableSet<Etat> path = closed.first() == etatInitial ? closed : closed.descendingSet();

        for (Etat etat : path) {
            if (path.higher(etat) != null)
                plan.add(printMovementEntre(etat, path.higher(etat)));
        }

        return plan;
    }

    private static String printMovementEntre(Etat origine, Etat destination) {
        return String.format("%1$s = Lieu %2$s -> Lieu %3$s)",
                Route.getAction(origine.emplacementHtepien.getPositionGeographique(), destination.emplacementHtepien.getPositionGeographique()),
                origine.emplacementHtepien.getNom(),
                destination.emplacementHtepien.getNom());
    }

}
