package com.huzan.interview.util;

import java.util.Random;

/**
 * Created by huzan on 2016/11/23.
 */
public class RandomUtil {
    public static int getRandom(int max)
    {
        int min = 0;
        max +=1;
        Random random = new Random();
        int s = random.nextInt(max) % (max - min + 1) + min;
        return s;
    }

}
