package com.sandstorm.softspec.punkarnban.Utility;

import android.os.Environment;
import android.util.Log;

import com.sandstorm.softspec.punkarnban.Models.Game.Game;
import com.sandstorm.softspec.punkarnban.Models.Player.Player;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Created by FTTX on 5/31/2016 AD.
 */
public class FileReader {

    static String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Punkarnban";


    public static Game.GameMemento readGame() {
        String fileName = "/game.txt";
        File file = new File(path+fileName);

        try {
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fileIn);
            return (Game.GameMemento) ois.readObject();
        } catch (FileNotFoundException e) {
            Log.e("File","Can't find file");
            e.printStackTrace();
        } catch (IOException e) {
            Log.e("File","IOException");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            Log.e("Class","ClassNotFoundException");
            e.printStackTrace();
        }
        return null;
    }

    public static Player.PlayerMemento readPlayer() {
        String fileName = "/player.txt";
        File file = new File(path+fileName);

        try {
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fileIn);
            return (Player.PlayerMemento) ois.readObject();
        } catch (FileNotFoundException e) {
            Log.e("File","Can't find file");
            e.printStackTrace();
        } catch (IOException e) {
            Log.e("File","IOException");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            Log.e("Class","ClassNotFoundException");
            e.printStackTrace();
        }
        return null;
    }
}
