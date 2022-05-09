<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
  prefix="sec"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title><tiles:getAsString name="title" /></title>

<link
  href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
  rel="stylesheet">
<link rel="stylesheet"
  href="<%=request.getContextPath()%>/resources/plugins/DataTables/datatables.min.css" />
<link rel="stylesheet"
  href="<%=request.getContextPath()%>/resources/plugins/fontawesome/css/all.min.css" />
<link rel="stylesheet"
  href="<%=request.getContextPath()%>/resources/css/reset.css" />
<link rel="stylesheet"
  href="<%=request.getContextPath()%>/resources/css/common.css" />
  
</head>
<body>
  <tiles:insertAttribute name="header" />
  <div class="content">
    <tiles:insertAttribute name="body" />
  </div>
  <tiles:insertAttribute name="footer" />
  
  <script
    src="<%=request.getContextPath()%>/resources/plugins/js/jquery-3.6.0.js"></script>
  <script
    src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
  <script type="text/javascript"
    src="<%=request.getContextPath()%>/resources/plugins/DataTables/datatables.min.js"></script>
  <script
    src="<%=request.getContextPath()%>/resources/plugins/fontawesome/js/all.min.js"></script>
  <script src="<%=request.getContextPath()%>/resources/js/common.js"></script>

</body>
</html>