package app.ai;

import app.model.entity.Monster;
import app.model.entity.Player;

public class LessManaNode extends FightNode implements INode{

	private Monster monstre;
	private Player player;

    public LessManaNode(Object o, int i, Object o1, Player player, Monster monster) {
        super(o, i, o1);
        this.player = player;
        this.monstre = monster;
    }
}
