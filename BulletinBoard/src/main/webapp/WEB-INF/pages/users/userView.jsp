<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:if test="${not empty msg}">
  <span class="msg"> ${msg}</span>
  <c:remove var="msg" />
</c:if>
<form:form action="${pageContext.request.contextPath}/searchUsers"
  modelAttribute="searchForm" method="POST">
  <div class="clearfix mt-5">
    <div class="row float-end">
      <div class="col-auto padding">
        <div class="input-group mb-3">
          <form:input type="text" path="name" placeholder="User Name"
            class="form-control" pattern="^[a-zA-Z ]*$|^NULL$"
            title="Name must be only characters" />
          <form:input type="text" path="email" placeholder="User Email"
            class="form-control"
            pattern="^(?:[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}|)$"
            title="Invalid Email Address" />
          <form:input type="text" path="CreatedFrom"
            placeholder="Created From" id="date"
            class="form-control inp" autocomplete="off" readonly="true" />
          <form:input type="text" path="CreatedTo"
            placeholder="Created To" id="date1" class="form-control inp"
            autocomplete="off" readonly="true" />
          <input type="submit" value="Search" class="btn btn-primary">
        </div>
      </div>
      <div class="col-auto">
        <a href="${pageContext.request.contextPath}/userRegistration"
          class="btn btn-primary add">Add</a>
      </div>
    </div>
  </div>
</form:form>
<c:if test="${empty usersList}">
  <h2 align="center" class="my-5">No Post Data Found</h2>
</c:if>
<c:if test="${not empty usersList}">
  <br>
  <h2 class="ml-1">User Lists</h2>
  <br>
  <table class="cell-border hover responsive nowrap" id="uTable">
    <thead>
      <tr>
        <th>Name</th>
        <th>Email</th>
        <th>Created User</th>
        <th>Phone</th>
        <th>Birth Date</th>
        <th>Address</th>
        <th>Created Date</th>
        <th>Updated Date</th>
        <th>Type</th>
        <th>Image</th>
        <th>Update</th>
        <th>Delete</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach items="${usersList}" var="users">
        <tr>
          <td align="left"><span class="text-success cursor"
            data-bs-toggle="modal" data-bs-target="#detail">${users.userName}</span></td>
          <td align="left">${users.userEmail}</td>
          <td align="left">${users.createdUser}</td>
          <td align="center">${users.userPhone}</td>
          <td align="center">${users.userDob}</td>
          <td align="left">${users.userAddress}</td>
          <td align="center"><fmt:formatDate pattern="yyyy-MM-dd"
              value="${users.createdAt}" /></td>
          <td align="center"><fmt:formatDate pattern="yyyy-MM-dd"
              value="${users.updatedAt}" /></td>
          <td><c:if test="${users.userType == 'ROLE_ADMIN'}">Admin</c:if>
            <c:if test="${users.userType == 'ROLE_USER'}">User</c:if></td>
          <td>${users.userProfile}</td>
          <td align="center"><a
            href="${pageContext.request.contextPath}/editUser/${users.userId}"
            class="btn btn-success"><i
              class="fa-solid fa-pen-to-square text-white edit"></i></a></td>
          <td align="center"><p class="btn btn-danger">
              <a
                href="${pageContext.request.contextPath}/deleteUser/${users.userId}"
                class="delete" data-bs-toggle="modal"
                data-bs-target="#delete"><i
                class="fa-solid fa-trash-can text-white"></i></a>
            </p></td>
        </tr>

        <!-- User Detail Modal -->
        <div class="modal fade" id="detail" tabindex="-1"
          aria-hidden="true">
          <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
              <div class="modal-header">
                <h3 class="modal-title">User Detail</h3>
                <button type="button" class="btn-close"
                  data-bs-dismiss="modal" aria-label="Close"></button>
              </div>
              <div class="modal-body">
                <div class="row">
                  <div class="col-md-3"></div>
                  <div class="col-md-6">
                    <img src="" class="img img-fluid"> <br>
                    <h5 class="text-success text-center">User
                      Profile</h5>
                    <br>
                    <div class="col-md-3"></div>
                  </div>
                  <div class="clearfix py-2 border-bottom">
                    <h5 class="text-success float-start">User Name</h5>
                    <h5 class="text-muted name float-end"></h5>
                  </div>
                  <br>
                  <div class="clearfix py-2 border-bottom">
                    <h5 class="text-success float-start">User Email</h5>
                    <h5 class="text-muted email float-end"></h5>
                  </div>
                  <br>

                  <div class="clearfix py-2 border-bottom">
                    <h5 class="text-success float-start">Created
                      User</h5>
                    <h5 class="text-muted createdUser float-end"></h5>
                  </div>
                  <br>
                  <div class="clearfix py-2 border-bottom">
                    <h5 class="text-success float-start">User Phone</h5>
                    <h5 class="text-muted phone float-end"></h5>
                  </div>
                  <br>
                  <div class="clearfix py-2 border-bottom">
                    <h5 class="text-success float-start">User
                      Birthday</h5>
                    <h5 class="text-muted dob float-end"></h5>
                  </div>
                  <br>
                  <div class="clearfix py-2 border-bottom">
                    <h5 class="text-success float-start">User
                      Address</h5>
                    <h5 class="text-muted address float-end"></h5>
                  </div>
                  <br>
                  <div class="clearfix py-2 border-bottom">
                    <h5 class="text-success float-start">Created
                      Date</h5>
                    <h5 class="text-muted createdDate float-end"></h5>
                  </div>
                  <br>
                  <div class="clearfix py-2 border-bottom">
                    <h5 class="text-success float-start">Updated
                      Date</h5>
                    <h5 class="text-muted updatedDate float-end"></h5>
                  </div>
                  <br>
                  <div class="clearfix py-2 border-bottom">
                    <h5 class="text-success float-start">User Type</h5>
                    <h5 class="text-muted type float-end"></h5>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>

        <!-- User Delete Confirm Modal -->
        <div class="modal fade" id="delete" tabindex="-1"
          aria-hidden="true">
          <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
              <div class="modal-header">
                <h3 class="modal-title">User Delete?</h3>
                <button type="button" class="btn-close"
                  data-bs-dismiss="modal" aria-label="Close"></button>
              </div>
              <div class="modal-body text-center">
                <i class="fa-regular fa-circle-xmark text-danger cross"></i><br>
                <br>
                <h4 class="modal-title">Are Your Sure?</h4>
                <br>
                <p>
                  Do you really want to delete this user?<br>This
                  process cannot be undone.
                </p>
                <br> <br>
                <button type="button" class="btn btn-secondary mr-auto"
                  data-bs-dismiss="modal">Cancel</button>
                <a href="" class="btn btn-danger modalDelete">Delete</a>
              </div>
            </div>
          </div>
        </div>
      </c:forEach>
    </tbody>
  </table>
</c:if>