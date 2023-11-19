package graph;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

/**
 * Tests for ConcreteEdgesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteEdgesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteEdgesGraphTest extends GraphInstanceTest {
   
    /*
     * Provide a ConcreteEdgesGraph for tests in GraphInstanceTest.
     */
    @Override
    public Graph<String> emptyInstance() {
        return new Graph<String>(Collections.emptySet());
    }

    /*
     * Testing ConcreteEdgesGraph...
     */


    @Test
    public void testConcreteEdgesGraphAdd() {
        Graph<String> graph = emptyInstance();
        assertTrue(graph.add("A"));
        assertTrue(graph.add("B"));
        assertFalse(graph.add("A")); // Adding the same vertex again should return false

        Set<String> vertices = graph.vertices();
        assertTrue(vertices.contains("A"));
        assertTrue(vertices.contains("B"));
        assertEquals(2, vertices.size());
    }



    @Test
    public void testConcreteEdgesGraphRemove() {
        Graph<String> graph = emptyInstance();
        graph.add("A");
        graph.add("B");
        graph.set("A", "B", 5);

        assertTrue(graph.remove("A"));

        Set<String> vertices = graph.vertices();
        assertFalse(vertices.contains("A"));
        assertTrue(vertices.contains("B"));

        Map<String, Integer> sources = graph.sources("B");
        assertEquals(0, sources.size()); // The edge from A to B should be removed
    }

    /*
     * Testing Edge...
     */

    @Test
    public void testEdgeToString() {
        ConcreteEdgesGraph.Edge edge = new ConcreteEdgesGraph.Edge("A", "B", 5);
        assertEquals("A -> B : 5", edge.toString());
    }

    
}
