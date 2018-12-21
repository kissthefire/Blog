import java.util.Random;

/**
 * 版本         开发者               创建日期
 * 1.0.0   InetCommunity(^_^)on     2018/12/19.
 */
public class RandomTest {
    public static void main(String[] args) {
        Random random=new Random();
        for(int n=0;n<100;n++){
            int i= random.nextInt(10);
            System.out.println(i);
        }
    }
}
