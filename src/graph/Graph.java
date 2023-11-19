package graph;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * A mutable weighted directed graph with labeled vertices.
 * Vertices have distinct labels of an immutable type {@code L} when compared
 * using the {@link Object#equals(Object) equals} method.
 * Edges are directed and have a positive weight of type {@code int}.
 * 
 * <p>PS2 instructions: this is a required ADT interface.
 * You MUST NOT change the specifications or add additional methods.
 * 
 * @param <L> type of vertex labels in this graph, must be immutable
 */
public class Graph<L> {

    private Set<L> vertices;
    private Map<L, Map<L, Integer>> edges;  // Map of source vertex to Map of target vertex and weight

    // Constructor
    public Graph(Set<L> vertices) {
        this.vertices = new HashSet<>(vertices);
        this.edges = new HashMap<>();
    }

    /**
     * Create an empty graph.
     * 
     * @param <L> type of vertex labels in the graph, must be immutable
     * @return a new empty weighted directed graph
     */
    public static <L> Graph<L> empty() {
        return new Graph<>(Collections.emptySet());
    }

    /**
     * Add a vertex to this graph.
     * 
     * @param vertex label for the new vertex
     * @return true if this graph did not already include a vertex with the
     *         given label; otherwise false (and this graph is not modified)
     */
    public boolean add(L vertex) {
        if (!vertices.contains(vertex)) {
            vertices.add(vertex);
            edges.put(vertex, new HashMap<>());
            return true;
        }
        return false;
    }

    /**
     * Add, change, or remove a weighted directed edge in this graph.
     * If weight is nonzero, add an edge or update the weight of that edge;
     * vertices with the given labels are added to the graph if they do not
     * already exist.
     * If weight is zero, remove the edge if it exists (the graph is not
     * otherwise modified).
     * 
     * @param source label of the source vertex
     * @param target label of the target vertex
     * @param weight nonnegative weight of the edge
     * @return the previous weight of the edge, or zero if there was no such
     *         edge
     */
    public int set(L source, L target, int weight) {
        add(source);
        add(target);

        Map<L, Integer> sourceEdges = edges.get(source);
        int previousWeight = sourceEdges.getOrDefault(target, 0);
        if (weight != 0) {
            sourceEdges.put(target, weight);
        } else {
            sourceEdges.remove(target);
        }

        return previousWeight;
    }

    /**
     * Remove a vertex from this graph; any edges to or from the vertex are
     * also removed.
     * 
     * @param vertex label of the vertex to remove
     * @return true if this graph included a vertex with the given label;
     *         otherwise false (and this graph is not modified)
     */
    public boolean remove(L vertex) {
        if (vertices.contains(vertex)) {
            vertices.remove(vertex);
            edges.remove(vertex);

            // Remove edges pointing to the removed vertex
            edges.forEach((source, targetEdges) -> targetEdges.remove(vertex));

            return true;
        }
        return false;
    }

    /**
     * Get all the vertices in this graph.
     * 
     * @return the set of labels of vertices in this graph
     */
    public Set<L> vertices() {
        return new HashSet<>(vertices);
    }

    /**
     * Get the source vertices with directed edges to a target vertex and the
     * weights of those edges.
     * 
     * @param target a label
     * @return a map where the key set is the set of labels of vertices such
     *         that this graph includes an edge from that vertex to target, and
     *         the value for each key is the (nonzero) weight of the edge from
     *         the key to target
     */
    public Map<L, Integer> sources(L target) {
        Map<L, Integer> sourceVertices = new HashMap<>();
        for (L source : vertices) {
            int weight = edges.getOrDefault(source, Collections.emptyMap()).getOrDefault(target, 0);
            if (weight != 0) {
                sourceVertices.put(source, weight);
            }
        }
        return sourceVertices;
    }

    /**
     * Get the target vertices with directed edges from a source vertex and the
     * weights of those edges.
     * 
     * @param source a label
     * @return a map where the key set is the set of labels of vertices such
     *         that this graph includes an edge from source to that vertex, and
     *         the value for each key is the (nonzero) weight of the edge from
     *         source to the key
     */
    public Map<L, Integer> targets(L source) {
        return new HashMap<>(edges.getOrDefault(source, Collections.emptyMap()));
    }
}
