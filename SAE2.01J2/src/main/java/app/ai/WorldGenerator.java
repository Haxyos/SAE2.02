package app.ai;

import app.model.entity.Monster;
import app.model.entity.Player;
import app.model.exceptions.MauvaisPourcentageException;
import app.model.exceptions.NotEnoughtPlaceException;
import app.model.map.Place;
import app.model.map.World;

public class WorldGenerator {
	public static World createWorld(String name, int nbPlace, Player p, float percentageEndPoint, float percentageStartPoint, float percentageDefeatPoint, float percentageCoverage) throws NotEnoughtPlaceException, MauvaisPourcentageException {
		World world = new World(name);
		if(nbPlace < 2) {
			throw new NotEnoughtPlaceException("Il n'y a pas assez de places dans le monde");
		}
		float totalPourcentage = percentageEndPoint + percentageStartPoint + percentageDefeatPoint;
		if(totalPourcentage >= 1 || totalPourcentage <= 0) {
			throw new MauvaisPourcentageException("Mauvaise répartition des pourcentages du monde");
		}
		
		int id = 0;
		
		//Création des places de Début
		for (int i = 0; i < nbPlace*percentageStartPoint; i++) {
			int maxHP = (int) (Math.random()%250);
			Monster monstre = new Monster("", maxHP, maxHP, (int) Math.random()%25, (int) (Math.random()%25));
			if (Math.random()%2 == 1) {
				monstre = null;
			}
			id++;
			Place place = new Place(i, "", monstre, "", world, true, false, false);
			world.addPlace(place);
		}
		
		//Création des places de fin
		for (int i = 0; i < nbPlace*percentageEndPoint; i++) {
			int maxHP = (int) (Math.random()%250);
			Monster monstre = new Monster("", maxHP, maxHP, (int) Math.random()%25, (int) (Math.random()%25));
			if (Math.random()%2 == 1) {
				monstre = null;
			}
			id++;
			Place place = new Place(i, "", monstre, "", world, false, true, false);
			world.addPlace(place);
		}
		
		//Création des Places piège
		for (int i = 0; i < nbPlace; i++) {
			int maxHP = (int) (Math.random()%250);
			Monster monstre = new Monster("", maxHP, maxHP, (int) Math.random()%25, (int) (Math.random()%25));
			if (Math.random()%2 == 1) {
				monstre = null;
			}
			id++;
			Place place = new Place(i, "", monstre, "", world, false, false, true);
			world.addPlace(place);
		}
		
		//Création des Places neutre
		
		if(totalPourcentage < 1) {
			totalPourcentage = 1- totalPourcentage;
			for (int i = 0; i < nbPlace*totalPourcentage; i++) {
				int maxHP = (int) (Math.random()%250);
				Monster monstre = new Monster("", maxHP, maxHP, (int) Math.random()%25, (int) (Math.random()%25));
				if (Math.random()%2 == 1) {
					monstre = null;
				}
				id++;
				Place place = new Place(i, "", monstre, "", world, false, false, false);
				world.addPlace(place);
			}
		}
		return world;
	}
}
