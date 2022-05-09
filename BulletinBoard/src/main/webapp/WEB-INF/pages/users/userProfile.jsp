<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="container mb-4">
  <div class="row">
    <div class="col-md-3"></div>
    <div class="col-md-6 mt-3">
      <c:if test="${not empty userProfile}">
        <fieldset class="border shadow-sm p-3 bg-body rounded">
          <legend class="float-none w-auto p-2">User Detail
            Profile</legend>
          <div class="row">
            <div class="col-md-3"></div>
            <div class="col-md-6">
              <c:if test="${userProfile.userProfile !=null}">
                <img src="${userProfile.userProfile}"
                  class="img img-fluid">
                <br>
              </c:if>
              <h5 class="text-success text-center">User Profile</h5>
              <br>
              <div class="col-md-3"></div>
            </div>
            <div class="clearfix py-2 border-bottom">
              <h5 class="text-success float-start">User Name</h5>
              <h5 class="text-muted float-end">${userProfile.userName}</h5>
            </div>
            <br>
            <div class="clearfix py-2 border-bottom">
              <h5 class="text-success float-start">User Email</h5>
              <h5 class="text-muted float-end">${userProfile.userEmail}</h5>
            </div>
            <br>
            <div class="clearfix py-2 border-bottom">
              <h5 class="text-success float-start">Created User</h5>
              <h5 class="text-muted float-end">${userProfile.createdUser}</h5>
            </div>
            <br>
            <div class="clearfix py-2 border-bottom">
              <h5 class="text-success float-start">User Phone</h5>
              <h5 class="text-muted float-end">${userProfile.userPhone}</h5>
            </div>
            <br>
            <div class="clearfix py-2 border-bottom">
              <h5 class="text-success float-start">User Birthday</h5>
              <h5 class="text-muted float-end">${userProfile.userDob}</h5>
            </div>
            <br>
            <div class="clearfix py-2 border-bottom">
              <h5 class="text-success float-start">User Address</h5>
              <h5 class="text-muted float-end">${userProfile.userAddress}</h5>
            </div>
            <br>
            <div class="clearfix py-2 border-bottom">
              <h5 class="text-success float-start">Created Date</h5>
              <h5 class="text-muted float-end">${userProfile.createdAt}</h5>
            </div>
            <br>
            <div class="clearfix py-2 border-bottom">
              <h5 class="text-success float-start">Updated Date</h5>
              <h5 class="text-muted float-end">${userProfile.updatedAt}</h5>
            </div>
            <br>
            <div class="clearfix py-2 border-bottom">
              <h5 class="text-success float-start">User Type</h5>
              <h5 class="text-muted float-end">
                <c:if test="${userProfile.userType == 'ROLE_ADMIN'}">Admin</c:if>
                <c:if test="${userProfile.userType == 'ROLE_USER'}">User</c:if>
              </h5>
            </div>
          </div>
        </fieldset>
      </c:if>
    </div>
    <div class="col-md-3"></div>
  </div>
</div>