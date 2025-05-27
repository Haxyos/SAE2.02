package app.controller;

import app.Main;
import app.ai.WorldAnalyzer;
import app.model.map.World;
import app.model.parser.WorldIO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;


import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WorldAnalyzerTest {

	World w;
    WorldAnalyzer analyzer;
    World w2;
    WorldAnalyzer analyzer2;

    @BeforeEach
    public void setUp() throws Exception {
        w = WorldIO.loadWorld(Main.class.getResourceAsStream("Monde1.json"));
        analyzer = new WorldAnalyzer(w);
        w2 = WorldIO.loadWorld(Main.class.getResourceAsStream("MondeTest1.json"));
        analyzer2 = new WorldAnalyzer(w2);
    }

    @Test
    public void testConnex() {
        assertTrue(analyzer.isConnexe());
        assertFalse(analyzer2.isConnexe());
    }
    
    @Test
    public void testFinish() {
    	assertTrue(analyzer.isFinishable());
    	assertFalse(analyzer2.isFinishable());
    }
    
    @Test
    public void testLessDistanceToReach() {
        assertEquals(12, analyzer.lessDistanceToReach(w.getPlaceFromId(1), w.getPlaceFromId(6)));
        assertEquals(9, analyzer.lessDistanceToReach(w.getPlaceFromId(1), w.getPlaceFromId(9)));
    }
}
