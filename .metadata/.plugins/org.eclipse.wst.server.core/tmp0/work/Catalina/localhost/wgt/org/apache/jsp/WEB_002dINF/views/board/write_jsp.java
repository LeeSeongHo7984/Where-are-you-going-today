/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.5.81
 * Generated at: 2022-08-13 10:59:34 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views.board;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class write_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(2);
    _jspx_dependants.put("/WEB-INF/lib/jstl-1.2.jar", Long.valueOf(1635088205534L));
    _jspx_dependants.put("jar:file:/C:/Users/tjdgh/LSH/Where-are-you-going-today/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/wgt/WEB-INF/lib/jstl-1.2.jar!/META-INF/c.tld", Long.valueOf(1153352682000L));
  }

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    final java.lang.String _jspx_method = request.getMethod();
    if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSP들은 오직 GET, POST 또는 HEAD 메소드만을 허용합니다. Jasper는 OPTIONS 메소드 또한 허용합니다.");
      return;
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html lang=\"ko\" xmlns:th=\"http://www.thymeleaf.org\">\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"UTF-8\" />\r\n");
      out.write("<meta name=\"viewport\"\r\n");
      out.write("	content=\"width=device-width, initial-scale=1.0, user-scalable=no,  maximum-scale=1.0, minimum-scale=1.0\">\r\n");
      out.write("<title>게시판 - 글쓰기</title>\r\n");
      out.write("<meta name=\"viewport\"\r\n");
      out.write("	content=\"width=device-width, initial-scale=1.0, user-scalable=no,  maximum-scale=1.0, minimum-scale=1.0\">\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\"\r\n");
      out.write("	href=\"../resources/board/write.css\">\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\"\r\n");
      out.write("	href=\"../resources/board/home.css\">\r\n");
      out.write("<link\r\n");
      out.write("	href=\"https://fonts.googleapis.com/css2?family=Lobster&display=swap\"\r\n");
      out.write("	rel=\"stylesheet\">\r\n");
      out.write("<link rel=\"stylesheet\"\r\n");
      out.write("	href=\"https://use.fontawesome.com/releases/v5.15.4/css/all.css\">\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("	<header class=\"headerContainer\">\r\n");
      out.write("		<!--상단 탭 만들기 뼈대구조-->\r\n");
      out.write("		<div class=\"headerContents\">\r\n");
      out.write("			<!--상단 탭 내용물 감싼구조-->\r\n");
      out.write("			<div class=\"WestagramTag\">\r\n");
      out.write("				<!--상단 좌측 내용물-->\r\n");
      out.write("				<a href=\"home\"><i class=\"fab fa-instagram\"></i> | <span>Wgt</span>agram</a>\r\n");
      out.write("			</div>\r\n");
      out.write("			<div class=\"headerSearchBar\">\r\n");
      out.write("				<!--상단 중앙 내용물-->\r\n");
      out.write("				<i class=\"fas fa-search\"></i> <input type=\"text\" placeholder=\"검색\"\r\n");
      out.write("					style=\"border: none; outline: none;\">\r\n");
      out.write("			</div>\r\n");
      out.write("			<!-- 	<form action=\"home\" method=\"post\">\r\n");
      out.write("		<button type=\"submit\" value=\"글쓰기\">글쓰기</button>\r\n");
      out.write("	</form> -->\r\n");
      out.write("		</div>\r\n");
      out.write("	</header>\r\n");
      out.write("	<div class=\"mypage\">\r\n");
      out.write("		<a href=\"mypage\"><img id=\"user\"\r\n");
      out.write("			src=\"../resources/board/img/board.png\" width=\"30px\" height=\"30px\"\r\n");
      out.write("			style=\"cursor: pointer;\"></a>\r\n");
      out.write("	</div>\r\n");
      out.write("	<h3 style=\"text-align: center; margin-top: 35px;\">게시글 작성</h3>\r\n");
      out.write("	<div class=\"write_area\">\r\n");
      out.write("		<form action=\"write\" method=\"post\" enctype=\"multipart/form-data\"\r\n");
      out.write("			style=\"text-align: center; display: grid; justify-content: center; margin-top: 40px;\">\r\n");
      out.write("			<span>제목</span>\r\n");
      out.write("			<textarea class=\"title_area\" name=\"title\" required=\"required\"\r\n");
      out.write("				maxlength=\"50\" placeholder=\"제목을 입력하세요(최대 50자)\"></textarea>\r\n");
      out.write("			<span>내용</span>\r\n");
      out.write("			<textarea class=\"content_area\" name=\"content\" required=\"required\"\r\n");
      out.write("				maxlength=\"150\" placeholder=\"내용을 입력하세요(최대 150자)\"></textarea>\r\n");
      out.write("			사진 선택 : <span><input type=\"file\" accept=\".jpg\" name=\"file\"\r\n");
      out.write("				required=\"required\"><br></span> <input type=\"submit\"\r\n");
      out.write("				class=\"add\" value=\"등록\" required=\"required\"><br>\r\n");
      out.write("		</form>\r\n");
      out.write("		<button class=\"go_home\" onclick=\"location.href='home'\">목록으로</button>\r\n");
      out.write("	</div>\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
