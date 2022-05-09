<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="container mb-4">
  <div class="row">
    <div class="col-md-3"></div>
    <div class="col-md-6 mt-3">
      <form:form action="${pageContext.request.contextPath}/savePost"
        modelAttribute="postForm" method="POST">
        <form:hidden path="postId" />
        <form:hidden path="postTitle" />
        <form:hidden path="postDescription" />
        <form:hidden path="postStatus" />
        <fieldset class="border shadow-sm p-3 bg-body rounded">
          <c:if test="${empty postForm.postId}">
            <legend class="float-none w-auto p-2">Post
              Registration Confirm</legend>
          </c:if>
          <c:if test="${not empty postForm.postId}">
            <legend class="float-none w-auto p-2">Post Edit
              Confirm</legend>
          </c:if>
          <h4 class="text-info">Post Title</h4>
          <p class="text-muted">${postForm.postTitle }</p>
          <br>
          <h4 class="text-info">Post Description</h4>
          <p class="text-muted">${postForm.postDescription }</p>
          <br>
          <c:if test="${not empty postForm.postId}">
            <h4 class="text-info">Post Status</h4>
            <p class="text-muted">
              <c:if test="${postForm.postStatus == 'true'}">Active</c:if>
              <c:if test="${postForm.postStatus == 'false'}">Inactive</c:if>
            </p>
          </c:if>
          <br>
          <div class="text-center">
            <input type="submit" name="cancel" class="btn btn-dark mx-3"
              value="Cancel"> <input type="submit"
              class="btn btn-primary mx-3" value="Post">
          </div>
        </fieldset>
      </form:form>
    </div>
    <div class="col-md-3"></div>
  </div>
</div>