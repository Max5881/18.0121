package ru.sapteh;

import java.io.*;
import java.nio.file.*;


import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Program {
    public static void main(String[] args) throws IOException {

        BufferedReader readerPathFile = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter path file: ");
        String pathFile = readerPathFile.readLine();
        Path path = Paths.get(pathFile);

        FileOutputStream zipArchive = new FileOutputStream(pathFile + ".zip");
        ZipOutputStream zip = new ZipOutputStream(zipArchive);

        MyVisitFile myVisitFile = new MyVisitFile();
        Files.walkFileTree(path,myVisitFile);

        List<File> filesList = myVisitFile.getFilesList();

        ZipEntry zipEntry;
        for (File file : filesList){
            if (file.isDirectory()){
                zipEntry = new ZipEntry(replacePath(file.toString(), path.getParent().toString()).concat("/"));
                System.out.println(replacePath(file.toString(), path.getParent().toString()).concat("/"));
                zip.putNextEntry(zipEntry);
                zip.closeEntry();
            }if (file.isFile()){
                zipEntry = new ZipEntry(replacePath(file.toString(),path.getParent().toString()));
                System.out.println(replacePath(file.toString(), path.getParent().toString()));
                zip.putNextEntry(zipEntry);
                Files.copy(file.toPath(),zip);
                zip.closeEntry();
            }
        }
        zip.close();
    }
    public static String replacePath (String path, String replacePath){

        return path.substring(replacePath.length() + 1);
    }
}