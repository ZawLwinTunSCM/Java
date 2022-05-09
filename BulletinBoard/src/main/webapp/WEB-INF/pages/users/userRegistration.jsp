<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
  <div class="container my-4">
    <div class="row">
      <div class="col-md-3"></div>
      <div class="col-md-6 mt-5">
        <c:if test="${not empty msg}">
          <span class="msg1"> ${msg}</span>
          <c:remove var="msg" />
        </c:if>
        <form:form
          action="${pageContext.request.contextPath}/createUserConfirm"
          modelAttribute="usersForm" method="POST">
          <fieldset class="border shadow-sm p-3 bg-body rounded">
            <c:if test="${empty usersForm.userId}">
              <legend class="float-none w-auto p-2">User
                Registration</legend>
            </c:if>
            <c:if test="${not empty usersForm.userId}">
              <legend class="float-none w-auto p-2">User Edit</legend>
              <form:hidden path="userId" />
              <form:hidden path="userPassword" />
              <form:hidden path="userConfirmPassword" />
            </c:if>
            <div class="mb-3">
              <div class="row">
                <div class="col-md-3"></div>
                <div class="col-md-6">
                  <c:if test="${empty usersForm.userId}">
                    <img src="" alt="Profile Preview" class="hidden"
                      id="img">
                    <br>
                  </c:if>
                  <c:if test="${not empty usersForm.userId}">
                    <c:if test="${not empty usersForm.userProfile}">
                      <img
                        src="${pageContext.request.contextPath}/${usersForm.userProfile }"
                        alt="Profile Preview" id="img" class="img-fluid">
                    </c:if>
                    <br>
                  </c:if>
                </div>
                <div class="col-md-3"></div>
              </div>
              <label class="form-label">Profile</label> <input
                type='file' class="form-control" accept='image/*'
                onchange="preview(this)" />
              <form:hidden path="userProfile" class="profile" />
              <i class="text-danger"><form:errors path="userProfile" /></i>
            </div>
            <div class="mb-3">
              <label class="form-label">Name</label>
              <form:input type="text" path="userName"
                class="form-control inp name"
                pattern="^[a-zA-Z ]{2,30}$"
                title="User Name must be only 2 to 30 characters" />
              <i class="text-danger"><form:errors path="userName" /></i>
            </div>
            <div class="mb-3">
              <label class="form-label">Email</label>
              <form:input type="email" path="userEmail"
                class="form-control inp email"
                pattern="^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$"
                title="Invalid Email Address" />
              <i class="text-danger"><form:errors path="userEmail" /></i>
            </div>
            <c:if test="${empty usersForm.userId}">
              <div class="mb-3">
                <label class="form-label">Password</label>
                <form:input type="password" path="userPassword"
                  class="form-control inp pass"
                  pattern="^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{8,}$"
                  title="Password must be at least 8 characters with at least one uppercase letter, one lowercase letter" />
                <i class="text-danger"><form:errors
                    path="userPassword" /></i>
              </div>
              <div class="mb-3">
                <label class="form-label">Confirm Password</label>
                <form:input type="password" path="userConfirmPassword"
                  class="form-control inp conpass"
                  pattern="^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{8,}$"
                  title="Confirm Password must be at least 8 characters with at least one uppercase letter, one lowercase letter" />
                <i class="text-danger"><form:errors
                    path="userConfirmPassword" /></i>
              </div>
            </c:if>
            <div class="mb-3">
              <label class="form-label">Type</label>
              <form:select path="userType" class="form-select inp type">
                <form:option value="" label="Open to choose"
                  selected="selected" />
                <form:option value="ROLE_ADMIN" label="ADMIN" />
                <form:option value="ROLE_USER" label="USER" />
              </form:select>
              <i class="text-danger"><form:errors path="userType" /></i>
            </div>
            <div class="mb-3">
              <label class="form-label">Phone</label>
              <form:input type="text" path="userPhone"
                class="form-control inp phone" pattern="\\d{11}$"
                title="Phone Number must be only 11 characters" />
              <i class="text-danger"><form:errors path="userPhone" /></i>
            </div>
            <div class="mb-3">
              <label class="form-label">Date of Birth</label>
              <div class="input-group">
                <form:input type="text" path="userDob" id="date"
                  class="form-control inp dob" autocomplete="off"
                  readonly="true" value="${usersForm.userDob}" />

                <div class="input-group-addon">
                  <span class="glyphicon glyphicon-th"></span>
                </div>
              </div>
              <i class="text-danger"><form:errors path="userDob" /></i>
            </div>
            <div class="mb-3">
              <label class="form-label">Address</label>
              <form:textarea path="userAddress"
                class="form-control textarea inp add" />
              <i class="text-danger"><form:errors path="userAddress" /></i>
            </div>
            <div class="text-center">
              <input type="reset" class="btn btn-dark" value="Clear">
              <input type="submit" class="btn btn-primary"
                value="Confirm">
            </div>
          </fieldset>
        </form:form>
      </div>
      <div class="col-md-3"></div>
    </div>
  </div>