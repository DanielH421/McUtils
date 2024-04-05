package dev.dan.mcutils.utils;

import dev.dan.mcutils.McUtils;
import org.rauschig.jarchivelib.ArchiveFormat;
import org.rauschig.jarchivelib.Archiver;
import org.rauschig.jarchivelib.ArchiverFactory;
import org.rauschig.jarchivelib.CompressionType;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Objects;

public class FileUtils {





    /*
        ALL OF THIS IS SUPER GROSS AND NEARLY 4 YEARS OLD!!
        PLEASE DO NOT READ
     */


    // TODO - Redo this entire thing.


    public static void deleteFile(File file){
        try {
            org.apache.commons.io.FileUtils.forceDelete(file);
        } catch (IOException e) {
            McUtils.getInstance().getCustomLogger().printStackTrace(e);
        }
    }


    public static void compress(File source){
        String archivename = source.getName();
        Archiver archiver = ArchiverFactory.createArchiver(ArchiveFormat.TAR, CompressionType.GZIP);
        try {
            File archive = archiver.create(archivename, source.getParentFile(), source);
        } catch (IOException e) {
            McUtils.getInstance().getCustomLogger().printStackTrace(e);
        }
    }

    public static void uncompressFile(File source){
        Archiver archiver = ArchiverFactory.createArchiver(ArchiveFormat.TAR, CompressionType.GZIP);
        try {
            archiver.extract(source, source.getParentFile());
        } catch (IOException e) {
            McUtils.getInstance().getCustomLogger().printStackTrace(e);
        }
    }
    public static void uncompressFolder(File source){
        Archiver archiver = ArchiverFactory.createArchiver(ArchiveFormat.TAR, CompressionType.GZIP);
        try {
            archiver.extract(source, new File(source.getParentFile(), source.getName().replace(".tar.gz", "")));
        } catch (IOException e) {
            McUtils.getInstance().getCustomLogger().printStackTrace(e);
        }
    }




    //SPECIFIALLY MADE FOR WORLD FILES
    public static void moveRegionOnPack(File world){
        for(String files : world.list()){
            if(files.contains("region")) {
                File regions = new File(world+"/region/");
                for (String region : regions.list()){
                    File regionFile = new File(regions+"/"+region);
                    try {
                        org.apache.commons.io.FileUtils.moveFileToDirectory(regionFile, world, false);
                    } catch (IOException e) {
                        McUtils.getInstance().getCustomLogger().printStackTrace(e);
                    }
                }
            }
        }
    }




    public static void moveRegionOnUnpack(File f){
        File world = new File(f.getParentFile(), f.getName().replace(".tar.gz", ""));
        for(String file : world.list()){
            if(file.contains(".mca")) {
                File region = new File(world+"/"+file);
                File regions = new File(world +"/region/");
                try {
                    org.apache.commons.io.FileUtils.moveFileToDirectory(region, regions, false);
                } catch (IOException e) {
                    McUtils.getInstance().getCustomLogger().printStackTrace(e);
                }

            }
        }
    }





}
