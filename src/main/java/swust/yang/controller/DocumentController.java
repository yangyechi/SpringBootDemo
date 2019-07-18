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
import swust.yang.pojo.Document;
import swust.yang.service.DocumentService;
import swust.yang.util.JwtToken;
import swust.yang.util.ResultResponse;
import swust.yang.util.UploadFile;
import swust.yang.vo.VODocument;

@Api(tags = "Document")
@RestController
@Validated
@RequestMapping("/Document")
public class DocumentController {

	@Autowired
	private DocumentService documentService;

	@Value("${web.uploadDoc-path-windows}")
	private String filePathOfWindows;

	@ApiOperation(value = "上传文档")
	@PostMapping(value = "/add")
	@ApiParam(required = true, value = "上传文档")
	public ResultResponse<Document> save(
			@ApiParam(required = true, value = "上传文档") @RequestParam(value = "file", required = true) MultipartFile file,
			@Valid Document record, HttpServletRequest request) {
		// 验证token
		String token = request.getHeader("Authorization");
		if (token == null || token.isEmpty()) {
			return new ResultResponse<Document>(1, "上传文档失败！请登录！", null);
		} else {
			try {
				Claims claims = JwtToken.parseToken(token);
				String role = claims.get("role", String.class);
				if (!role.equals("admin")) {
					return new ResultResponse<Document>(1, "没有上传文档的权限！请联系管理员！", null);
				} 
			} catch (Exception e) {
				return new ResultResponse<Document>(1, "登录已失效！请重新登录！", null);
			}
		}

		// 保存文档
		HashMap<String, String> ret = null;
		if (!System.getProperty("os.name").equals("Linux")) {
			ret = UploadFile.upload(filePathOfWindows, file.getOriginalFilename(), file);
		} else {
			// Linux环境下
		}
		if (!ret.get("msg").equals("OK")) {
			return new ResultResponse<Document>(1, ret.get("msg"), null);
		}
		
		//把文档信息存储到数据库中
		record.setDate(new Date(System.currentTimeMillis()));
		record.setDownloads("0");
		record.setLocation(ret.get("filePath"));
		if(documentService.save(record) != 1) {
			return new ResultResponse<Document>(1, "文档上传失败", null);
		}
		return new ResultResponse<Document>(0, "文档上传成功", null);
	}

	@ApiOperation(value = "下载文档")
	@PostMapping(value = "/download")
	public ResultResponse<Document> getById(
			@ApiParam(required = true, value = "文档id") Integer id,
			HttpServletResponse response) {
		Document record = documentService.getById(id);
		// 获取该插件的下载次数
		Integer downloads = Integer.valueOf(record.getDownloads());
		// 获取该文件的物理位置
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
			return new ResultResponse<Document>(1, "500：下载失败", null);
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
            documentService.editDownloadsById(map);
            return null;
        } catch (IOException ex) {
            ex.printStackTrace();
            return new ResultResponse<Document>(1, "500：下载失败", null);
        } 
	}
	
	@ApiOperation(value = "详细列表查询")
	@GetMapping(value = "/list")
	public ResultResponse<List<VODocument>> list(@ApiParam(required = true, value = "查询类别") String tag) {
		List<VODocument> list = documentService.list(tag);
		return new ResultResponse<List<VODocument>>(0, "查询成功", list);
	}
	/*
	 * @ApiParam(required = true, value = "修改内容")
	 * 
	 * @PostMapping(value = "/edit") public ResultResponse<Doucument> editById(
	 * 
	 * @ApiParam(required = true, value = "修改Doucument") @Valid @RequestBody
	 * Doucument record) {
	 * 
	 * doucumentService.editById(record);
	 * 
	 * return new ResultResponse<Doucument>(0, "修改成功", record); }
	 * 
	 * 
	 * @ApiOperation(value = "详细列表查询")
	 * 
	 * @GetMapping(value = "/pagelist") public ResultResponse pagelist(
	 * 
	 * @ApiParam(required = true, value = "查询Demo") @RequestParam(value = "page",
	 * required = false) int page,
	 * 
	 * @RequestParam(value = "limit", required = false) int limit) { List<Doucument>
	 * list = doucumentService.pagelist((page - 1) * limit, limit); return new
	 * ResultResponse(0, "查询成功", list, list.size()); }
	 * 
	 * @ApiOperation(value = "删除记录")
	 * 
	 * @DeleteMapping(value = "/del/{id}") public ResultResponse<Doucument>
	 * delById(@ApiParam(required = true, value = "查询编号") @PathVariable Integer id)
	 * { doucumentService.delById(id); return new ResultResponse<Doucument>(0,
	 * "删除成功", null); }
	 * 
	 *  @ApiOperation(value = "根据ID查询记录")
		@GetMapping(value = "/get/{id}")
		public ResultResponse<Document> getById(@ApiParam(required = true, value = "查询编号") @PathVariable Integer id) {
	
			Document record = documentService.getById(id);
			return new ResultResponse<Document>(0, "查询成功", record);
		}
	 */
}
