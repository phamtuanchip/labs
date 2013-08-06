/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package thi;

import java.util.Random;

/**
 *
 * @author ninh
 */
public class Bai1 implements Runnable {

    Thread obj1;

    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            if (Thread.currentThread() == obj1) {
                print();
            }
        }

    }

    public void print() {
        int a = new Random().nextInt(100);
        System.out.println(a);
        if(a==99){
            System.exit(0);
        }
    }

    public Bai1() {
        obj1 = new Thread(this);
        obj1.start();
    }

    public static void main(String[] args) {
        new Bai1();
    }
}
