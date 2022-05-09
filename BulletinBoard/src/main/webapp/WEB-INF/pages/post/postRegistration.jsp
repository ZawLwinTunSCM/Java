<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:if test="${loggedInId == createdUserId}">
  <div class="container mb-4">
    <div class="row">
      <div class="col-md-3"></div>
      <div class="col-md-6 mt-3">
        <form:form
          action="${pageContext.request.contextPath}/createPostConfirm"
          modelAttribute="postForm" method="POST">
          <fieldset class="border shadow-sm p-3 bg-body rounded">
            <c:if test="${empty postForm.postId}">
              <legend class="float-none w-auto p-2">Post
                Registration</legend>
              <c:set var="button" scope="session" value="Clear" />
            </c:if>
            <c:if test="${not empty postForm.postId}">
              <legend class="float-none w-auto p-2">Post Edit</legend>
              <c:set var="button" scope="session" value="Reset" />
              <form:input type="hidden" path="postId" />
            </c:if>
            <div class="mb-3">
              <label class="form-label">Post Title</label>
              <form:input type="text" path="postTitle"
                class="form-control inp" />
              <i class="text-danger"><form:errors path="postTitle" /></i>
            </div>
            <div class="mb-3">
              <label class="form-label">Post Description</label>
              <form:textarea path="postDescription"
                class="form-control textarea inp" />
              <i class="text-danger"><form:errors
                  path="postDescription" /></i>
            </div>
            <c:if test="${not empty postForm.postId}">
              <div class="mb-3">
                <div class="form-check form-switch">
                  <label class="form-check-label" for="status">Post
                    Status</label>
                  <form:checkbox path="postStatus"
                    class="form-check-input" id="status" />
                </div>
              </div>
            </c:if>
            <div class="text-center">
              <input type="reset" class="btn btn-dark mx-3"
                value="${button}"><input type="submit"
                class="btn btn-primary mx-3" value="Confirm">
            </div>
          </fieldset>
        </form:form>
      </div>
      <div class="col-md-3"></div>
    </div>
  </div>
</c:if>
<c:if test="${loggedInId != createdUserId}">
  <jsp:include page="../users/error.jsp" />
</c:if>