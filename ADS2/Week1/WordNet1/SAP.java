// import required class files from edu.princeton.cs.algs4

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;

import java.lang.IllegalArgumentException;

import java.util.Arrays;

/** 
 * @author L.Venugopalrao
 * 
 * This SAP class used to find the shortestPath path between the given 
 * two totalVertices and find the shortestPath ancestor path between the them
*/

public class SAP {

    private final Digraph digraph; // Digraph object creation 'digraph'
    /** 
     * @param G Digraph G
     *
     * This method takes the input 'G' (digraph)
     * and creates object for the graph 'G'
    */

    public SAP(Digraph G) {
        // if (G == null) {
        //     throw IllegalArgumentException("Graph is null");
        // }
        digraph = new Digraph(G);
    }

    /** 
     * @param v integer
     *
     * This method checks the vertex is valid vertex
     * in the graph or not
     * If vertex in the graph is not valid, throws 'InexOutOfBoundsException'
     * and displays 'vertex is not between 0 and total totalVertices' 
    */

    private void validateVertex(int v) {
        int totalVerticesCount = digraph.V();
        if (v < 0 && v >= totalVerticesCount) {
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (totalVerticesCount-1));
        }
    }
    /** 
     * @param totalVertices Iterable<Integer>
     * 
     * This method checks the Iterable of totalVertices 
     * are valid or not
     * If vertex in the graph is not valid, throws 'InexOutOfBoundsException'
     * and displays 'vertex is not between 0 and total totalVertices' 
    */

    private void validatetotalVertices(Iterable<Integer> totalVertices) {
        if (totalVertices == null) {
            throw new IllegalArgumentException("argument is null");
        }
        int totalVerticesCount = digraph.V();
        for (int v : totalVertices) {
            if (v < 0 || v >= totalVerticesCount) {
                throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (totalVerticesCount-1));
            }
        }
    } 

    /** 
     * @param v input 'v' integer
     * @param w input 'w' integer
     *
     * This method 'shortestPath' used to find and 
     * return the shortestPath length between 
     * two totalVertices and its corresponding common ancestor
    */

    private int[] shortestPath(int v, int w) {
        validateVertex(v); // check if the input vertex 'v' is valid vertex or not
        validateVertex(w); // check if the input vertex 'w' is valid vertex or not
        int[] result = new int[2];
        BreadthFirstDirectedPaths bfsV = new BreadthFirstDirectedPaths(digraph, v); // bfs for 'v' vertex
        BreadthFirstDirectedPaths bfsW = new BreadthFirstDirectedPaths(digraph, w); // bfs for 'w' vertex
        
        int pathLength = Integer.MAX_VALUE; // st result Integer MAX_VALUE as default
        int shortestPath = -1; // st result -1 as default
        // checking for the paths for both of the totalVertices and finding the length of both the paths
        // Then, if it is shortestPath length update the shortestPath_Len and the vertex 'i' as the common ancestor 
        for (int i = 0; i < digraph.V(); ++i) {
            if (bfsV.hasPathTo(i) && bfsW.hasPathTo(i)) {
                int length1 = bfsV.distTo(i) + bfsW.distTo(i);
                if (length1 < pathLength) {
                    pathLength = length1;
                    shortestPath = i;
                }
            }
        }
        // if the shortestPath_Ancestor is -1, it has no path
        // So, update shortestPath_Len and shortestPath_Ancestor as -1
        // Or else, update its actual shortestPath_Len and shortestPath_Ancestor
        if (shortestPath == -1) {
         result[0] = -1;
         result[1] = -1;
        }
        else {
         result[0] = pathLength;
         result[1] = shortestPath;
        }
        return result; // returns the shortestPath_Len and shortestPath_Ancestor as a array
    }

    /** 
     * @param v input 'v' Iterable integer
     * @param w input 'w' Iterable integer
     *
     * This method 'shortestPath' used to find and
     * return the shortestPath length between 
     * two totalVertices and its co resultponding common ancestor
    */

    private int[] shortestPath(Iterable<Integer> v, Iterable<Integer> w) {
        validatetotalVertices(v); // check if the input vertex 'v' is valid vertex or not
        validatetotalVertices(w); // check if the input vertex 'w' is valid vertex or not
        int[] result = new int[2];
        BreadthFirstDirectedPaths bfsV = new BreadthFirstDirectedPaths(digraph, v); // bfs for 'v' vertex
        BreadthFirstDirectedPaths bfsW = new BreadthFirstDirectedPaths(digraph, w); // bfs for 'w' vertex
        
        int pathLength = Integer.MAX_VALUE; // st result Integer MAX_VALUE as default
        int shortestPath = -1; // st result -1 as deault
        // checking for the paths for both of the totalVertices and finding the length of both the paths
        // Then, if it is shortestPath length update the shortestPath_Len and the vertex 'i' as the common ancestor 
        for (int i = 0; i < digraph.V(); ++i) {
            if (bfsV.hasPathTo(i) && bfsW.hasPathTo(i)) { 
                int len = bfsV.distTo(i) + bfsW.distTo(i);
                if (len < pathLength) {
                    pathLength = len;
                    shortestPath = i;
                }
            }
        }
        // if the shortestPath_Ancestor is -1, it has no path
        // So, update shortestPath_Len and shortestPath_Ancestor as -1
        // Or else, update its actual shortestPath_Len and shortestPath_Ancestor
        if (shortestPath == -1) {
         result[0] = -1;
         result[1] = -1;
        }
        else {
         result[0] = pathLength;
         result[1] = shortestPath;
        }
        return result; // returns the shortestPath_Len and shortestPath_Ancestor as a array
    }

    /** 
     * @param v input 'v' integer 
     * @param w input 'w' integer
     *
     * To find the length of shortestPath ancestral path between v and w
     * if no such path, returns -1 
    */
    
    public int length(int v, int w) {
        int[] resultant = shortestPath(v, w);
        return resultant[0];
    }
 
    /** 
     * @param v input 'v' integer 
     * @param w input 'w' integer
     *
     * To find the common ancestor of v and w that  resultent in a shortestPath ancestral path
     * if no such path, returns -1
    */

    public int ancestor(int v, int w) {
        int[] resultant = shortestPath(v, w);
        return resultant[1];
    }
 
    /** 
     * @param v input 'v' Iterable integer 
     * @param w input 'w' Iterable integer
     *
     * To find the length of shortestPath ancestral path between v and w
     * if no such path, returns -1 
    */

    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        if (Arrays.asList(v).contains(null) || Arrays.asList(w).contains(null)) {
            throw new IllegalArgumentException("argument is null");
        } 
        int[] resultant = shortestPath(v, w);
        return resultant[0];
    }
 
    /** 
     * @param v input 'v' Iterable integer 
     * @param w input 'w' Iterable integer
     *
     * To find the common ancestor of v and w that  resultent in a shortestPath ancestral path
     * if no such path, returns -1
    */

    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        if (Arrays.asList(v).contains(null) || Arrays.asList(w).contains(null)) {
            throw new IllegalArgumentException("argument is null");
        }
        int[] resultant = shortestPath(v, w);
        return resultant[1];
    }
 
    // do unit testing of this class
    // public static void main(String[] args)
 }