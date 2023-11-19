package graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * An implementation of Graph.
 * 
 * <p>PS2 instructions: you MUST use the provided rep.
 */
public class ConcreteVerticesGraph extends Graph<String> {

    private final List<Vertex> vertices = new ArrayList<>();

    /**
     * Abstraction function:
     *   Represents a directed graph with labeled vertices. Each vertex is
     *   associated with a Vertex object in the 'vertices' list.
     * 
     * Representation invariant:
     *   - Each vertex label is unique.
     *   - The 'vertices' list contains all the vertices in the graph.
     * 
     * Safety from rep exposure:
     *   - The 'vertices' list is private and final. Any external modifications
     *     to the list are prevented.
     *   - Methods that return information about vertices (e.g., 'vertices()',
     *     'sources()', 'targets()') return copies of data to avoid exposing the
     *     internal representation.
     */

    // Constructor
    public ConcreteVerticesGraph(Set<String> vertices) {
        super(vertices);
        for (String vertexLabel : vertices) {
            this.vertices.add(new Vertex(vertexLabel));
        }
    }

    // Representation invariant
    private void checkRep() {
        Set<String> labels = new HashSet<>();
        for (Vertex vertex : vertices) {
            String label = vertex.getLabel();
            if (labels.contains(label)) {
                throw new RuntimeException("Duplicate vertex label: " + label);
            }
            labels.add(label);
        }
    }

    @Override
    public boolean add(String vertex) {
        if (super.add(vertex)) {
            vertices.add(new Vertex(vertex));
            checkRep();
            return true;
        }
        return false;
    }

    @Override
    public int set(String source, String target, int weight) {
        // Implementation of set method...
        checkRep();
        return 0; // Placeholder, replace with actual implementation
    }

    @Override
    public boolean remove(String vertex) {
        if (super.remove(vertex)) {
            vertices.removeIf(v -> v.getLabel().equals(vertex));
            checkRep();
            return true;
        }
        return false;
    }

    @Override
    public Set<String> vertices() {
        Set<String> vertexSet = new HashSet<>();
        for (Vertex vertex : vertices) {
            vertexSet.add(vertex.getLabel());
        }
        return vertexSet;
    }

    @Override
    public Map<String, Integer> sources(String target) {
        // Implementation of sources method...
        return Collections.emptyMap(); // Placeholder, replace with actual implementation
    }

    @Override
    public Map<String, Integer> targets(String source) {
        // Implementation of targets method...
        return Collections.emptyMap(); // Placeholder, replace with actual implementation
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Vertices: [");
        for (Vertex vertex : vertices) {
            result.append(vertex).append(", ");
        }
        if (!vertices.isEmpty()) {
            result.delete(result.length() - 2, result.length()); // Remove the trailing comma and space
        }
        result.append("]");
        return result.toString();
    }

}

/**
 * Represents a vertex in a directed graph.
 */
class Vertex {

    private final String label;

    /**
     * Abstraction function:
     *   Represents a vertex in a directed graph with a unique label.
     * 
     * Representation invariant:
     *   - 'label' is not null.
     * 
     * Safety from rep exposure:
     *   - 'label' is private and final.
     */

    // Constructor
    public Vertex(String label) {
        this.label = label;
        checkRep();
    }

    // Representation invariant
    private void checkRep() {
        if (label == null) {
            throw new RuntimeException("Vertex label cannot be null.");
        }
    }

    /**
     * Get the label of the vertex.
     * 
     * @return The label of the vertex.
     */
    public String getLabel() {
        return label;
    }

    // TODO methods

    @Override
    public String toString() {
        return label;
    }

}




