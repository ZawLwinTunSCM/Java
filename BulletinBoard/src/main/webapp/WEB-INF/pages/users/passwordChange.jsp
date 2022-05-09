<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="container mt-5">
  <div class="row">
    <div class="col-md-3"></div>
    <div class="col-md-6 mt-5">
      <form
        action="${pageContext.request.contextPath}/userPasswordChange"
        method="POST">
        <c:if test="${not empty msg}">
          <span class="msg1">${msg}</span>
        </c:if>
        <fieldset class="border shadow-sm p-3 bg-body rounded">
          <legend class="float-none w-auto p-2">Password Change</legend>
          <div class="mb-3">
            <label class="form-label">Old Password</label> <input
              type="password" class="form-control" name="password"
              required="required" placeholder="Old Password">
          </div>
          <div class="mb-3">
            <label class="form-label">New Password</label> <input
              type="password" class="form-control" name="newPassword"
              required="required" placeholder="New Password"
              pattern="^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{8,}$"
              title="Password must be at least 8 characters with at least one uppercase letter, one lowercase letter">
          </div>
          <div class="mb-3">
            <label class="form-label">Confirm New Password</label> <input
              type="password" class="form-control"
              name="newConfirmPassword" required="required"
              placeholder="Confirm New Password"
              pattern="^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{8,}$"
              title="Password must be at least 8 characters with at least one uppercase letter, one lowercase letter">
          </div>
          <div class="text-center">
            <input type="reset" class="btn btn-dark" value="Clear">
            <input type="submit" class="btn btn-primary"
              value="Change Password">
          </div>
        </fieldset>
      </form>
    </div>
    <div class="col-md-3"></div>
  </div>
</div>