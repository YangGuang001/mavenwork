package com.maven.work;

/**
 * Created by yz on 2017/7/30.
 */
public class helloworld {
    public void sayhello(String string){
        System.out.println("say: " + string);
    }

    public static void main(String[] args) {
        helloworld helloworld = new helloworld();
        helloworld.sayhello("yangxinzhao");
    }
}
