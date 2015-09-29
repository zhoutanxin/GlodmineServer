package com.doadway.framework.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.context.Context;
import org.apache.velocity.tools.Scope;
import org.apache.velocity.tools.ToolManager;
import org.apache.velocity.tools.view.ViewToolContext;
import org.springframework.web.servlet.view.velocity.VelocityToolboxView;
/**
 * spring3.0的org.springframework.web.servlet.view.velocity.VelocityToolboxView不支持velocity tools2.0版本，
 * 需重写createVelocityContext方法才能支持tools2.0
 * */
public class VelocityToolboxViewExt extends VelocityToolboxView  {
	@Override
	 protected Context createVelocityContext(Map<String, Object> model, HttpServletRequest request,
	            HttpServletResponse response) throws Exception {
	        ViewToolContext ctx;

	        ctx = new ViewToolContext(getVelocityEngine(), request, response, getServletContext());

	        ctx.putAll(model);

	        if (this.getToolboxConfigLocation() != null) {
	            ToolManager tm = new ToolManager();
	            tm.setVelocityEngine(getVelocityEngine());
	            tm.configure(getServletContext().getRealPath(getToolboxConfigLocation()));
	            if (tm.getToolboxFactory().hasTools(Scope.REQUEST)) {
	                ctx.addToolbox(tm.getToolboxFactory().createToolbox(Scope.REQUEST));
	            }
	            if (tm.getToolboxFactory().hasTools(Scope.APPLICATION)) {
	                ctx.addToolbox(tm.getToolboxFactory().createToolbox(Scope.APPLICATION));
	            }
	            if (tm.getToolboxFactory().hasTools(Scope.SESSION)) {
	                ctx.addToolbox(tm.getToolboxFactory().createToolbox(Scope.SESSION));
	            }
	        }
	        return ctx;
	    }
	public VelocityToolboxViewExt(){
		super();
	}	
}
