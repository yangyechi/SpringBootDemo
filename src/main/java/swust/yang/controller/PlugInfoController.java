package swust.yang.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import swust.yang.pojo.PlugInfo;
import swust.yang.service.PlugInfoService;
import swust.yang.util.JwtToken;
import swust.yang.util.ResultResponse;
import swust.yang.util.UploadFile;
import swust.yang.vo.VOPlugIdAndName;
import swust.yang.vo.VOPlugInfo;


@Api(tags = "PlugInfo")
@RestController
@Validated
@RequestMapping("/PlugInfo")
public class PlugInfoController {

	@Autowired
	private PlugInfoService plugInfoService;

	@Value("${web.uploadPlug-path-windows}")	
	private String filePathOfWindows;
	
	@ApiOperation(value = "上传插件")
	@PostMapping(value = "/add")
	@ApiParam(required = true, value = "上传插件")
	public ResultResponse<PlugInfo> save(
			@ApiParam(required = true, value = "上传插件")
			@RequestParam(value = "file",required = true) MultipartFile file,
			@Valid PlugInfo record,
			HttpServletRequest request) {
		
		//上传者ID
		Integer userId = null;
		//验证token
		String token = request.getHeader("Authorization");
		if(token == null || token.isEmpty()) {
			return new ResultResponse<PlugInfo>(1, "上传插件失败！请登录！",null);
		} else {
			try {
				Claims claims = JwtToken.parseToken(token);
				String role = claims.get("role", String.class);
				if(!role.equals("admin")) {
					return new ResultResponse<PlugInfo>(1, "没有上传插件的权限！请联系管理员！",null);
				} else {
					userId = Integer.valueOf(claims.get("uid", String.class));			
				}
			} catch(Exception e) {
				return new ResultResponse<PlugInfo>(1, "登录已失效！请重新登录！",null);
			}		
		}
		
		//保存插件
		HashMap<String,String> ret = null;
		if(!System.getProperty("os.name").equals("Linux")) {
			ret = UploadFile.upload(filePathOfWindows, file.getOriginalFilename(), file);
		} else {
			//Linux环境下
		}
		if(!ret.get("msg").equals("OK")) {
			return new ResultResponse<PlugInfo>(1, ret.get("msg"), null);
		}
		
		//将插件信息储存至数据库
		record.setUserId(userId);
		record.setDate(new Date(System.currentTimeMillis()));
		record.setDownloads("0");
		record.setLocation(ret.get("filePath"));
		if(plugInfoService.save(record) != 1) {
			return new ResultResponse<PlugInfo>(1, "500：插件上传失败",null);
		}
		return new ResultResponse<PlugInfo>(0, "插件上传成功",null);
	}

	@ApiOperation(value = "下载插件")
	@PostMapping(value = "/download")
	public ResultResponse<PlugInfo> getById(
			@ApiParam(required = true, value = "插件id") Integer id,
			HttpServletResponse response) {
		PlugInfo record = plugInfoService.getById(id);
		//获取该插件的下载次数
		Integer downloads = Integer.valueOf(record.getDownloads());
		String filePath = record.getLocation();
		// 获取待下载的插件的文件名
		String fileName = filePath.substring(filePath.lastIndexOf(System.getProperty("file.separator")) + 1);
		// 清空response
		response.reset();
        response.setContentType("application/octet-stream");
        try {
        	 // 如果输出的是中文名的文件，在此处就要用URLEncoder.encode方法进行处理
			response.setHeader("Content-Disposition", 
					"attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
			// 设置filename请求头，并加入白名单，使前端可以获取
			response.addHeader("filename", URLEncoder.encode(fileName, "UTF-8"));
			response.setHeader("Access-Control-Expose-Headers", "filename");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return new ResultResponse<PlugInfo>(1, "500：下载失败", null);
		}
        //用于计算文件大小
        int num = 0;
        //开始下载
        try (BufferedInputStream input = new BufferedInputStream(new FileInputStream(filePath));
               BufferedOutputStream toClient = new BufferedOutputStream(response.getOutputStream())){
			byte[] buffer = new byte[10];
            int ret = 0;
            while((ret = input.read(buffer)) != -1) {
            	toClient.write(buffer, 0, ret); 
            	num += ret;
            }                
            response.setHeader("Content-length", String.valueOf(num));
            toClient.flush();
            //更新下载次数
            downloads++;
            HashMap<String,Object> map = new HashMap<String,Object>();
            map.put("id", id);
            map.put("downloads",String.valueOf(downloads));
            plugInfoService.editDownloadsById(map);
            return null;
        } catch (IOException ex) {
            ex.printStackTrace();
            return new ResultResponse<PlugInfo>(1, "500：下载失败", null);
        } 
	}

	@ApiOperation(value = "详细列表查询 ")
	@GetMapping(value = "/list")
	public ResultResponse<List<VOPlugInfo>> list() {
		List<VOPlugInfo> list = plugInfoService.list();
		return new ResultResponse<List<VOPlugInfo>>(0, "查询成功", list);
	}

	@ApiOperation(value = "列表查询(插件ID及插件名)")
	@GetMapping(value = "/listIdAndName")
	public ResultResponse<List<VOPlugIdAndName>> listPlugIdAndName() {
		List<VOPlugIdAndName> list = plugInfoService.listIdAndName();
		return new ResultResponse<List<VOPlugIdAndName>>(0, "查询成功", list);
	}
	
	@ApiOperation(value = "根据传入的搜索内容搜索插件")
	@GetMapping(value = "/search")
	public ResultResponse<List<VOPlugInfo>> searchPlugs(@ApiParam(required = true, value = "搜索内容") String content) {
		List<VOPlugInfo> list = plugInfoService.searchPlugs(content.trim());
		return new ResultResponse<List<VOPlugInfo>>(0, "查询成功", list);
	}
	
	/*
	@ApiOperation(value = "详细列表查询")
	@GetMapping(value = "/pagelist")
	public ResultResponse pagelist(
			@ApiParam(required = true, value = "查询Demo") @RequestParam(value = "page", required = false) int page,
			@RequestParam(value = "limit", required = false) int limit) {
		List<PlugInfo> list = plugInfoService.pagelist((page - 1) * limit, limit);
		return new ResultResponse(0, "查询成功", list, list.size());
	}
	
	@ApiOperation(value = "删除记录")
	@DeleteMapping(value = "/del/{id}")
	public ResultResponse<PlugInfo> delById(@ApiParam(required = true, value = "查询编号") @PathVariable Integer id) {
		plugInfoService.delById(id);
		return new ResultResponse<PlugInfo>(0, "删除成功", null);
	}
	
	@ApiParam(required = true, value = "修改内容")
	@PostMapping(value = "/edit")
	public ResultResponse<PlugInfo> editById(
			@ApiParam(required = true, value = "修改PlugInfo") @Valid @RequestBody PlugInfo record) {

		plugInfoService.editById(record);

		return new ResultResponse<PlugInfo>(0, "修改成功", record);
	}
	*/
}
