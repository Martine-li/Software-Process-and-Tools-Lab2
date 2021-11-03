package sample.calculate;
import java.util.Random;

public class RandomDemo {
    public static int[] getRandom(int flag){
        long a = System.currentTimeMillis();
        int work[] = new int[4];
        Random seed = new Random(a);
        work[0] = seed.nextInt(100);
        seed = new Random(work[0]);
        work[1] = seed.nextInt(100);
        seed = new Random(a);
        work[2] = seed.nextInt(2);
        if(flag == 1)
            work[2] = 0;
        if(flag == 0)
            work[2] = 1;
        return work;
    }

    public static String toString(int work[]){
        String result = null;
        if ( work[2] ==0 ) //加法
        {
            work[3] = work[1] + work[0];
            result = String.valueOf(work[0]) + "+" + String.valueOf(work[1])+ "=";
        }
        else{
            if (work[0] < work[1]){ //让被减数比减数大
            int temp = work[0];
            work[0] = work[1];
            work[1] = temp;
            }
            work[3] = work[0] - work[1];
            result = String.valueOf(work[0]) + "-" + String.valueOf(work[1])+ "=";
        }
        return result;
    }
}
