package app.ai;

import app.ai.INode;
import app.ai.LessHPNode;
import app.ai.LessManaNode;
import app.ai.LessTurnNode;
import app.model.entity.Monster;
import app.model.entity.Player;
import app.model.fight.Spell;
import app.model.fight.spells.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SolverTest {

    @Test
    public void testLessManaNode1() {
        Player player = new Player(100, 100, 10, 20, 100);
        Monster monster = new Monster("", 100, 100, 10, 20);
        LessManaNode start = new LessManaNode(null, 0, null, player, monster);

        Solver<Spell> solver = new Solver<>();
        List<INode<Spell>> l = solver.AStar(start).rebuildPath();

        assertEquals(l.stream().map(INode::getCost).reduce(0, Integer::sum), 0);
    }

    @Test
    public void testLessManaNode2() {
        Player player = new Player(100, 100, 10, 20, 100, 100, new Heal(4, 10, 30),
                new Poison(4, 20, 80),
                new ManaGain(4, 20, 30),
                new Strength(2, 10, 30),
                new Shield(3, 10, 25));
        
        Monster monster = new Monster("", 210, 210, 10, 20);

        LessManaNode start = new LessManaNode(null, 0, null, player, monster);

        Solver<Spell> solver = new Solver<>();

        List<INode<Spell>> l = solver.AStar(start).rebuildPath();
        int i=0;
        for (INode<Spell> node : l) {
            System.out.println((i++) +" Nœud : " + node.getPath());
            System.out.println("Coût jusqu'à ce nœud : " + node.getCost());

            // Supposons que votre nœud contient des informations sur le joueur et le monstre
            if (node instanceof LessManaNode) {
                LessManaNode<Spell> lessManaNode = (LessManaNode<Spell>) node;

                // Affichage de l'état du joueur
                System.out.println("État du joueur : " + lessManaNode.getPlayer());

                // Affichage de l'état du monstre
                System.out.println("État du monstre : " + lessManaNode.getMonster());
            }

            System.out.println("--------------------------------");
        }
        assertEquals(l.get(l.size() - 1).getCost(), 150);
    }

    @Test
    public void testLessHPNode1() {
        Player player = new Player(100, 100, 10, 20, 100, 100,
                new Poison(4, 20, 80),
                new ManaGain(4, 20, 30),
                new Shield(4, 10, 25));

        Monster monster = new Monster("", 210, 210, 10, 20);

        LessHPNode start = new LessHPNode(null, 0, null, player, monster);

        Solver<Spell> solver = new Solver<>();

        List<INode<Spell>> l = solver.AStar(start).rebuildPath();

        LessHPNode solution = (LessHPNode) l.get(l.size() - 1);
        assertEquals(l.get(l.size() - 1).getCost(), 20);
    }

    @Test
    public void testLessTurnNode1() {
        Player player = new Player(100, 100, 10, 20, 100, 100,
                new Poison(4, 20, 80),
                new ManaGain(4, 20, 30),
                new Shield(4, 10, 25));

        Monster monster = new Monster("", 210, 210, 10, 20);

        LessTurnNode start = new LessTurnNode(0, null, null, player, monster);

        Solver<Spell> solver = new Solver<>();

        List<INode<Spell>> l = solver.AStar(start).rebuildPath();

        assertEquals(l.size(), 11);
    }
    /*
    @Test
    public void canBeat() {
        Player player = new Player(100, 100, 10, 20, 100, 100,
                new Poison(4, 20, 80),
                new ManaGain(4, 20, 30),
                new Shield(4, 10, 25));


        Monster monster = new Monster("", 210, 210, 10, 20);

        assertTrue(Solver.playerCanBeat(player, monster));
        assertFalse(Solver.playerCanBeat(player, new Monster("", 2500, 2500, 10, 20)));
        assertFalse(Solver.playerCanBeat(player, new Monster("", 100, 100, 10, 500)));
        assertTrue(Solver.playerCanBeat(player, new Monster("", 1, 1, 1, 5000)));
    }*/
}
