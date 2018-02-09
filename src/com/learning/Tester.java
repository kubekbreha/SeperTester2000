package com.learning;

import java.util.Random;

public class Tester {

    private Random mRand;
    private int x, y;
    private int mScreenWidth;
    private int mScreenHeight;
    private int mLength;

    public Tester(String packageName, String screenSize, int length) throws InterruptedException {
        mRand = new Random();
        openAndroidPackage(packageName);
        System.out.println("opened");
        Thread.sleep(7000);
        pickScreenSize(screenSize);
        mLength = length;
    }

    public Tester(String screenSize, int length){
        mRand = new Random();
        pickScreenSize(screenSize);
        mLength = length;
    }

    public void doTest(){
        for(int i = 0; i < mLength; i++) {
            x = mRand.nextInt(mScreenWidth) + 1;
            y = mRand.nextInt(mScreenHeight) + 1;
            sendClicks(x, y);
            System.out.println("Clicked " + i);
        }
    }


    /**
     * Open android package via ADB.
     */
    private void openAndroidPackage(String packageName){

        ProcessBuilder pb = new ProcessBuilder("adb", "shell", "monkey", "-p", packageName,
                "-c", "android.intent.category.LAUNCHER", "1");
        try {
            Process pc = pb.start();
            pc.waitFor();
        } catch (Exception e) {

        }
    }

    /**
     * Send clicks to phone.
     */
    private void sendClicks(int x, int y){
        ProcessBuilder pb = new ProcessBuilder("adb", "shell", "input", "tap", String.valueOf(x), String.valueOf(y));
        try {
            Process pc = pb.start();
            pc.waitFor();
        } catch (Exception e) {

        }
    }

    private void pickScreenSize(String resolution){
        switch (resolution){
            case "nHD":
                mScreenWidth = 480;
                mScreenHeight = 800;
                break;

            case "HD":
                mScreenWidth = 720;
                mScreenHeight = 1280;
                break;

            case "FHD":
                mScreenWidth = 1080;
                mScreenHeight = 1920;
                break;

            case "QHD":
                mScreenWidth = 1440;
                mScreenHeight = 2560;
                break;

            default: //for my samsung S7
                mScreenWidth = 1440;
                mScreenHeight = 2560;
                break;
        }
    }
}
