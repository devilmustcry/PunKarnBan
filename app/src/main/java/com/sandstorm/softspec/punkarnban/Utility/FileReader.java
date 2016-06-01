package com.sandstorm.softspec.punkarnban.Utility;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.sandstorm.softspec.punkarnban.Models.Game.Game;
import com.sandstorm.softspec.punkarnban.Models.Player.Player;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.StreamCorruptedException;

import Memento.Memento;

/**
 * Created by FTTX on 5/31/2016 AD.
 */
public class FileReader {

    static String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Punkarnban";


    public static Memento readMemento(String name,Context context) {
        File file = new File(path+"/"+name+".txt");

        try {
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fileIn);
            Memento m = (Memento) ois.readObject();
            fileIn.close();
            ois.close();
            return m;
        } catch (FileNotFoundException e) {
            Log.e("File", "Can't find file");
            e.printStackTrace();
            return internalRead(name,context);
        } catch (IOException e) {
            Log.e("File","IOException");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            Log.e("Class","ClassNotFoundException");
            e.printStackTrace();
        }
        return null;
    }

    private static Memento internalRead(String name,Context context) {
        String fileName = name+".txt";

        try {
            FileInputStream fileIn = context.openFileInput(fileName);
            ObjectInputStream ois = new ObjectInputStream(fileIn);
            Memento m = (Memento) ois.readObject();
            fileIn.close();
            ois.close();
            return m;
        } catch (FileNotFoundException e) {
            Log.e("File","Can't read file in internal");
            e.printStackTrace();
        } catch (StreamCorruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
