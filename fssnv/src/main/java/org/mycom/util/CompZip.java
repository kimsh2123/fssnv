package org.mycom.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;

/*
 * File compress UTIL 2016.05.02
 */

public class CompZip {

	public static String createZipFile(String date, String path, String file_name, String target_path) throws Exception {

		File target_file = new File(path); //to compress the file
		File[] files = null;
		String outfilename = target_path + "/" + file_name; //zip file name

		try {
			// to compress the file
			if (target_file.isDirectory()) {
				files = target_file.listFiles();
			}

			ZipArchiveOutputStream out = new ZipArchiveOutputStream(
					new BufferedOutputStream(new FileOutputStream(outfilename)));
			
			makeZip(files, out, "");
			
			// zip file end
			out.close();

		} catch (Exception e) {
		}

		return file_name;
	}
	
	private static void makeZip(File[] files, ZipArchiveOutputStream out, String targetDir) throws Exception {
		
		byte[] bt = new byte[1024];
		
		for(int i=0; i < files.length; i++){
			
			File compath = new File(files[i].getPath());
			
			if(compath.isDirectory()){
				File[] subfiles = compath.listFiles();
				makeZip(subfiles, out, targetDir+compath.getName()+"/");
				continue;
			}
			
			FileInputStream in = new FileInputStream(compath);
			
			out.putArchiveEntry(new ZipArchiveEntry(targetDir+"/"+files[i].getName()));
			
			int data;
			while((data = in.read(bt)) > 0){
				out.write(bt, 0, data);
			}
			
			out.closeArchiveEntry();
			in.close();
		}
		
	}

}
