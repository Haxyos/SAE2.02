package app.ai;

import app.model.entity.Entity;
import app.model.entity.Monster;
import app.model.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LessManaNode<Spell> extends FightNode<Spell>{

	
	public LessManaNode(INode<Spell> parent, int i, Spell path, Player player2, Monster monster) {
		super(i, parent, path, player2, monster);
	}

	@Override
	public Map<INode<Spell>, Integer> getNeighbors() {
		// Initialisation d'une nouvelle map pour stocker les voisins et leur coût
		Map<INode<Spell>, Integer> map = new HashMap<>();

		// === Attaque de base (coût 0) ===
		// Clonage des objets Player et Monster pour générer un nouvel état après une attaque
		Player joueurAttaque = this.player.clone();
		Monster monstreAttaque = this.monstre.clone();

		// Le joueur attaque le monstre
		joueurAttaque.attack(monstreAttaque);

		// Application des effets actifs sur le joueur et le monstre
		joueurAttaque.applyEffects();
		monstreAttaque.applyEffects();

		// Si le monstre n'est pas mort après l'attaque, il contre-attaque le joueur
		if (!monstreAttaque.isDead()) {
			monstreAttaque.attack(joueurAttaque);
		}

		// Si le joueur n'est pas mort après la contre-attaque, un nouveau noeud de type LessManaNode est créé
		if (!joueurAttaque.isDead()) {
			LessManaNode<Spell> attaqueNode = new LessManaNode<>(
					this,                       // Référence au noeud parent (le noeud actuel)
					this.getCost(),             // Coût actuel (l'attaque de base a un coût nul, donc pas d'ajout)
					null,                       // Pas d'action spécifique (attaque de base)
					joueurAttaque,              // Nouvel état du joueur après l'attaque
					monstreAttaque              // Nouvel état du monstre après l'attaque
			);
			// Ajout de ce nouveau noeud dans la map des voisins avec son coût
			map.put(attaqueNode, attaqueNode.getCost());
		}

		// === Gestion des sorts disponibles pour le joueur ===
		// Parcourt des sorts disponibles pour le joueur
		for (app.model.fight.Spell s : player.availableSpells()) {
			// Clonage des objets Player et Monster pour simuler les résultats d'un sort
			Player nouveauJoueur = this.player.clone();
			Monster nouveauMonstre = this.monstre.clone();


				// Appliquer les effets du sort : concerne les impacts (sur le monstre ou le joueur)
				if (s.isSelfSpell()) {
					nouveauJoueur.castSpell(s, nouveauJoueur);
					nouveauJoueur.applyEffects(); // Application des effets sur le joueur
				} else {
					nouveauJoueur.castSpell(s, nouveauMonstre);
					nouveauMonstre.applyEffects(); // Application des effets sur le monstre
				}

				// Application des effets existants sur le joueur et le monstre après le sort
				nouveauJoueur.applyEffects();
				nouveauMonstre.applyEffects();

				// Si le monstre survit, il contre-attaque
				if (!nouveauMonstre.isDead()) {
					nouveauMonstre.attack(nouveauJoueur);
				}

				// Si le joueur survit, création d'un nœud voisin pour cet état
				if (!nouveauJoueur.isDead()) {
					LessManaNode<Spell> voisin = new LessManaNode<>(
							this,                         // Parent
							this.getCost() + s.getCost(), // Coût mis à jour (mana consommé)
							(Spell) s,                    // Sort utilisé
							nouveauJoueur,                // État actualisé du joueur
							nouveauMonstre                // État actualisé du monstre
					);
					// Ajout du voisin à la map avec son coût
					map.put(voisin, voisin.getCost());
				}

			}

		// Retourne la map contenant les voisins générés et leurs coûts
		return map;
	}

	@Override
	public int getHeuristic() {
		return super.player.getMaximumMana() - super.player.getCurrentMana();
	}
	
	public Entity getPlayer() {
		return super.player;
	}
	
	public Entity getMonster() {
		return super.monstre;
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true; // Vérification si c'est le même objet
		if (o == null || getClass() != o.getClass()) return false; // Vérification du type de l'objet

		// Comparaison des attributs
		LessManaNode<?> that = (LessManaNode<?>) o;
		return cost == that.cost &&                // Le coût doit être identique
				Objects.equals(player, that.player) && // L'état du joueur doit être identique
				Objects.equals(monstre, that.monstre) && // L'état du monstre doit être identique
				Objects.equals(path, that.path);     // Le chemin (spell/action) doit être identique
	}

	@Override
	public int hashCode() {
		// Génération du hash basé sur les mêmes attributs utilisés dans equals
		return Objects.hash(cost, player, monstre, path);
	}

}
