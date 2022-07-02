package com.sathidar.controller;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import org.apache.commons.codec.binary.Base64;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sathidar.model.UploadImagesModel;
import com.sathidar.service.UploadImagesService;

@CrossOrigin(origins = "http://localhost:4200" , methods = {RequestMethod.OPTIONS, RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}, allowedHeaders = "*", allowCredentials = "true")
@RestController
@RequestMapping("/api")
public class UploadImagesController {

	@Autowired
	private UploadImagesService uploadImagesService;
	
	@PostMapping(path = "/member/upload/photo")
	public HashMap<String, String> uploadImages(@Validated @RequestBody UploadImagesModel uploadImagesModel) {
		HashMap<String, String> map = new HashMap<>();
		try {
			
			String[] strArray=uploadImagesModel.getImage_base_urls();
			for(int i=0;i<strArray.length;i++) {
				
				String base64Image = strArray[i].toString().split(",")[1];
	            byte[] data = java.util.Base64.getDecoder().decode(base64Image);
//	            String directory=
	            try( OutputStream stream = new FileOutputStream("d:/saathidar_images"+i+".jpg") ) 
	            {
	               stream.write(data);
	            }
	            
//	            ************************ new code *******************************
	            
	            JSONObject jsonObject = new JSONObject();
	             jsonObject.put("image_code", base64Image);
	             
	            String data1=jsonObject.toString();
	            String yourURL = "http://www.saathidaar.com/assets/images";
	             URL url = new URL(yourURL);
	             HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	             connection.setDoOutput(true);
	             connection.setDoInput(true);
	             connection.setRequestMethod("POST");
	             connection.setFixedLengthStreamingMode(base64Image.getBytes().length);
	             connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
	             OutputStream out = new BufferedOutputStream(connection.getOutputStream());
	             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));
	             writer.write(data1);
	             writer.flush();
	             writer.close();
	             out.close();
	             connection.connect();
	            
	            
				System.out.println("success");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
}
