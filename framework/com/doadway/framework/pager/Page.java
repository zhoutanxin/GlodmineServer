package com.doadway.framework.pager;

public class Page {
	private int showCount = 10; 	// 每页显示记录数
	private int totalPage; 			// 总页数
	private int totalResult; 		// 总记录数
	private int currentPage; 		// 当前页
	private int currentResult; 		// 当前记录起始索引
	private boolean entityOrField; 	// true:需要分页的地方，传入的参数就是Page实体；false:需要分页的地方，传入的参数所代表的实体拥有Page属性
	private String pageStr; 		// 最终页面显示的底部翻页导航，详细见：getPageStr();

	public int getTotalPage() {
		if (totalResult % showCount == 0)
			totalPage = totalResult / showCount;
		else
			totalPage = totalResult / showCount + 1;
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getTotalResult() {
		return totalResult;
	}

	public void setTotalResult(int totalResult) {
		this.totalResult = totalResult;
	}

	public int getCurrentPage() {
		if (currentPage <= 0)
			currentPage = 1;
		if (currentPage > getTotalPage())
			currentPage = getTotalPage();
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public String getPageStr() {
		StringBuffer sb = new StringBuffer();
		if (totalResult > 0) {
			sb.append("共 "+totalResult+"条&nbsp;	每页<input name=\"showCount\" value=\""+showCount+"\" style=\"width: 30px;\" onfocus=\"this.select();\" onblur=\"jQuery.cookie('_cookie_page_size',this.value,{expires:3650});\" onkeypress=\"if(event.keyCode==13){$(this).blur();return false;}\" type=\"text\">条&nbsp;");
			if (currentPage == 1) {
				sb.append(" <input value=\"首 页\" onclick=\"_gotoPage('1');\" type=\"button\">");
				sb.append(" <input value=\"上一页\" onclick=\"_gotoPage('1');\" type=\"button\">");
			} else {
				sb.append(" <input value=\"首 页\" onclick=\"_gotoPage('1');\" type=\"button\">");
				sb.append(" <input value=\"上一页\" onclick=\"_gotoPage('"+(currentPage-1)+"');\" type=\"button\">");
			}
//			int showTag = 3; // 分页标签显示数量
//			int startTag = 1;
//			if (currentPage > showTag) {
//				startTag = currentPage - 1;
//			}
//			int endTag = startTag + showTag - 1;
//			for (int i = startTag; i <= totalPage && i <= endTag; i++) {
//				if (currentPage == i)
//					sb.append("<li class=\"current\">" + i + "</li>\n");
//				else
//					sb.append(" <li><a href=\"#@\" onclick=\"nextPage(" + i
//							+ ")\">" + i + "</a></li>\n");
//			}
			
			if (currentPage == totalPage) {
				sb.append(" <input value=\"下一页\" onclick=\"_gotoPage('"+totalPage+"');\" type=\"button\">");
				sb.append(" <input value=\"尾页\" onclick=\"_gotoPage('"+totalPage+"');\" type=\"button\">");
			} else {
				sb.append(" <input value=\"下一页\" onclick=\"_gotoPage('"+(currentPage+1)+"');\" type=\"button\">");
				sb.append(" <input value=\"尾页\" onclick=\"_gotoPage('"+totalPage+"');\" type=\"button\">");
			}
			sb.append("当前 "+currentPage+"/"+totalPage+" 页 &nbsp;转到第<input id=\"_goPs\" value="+currentPage+" style=\"width: 50px;\" onfocus=\"this.select();\" onkeypress=\"if(event.keyCode==13){$('#_goPage').click();return false;}\" type=\"text\">页");
			sb.append(" <input id=\"_goPage\" value=\"转\" onclick=\"_gotoPage($('#_goPs').val());\"  type=\"button\">");
			sb.append("<script type=\"text/javascript\">\n");
			sb.append("function _gotoPage(pageNo) {\n");
			sb.append("try{\n");
			sb.append("var tableForm = getTableForm();\n");
			sb.append("$(\"input[name=pageNo]\").val(pageNo);\n");
			sb.append("tableForm.action=\"v_list.do\";\n");
			sb.append("tableForm.onsubmit=null;\n");
			sb.append("tableForm.submit();\n");
			sb.append("} catch(e) {\n");
			sb.append("alert('_gotoPage(pageNo)方法出错');\n");
			sb.append("}\n");
			sb.append("}\n");
			sb.append("</script>\n");
		}
		pageStr = sb.toString();
		return pageStr;
	}

	public void setPageStr(String pageStr) {
		this.pageStr = pageStr;
	}

	public int getShowCount() {
		return showCount;
	}

	public void setShowCount(int showCount) {
		this.showCount = showCount;
	}

	public int getCurrentResult() {
		currentResult = (getCurrentPage() - 1) * getShowCount();
		if (currentResult < 0)
			currentResult = 0;
		return currentResult;
	}

	public void setCurrentResult(int currentResult) {
		this.currentResult = currentResult;
	}

	public boolean isEntityOrField() {
		return entityOrField;
	}

	public void setEntityOrField(boolean entityOrField) {
		this.entityOrField = entityOrField;
	}
}