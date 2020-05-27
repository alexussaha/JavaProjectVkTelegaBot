package vk;


import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.messages.Message;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import java.io.*;

public class VKServer {
    public static VKCore vkCore;

    static {
        try {
            vkCore = new VKCore();
        } catch (ApiException | ClientException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws NullPointerException, ApiException, InterruptedException, IOException {


        System.out.println("Running server...");
        File dir = new File("db.txt");
        dir.createNewFile();
        while (true) {
            Thread.sleep(300);

            try {
                Message message = vkCore.getMessage();
            if (message != null) {
                try(FileWriter writer = new FileWriter("db.txt", true))
                {
                    // запись всей строки
                    String text = message.getBody();
                    writer.write(text);
                    // запись по символам
                    writer.append('\n');
                    System.out.println("Done");

                    writer.flush();
                }
                catch(IOException ex){

                    System.out.println(ex.getMessage());
                }
                ExecutorService exec = Executors.newCachedThreadPool();
                    exec.execute(new Messenger(message));
                }
            } catch (ClientException e) {
                System.out.println("Возникли проблемы");
                final int RECONNECT_TIME = 10000;
                System.out.println("Повторное соединение через " + RECONNECT_TIME / 1000 + " секунд");
                Thread.sleep(RECONNECT_TIME);

            }
        }
    }
}