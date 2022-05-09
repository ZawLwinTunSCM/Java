<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="container mb-4">
  <div class="row">
    <div class="col-md-3"></div>
    <div class="col-md-6 mt-3">
      <form:form action="${pageContext.request.contextPath}/saveUser"
        modelAttribute="usersForm" method="POST">
        <form:hidden path="userId" />
        <form:hidden path="userName" />
        <form:hidden path="userEmail" />
        <form:hidden path="userPassword" />
        <form:hidden path="userConfirmPassword" />
        <form:hidden path="userType" />
        <form:hidden path="userPhone" />
        <form:hidden path="userDob" />
        <form:hidden path="userAddress" />
        <form:hidden path="userProfile" />
        <fieldset class="border shadow-sm p-3 bg-body rounded">
          <c:if test="${empty usersForm.userId}">
            <legend class="float-none w-auto p-2">User
              Registration Confirm</legend>
          </c:if>
          <c:if test="${not empty usersForm.userId}">
            <legend class="float-none w-auto p-2">User Edit
              Confirm</legend>
          </c:if>
          <div class="row">
            <div class="col-md-3"></div>
            <div class="col-md-6">
              <c:if test="${usersForm.userProfile !=null}">
              <img src="${usersForm.userProfile}" class="img-fluid"
                alt="User Profile"> <br>
                </c:if>
              <h5 class="text-success text-center">User Profile</h5>
              <br>
              <div class="col-md-3"></div>
            </div>
            <div class="clearfix py-2 border-bottom">
              <h5 class="text-success float-start">User Name</h5>
              <h5 class="text-muted float-end">${usersForm.userName}</h5>
            </div>
            <br>
            <div class="clearfix py-2 border-bottom">
              <h5 class="text-success float-start">User Email</h5>
              <h5 class="text-muted float-end">${usersForm.userEmail}</h5>
            </div>
            <br>
            <div class="clearfix py-2 border-bottom">
              <h5 class="text-success float-start">User Type</h5>
              <h5 class="text-muted float-end">${usersForm.userType}</h5>
            </div>
            <br>
            <div class="clearfix py-2 border-bottom">
              <h5 class="text-success float-start">User Phone</h5>
              <h5 class="text-muted float-end">${usersForm.userPhone}</h5>
            </div>
            <br>
            <div class="clearfix py-2 border-bottom">
              <h5 class="text-success float-start">User Birthday</h5>
              <h5 class="text-muted float-end">${usersForm.userDob}</h5>
            </div>
            <br>
            <div class="clearfix py-2 border-bottom">
              <h5 class="text-success float-start">User Address</h5>
              <h5 class="text-muted float-end">${usersForm.userAddress}</h5>
            </div>
            <br> <br> <br>
            <div class="text-center">
              <input type="submit" name="cancel"
                class="btn btn-dark mx-3" value="Cancel"> <input
                type="submit" class="btn btn-primary mx-3"
                value="Insert">
            </div>
          </div>
          <div class="col-md-3"></div>
        </fieldset>
      </form:form>
    </div>
    <div class="col-md-3"></div>
  </div>
</div>