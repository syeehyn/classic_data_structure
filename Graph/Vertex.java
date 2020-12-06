/*
 * NAME: Jackie Yuan
 */

import java.util.LinkedList;
/**
 * The class contains the implement of vertex.
 * @author Shuibenyang Yuan
 * @date June 4 2018
 */
public class Vertex {
    // Initialize the variable and instance attribute.
    public String name; // the name of this vertex
    public int x; // the x coordinates of this vertex on map
    public int y; // the y coordinates of this vertex on map
    public LinkedList<Vertex> neighbors; // neighbors of vertex.
    public LinkedList<Edge> edges; // edges connected.
    public double disc; // The tracking of distance for dij & A*.
    public Vertex prev; // The previous vertex.
    public boolean visited; // If the vertex is visited.

    /**
     * The Constructor of Vertex.
     * @param name the name of vertex.
     * @param x the x coordinate.
     * @param y the y coordinate.
     */
    public Vertex(String name, int x, int y) {
        // Initialize the vertex.
        this.name = name;
        this.x = x;
        this.y = y;
        this.neighbors = new LinkedList<>();
        this.edges = new LinkedList<>();
        this.disc = Double.MAX_VALUE;
        this.prev = null;
        this.visited = false;
    }

    /**
     * method to get hashcode of the vertex.
     * @return The hashcode of the vertex.
     */
    @Override
    public int hashCode() {
        // we assume that each vertex has a unique name
        return name.hashCode();
    }

    /**
     * Method to compare vertex
     * @param o the other vertex.
     * @return comparison boolean of vertex, true if they are the same.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (!(o instanceof Vertex)) {
            return false;
        }
        Vertex oVertex = (Vertex) o;

        return name.equals(oVertex.name) && x == oVertex.x && y == oVertex.y;
    }

    /**
     * Get the vertex to String.
     * @return the string of vertex.
     */
    public String toString() {
        return name + " (" + x + ", " + y + ")";
    }

}
