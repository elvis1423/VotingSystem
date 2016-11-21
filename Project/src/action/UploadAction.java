package action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import org.apache.struts2.ServletActionContext;

import service.ElectVoteService;
import view.LoginStatus;
import bean.ElectVote;
import bean.User;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class UploadAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private File file;
	private Integer electVoteId;
	private Integer order;
 	private String fileFileName;
	private String fileContentType;
	private String fileUrl;
	private String fileName;
	private InputStream inputStream;
	private ElectVoteService electVoteService;

	@SuppressWarnings("deprecation")
	public String uploadImage() throws Exception {
		LoginStatus loginStatus = (LoginStatus)ActionContext.getContext().getSession().get("loginStatus");
		String realPath = ServletActionContext.getRequest().getRealPath("/WEB-INF/electvote");
		User user = electVoteService.getUser(loginStatus.getUserId());//通过userId找userName
		ElectVote vote = electVoteService.getElectVoteById(getElectVoteId());//通过voteId找voteName
		File folder = new File(realPath + "/" + user.getUsername() + "_" + vote.getName() + "_option" + getOrder());
		if (!folder .exists()  && !folder .isDirectory()) {
			folder .mkdir();
		}
		InputStream is = new FileInputStream(getFile());
		File destFile = new File(realPath + "/" + user.getUsername() + "_" + vote.getName() + "_option" + getOrder(), getFileFileName());
		OutputStream os = new FileOutputStream(destFile);
		byte[] buffer = new byte[400];
		int length = 0;
		while((length = is.read(buffer))>0) {
			os.write(buffer, 0, length);
		}
		is.close();
		os.close();
		return SUCCESS;
	}
	
	public String downloadFile() throws UnsupportedEncodingException, FileNotFoundException {
		String fileUrl = new String(getFileUrl().getBytes("iso8859-1"),"UTF-8");
		String realPath = ServletActionContext.getRequest().getRealPath(fileUrl);
		String fileName = new String(fileUrl.substring(fileUrl.lastIndexOf("/") + 1).getBytes(), "ISO8859-1");
		this.setFileName(fileName);
		inputStream = new FileInputStream(realPath);
		return SUCCESS;
	}
	
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}


	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public Integer getElectVoteId() {
		return electVoteId;
	}

	public void setElectVoteId(Integer electVoteId) {
		this.electVoteId = electVoteId;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public ElectVoteService getElectVoteService() {
		return electVoteService;
	}

	public void setElectVoteService(ElectVoteService electVoteService) {
		this.electVoteService = electVoteService;
	}

}
