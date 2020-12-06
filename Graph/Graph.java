/*
 * NAME: Jackie Yuan
 */
import java.util.*;
/**
 * The class contains the graph.
 * @author Shuibenyang Yuan
 * @date June 4 2018
 */
public class Graph {
    // data structure to store vertex and edges.
    private HashMap<Integer, Vertex> graph;
    private HashSet<Edge> edge;

    /**
     * Constructor for Graph
     */
    public Graph() {
        graph = new HashMap<>();
        edge = new HashSet<>();
    }

    /**
     * Adds a vertex to the graph. Throws IllegalArgumentException if given vertex
     * already exist in the graph.
     *
     * @param v vertex to be added to the graph
     * @throws IllegalArgumentException if two vertices with the same name are added.
     */
    public void addVertex(Vertex v) throws IllegalArgumentException {
        // same position exception.
        if (graph.containsKey(v.hashCode())) {
            throw new IllegalArgumentException();
        }
        graph.put(v.hashCode(), v);
    }

    /**
     * Gets a collection of all the vertices in the graph
     *
     * @return collection of all the vertices in the graph
     */
    public Collection<Vertex> getVertices() {
        return graph.values();
    }

    /**
     * Gets the vertex object with the given name
     *
     * @param name name of the vertex object requested
     * @return vertex object associated with the name
     */
    public Vertex getVertex(String name) {
        return graph.get(name.hashCode());
    }

    /**
     * Adds a directed edge from vertex u to vertex v, Throws IllegalArgumentException if one of
     * the vertex does not exist
     *
     * @param nameU  name of vertex u
     * @param nameV  name of vertex v
     * @param weight weight of the edge between vertex u and v
     * @throws IllegalArgumentException if one of the vertex does not exist
     */
    public void addEdge(String nameU, String nameV, Double weight) throws IllegalArgumentException {
        // exception.
        if (!graph.containsKey(nameU.hashCode()) || !graph.containsKey(nameV.hashCode())) {
            throw new IllegalArgumentException();
        }
        // vertex and edges initiation.
        Vertex u = getVertex(nameU);
        Vertex v = getVertex(nameV);
        Edge e = new Edge(u, v, weight);
        // add vertex and edges to the vertex.
        u.neighbors.add(v);
        u.edges.add(e);
        // add the edge to the graph.
        this.edge.add(e);
    }

    /**
     * Adds an undirected edge between vertex u and vertex v by adding a directed
     * edge from u to v, then a directed edge from v to u
     *
     * @param nameU  name of vertex u
     * @param nameV  name of vertex v
     * @param weight weight of the edge between vertex u and v
     */
    public void addUndirectedEdge(String nameU, String nameV, double weight) {
        // exception when vertex does not existed
        if (!graph.containsKey(nameU.hashCode()) || !graph.containsKey(nameV.hashCode())) {
            throw new IllegalArgumentException();
        }
        // add edges by calling each other。
        addEdge(nameU, nameV, weight);
        addEdge(nameV, nameU, weight);
    }

    /**
     * Computes the euclidean distance between two points as described by their
     * coordinates
     *
     * @param ux (double) x coordinate of point u
     * @param uy (double) y coordinate of point u
     * @param vx (double) x coordinate of point v
     * @param vy (double) y coordinate of point v
     * @return (double) distance between the two points
     */
    public double computeEuclideanDistance(double ux, double uy, double vx, double vy) {
        return Math.sqrt((ux - vx) * (ux - vx) + (uy - vy) * (uy - vy));
    }

    /**
     * Calculates the euclidean distance for all edges in the map using the
     * computeEuclideanCost method.
     */
    public void computeAllEuclideanDistances() {
        for (Edge e : edge) {
            e.distance = computeEuclideanDistance(e.source.x, e.source.y,
                    e.target.x, e.target.y);
        }
        for (Vertex v: graph.values()) {
            for (Edge e : v.edges) {
                e.distance = computeEuclideanDistance(e.source.x, e.source.y,
                        e.target.x, e.target.y);
            }
        }
    }

    /**
     * Helper method to reset all the vertices before doing graph traversal algorithms
     */
    private void resetAllVertices() {
        // reset all the vertex tracing attribute.
        for (Vertex v : graph.values()) {
            v.prev = null;
            v.disc = Double.MAX_VALUE;
            v.visited = false;
        }
    }

    /**
     * Find the path from vertex with name s to vertex with name t, using DFS
     *
     * @param s the name of the starting vertex
     * @param t the name of the targeting vertex
     */
    public void DFS(String s, String t) {
        // clear the memory of vertex data.
        resetAllVertices();
        // initializing the processing by creating an empty stack and finding source
        Stack<Vertex> frontier = new Stack<>();
        Vertex source = this.getVertex(s);
        Vertex target = this.getVertex(t);
        // push the source into the frontier stack.
        frontier.push(source);
        while (!frontier.empty() && !target.visited) { //check if target already reached
            Vertex v = frontier.pop();
            if (!v.visited) { //check if v not explored
                v.visited = true;
                for (Vertex neighbor : v.neighbors) {
                    if (!neighbor.visited) { //check if neighbor is explored
                        frontier.push(neighbor);
                        neighbor.prev = v;
                    }
                }
            }
        }
    }

