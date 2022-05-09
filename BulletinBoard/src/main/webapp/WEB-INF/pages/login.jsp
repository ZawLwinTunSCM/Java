<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="container mt-5">
  <div class="row">
    <div class="col-md-3"></div>
    <div class="col-md-6 mt-3">
      <c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION.message }">
        <span class="msg2">${msg}Incorrect Email or Password!</span>
      </c:if>
      <form action="<%=request.getContextPath()%>/login" method="POST">
        <fieldset class="border shadow-sm p-3 bg-body rounded mt-5">
          <legend class="float-none w-auto p-2">Login Form</legend>
          <div class="mb-3 mt-3">
            <label class="form-label">Email address</label> <input
              type="email" class="form-control" name="userEmail"
              placeholder="Email Address">
          </div>
          <div class="mb-3">
            <label class="form-label">Password</label> <input
              type="password" class="form-control" name="userPassword"
              placeholder="Password">
          </div>
          <div class="text-center">
            <input type="submit" class="btn btn-primary" value="Login">
          </div>
        </fieldset>
      </form>
    </div>
    <div class="col-md-3"></div>
  </div>
</div>