package com.sandstorm.softspec.punkarnban.Utility;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.sandstorm.softspec.punkarnban.Models.Game.Game;
import com.sandstorm.softspec.punkarnban.Models.Player.Player;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import Memento.Memento;

/**
 * Created by FTTX on 5/31/2016 AD.
 */
public class FileWriter {

    static String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Punkarnban";

    public static void write(Memento memento,Context context) {
        File dir = new File(path);
        if(!dir.exists())
            dir.mkdirs();


        String fileName = "/" + memento.getName()+".txt";

        File file = new File(path+fileName);

        try {
            FileOutputStream fileOut = new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(memento);
            out.close();
            fileOut.close();
        } catch (FileNotFoundException e) {
            Log.e("File","Can't find file");
            internalWrite(memento,context);
            e.printStackTrace();
        } catch (IOException e) {
            Log.e("File","IOException");
            e.printStackTrace();
        }
    }

    private static void internalWrite(Memento memento,Context context) {
        //Internal Write
        String fileName = memento.getName()+".txt";
        try {
            FileOutputStream fileOut = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(memento);
            out.close();
            fileOut.close();
        } catch (FileNotFoundException e) {
            Log.e("File","Can't Write file in internal");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }








}
