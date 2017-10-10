package br.edu.utfpr.dainf.eex23.heliustester;

import java.util.Scanner;

/**
 *
 * @author rapha
 */
public class Execute {
    public static void main(String[] args) {
        SenderUDPThread runnable = new SenderUDPThread();
        Thread thread = new Thread(runnable);
        thread.start();
        System.out.println("Pressione qualquer tecla para continuar...");
        Scanner s = new Scanner(System.in);
        s.nextLine();
        runnable.stop();
    }
}
