package action;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ParameterAware;

import service.ElectVoteService;
import view.ElectVoteConfig;
import view.ElectVoteResultView;
import view.ElectVoteResultsView;
import view.LoginStatus;
import bean.ElectVote;
import bean.ElectVoteDecision;
import bean.ElectVoteOption;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ElectVoteAction extends ActionSupport implements ParameterAware{
	private static final long serialVersionUID = 1L;
	
	private ElectVoteService electVoteService;
	private ElectVoteConfig electVoteConfig;  //used for UI: electVoteEdit.jsp, userelectvote.jsp
	private Map<String, String[]> parameters;  //needed by ParameterAware
	private String voteJson;
	private Integer voteId;
	private String electVoteResultsJson;
	private Integer isPublish;
	private List<ElectVote> electVotes;

	private InputStream inputStream;
	
	public String retrieveEarliestActiveElectVote() throws Exception {
        ElectVote vote = electVoteService.getEarliestPublishedElectVote();
		if (vote == null) {
			return SUCCESS;
		}
		List<ElectVoteOption> options = electVoteService.listElectVoteOptionsByVoteId(vote.getId());
		List<ElectVoteDecision> decisions = electVoteService.listElectVoteDecisionByVoteId(vote.getId());		
		Map<String,String> decisionMap = new HashMap<String,String>();
		for(ElectVoteDecision decision : decisions){
			decisionMap.put(decision.geteName(), decision.geteName());
		}
		setElectVoteConfig(new ElectVoteConfig());
		getElectVoteConfig().setElectVote(vote);
		getElectVoteConfig().setOptions(options);
		getElectVoteConfig().setDecisionMap(decisionMap);
		return SUCCESS;
	}
	
	public String retrieveElectVoteConfig() throws Exception {
		String[] params = (String[]) parameters.get("id");
		String id = new String(params[0]);
//		String id = request.getParameter("id");
		ElectVote vote = electVoteService.getElectVoteById(Integer.parseInt(id));
		List<ElectVoteOption> options = electVoteService.listElectVoteOptionsByVoteId(Integer.parseInt(id));
		List<ElectVoteDecision> decisions = electVoteService.listElectVoteDecisionByVoteId(Integer.parseInt(id));
		Map<String,String> decisionMap = new HashMap<String,String>();
		for(ElectVoteDecision decision : decisions){
			decisionMap.put(decision.geteName(), decision.geteName());
		}
		setElectVoteConfig(new ElectVoteConfig());
		getElectVoteConfig().setElectVote(vote);
		getElectVoteConfig().setOptions(options);
		getElectVoteConfig().setDecisionMap(decisionMap);
		return SUCCESS;
	}
	
	
	public String listElectVotes() throws Exception {
		setElectVotes(electVoteService.getElectVotes());
		return SUCCESS;
	}
	
	public String viewElectVoteDetailResult() throws Exception {
		String[] electVoteResultIdStrs = (String[]) parameters.get("electVoteId");
		String electVoteResultIdStr = new String(electVoteResultIdStrs[0]);
		ElectVoteResultsView electVoteResultsView = electVoteService.constructElectVoteDetailResultBy(Integer.valueOf(electVoteResultIdStr));
		try {
			JSONObject electVoteResultsViewJsonObject = JSONObject.fromObject(electVoteResultsView);
			setInputStream(new ByteArrayInputStream(electVoteResultsViewJsonObject.toString().getBytes("UTF-8")));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String downloadElectVoteDetailFile() throws Exception {
		String[] electVoteResultIdStrs = (String[]) parameters.get("electVoteId");
		String electVoteResultIdStr = new String(electVoteResultIdStrs[0]);
        ElectVoteResultsView electVoteResultsView = electVoteService.constructElectVoteDetailResultBy(Integer.valueOf(electVoteResultIdStr));
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet();
        HSSFRow row0 = sheet.createRow(0);
        Map<String, Integer> optionAndColumnIndexMap = new HashMap<String, Integer>();
        for (int i = 0; i < electVoteResultsView.getAllOptions().size(); i++) {
			HSSFCell row0Cell = row0.createCell(i + 1);
			row0Cell.setCellValue(electVoteResultsView.getAllOptions().get(i));
			optionAndColumnIndexMap.put(electVoteResultsView.getAllOptions().get(i), i + 1);
		}
        
        int voterRowNum = 1;
        for (Entry<String, List<ElectVoteResultView>> voterAndResultsEntry : electVoteResultsView.getVoterAndResultsMap().entrySet()) {
			HSSFRow excelRow = sheet.createRow(voterRowNum);
			HSSFCell currentRowCell0 = excelRow.createCell(0);
			String voter = voterAndResultsEntry.getKey();
			currentRowCell0.setCellValue(voter);
			for (ElectVoteResultView result : voterAndResultsEntry.getValue()) {
				Integer columnNum = optionAndColumnIndexMap.get(result.getOptionName());
				HSSFCell currentRowCellByOption = excelRow.createCell(columnNum);
				currentRowCellByOption.setCellValue(result.getDecision());
			}
			voterRowNum++;
		}
        
		ActionContext ctx = ActionContext.getContext();
		HttpServletResponse response = (HttpServletResponse)ctx.get(ServletActionContext.HTTP_RESPONSE);
		String fileNameString = electVoteResultsView.getElectVoteName() + "_详细结果.xls";
		String name = new String(fileNameString.getBytes(), "iso8859-1");
		response.setHeader("Content-disposition","attachment; filename=" + name);
		response.addHeader("Cache-Control", "no-cache");
		response.setContentType("application/msexcel;charset=UTF-8");   
		OutputStream out = response.getOutputStream();
		workbook.write(out);
		out.flush();
		out.close();
		workbook.close();
		return null;
	} 
	
	/**
	 * 保存投票
	 * @return
	 * @throws Exception
	 */
	public String saveElectVote() throws Exception {
		boolean isSuccess = true;
		LoginStatus loginStatus = (LoginStatus)ActionContext.getContext().getSession().get("loginStatus");
		try{
			voteId = electVoteService.saveOrUpdateElectVote(electVoteService.getUser(loginStatus.getUserId()).getUsername(), voteJson);
		}catch(Exception e){
			isSuccess = false;
		}
		
		
		JSONObject object = new JSONObject();
		object.element("result", isSuccess);
		object.element("voteId", voteId);
		ajaxForAction(object.toString());
		return null;
	}
	
	/**
	 * 删除投票
	 * @return
	 * @throws Exception
	 */
	public String delElectVote() throws Exception{
		boolean isSuccess = true;
		try{
			electVoteService.deleteVoteElectVote(voteId);
		}catch(Exception e){
			isSuccess = false;
		}
		
		JSONObject object = new JSONObject();
		object.element("result", isSuccess);
		ajaxForAction(object.toString());
		return null;
	}
	
	/**
	 * 发布(撤销发布)
	 * @return
	 */
	public String publishVote() throws Exception{
		boolean isSuccess = true;
		try{
			ElectVote vote = electVoteService.getElectVoteById(voteId);
			vote.setIsPublish(isPublish);
			electVoteService.saveElectVote(vote);
		}catch(Exception e){
			isSuccess = false;
		}
		JSONObject object = new JSONObject();
		object.element("result", isSuccess);
		ajaxForAction(object.toString());
		return null;
	}
	
	/**
	 * 投票
	 * @return
	 * @throws Exception
	 */
	public String vote() throws Exception{
		//System.out.println(electVoteResultsJson);
		boolean isSuccess = true;
		try{
			electVoteService.saveElectVoteResults(electVoteResultsJson);
		}catch(Exception e){
			isSuccess = false;
		}
		
		JSONObject object = new JSONObject();
		object.element("result", isSuccess);
		ajaxForAction(object.toString());
		return null;
	}

	public Integer getVoteId() {
		return voteId;
	}
	
	public String publishElectVote() {
		try {
			electVoteService.publishElectVoteOrNot(voteId, true);
			inputStream = new ByteArrayInputStream("1".getBytes("UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
			try {
				inputStream = new ByteArrayInputStream("0".getBytes("UTF-8"));
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return "ajax-success";
	}
	
	public String dePublishElectVode() {
		try {
			electVoteService.publishElectVoteOrNot(voteId, false);
			inputStream = new ByteArrayInputStream("1".getBytes("UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
			try {
				inputStream = new ByteArrayInputStream("0".getBytes("UTF-8"));
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return "ajax-success";
	}


	public void setVoteId(Integer voteId) {
		this.voteId = voteId;
	}
	
	public void setElectVoteService(ElectVoteService electVoteService) {
		this.electVoteService = electVoteService;
	}

	public String getVoteJson() {
		return voteJson;
	}

	public void setVoteJson(String voteJson) {
		this.voteJson = voteJson;
	}

	
	public String getElectVoteResultsJson() {
		return electVoteResultsJson;
	}


	public void setElectVoteResultsJson(String electVoteResultsJson) {
		this.electVoteResultsJson = electVoteResultsJson;
	}

	public Integer getIsPublish() {
		return isPublish;
	}

	public void setIsPublish(Integer isPublish) {
		this.isPublish = isPublish;
	}


	private void ajaxForAction(String data) throws IOException{
		ActionContext ctx = ActionContext.getContext();
		HttpServletResponse response = (HttpServletResponse)ctx.get(ServletActionContext.HTTP_RESPONSE);
		
		response.addHeader("Cache-Control", "no-cache");
		response.setContentType("text/html;charset=UTF-8");   
		PrintWriter out = response.getWriter();  
		out.print(data);
		out.flush();
		out.close();
	}

	public List<ElectVote> getElectVotes() {
		return electVotes;
	}
	
	public void setElectVotes(List<ElectVote> electVotes) {
		this.electVotes = electVotes;
	}


	@Override
	public void setParameters(Map<String, String[]> arg0) {
		parameters = arg0;
	}


	public ElectVoteConfig getElectVoteConfig() {
		return electVoteConfig;
	}


	public void setElectVoteConfig(ElectVoteConfig electVoteConfig) {
		this.electVoteConfig = electVoteConfig;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
}
