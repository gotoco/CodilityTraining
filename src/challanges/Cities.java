package challanges;

import sun.security.provider.certpath.Vertex;

import java.lang.reflect.Array;
import java.util.*;

/**
 A network consisting of N cities and N − 1 roads is given. Each city is labeled with a distinct integer from 0 to N − 1.
 Roads connect cities in such a way that each distinct pair of cities is connected either by a direct road or through a path consisting of direct roads. There is exactly one way to reach any city from any other city.
 Each city also has its own attractiveness level, which will be denoted by an integer. City P is more attractive than city Q if the attractiveness level of city P is strictly greater than the attractiveness level of city Q.
 You are planning a trip to visit some of the most attractive cities. You want to select cities to visit based on the following requirements:

  - At most K cities can be included in the trip plan.
  - It must be possible to travel among the cities included in the trip plan
    without having to travel through cities that have been excluded from the trip plan.
  - None of the cities included in the trip plan can be less attractive than any of the excluded cities.
    City attractiveness levels do not have to be unique, though,
    so it is permissible to visit only a subset of cities that are equally attractive.

 The goal is to maximize the number of cities selected while satisfying the above requirements.
 The network of cities is described using arrays C and D, each of length N. Array C describes a network of cities as follows: if C[P] = Q and P ≠ Q, then there is a direct road between cities P and Q. Array D describes attractiveness of the cities: D[P] is the attractiveness level of city P.
 For example, consider the following network consisting of seven cities (each circle represents a city: the city label appears inside the circle and its attractiveness level outside the circle):

 If K = 2, we can select a maximum number of two cities: either 2 and 0 or 2 and 4. In both cases, the attractiveness levels of the selected cities are greater than or equal to 6 and the attractiveness levels of the excluded cities are less than or equal to 6.
 If, however, K = 5, the maximum number of cities we can select according to the rules above is four: we must select cities 2, 0, 4 and 5.
 Write a function:
 class Solution { public int solution(int K, int[] C, int[] D); }
 that, given the integer K and non-empty zero-indexed arrays C and D of length N describing a network of cities and their attractiveness, returns the maximum number of cities that can be included in a valid trip plan.
 For example, given arrays C and D describing the network above:

 C[0] = 1    D[0] = 6
 C[1] = 3    D[1] = 2
 C[2] = 0    D[2] = 7
 C[3] = 3    D[3] = 5
 C[4] = 2    D[4] = 6
 C[5] = 4    D[5] = 5
 C[6] = 4    D[6] = 2

 and K = 2 the function should return 2, as explained above. Similarly, given the same network but with K = 5, the function should return 4, as explained above.
 Assume that:

 N is an integer within the range [1..100,000];
 K is an integer within the range [1..N];

 each element of array C is an integer within the range [0..(N−1)];
 there is exactly one (possibly indirect) connection between any two distinct cities;
 each element of array D is an integer within the range [0..1,000,000].

 Complexity:
 expected worst-case time complexity is O(N*log(N));
 expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
 Elements of input arrays can be modified.
 */
public class Cities {

    public static void main(String [] args){

        Cities c = new Cities();

        int [] C = new int [7];
        int [] D = new int [7];

        C[0] = 1 ;
        C[1] = 3 ;
        C[2] = 0 ;
        C[3] = 3;
        C[4] = 2;
        C[5] = 4;
        C[6] = 4;

        D[0] = 6;
        D[1] = 2;
        D[2] = 7;
        D[3] = 5;
        D[4] = 6;
        D[5] = 5;
        D[6] = 2;

        System.out.println("First test case should return 2 : " + c.solution(2, C, D));
        System.out.println("First test case should return 4 : " + c.solution(4, C, D));

    }

    /**
     * @param K - K param
     * @param C - City connections
     * @param D - Cities attractiveness
     * @return  max trip plan
     */
    public int solution(int K, int[] C, int[] D) {

        int N = C.length;
        Integer[] dd = new Integer[D.length];
        for (int i = 0; i < D.length; i++) {
            dd[i] = Integer.valueOf(D[i]);
        }
        List<Integer> order  = new ArrayList<Integer>(Arrays.asList(dd));

        Collections.sort(order);
        Arrays.sort(dd);

        //We start from list of most attractive city
        int start = -1;

        //find one of this max index to start travel no matter which
        for(int i=0; i<N; i++)
            if(D[i]==order.get(N-1))
                start = i;

        //Important we can create minimum spanning tree that depends on K
        if(K>N) return N;
        int ll = order.get(N-K); //LowerLimit
        int max = order.get(N-1);

        //Lets create awesome graph :)
        Graph g = new Graph(N);
        for(int i=0; i<N; i++){
            //city i is connected with its C val and vice versa
            g.v[i].i = i;
            if(C[i] != i) {
                g.v[i].c.add(C[i]);
                g.v[C[i]].c.add(i);
            }
        }

        //Something like BSF
        int qu[] = new int[N];
        int b = 0, e = 0;
        // Do kolejki wstawione zostaje zrodlo
        qu[0] = start;
        for(int i=1; i<N; i++) qu[i]=-1;
        g.v[start].t++;
        // Dopoki w kolejce sa jakies wierzcholki...
        while(b<=e) {
            start=qu[b];
            b++;
            // Dla kazdej krawedzi wychodzacej z aktualnie przetwarzanego wierzcholka,
            // jesli wierzcholek, do ktorego ona prowadzi, nie byl jeszcze wstawiony do
            // kolejki, wstaw go i wyznacz wartosci zmiennych int t i int s

            for(Integer ver : g.v[start].c){
                if(g.v[ver].t == 0 && D[ver] >= ll){ //Important we go bread util lower limit
                    qu[++e]=g.v[ver].i;
                    g.v[ver].t++;
                }
            }
        }

        int ss = 0; //StackSize
        for(int i=0; i<N; i++) if(qu[i]==-1){ ss=i; break; };

        //Checkin if there are no "island" element with big value outside the minimum spanning tree
        for (int i=0; i<ss; i++){
            int attr = D[qu[i]];
            int found = Collections.binarySearch(order, attr);
            order.remove(found);
        }
        int j;
        for(j=max; j>=ll; j--){
            if(Collections.binarySearch(order, j) != -1) break;
        }
        if(j != ll){
            int result = 0;
            for(int i=N-1; i>0; i--){
                if(dd[i]>j)result++;
            }
        }

        return K>ss ? ss : K;
    }

    //Vertex
    class V{
        List<Integer> c = new ArrayList<Integer>();
        int i; //order index

        int t = 0; // if vertex was discover
    }

    class Graph{

        V [] v;

        public Graph(int size){
            v = new V[size];

            for(int i=0; i<size; i++)
                v[i] = new V();
        }
    }
}
