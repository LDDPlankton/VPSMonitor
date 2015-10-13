package com.vpsmonitor.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.Size;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vpsmonitor.dao.ParamBuilder;
import com.vpsmonitor.library.JSONResponse;
import com.vpsmonitor.library.Paginator;
import com.vpsmonitor.manager.CoreManager;
import com.vpsmonitor.manager.IPManager;
import com.vpsmonitor.manager.MonitorManager;
import com.vpsmonitor.manager.ServerManager;
import com.vpsmonitor.manager.SettingManager;
import com.vpsmonitor.model.SettingModel;
import com.vpsmonitor.model.VPSIPModel;
import com.vpsmonitor.model.VPSListModel;
import com.vpsmonitor.model.VPSMonitorModel;
import com.vpsmonitor.viewmodel.ViewModelAddMonitor;
import com.vpsmonitor.viewmodel.ViewModelAddServer;
import com.vpsmonitor.viewmodel.ViewModelAdminUpdateAccount;
import com.vpsmonitor.viewmodel.ViewModelEditAccount;
import com.vpsmonitor.viewmodel.ViewModelEditIP;
import com.vpsmonitor.viewmodel.ViewModelEditMonitor;
import com.vpsmonitor.viewmodel.ViewModelEditServer;
import com.vpsmonitor.viewmodel.ViewModelEditSetting;
import com.vpsmonitor.viewmodel.ViewModelIPList;
import com.vpsmonitor.viewmodel.ViewModelMonitorList;
import com.vpsmonitor.viewmodel.ViewModelServerList;
import com.vpsmonitor.viewmodel.ViewModelSettingList;
import com.vpsmonitor.viewmodel.ViewModelAddIP;

@Controller
@RequestMapping(value = "/admin")
public class AdminController
{
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private CoreManager coreManager;
	
	@Autowired
	private ServerManager serverManager;
	
	@Autowired
	private IPManager ipManager;
	
	@Autowired
	private MonitorManager monitorManager;
	
	@Autowired
	private SettingManager settingManager;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getIndex(Locale locale, Model model)
	{
		logger.info("Welcome admin/index! The client locale is {}.", locale);
		
		//IF UNABLE TO CONNECT TO DB
		if(!this.coreManager.isValidDatabaseConnection())
		{
			logger.info("DBCON FAILED {}.", locale);
			model.addAttribute("message", "Unable to connect to database!");
			return "error_template";
		}		
		
		//BUILD TEMPLATE VARIABLES
		Map<String,String> template_map = new HashMap<String,String>();
		template_map.put("page_title", "Servers");
		template_map.put("admin_logged_in", "true");
		model.addAttribute("total_vps_count", "1");
		model.addAttribute("total_ip_count", "2");
		model.addAllAttributes(template_map);
		
		return "admin/index";
	}	
		
	@RequestMapping(value = "/servers", method = RequestMethod.GET)
	public String getServers(Locale locale, Model model) {
		logger.info("Welcome admin/servers! The client locale is {}.", locale);

		//GET ALL VPS LIST
		List<ViewModelServerList> viewModelList = new ArrayList<ViewModelServerList>();
		List<VPSListModel> vpslist_all = serverManager.getBy( new ParamBuilder() );
		for(VPSListModel m : vpslist_all)
		{
			ViewModelServerList viewModel = new ViewModelServerList();
			viewModel.setID(m.getID());
			viewModel.setName(m.getName());
			viewModel.setPrimaryIP(m.getPrimaryIP());
			
			if(m.getStatus() == 0)
				viewModel.setStatus("Disabled");
			else
				viewModel.setStatus("Enabled");
			
			viewModelList.add(viewModel);
		}

		Paginator<ViewModelServerList> paginator = new Paginator<ViewModelServerList>(viewModelList);
		paginator.setCurrentPageNumber(1);
		paginator.setItemCountPerPage(1000);
		List<ViewModelServerList> serverlist = paginator.getCurrentItems();

		//BUILD TEMPLATE VARIABLES
		Map<String,String> template_map = new HashMap<String,String>();
		template_map.put("page_title", "Server Management");
		template_map.put("admin_logged_in", "true");
		model.addAttribute("serverCount", serverlist.size());
		model.addAttribute("serverList", serverlist);
		model.addAllAttributes(template_map);

		return "servers/servers";
	}
	
