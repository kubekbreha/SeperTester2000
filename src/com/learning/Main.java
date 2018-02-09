package com.learning;

import java.util.Random;

public class Main {

    private Random mRand;

    public static void main(String[] args) throws InterruptedException {
        Tester tester = new Tester("sk.inlogic.jewelbubblesmania", "QHD" , 100);
        tester.doTest();
    }
}
