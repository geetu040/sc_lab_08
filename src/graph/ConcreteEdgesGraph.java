package graph;

import java.util.*;

/**
 * An implementation of Graph.
 * 
 * <p>PS2 instructions: you MUST use the provided rep.
 */
public class ConcreteEdgesGraph extends Graph<String> {
    
	public ConcreteEdgesGraph(Set<String> vertices) {
	    super(vertices);

	    // Initialize edges field
	    for (String vertex : vertices) {
	        edges.add(new Edge(vertex, vertex, 0)); // Assume a self-loop with weight 0 for each vertex
	    }
	}


	private final Set<String> vertices = new HashSet<>();
    private final List<Edge> edges = new ArrayList<>();
    
    // Abstraction function:
    //   Represents a weighted directed graph with string-labeled vertices and edges.
    //   The set 'vertices' contains all vertices in the graph.
    //   The list 'edges' contains all directed edges with their corresponding weights.
    
    // Representation invariant:
    //   - Every edge in 'edges' must have valid source and target vertices that exist in 'vertices'.
    //   - There should be no duplicate edges with the same source and target vertices.
    
    // Safety from rep exposure:
    //   - All fields are private and final.
    //   - 'vertices' is returned as an unmodifiable set to prevent modification.
    //   - 'edges' is not exposed outside of the class.
    
//    // Constructor
//    public ConcreteEdgesGraph() {
//        checkRep();
//    }
    
    // Check the representation invariant
    private void checkRep() {
        for (Edge edge : edges) {
            assert vertices.contains(edge.getSource()) : "Invalid edge source vertex";
            assert vertices.contains(edge.getTarget()) : "Invalid edge target vertex";
        }
        Set<Edge> uniqueEdges = new HashSet<>(edges);
        assert uniqueEdges.size() == edges.size() : "Duplicate edges found";
    }
    
    public boolean add(String vertex) {
        boolean added = vertices.add(vertex);
        checkRep();
        return added;
    }
    

    public int set(String source, String target, int weight) {
        Edge newEdge = new Edge(source, target, weight);
        Edge existingEdge = edges.stream()
                .filter(edge -> edge.getSource().equals(source) && edge.getTarget().equals(target))
                .findFirst()
                .orElse(null);
        
        if (existingEdge != null) {
            int previousWeight = existingEdge.getWeight();
            existingEdge.setWeight(weight);
            checkRep();
            return previousWeight;
        } else {
            edges.add(newEdge);
            checkRep();
            return 0;
        }
    }
    
    public boolean remove(String vertex) {
        boolean removed = vertices.remove(vertex);
        edges.removeIf(edge -> edge.getSource().equals(vertex) || edge.getTarget().equals(vertex));
        checkRep();
        return removed;
    }
    
    public Set<String> vertices() {
        return Collections.unmodifiableSet(vertices);
    }
    
    public Map<String, Integer> sources(String target) {
        Map<String, Integer> sourceVertices = new HashMap<>();
        for (Edge edge : edges) {
            if (edge.getTarget().equals(target)) {
                sourceVertices.put(edge.getSource(), edge.getWeight());
            }
        }
        return sourceVertices;
    }
    
    public Map<String, Integer> targets(String source) {
        Map<String, Integer> targetVertices = new HashMap<>();
        for (Edge edge : edges) {
            if (edge.getSource().equals(source)) {
                targetVertices.put(edge.getTarget(), edge.getWeight());
            }
        }
        return targetVertices;
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Edge edge : edges) {
            sb.append(edge.toString()).append("\n");
        }
        return sb.toString();
    }
    
    /**
     * An edge in the graph.
     * 
     * <p>PS2 instructions: the specification and implementation of this class are
     * up to you.
     */
    static class Edge {
        
        private final String source;
        private final String target;
        private int weight;
        
        // Abstraction function:
        //   Represents a directed edge with a source vertex, target vertex, and weight.
        
        // Representation invariant:
        //   - 'source' and 'target' must not be null.
        //   - 'weight' must be non-negative.
        
        // Safety from rep exposure:
        //   - All fields are private and final, except 'weight', which is mutable.
        
        // Constructor
        public Edge(String source, String target, int weight) {
            this.source = source;
            this.target = target;
            this.weight = weight;
            checkRep();
        }
        
        // Check the representation invariant
        private void checkRep() {
            assert source != null : "Source vertex cannot be null";
            assert target != null : "Target vertex cannot be null";
            assert weight >= 0 : "Weight must be non-negative";
        }
        
        // Getters
        public String getSource() {
            return source;
        }
        
        public String getTarget() {
            return target;
        }
        
        public int getWeight() {
            return weight;
        }
        
        // Setters
        public void setWeight(int weight) {
            this.weight = weight;
            checkRep();
        }
        
        // TODO: Implement any additional methods
        
        @Override
        public String toString() {
            return String.format("%s -> %s : %d", source, target, weight);
        }
    }
}