	@RequestMapping(value = "/add-server", method = RequestMethod.GET)
	public String getAddServer(Locale locale, Model model) {
		logger.info("Welcome admin/add-server! The client locale is {}.", locale);

		Map<Integer,String> VPSStatusMap = new LinkedHashMap<Integer,String>();
		VPSStatusMap.put(0, "Disabled");
		VPSStatusMap.put(1, "Enabled");
		
		//BUILD TEMPLATE VARIABLES
		Map<String,String> template_map = new HashMap<String,String>();
		template_map.put("page_title", "Add Server");
		template_map.put("admin_logged_in", "true");
		model.addAttribute("VPSStatusMap", VPSStatusMap);
		model.addAttribute("ViewModelAddServer", new ViewModelAddServer() );
		model.addAllAttributes(template_map);
		
		return "servers/server_add";
	}
	
	@RequestMapping("/edit-server/{id}")
	public String getEditServer(Locale locale, Model model, @PathVariable("id") int serverID)
	{
		logger.info("Welcome admin/edit-server! The client locale is {}.", locale);
		logger.info("serverID=" + serverID);

		//BUILD TEMPLATE VARIABLES
		Map<String,String> template_map = new HashMap<String,String>();
		template_map.put("page_title", "Edit Server");
		template_map.put("admin_logged_in", "true");
		
		//FIND SERVER MODEL
		VPSListModel serverModel = this.serverManager.findByVPSID(serverID);
		if(serverModel == null)
		{
			model.addAllAttributes(template_map);
			template_map.put("message", "Unable to find ServerID: " + serverID);
			return "error_template";
		}
		else
		{
			ViewModelEditServer viewModel = new ViewModelEditServer();
			viewModel.setID( serverModel.getID() );
			viewModel.setName( serverModel.getName() );
			viewModel.setPrimaryIP( serverModel.getPrimaryIP() );
			viewModel.setStatus( serverModel.getStatus() );
			viewModel.setSshNormalUser( serverModel.getSshNormalUser() );
			viewModel.setSshNormalPass( serverModel.getSshNormalPass() );
			viewModel.setSshRootPassword( serverModel.getSshRootPassword() );
			viewModel.setSshRootKey( serverModel.getSshRootKey() );
			viewModel.setSshIsNormalUserRequired( serverModel.getSshIsNormalUserRequired() );
			viewModel.setSshIsRootKeyRequired( serverModel.getSshIsRootKeyRequired() );
			viewModel.setRestAuthorizationHash( serverModel.getRestAuthorizationHash() );
			
			model.addAttribute("ViewModelEditServer", viewModel );
			model.addAllAttributes(template_map);
			return "servers/server_edit";
		}
	}
	
	@RequestMapping(value = "/ips", method = RequestMethod.GET)
	public String getIPs(Locale locale, Model model) {
		logger.info("Welcome admin/ips! The client locale is {}.", locale);

		//GET ALL IP LIST
		List<ViewModelIPList> viewModelList = new ArrayList<ViewModelIPList>();
		List<VPSIPModel> iplist_all = this.ipManager.getBy( new ParamBuilder() );
		for(VPSIPModel m : iplist_all)
		{
			ViewModelIPList viewModel = new ViewModelIPList();
			viewModel.setID(m.getID());
			viewModel.setIPAddress(m.getIPAddress());
			
			VPSListModel tmpModel = this.serverManager.findByVPSID(m.getVpsID());
			if(tmpModel != null)
				viewModel.setVpsName(tmpModel.getName());
			else
				viewModel.setVpsName("None");
			
			viewModelList.add(viewModel);
		}

		Paginator<ViewModelIPList> paginator = new Paginator<ViewModelIPList>(viewModelList);
		paginator.setCurrentPageNumber(1);
		paginator.setItemCountPerPage(1000);
		List<ViewModelIPList> iplist = paginator.getCurrentItems();

		//BUILD TEMPLATE VARIABLES
		Map<String,String> template_map = new HashMap<String,String>();
		template_map.put("page_title", "IP Management");
		template_map.put("admin_logged_in", "true");
		model.addAttribute("ipCount", iplist.size());
		model.addAttribute("ipList", iplist);
		model.addAllAttributes(template_map);
		
		return "ips/ips";
	}

