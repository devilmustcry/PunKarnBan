package com.sandstorm.softspec.punkarnban.Utility;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.sandstorm.softspec.punkarnban.Models.Game.Game;
import com.sandstorm.softspec.punkarnban.Models.Player.Player;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Created by FTTX on 5/31/2016 AD.
 */
public class FileWriter {

    static String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Punkarnban";

    public static void write(Game.GameMemento gameMemento) {

        File dir = new File(path);
        if(!dir.exists())
            dir.mkdirs();


        String fileName = "/game.txt";

        File file = new File(path+fileName);

        try {
            FileOutputStream fileOut = new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(gameMemento);
        } catch (FileNotFoundException e) {
            Log.e("File","Can't find file");
            e.printStackTrace();
        } catch (IOException e) {
            Log.e("File","IOException");
            e.printStackTrace();
        }
    }

    public static void write(Player.PlayerMemento playerMemento) {
        File dir = new File(path);
        if(!dir.exists())
            dir.mkdirs();

        String fileName = "/player.txt";

        File file = new File(path+fileName);

        try {
            FileOutputStream fileOut = new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(playerMemento);
        } catch (FileNotFoundException e) {
            Log.e("File","Can't find file");
            e.printStackTrace();
        } catch (IOException e) {
            Log.e("File","IOException");
            e.printStackTrace();
        }

    }




}