        /**
         * Find the path from vertex with name s to vertex with name t, using BFS
         *
         * @param s the name of the starting vertex
         * @param t the name of the targeting vertex
         */
    public void BFS(String s, String t) {
        // clear the memory of vertex data.
        resetAllVertices();
        // initializing the processing by creating an empty queue and finding source
        Vertex start = getVertex(s);
        Vertex target = getVertex(t);
        LinkedList<Vertex> frontier = new LinkedList<>();
        frontier.add(start); // adding the start vertex to the frontier queue.
        start.visited = true; // visited.
        while (!frontier.isEmpty() && !target.visited) { // check if target already visited.
            Vertex current = frontier.pollFirst();
            if (current.name == t) {
                continue;
            }
            for (Vertex v : current.neighbors) {
                if (!v.visited) { // is not visited. then visit it.
                    frontier.add(v);
                    v.visited = true;
                    v.prev = current;
                }
            }
        }

    }

    /**
     * Helper class for Dijkstra and A*, used in priority queue
     */
    private class CostVertex implements Comparable<CostVertex> {
        double cost;
        Vertex vertex;

        public CostVertex(double cost, Vertex vertex) {
            this.cost = cost;
            this.vertex = vertex;
        }

        public int compareTo(CostVertex o) {
            return Double.compare(cost, o.cost);
        }
    }

    /**
     * Find the shortest path from vertex with name s to vertex with name t, using Dijkstra
     *
     * @param s the name of starting vertex
     * @param t the name of targeting vertex
     */


    public void Dijkstra(String s, String t) {
        // clear the memory of vertex data.
        resetAllVertices();
        // initializing the processing by creating an empty priority queue and finding source.
        PriorityQueue<CostVertex> queue = new PriorityQueue<>();
        CostVertex start = new CostVertex(0, getVertex(s));
        // add the start to the priority queue.
        queue.add(start);

        while (!queue.isEmpty()) {
            // Visit vertex with minimum distance from startV
            CostVertex currentV = queue.poll();
            currentV.vertex.visited = true;
            if (currentV.vertex.name.equals(t)) { // found the target.
                break;
            }
            if (currentV.vertex.name.equals(s)) {
                currentV.vertex.disc = 0;
            }

            for (Vertex adj: currentV.vertex.neighbors) { // neighbors' cost calculating process.
                double distance = 0.0;
                for (Edge e: currentV.vertex.edges) {
                    if (e.target.equals(adj)) {
                        distance = e.distance;
                    }
                }
                if (adj.disc > distance + currentV.vertex.disc) { // if cost is better.
                    adj.prev = currentV.vertex;
                    adj.disc = distance + currentV.cost; // update new distance.
                    queue.add(new CostVertex(adj.disc, adj));
                }
            }
        }
    }


    /**
     * Helper method to calculate the h value in A*
     *
     * @param cur  the current vertex being explored
     * @param goal the goal vertex to reach
     * @return the h value of cur and goal vertices
     */
    private double hValue(String cur, String goal) {

        return computeEuclideanDistance(getVertex(cur).x, getVertex(cur).y, getVertex(goal).x,
                getVertex(goal).y);
    }

    /**
     * Find the path from vertex with name s to vertex with name t, using A*
     *
     * @param s the name of starting vertex
     * @param t the name of targeting vertex
     */
    public void AStar(String s, String t) {
        // clear the memory of vertex data.
        resetAllVertices();
        // initializing the processing by creating an empty priority queue and finding source.
        PriorityQueue<CostVertex> queue = new PriorityQueue<>();
        CostVertex start = new CostVertex(0, getVertex(s));
        // add the start to the priority queue.
        queue.add(start);

        while (!queue.isEmpty()) {
            // Visit vertex with minimum distance from startV
            CostVertex currentV = queue.poll();
            currentV.vertex.visited = true; // visited the vertex
            if (currentV.vertex.name.equals(t)) { // get the target.
                break;
            }
            if (currentV.vertex.name.equals(s)) {
                currentV.vertex.disc = 0;
            }

            for (Vertex adj: currentV.vertex.neighbors) { // calculate the neighbor's cost
                double distance = 0.0;
                for (Edge e: currentV.vertex.edges) {
                    if (e.target.equals(adj)) {
                        distance = e.distance;
                    }
                }
                if (adj.disc > (distance + currentV.vertex.disc)) {
                    // updating the cost and distance.
                    adj.prev = currentV.vertex;
                    adj.disc = distance + currentV.vertex.disc;
                    queue.add(new CostVertex(distance + currentV.vertex.disc + hValue(adj.name, t),
                            adj));
                }
            }
        }
    }

    /**
     * Returns a list of edges for a path from city s to city t.
     *
     * @param s starting city name
     * @param t ending city name
     * @return list of edges from s to t
     */
    public List<Edge> getPath(String s, String t) { // get path by getting the previous.
        List<Edge> path = new ArrayList<>();
        Vertex previous = this.getVertex(t);
        while (!previous.equals(getVertex(s))) { // iterating untill get to the start.
            for (Edge e : this.edge) {
                if (e.target.equals(previous) && e.source.equals(previous.prev)) {
                    path.add(e);
                    break;
                }
            }
            previous = previous.prev;
        }
        Collections.reverse(path);
        return path;
    }

}