	@RequestMapping(value = "/add-ip", method = RequestMethod.GET)
	public String getAddIP(Locale locale, Model model) {
		logger.info("Welcome admin/add_ips! The client locale is {}.", locale);

		//BUILD MAP OF ALL SERVERS
		Map<Integer,String> VPSListMap = new LinkedHashMap<Integer,String>();
		List<VPSListModel> vpslist_all = serverManager.getBy( new ParamBuilder() );
		VPSListMap.put(0, "None");
		for(VPSListModel m : vpslist_all)
		{
			VPSListMap.put(m.getID(), m.getName());
		}
		
		//BUILD TEMPLATE VARIABLES
		Map<String,String> template_map = new HashMap<String,String>();
		template_map.put("page_title", "Add IP");
		template_map.put("admin_logged_in", "true");
		model.addAttribute("VPSListMap", VPSListMap);
		model.addAttribute("ViewModelAddIP", new ViewModelAddIP() );
		model.addAllAttributes(template_map);
		
		return "ips/ip_add";
	}
	
	@RequestMapping("/edit-ip/{id}")
	public String getEditIP(Locale locale, Model model, @PathVariable("id") int ipID)
	{
		logger.info("Welcome admin/edit-ip! The client locale is {}.", locale);
		logger.info("ipID=" + ipID);

		//BUILD TEMPLATE VARIABLES
		Map<String,String> template_map = new HashMap<String,String>();
		template_map.put("page_title", "Edit IP");
		template_map.put("admin_logged_in", "true");
		
		//FIND IP MODEL
		VPSIPModel ipModel = this.ipManager.findByID(ipID);
		if(ipModel == null)
		{
			model.addAllAttributes(template_map);
			template_map.put("message", "Unable to find IP ID: " + ipID);
			return "error_template";
		}
		else
		{
			//BUILD MAP OF ALL SERVERS
			ViewModelServerList defaultModel = new ViewModelServerList();
			defaultModel.setID(0);
			defaultModel.setName("None");
			defaultModel.setStatus("Not Used");
			defaultModel.setPrimaryIP("0");
			
			List<ViewModelServerList> VPSListMap = new ArrayList<ViewModelServerList>();
			List<VPSListModel> vpslist_all = serverManager.getBy( new ParamBuilder() );
			VPSListMap.add(defaultModel);		
			for(VPSListModel m : vpslist_all)
			{
				ViewModelServerList tmpModel = new ViewModelServerList();
				tmpModel.setID(m.getID());
				tmpModel.setName(m.getName());
				tmpModel.setPrimaryIP(m.getPrimaryIP());
				if(m.getStatus() == 0)
					tmpModel.setStatus("Disabled");
				else
					tmpModel.setStatus("Disabled");
				
				VPSListMap.add(tmpModel);
			}
			
			ViewModelEditIP viewModel = new ViewModelEditIP();
			viewModel.setID(ipModel.getID());
			viewModel.setVpsID(ipModel.getVpsID());
			viewModel.setIPAddress(ipModel.getIPAddress());
			
			model.addAttribute("VPSListMap", VPSListMap);
			model.addAttribute("ViewModelEditIP", viewModel );
			model.addAllAttributes(template_map);
			return "ips/ip_edit";
		}
	}
	
	@RequestMapping(value = "/monitors", method = RequestMethod.GET)
	public String getMonitors(Locale locale, Model model) {
		logger.info("Welcome admin/monitors! The client locale is {}.", locale);
		
		//GET ALL VPS LIST
		List<ViewModelMonitorList> viewModelList = new ArrayList<ViewModelMonitorList>();
		List<VPSMonitorModel> vpslist_all = monitorManager.getBy( new ParamBuilder() );
		for(VPSMonitorModel m : vpslist_all)
		{
			VPSListModel tmpModel = this.serverManager.findByVPSID(m.getVpsID());
			if(tmpModel == null)
				continue;			
			
			ViewModelMonitorList viewModel = new ViewModelMonitorList();
			viewModel.setID(m.getID());
			viewModel.setVpsName(tmpModel.getName());
			viewModel.setMonitorIPOrUrl(m.getMonitorIPOrUrl());
			viewModel.setMonitorPort(m.getMonitorPort());;
			viewModel.setMonitorTimeout(m.getMonitorTimeout());
			viewModel.setIsRequireValidContent(m.getIsRequireValidContent());
			viewModel.setValidContent(m.getValidContent());
			viewModelList.add(viewModel);
		}

		Paginator<ViewModelMonitorList> paginator = new Paginator<ViewModelMonitorList>(viewModelList);
		paginator.setCurrentPageNumber(1);
		paginator.setItemCountPerPage(1000);
		List<ViewModelMonitorList> monitorlist = paginator.getCurrentItems();
		
		//BUILD TEMPLATE VARIABLES
		Map<String,String> template_map = new HashMap<String,String>();
		template_map.put("page_title", "Monitor Management");
		template_map.put("admin_logged_in", "true");
		model.addAttribute("monitorCount", monitorlist.size());
		model.addAttribute("monitorList", monitorlist);
		model.addAllAttributes(template_map);
		
		
		return "monitors/monitors";
	}
	
