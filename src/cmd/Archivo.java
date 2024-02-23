/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cmd;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author jomel
 */
public class Archivo {
    File file = new File("");
    
    String getPath(){
        return file.getAbsolutePath();
    }
    
    boolean  Mkdir(String nombre){
        return new File(file.getAbsolutePath()+"\\\\"+nombre).mkdirs();
    }
    
    boolean Mfile(String nombre) throws IOException{
        return new File(file.getAbsolutePath()+"\\\\"+nombre).createNewFile();
    }
    
    void Remove(String nombre){
        File file = new File(nombre);
        if(file.isDirectory()){
            for(File child: file.listFiles()){
                Remove(child.getAbsolutePath());
            }
        }
        file.delete();
    }
    
    //Toca cambio
    void ChangeDirectory(String nombre){
            file = new File(nombre);
    }
    
    void back(){
        String path = file.getAbsolutePath();
        String newPath = "";
        String[] divs = path.split("\\\\");
        newPath = divs[0];
        for(int i= 1; i < divs.length - 1; i++){
            newPath += "/" + divs[i];
        }
        file = new File(newPath);
    }
    
    String Directory() throws IOException{
        String txt;
        if(file.isDirectory()){
            txt = "<DIR> "+file.getPath();
            for(File child: file.listFiles()){
                if(child.isDirectory()){
                    txt += "\n     <DIR>   " + child.getPath();
                }else{
                    txt += "\n     <FILE>   " + child.getPath();
                }
            }
        }else{
            txt = "<FILE> " + file.getPath();
        }
        return txt;
    }
    
    String Date(){
        return "Fecha Actual: " + new SimpleDateFormat("dd/MM/yy").format(Calendar.getInstance().getTime());
    }
    
    String Time(){
        return "Hora Actual: " + new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
    }
    
    //Cambiar
    void Write(String txt) throws FileNotFoundException, IOException{
        FileOutputStream archivo = new FileOutputStream(txt);
                        ObjectOutputStream objeto = new ObjectOutputStream(archivo);
                        objeto.writeObject("Se escribio aqui para el archivo " + txt);
                        objeto.close();
    }
    
    String Read(String path) throws FileNotFoundException, IOException, ClassNotFoundException{
        FileInputStream archivo = new FileInputStream(path);
        ObjectInputStream object = new ObjectInputStream(archivo);
        return (String)object.readObject();
    }
    
}
