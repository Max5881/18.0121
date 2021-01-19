package ru.sapteh;

import java.io.File;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class MyVisitFile extends SimpleFileVisitor<Path> {
    private final List<File> filesList = new ArrayList<>();

    public List<File> getFilesList() {
        return filesList;
    }

    @Override
    public FileVisitResult visitFile (Path file, BasicFileAttributes attr){
        filesList.add(file.toFile());

        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult preVisitDirectory (Path dir, BasicFileAttributes attr){
        filesList.add(dir.toFile());

        return FileVisitResult.CONTINUE;
    }
}
