package swust.yang.util;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.springframework.web.multipart.MultipartFile;

public class UploadFile {

	public static HashMap<String,String> upload(String destDir,String fileName,MultipartFile file) {
		HashMap<String,String> map = new HashMap<String,String>();
		File dir = new File(destDir);
		if(!dir.exists()) {
			dir.mkdirs();
		}
		//文件后缀名.xxx
		String fileSuffix = fileName.substring(fileName.lastIndexOf('.'));
		//文件重命名
		fileName = fileName.substring(0, fileName.lastIndexOf('.')) 
					+ System.currentTimeMillis() + fileSuffix;
		//插件上传的文件路径
		String filePath = destDir + fileName;
		map.put("filePath", filePath);
		//储存插件的文件对象
		File plugFile = new File(filePath);
		//把上传的文件保存至指定位置
		try {
			file.transferTo(plugFile);
		} catch (IllegalStateException e) {
			e.printStackTrace();
			map.put("msg","文件过大！");
			return map;
		} catch (IOException e) {
			e.printStackTrace();
			map.put("msg","上传文件失败！");
			return map;
		}
		map.put("msg","OK");
		return map;
	}
}