	@RequestMapping(value = "/add-monitor", method = RequestMethod.GET)
	public String getAddMonitor(Locale locale, Model model) {
		logger.info("Welcome admin/add-monitor! The client locale is {}.", locale);
		
		//BUILD MAP OF ALL SERVERS
		Map<Integer,String> VPSListMap = new LinkedHashMap<Integer,String>();
		List<VPSListModel> vpslist_all = serverManager.getBy( new ParamBuilder() );
		VPSListMap.put(0, "None");
		for(VPSListModel m : vpslist_all)
		{
			VPSListMap.put(m.getID(), m.getName());
		}
		
		ViewModelAddMonitor viewModel = new ViewModelAddMonitor();
		viewModel.setIsRequireValidContent(0);
		
		//BUILD TEMPLATE VARIABLES
		Map<String,String> template_map = new HashMap<String,String>();
		template_map.put("page_title", "Add Monitor");
		template_map.put("admin_logged_in", "true");
		model.addAttribute("ViewModelAddMonitor", viewModel);
		model.addAttribute("VPSListMap", VPSListMap);
		model.addAllAttributes(template_map);
		return "monitors/monitor_add";
	}
	
	@RequestMapping("/edit-monitor/{id}")
	public String getEditMonitor(Locale locale, Model model, @PathVariable("id") int monitorID)
	{
		logger.info("Welcome admin/edit-monitor! The client locale is {}.", locale);
		logger.info("monitorID=" + monitorID);
		
		//BUILD TEMPLATE VARIABLES
		Map<String,String> template_map = new HashMap<String,String>();
		template_map.put("page_title", "Edit Monitor");
		template_map.put("admin_logged_in", "true");
		
		//FIND SERVER MODEL
		VPSMonitorModel monitorModel = this.monitorManager.findByVPSID(monitorID);
		if(monitorModel == null)
		{
			model.addAllAttributes(template_map);
			template_map.put("message", "Unable to find MonitorID: " + monitorID);
			return "error_template";
		}
		else
		{
			ViewModelEditMonitor viewModel = new ViewModelEditMonitor();
			viewModel.setID(monitorModel.getID());
			viewModel.setVpsID(monitorModel.getVpsID());
			viewModel.setMonitorIPOrUrl(monitorModel.getMonitorIPOrUrl());
			viewModel.setMonitorPort(monitorModel.getMonitorPort());
			viewModel.setMonitorTimeout(monitorModel.getMonitorTimeout());
			viewModel.setIsRequireValidContent(monitorModel.getIsRequireValidContent());
			viewModel.setValidContent(monitorModel.getValidContent());
			
			model.addAttribute("ViewModelEditMonitor", viewModel );
			model.addAllAttributes(template_map);
			return "monitors/monitor_edit";
		}
	}
	
	@RequestMapping(value = "/settings", method = RequestMethod.GET)
	public String getSettings(Locale locale, Model model) {
		logger.info("Welcome admin/settings! The client locale is {}.", locale);
		
		ViewModelSettingList viewModel = new ViewModelSettingList();
		viewModel.setVersion("1.0.0");
		
		Map<String,String> template_map = new HashMap<String,String>();
		template_map.put("page_title", "Settings");
		template_map.put("admin_logged_in", "true");
		model.addAttribute("ViewModelSettingList", viewModel );
		model.addAllAttributes(template_map);
		
		return "admin/settings";
	}
	
