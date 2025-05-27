package app.ai;

import java.util.Random;

import app.model.entity.Monster;
import app.model.entity.Player;
import app.model.exceptions.MauvaisPourcentageException;
import app.model.exceptions.NotEnoughtPlaceException;
import app.model.map.Path;
import app.model.map.Place;
import app.model.map.World;
import app.model.parser.JSONObject;
import app.model.parser.JSONParser;

public class WorldGenerator {
	public static World createWorld(String name, int nbPlace, Player p, float percentageEndPoint,
			float percentageStartPoint, float percentageDefeatPoint, float percentageCoverage)
					throws NotEnoughtPlaceException, MauvaisPourcentageException {
		World world = new World(name);
		WorldAnalyzer analyze = new WorldAnalyzer(world);
		Random rand = new Random();
		while (analyze.isFinishable() == false) {
			world = new World(name);
			if (nbPlace < 2) {
				throw new NotEnoughtPlaceException("Il n'y a pas assez de places dans le monde");
			}
			float totalPourcentage = percentageEndPoint + percentageStartPoint + percentageDefeatPoint;
			if (totalPourcentage >= 1 || totalPourcentage <= 0) {
				throw new MauvaisPourcentageException("Mauvaise répartition des pourcentages du monde");
			}

			int id = 0;

			// Création des places de Début
			for (int i = 0; i < nbPlace * percentageStartPoint; i++) {
				int maxHP = rand.nextInt(1,250);
				Monster monstre = new Monster("", maxHP, maxHP,  rand.nextInt(1,25), rand.nextInt(1,25));
				if (Math.random() % 2 == 1) {
					monstre = null;
				}
				id++;
				API api = new API();
				JSONParser parser = new JSONParser(api.getResult());
				JSONObject nomEtTexte = parser.parse();
				Place place = new Place(id, (String) nomEtTexte.get("nom"), monstre, "", world, true, false, false);
				world.addPlace(place);
			}

			// Création des places de fin
			for (int i = 0; i < nbPlace * percentageEndPoint; i++) {
				int maxHP =  rand.nextInt(1,250);
				Monster monstre = new Monster("", maxHP, maxHP,  rand.nextInt(1,25),  rand.nextInt(1,25));
				if (Math.random() % 2 == 1) {
					monstre = null;
				}
				id++;
				Place place = new Place(id, "", monstre, "", world, false, true, false);
				world.addPlace(place);
			}

			// Création des Places piège
			for (int i = 0; i < nbPlace * percentageDefeatPoint; i++) {
				int maxHP =  rand.nextInt(1,250);
				Monster monstre = new Monster("", maxHP, maxHP,  rand.nextInt(1,25),  rand.nextInt(1,25));
				if (Math.random() % 2 == 1) {
					monstre = null;
				}
				id++;
				Place place = new Place(id, "", monstre, "", world, false, false, true);
				world.addPlace(place);
			}

			// Création des Places neutre

			if (totalPourcentage < 1) {
				totalPourcentage = 1 - totalPourcentage;
				for (int i = 0; i < nbPlace * totalPourcentage; i++) {
					int maxHP =  rand.nextInt(1,250);
					Monster monstre = new Monster("", maxHP, maxHP,  rand.nextInt(1,25),  rand.nextInt(1,25));
					if (Math.random() % 2 == 1) {
						monstre = null;
					}
					id++;
					Place place = new Place(id, "", monstre, "", world, false, false, false);
					world.addPlace(place);
				}
			}

			WorldAnalyzer analyse = new WorldAnalyzer(world);
			// Création des chemins


			int nbCheminParPlace = (int) (percentageCoverage * nbPlace);
			for (int i = 1; i <= world.getPlaces().size(); i++) {
				for (int j = 0; j < nbCheminParPlace; j++) {
					Path path = new Path(world.getPlaceFromId(i),
							world.getPlaceFromId(rand.nextInt(1, world.getPlaces().size())),
							rand.nextInt(1, 250));
					world.addPath(path);
				}
			}
			while (analyse.isConnexe() == false) {
				Path path = new Path(world.getPlaceFromId(rand.nextInt(1, world.getPlaces().size())),
						world.getPlaceFromId(rand.nextInt(1, world.getPlaces().size())),
						rand.nextInt(1,250));
				world.addPath(path);
			}
			System.out.println("Toujours dans la boucle");
		}
		return world;
	}
}
