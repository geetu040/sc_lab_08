package graph;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.junit.Test;

public class ConcreteVerticesGraphTest extends GraphInstanceTest {

    /*
     * Provide a ConcreteVerticesGraph for tests in GraphInstanceTest.
     */
    @Override
    public Graph<String> emptyInstance() {
        return new Graph<String>(Collections.emptySet());
    }

    /*
     * Testing ConcreteVerticesGraph...
     */

    // Testing strategy for ConcreteVerticesGraph.toString()
    //   - Empty graph
    //   - Graph with vertices
    //   - Add vertices, remove vertices, and check the result

    
    @Test
    public void testGraphWithVerticesToString() {
        // Ensure that the toString method works for a graph with vertices
        Set<String> vertices = new HashSet<>();
        vertices.add("A");
        vertices.add("B");
        vertices.add("C");
        Graph<String> graph = new ConcreteVerticesGraph(vertices);
        assertEquals("Vertices: [A, B, C]", graph.toString());
    }

    @Test
    public void testAddRemoveVerticesToString() {
        // Ensure that adding and removing vertices updates the toString result correctly
        Set<String> initialVertices = new HashSet<>();
        initialVertices.add("A");
        initialVertices.add("B");
        initialVertices.add("C");

        ConcreteVerticesGraph graph = new ConcreteVerticesGraph(initialVertices);
        assertEquals("Vertices: [A, B, C]", graph.toString());

        graph.remove("B");
        assertEquals("Vertices: [A, C]", graph.toString());

        graph.add("D");
        assertEquals("Vertices: [A, C, D]", graph.toString());
    }


    /*
     * Testing Vertex...
     */

    // Testing strategy for Vertex
    //   - Vertex with a label
    //   - Compare two vertices

    @Test
    public void testVertexToString() {
        // Ensure that the toString method works for a Vertex with a label
        Vertex vertex = new Vertex("A");
        assertEquals("A", vertex.toString());
    }

    
}
