package com.spoors.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.spoors.service.FileUploadService;

@Controller

public class ZipFileUploadController {
	//private static final String OUTPUT_FOLDER =  "/home/prasanna/Desktop/project/unzip";
	@Autowired
	 FileUploadService service;
	
	@RequestMapping(value="/savefile",method=RequestMethod.POST)  
	public ModelAndView upload(@RequestParam CommonsMultipartFile file,HttpSession session){  
	        String path=session.getServletContext().getRealPath("/");  
	        String filename=file.getOriginalFilename();  
	          
	        System.out.println(path+" "+filename);  
	        
	        byte barr[]=file.getBytes();
	        
	        String INPUT_ZIP_FILE=(path+"/"+filename);
	        String OUTPUT_FOLDER = "/home/sahana/Desktop/Download Pdf/unzip";

			 byte[] buffer = new byte[4*1024];
		    	
		    
		    		try {
		    	//create output directory is not exists
		    	File folder = new File(OUTPUT_FOLDER);
		    	if(!folder.exists()){
		    		folder.mkdir();
		    	}
		    		
		    	//get the zip file content
		    	ZipInputStream zis = 
		    		new ZipInputStream(new FileInputStream(INPUT_ZIP_FILE));
		    	//get the zipped file list entry
		    	ZipEntry ze = zis.getNextEntry();//Returns the next entry from 
		    	//this ZipInputStream or null if no more entries are present.
		    		
		    	while(ze!=null){
		    			
		    	   String fileName = ze.getName();
		           File newFile = new File(OUTPUT_FOLDER + File.separator + fileName);
		                
		          System.out.println("file unzip : "+ newFile.getAbsoluteFile());
		                
		            //create all non exists folders
		            //else you will hit FileNotFoundException for compressed folder
		            new File(newFile.getParent()).mkdirs();
		              
		            FileOutputStream fos = new FileOutputStream(newFile);             

		            int len;
		            while ((len = zis.read(buffer)) > 0) {
		       		fos.write(buffer, 0, len);
		            }
		        		
		            fos.close();   
		            ze = zis.getNextEntry();
		    	
		    	
		        zis.closeEntry();
		    	zis.close();
		    	service.importFile(OUTPUT_FOLDER);
		    	}
		    		}
		    	
		    	
		    		catch(Exception e) {
	        e.printStackTrace();
	        }
		    		return new ModelAndView("personReport","filename",path+"/"+filename);  

	        
	}
	}



	

	
	/*
	@RequestMapping(value="/welcomeFile",method=RequestMethod.GET)
	public ModelAndView indexFile() {
		return new ModelAndView("file_upload");
	}*/
	/*@RequestMapping(value="/zipFile",method=RequestMethod.GET)
	public String uploadFile(Model model,@ModelAttribute("uploadForm") FileUpload upload) {
		File dir=new File("/home/prasanna/Desktop/project/");
		File newFile=null;
		MultipartFile file=upload.getFile();
		String  OUTPUT_FOLDER =null;
		String fileName="";
		if(file!=null) {
			fileName=file.getOriginalFilename();
			//System.out.println("original file name"+fileName);
			InputStream inputStream=null;
			//OutputStream outputStream=null;
			newFile=new File("/home/prasanna/Desktop/project/"+fileName);
			 
		
			try {
				inputStream=file.getInputStream();
				if(!dir.exists()) {
					dir.mkdir();
				}
				//File newFile=new File("/home/prasanna/Desktop/project/"+fileName);
				//outputStream=new FileOutputStream(newFile);
				
				int read=0;
				byte[] buffer=new byte[1024];
				while((read=inputStream.read(buffer))!=-1) {
					outputStream.write(buffer,0,read);
				}
				outputStream.close();
				inputStream.close();

		 byte[] buffer = new byte[1024];
	    	
	    
	    		try {
	    	//create output directory is not exists
	    	File folder = new File(OUTPUT_FOLDER);
	    	if(!folder.exists()){
	    		folder.mkdir();
	    	}*/
	    		
	    	//get the zip file content
	    	/*ZipInputStream zis = 
	    		new ZipInputStream(new FileInputStream(newFile));
	    	//get the zipped file list entry
	    	ZipEntry ze = zis.getNextEntry();//Returns the next entry from 
	    	//this ZipInputStream or null if no more entries are present.
	    		
	    	while(ze!=null){
	    			
	    	   fileName = ze.getName();
	           newFile = new File(OUTPUT_FOLDER + File.separator + fileName);
	                
	          // System.out.println("file unzip : "+ newFile.getAbsoluteFile());
	                
	            //create all non exists folders
	            //else you will hit FileNotFoundException for compressed folder
	            new File(newFile.getParent()).mkdirs();
	              
	            FileOutputStream fos = new FileOutputStream(newFile);             

	            int len;
	            while ((len = zis.read(buffer)) > 0) {
	       		fos.write(buffer, 0, len);
	            }
	        		
	            fos.close();   
	            ze = zis.getNextEntry();
	    	}
	    	
	        zis.closeEntry();
	    	zis.close();
	    	
	    	
	    	
				
			}
			catch(IOException ioe) {
				ioe.printStackTrace();
			}
	    		
				//rdab.addFlashAttribute("message", "You successfully uploaded '" + file.getOriginalFilename() + "'");

			catch(Exception e) {
				//rdab.addFlashAttribute("message", "Failure occured during upload " + file.getOriginalFilename() + "'");
				e.printStackTrace();
				
	    		
	    }
	    		service.importFile(OUTPUT_FOLDER);
	
	
		model.addAttribute("files", fileName);		
		return "fileupload_success";
		
	}

}
*/
