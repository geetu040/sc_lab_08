package graph;

import static org.junit.Assert.*;

import org.junit.Test;
import java.util.Collections;
import java.util.Map;
import java.util.Set;


public class YourGraphInstanceTest extends GraphInstanceTest {

    // Override the abstract method to return an instance of your Graph implementation
	@Override
	public Graph<String> emptyInstance() {
	    return new Graph<String>(Collections.emptySet());
	}


    @Test
    public void testAddVerticesAndEdges() {
        Graph<String> graph = emptyInstance();

        assertTrue("Adding vertex A should return true", graph.add("A"));
        assertTrue("Adding vertex B should return true", graph.add("B"));
        assertTrue("Adding vertex C should return true", graph.add("C"));

        assertEquals("Vertices set should contain A, B, and C", Set.of("A", "B", "C"), graph.vertices());

        assertEquals("Setting edge weight A -> B should return 0", 0, graph.set("A", "B", 5));
        assertEquals("Setting edge weight B -> C should return 0", 0, graph.set("B", "C", 10));

        assertEquals("Edge weight A -> B should be 5", 5, graph.set("A", "B", 5));
        assertEquals("Edge weight B -> C should be 10", 10, graph.set("B", "C", 10));
    }

    @Test
    public void testRemoveVertexAndEdges() {
        Graph<String> graph = emptyInstance();

        graph.add("A");
        graph.add("B");
        graph.add("C");

        graph.set("A", "B", 5);
        graph.set("B", "C", 10);

        assertTrue("Removing vertex B should return true", graph.remove("B"));
        assertFalse("Removing non-existing vertex D should return false", graph.remove("D"));

        assertEquals("Vertices set should contain A and C after removal of B", Set.of("A", "C"), graph.vertices());
        assertTrue("Sources of B should be empty after removal", graph.sources("B").isEmpty());
        assertTrue("Targets of B should be empty after removal", graph.targets("B").isEmpty());
    }

    @Test
    public void testSourcesAndTargetsAfterSetWeight() {
        Graph<String> graph = emptyInstance();

        graph.add("A");
        graph.add("B");
        graph.add("C");

        graph.set("A", "B", 5);
        graph.set("B", "C", 10);

        // Test sources of "B"
        Map<String, Integer> sourcesB = graph.sources("B");
        assertEquals("Number of sources for B", 1, sourcesB.size());
        assertEquals("Source A has an edge with weight 5", 5, sourcesB.get("A").intValue());

        // Test targets of "A"
        Map<String, Integer> targetsA = graph.targets("A");
        assertEquals("Number of targets for A", 1, targetsA.size());
        assertEquals("Target B has an edge with weight 5", 5, targetsA.get("B").intValue());
    }

    @Test
    public void testAddAndRemoveVerticesAlternately() {
        Graph<String> graph = emptyInstance();

        assertTrue("Adding vertex A should return true", graph.add("A"));
        assertEquals("Vertices set should contain A", Set.of("A"), graph.vertices());

        assertTrue("Removing vertex A should return true", graph.remove("A"));
        assertEquals("Vertices set should be empty after removal of A", Collections.emptySet(), graph.vertices());

        assertTrue("Adding vertex B should return true", graph.add("B"));
        assertEquals("Vertices set should contain B", Set.of("B"), graph.vertices());

        assertTrue("Removing vertex B should return true", graph.remove("B"));
        assertEquals("Vertices set should be empty after removal of B", Collections.emptySet(), graph.vertices());
    }

    @Test
    public void testSetZeroWeightToRemoveEdge() {
        Graph<String> graph = emptyInstance();

        graph.add("A");
        graph.add("B");

        graph.set("A", "B", 5);

        assertEquals("Setting edge weight to 0 should return the previous weight (5)", 5, graph.set("A", "B", 0));
        assertEquals("Edge should be removed, so weight should be 0", 0, graph.set("A", "B", 10));
    }

}