	@RequestMapping(value = "/account", method = RequestMethod.GET)
	public String getAccount(Locale locale, Model model) {
		logger.info("Welcome admin/account! The client locale is {}.", locale);
		
		Map<String,String> template_map = new HashMap<String,String>();
		template_map.put("page_title", "Account Settings");
		template_map.put("admin_logged_in", "true");
		model.addAttribute("ViewModelAdminUpdateAccount", new ViewModelAdminUpdateAccount() );
		model.addAllAttributes(template_map);
		
		return "admin/account";
	}
	
	
	
	/*
	 * 
	 * 
	 * POST SECTION
	 * 
	 * 
	 */
	@RequestMapping(value = "/add-server", method = RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody JSONResponse postAddServer(Model model, @ModelAttribute("ViewModelAddServer") @Valid ViewModelAddServer view, BindingResult result, HttpSession session)
	{
		logger.info("Welcome POST admin/add-server!");

		//SET RESPONSE INFORMATION
		String message = "";
		JSONResponse resp = new JSONResponse();
		resp.setStatus(false);
		resp.setRedirect(false);	
		
		if (result.hasErrors())
		{
			logger.info("ADD SERVER => ERRORS");
		    for (FieldError error : result.getFieldErrors())
		    {
		    	String error_msg = this.messageSource.getMessage(error, null);
		    	message += error_msg + "<br>";		    	
		    }
        }
		else
		{
			logger.info("ADD SERVER => OK A");
			
			//ADD SERVER
			try
			{
				this.serverManager.addServer(view.getName(), view.getPrimaryIP(), view.getSshNormalUser(), view.getSshNormalPass(),
					view.getSshRootPassword(), view.getSshRootKey(), view.getSshIsNormalUserRequired(), view.getSshIsRootKeyRequired(), view.getRestAuthorizationHash());
				
				//NO ERRORS ALL OK
				resp.setStatus(true);
				resp.setRedirect(true);
			}
			catch(Exception e)
			{
				message = e.getMessage();
			}
		
		}
		
		//RETURN RESPONSE
		resp.setMessage(message);
		return resp;
	}
	
	@RequestMapping(value = "/edit-server", method = RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody JSONResponse postEditServer(Model model, @ModelAttribute("ViewModelEditServer") @Valid ViewModelEditServer view, BindingResult result, HttpSession session)
	{
		logger.info("Welcome POST admin/edit-server!");

		//SET RESPONSE INFORMATION
		String message = "";
		JSONResponse resp = new JSONResponse();
		resp.setStatus(false);
		resp.setRedirect(false);
		
		if (result.hasErrors())
		{
			logger.info("ADD SERVER => ERRORS");
		    for (FieldError error : result.getFieldErrors())
		    {
		    	String error_msg = this.messageSource.getMessage(error, null);
		    	message += error_msg + "<br>";		    	
		    }
        }
		else
		{
			VPSListModel tmpModel = this.serverManager.findByVPSID(view.getID());
			if(tmpModel == null)
			{
				message = "Unable to locate Server ID " + view.getID();
			}
			else
			{
				//NO ERRORS ALL OK
				resp.setStatus(true);
				resp.setRedirect(true);
			
				//UPDATE MODEL
				tmpModel.setName(view.getName());
				tmpModel.setPrimaryIP(view.getPrimaryIP());
				tmpModel.setStatus(view.getStatus());
				tmpModel.setSshNormalUser(view.getSshNormalUser());
				tmpModel.setSshNormalPass(view.getSshNormalPass());
				tmpModel.setSshRootPassword(view.getSshRootPassword());
				tmpModel.setSshRootKey(view.getSshRootKey());
				tmpModel.setSshIsNormalUserRequired(view.getSshIsNormalUserRequired());
				tmpModel.setSshIsRootKeyRequired(view.getSshIsRootKeyRequired());
				tmpModel.setRestAuthorizationHash(view.getRestAuthorizationHash());

				//SAVE MODEL
				this.serverManager.update(tmpModel);
			}
		}
		
		//RETURN RESPONSE
		resp.setMessage(message);
		return resp;
	}
	
	@RequestMapping(value = "/add-ip", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody JSONResponse postAddIP(Model model, @ModelAttribute("ViewModelAddIP") @Valid ViewModelAddIP view, BindingResult result, HttpSession session)
	{
		logger.info("Welcome POST admin/add-ip!");

		/*
		Map<String,String> postmap = new HashMap<String,String>();
		postmap.put("vpsID", "VPS Name");
		postmap.put("IPAddress", "IP Address");
		*/
		
		//SET RESPONSE INFORMATION
		String message = "";
		JSONResponse resp = new JSONResponse();
		resp.setStatus(false);
		resp.setRedirect(false);	
		
		if (result.hasErrors())
		{
			logger.info("ADD IP => ERRORS");
		    for (FieldError error : result.getFieldErrors())
		    {
		    	logger.info("Error Field: " + error.getField() );
		    	String error_msg = this.messageSource.getMessage(error, null);
		    	message += error_msg + "<br>";		    	
		    }
        }
		else
		{
			try
			{
				this.ipManager.addIP(view.getVpsID(), view.getIPAddress());
				
				//NO ERRORS ALL OK
				resp.setStatus(true);
				resp.setRedirect(true);
			}
			catch(Exception e)
			{
				message = e.getMessage();
			}
		}
		
		//RETURN RESPONSE
		resp.setMessage(message);
		return resp;
	}
	
	@RequestMapping(value = "/edit-ip", method = RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody JSONResponse postEditIP(Model model, @ModelAttribute("ViewModelEditIP") @Valid ViewModelEditIP view, BindingResult result, HttpSession session)
	{
		logger.info("Welcome POST admin/edit-ip!");

		Map<String,String> postmap = new HashMap<String,String>();
		postmap.put("vpsipID", "IP ID");
		postmap.put("vpsID", "VPS Name");
		postmap.put("ipAddress", "IP Address");
		
		//SET RESPONSE INFORMATION
		String message = "";
		JSONResponse resp = new JSONResponse();
		resp.setStatus(false);
		resp.setRedirect(false);
		
		if (result.hasErrors())
		{
			logger.info("ADD IP => ERRORS");
		    for (FieldError error : result.getFieldErrors())
		    {
		    	String error_msg = this.messageSource.getMessage(error, null);
		    	message += postmap.get(error.getField()) + " " + error_msg + "<br>";		    	
		    }
        }
		else
		{
			VPSIPModel tmpModel = this.ipManager.findByID(view.getID());
			if(tmpModel == null)
			{
				message = "Unable to find IP ID " + view.getID();
			}
			else
			{
				//NO ERRORS ALL OK
				resp.setStatus(true);
				
				//UPDATE MODEL
				tmpModel.setVpsID(view.getVpsID());
				tmpModel.setIPAddress(view.getIPAddress());
				
				//SAVE MODEL
				this.ipManager.update(tmpModel);
			}
		}
		
		//RETURN RESPONSE
		resp.setMessage(message);
		return resp;		
	}
	
	@RequestMapping(value = "/add-monitor", method = RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody JSONResponse postAddMonitor(Model model, @ModelAttribute("ViewModelAddMonitor") @Valid ViewModelAddMonitor view, BindingResult result, HttpSession session)
	{
		logger.info("Welcome POST admin/add-monitor!");

		//SET RESPONSE INFORMATION
		String message = "";
		JSONResponse resp = new JSONResponse();
		resp.setStatus(false);
		resp.setRedirect(false);
		
		if (result.hasErrors())
		{
			logger.info("ADD MONITOR => ERRORS");
		    for (FieldError error : result.getFieldErrors())
		    {
		    	String error_msg = this.messageSource.getMessage(error, null);
		    	message += error_msg + "<br>";		    	
		    }
        }
		else
		{
			//NO ERRORS ALL OK
			resp.setStatus(true);
			
			this.monitorManager.addMonitor(view.getVpsID(), view.getMonitorIPOrUrl(), view.getMonitorTimeout(), 
					view.getMonitorTimeout(), view.getIsRequireValidContent(), view.getValidContent());
		}
		
		//RETURN RESPONSE
		resp.setMessage(message);
		return resp;
	}
	
	@RequestMapping(value = "/edit-monitor", method = RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody JSONResponse postEdotMonitor(Model model, @ModelAttribute("ViewModelEditMonitor") @Valid ViewModelEditMonitor view, BindingResult result, HttpSession session)
	{
		logger.info("Welcome POST admin/edit-monitor!");

		Map<String,String> postmap = new HashMap<String,String>();
		postmap.put("monitorID", "Monitor ID");
		postmap.put("vpsID", "VPS ID");
		postmap.put("monitorIPOrUrl", "Monitor URL");
		postmap.put("monitorPort", "Monitor Port");
		postmap.put("monitorTimeout", "Monitor Timeout");
		postmap.put("isRequireValidContent", "Require Valid Content");
		postmap.put("validContent", "Valid Content");

		//SET RESPONSE INFORMATION
		String message = "";
		JSONResponse resp = new JSONResponse();
		resp.setStatus(false);
		resp.setRedirect(false);
		
		if (result.hasErrors())
		{
			logger.info("ADD MONITOR => ERRORS");
		    for (FieldError error : result.getFieldErrors())
		    {
		    	String error_msg = this.messageSource.getMessage(error, null);
		    	message += postmap.get(error.getField()) + " " + error_msg + "<br>";		    	
		    }
        }
		else
		{
			VPSMonitorModel tmpModel = this.monitorManager.findByVPSID(view.getID());
			if(tmpModel == null)
			{
				message = "Unable to find Monitor ID " + view.getID();
			}
			else
			{
				//NO ERRORS ALL OK
				resp.setStatus(true);
			
				//UPDATE MODEL
				tmpModel.setVpsID(view.getVpsID());
				tmpModel.setMonitorIPOrUrl(view.getMonitorIPOrUrl());
				tmpModel.setMonitorPort(view.getMonitorPort());
				tmpModel.setMonitorTimeout(view.getMonitorTimeout());
				tmpModel.setIsRequireValidContent(view.getIsRequireValidContent());
				tmpModel.setValidContent(view.getValidContent());
				
				//SAVE MODEL
				this.monitorManager.update(tmpModel);
			}
		}
		
		//RETURN RESPONSE
		resp.setMessage(message);
		return resp;
	}
	
	@RequestMapping(value = "/settings", method = RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody JSONResponse postUpdateSettings(Model model, @ModelAttribute("ViewModelEditSetting") @Valid ViewModelEditSetting view, BindingResult result, HttpSession session)
	{
		Map<String,String> postmap = new HashMap<String,String>();
		postmap.put("version", "Build Version");
		
		//SET RESPONSE INFORMATION
		String message = "";
		JSONResponse resp = new JSONResponse();
		resp.setStatus(false);
		resp.setRedirect(false);	
		
		if (result.hasErrors())
		{
			logger.info("SETTINGS => ERRORS");
		    for (FieldError error : result.getFieldErrors())
		    {
		    	String error_msg = this.messageSource.getMessage(error, null);
		    	message += postmap.get(error.getField()) + " " + error_msg + "<br>";		    	
		    }
        }
		else
		{
			//NO ERRORS ALL OK
			resp.setStatus(true);
			
			//UPDATE THINGS HERE!
			//SettingModel sm1 = this.settingManager.findByKey(view.getVersion());

		}
		
		//RETURN RESPONSE
		resp.setMessage(message);
		return resp;
	}
	
	@RequestMapping(value = "/account", method = RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody JSONResponse postUpdateAccount(Model model, @ModelAttribute("ViewModelEditAccount") @Valid ViewModelEditAccount admin, BindingResult result, HttpSession session)
	{
		Map<String,String> postmap = new HashMap<String,String>();
		postmap.put("adminID", "Admin ID");
		postmap.put("groupID", "Group ID");
		postmap.put("email", "Email");
		postmap.put("passWord", "Password");
		postmap.put("phoneNumber", "Phone Number");
		
		//SET RESPONSE INFORMATION
		String message = "";
		JSONResponse resp = new JSONResponse();
		resp.setStatus(false);
		resp.setRedirect(false);	
		
		if (result.hasErrors())
		{
			logger.info("UPDATE ACCOUNT => ERRORS");
		    for (FieldError error : result.getFieldErrors())
		    {
		    	String error_msg = this.messageSource.getMessage(error, null);
		    	message += postmap.get(error.getField()) + " " + error_msg + "<br>";		    	
		    }
        }
		else
		{
			//NO ERRORS ALL OK
			resp.setStatus(true);
			


		}
		
		//RETURN RESPONSE
		resp.setMessage(message);
		return resp;
	}
	
}
