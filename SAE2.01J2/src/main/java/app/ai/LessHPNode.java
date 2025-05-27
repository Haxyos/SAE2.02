package app.ai;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.model.entity.Entity;
import app.model.entity.Monster;
import app.model.entity.Player;

public class LessHPNode<Spell> extends FightNode<Spell>{
	
	public LessHPNode(INode<Spell> parent, int i, Spell path, Player player2, Monster monster) {
		super(i, parent, path, player2, monster);
	}
	@Override
	public Map<INode<Spell>, Integer> getNeighbors() {
		Map<INode<Spell>, Integer> map = new HashMap<>();
		for (app.model.fight.Spell s : player.availableSpells()) {
			Player nouveauJoueur = this.player.clone();
			Monster nouveauMonstre = this.monstre.clone();
			if (s.isSelfSpell()) {
				s.applyEffect(nouveauJoueur);
			} else {
				s.applyEffect(nouveauMonstre);
			}
			LessHPNode<Spell> voisin = new LessHPNode<>(
					this, this.getCost() + s.getCost(), (Spell) s, nouveauJoueur, nouveauMonstre
			);
			map.put(voisin, voisin.getCost());
		}
		return map;
	}

	@Override
	public int getHeuristic() {
		return super.player.getMaximumHP() - super.player.getCurrentHP();
	}
	public Entity getPlayer() {
		return super.player;
	}
	
	public Entity getMonster() {
		return super.monstre;
	}

}
