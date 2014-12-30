package codilitytraining.lesson13CaterpillarMethod;

import java.util.Random;

/**
 * Author: Mazeryt Freager
 * Contact: skirki@o2.pl
 * Date: 14.12.14
 * Time: 15:31
 */
public class Test {

    public static void main(String [] args){



//        int [] A = {6, 2, 7, 4, 1, 3, 6};
//
//        System.out.println("is sum 12?" + caterpillarExample(A, 12));
    }


    public static boolean caterpillarExample(int [] A, int s){

        int N = A.length;
        int front = 0, total = 0;

        for(int i=0; i<N; i++){
            while (front < N && total+A[front] <=s) {
                total += A[front];
                front += 1;
            }
            if(total == s)
                return true;

            total -= A[i];
        }
        return false;
    }


    public static void genericRandoms(){
        String endSgn = ";" ;
        Random random = new Random();


        //Zigbee1     231	748	21
        //231 probes from 90 to 800 ms
             System.out.println("Zigbee1 231 probes from 90 to 800 ms: ");
        for(int i=0; i<231; i++){
            System.out.println(random.nextInt(720)+90 + endSgn);
        }
        //748 probes from 1100ms to 4800
             System.out.println("Zigbee1 748 probes from 1100ms to 4800 ms:  ");
        for(int i=0; i<748; i++){
            System.out.println(random.nextInt(3700)+1100 + endSgn);
        }
        //21 probes from 5100 ms to 6000 ms
        System.out.println("Zigbee1 21 probes from 5100 ms to 6000 ms :  ");
        for(int i=0; i<21; i++){
            System.out.println(random.nextInt(900)+5100 + endSgn);
        }

        //Zigbee2    0	863	137
        //863 probes from 2500ms to 4800
        System.out.println("Zigbee1 863 probes from 1100ms to 4800 ms:  ");
        for(int i=0; i<863; i++){
            System.out.println(random.nextInt(3700)+1100 + endSgn);
        }
        //137 probes from 5200ms to 9100 ms
        System.out.println("Zigbee1 137 probes from 5200 ms to 9100 ms:  ");
        for(int i=0; i<137; i++){
            System.out.println(random.nextInt(4100)+5200 + endSgn);
        }

        //BLE     0	882	118
        //882 probes from 2100ms to 4800
        System.out.println("BLE 882 probes from 1100 ms to 4800 ms:  ");
        for(int i=0; i<882; i++){
            System.out.println(random.nextInt(3700)+1100 + endSgn);
        }
        //118 probes from 5200 ms to 9800 ms
        System.out.println("BLE 118 probes from 5200 ms to 9800 ms:  ");
        for(int i=0; i<118; i++){
            System.out.println(random.nextInt(4600)+5200 + endSgn);
        }
    }
}                          //
//
//int lastProcent = 0;
//System.out.println(lastProcent+"%");
//        for (int i = 0; i < size; i++) {
//
//        int randomNumber = random.nextInt(8);

