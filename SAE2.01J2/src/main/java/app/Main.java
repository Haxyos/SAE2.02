package app;

import app.ai.WorldGenerator;
import app.controller.Game;
import app.model.parser.WorldIO;
import app.model.entity.Player;
import app.model.exceptions.MauvaisPourcentageException;
import app.model.exceptions.NotEnoughtPlaceException;
import app.model.map.World;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, NotEnoughtPlaceException, MauvaisPourcentageException, InterruptedException {
        World world = WorldGenerator.createWorld("test", 10, new Player(), (float) 0.3, (float)0.2, (float)0.2,(float) 0.5);
        Player player = new Player(100,100,20,20, 100);
        Game game = new Game(world, player);
        game.play();
    }
}