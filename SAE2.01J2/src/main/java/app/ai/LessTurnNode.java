package app.ai;

import app.model.entity.Entity;
import app.model.entity.Monster;
import app.model.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class LessTurnNode<Spell> extends FightNode<Spell>{
	
	public LessTurnNode(int cost, INode<Spell> parent, Spell path, Player player, Monster monster) {
		super(cost, parent, path, player, monster);
		this.player = player;
		this.monstre = monster;
		
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
			LessTurnNode<Spell> voisin = new LessTurnNode(
					this.getCost() + s.getCost(), this, (Spell) s, nouveauJoueur, nouveauMonstre
			);
			map.put(voisin, voisin.getCost());
		}
		return map;
	}

	public int getHeuristic() {
		return (int) Math.ceil(super.monstre.getCurrentHP()/super.player.getAttack());
		
	}
	
	public Entity getPlayer() {
		return super.player;
	}
	
	public Entity getMonster() {
		return super.monstre;
	}
}
