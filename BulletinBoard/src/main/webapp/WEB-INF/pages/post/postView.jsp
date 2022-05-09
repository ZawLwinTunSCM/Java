<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
  prefix="sec"%>
<c:if test="${not empty msg}">
  <span class="msg"> ${msg}</span>
  <c:remove var="msg" />
</c:if>
<form action="${pageContext.request.contextPath}/searchPost"
  method="POST">
  <div class="clearfix mt-5">
    <div class="row float-end">
      <div class="col-auto padding">
        <div class="input-group mb-3">
          <input type="text" class="form-control"
            placeholder="Search Post" name="search"> <input
            class="btn btn-primary" type="submit" value="Search">
        </div>
      </div>
      <div class="col-auto">
        <a href="${pageContext.request.contextPath}/postRegistration"
          class="btn btn-primary add">Add</a>
        <c:if test="${not empty postList}">
          <a href="${pageContext.request.contextPath}/downloadPost"
            class="btn btn-primary download">Download</a>
        </c:if>
        <a href="${pageContext.request.contextPath}/uploadPostChoose"
          class="btn btn-primary upload">Upload</a>
      </div>
    </div>
  </div>
</form>
<c:if test="${empty postList}">
  <h2 align="center" class="my-5">No Post Data Found</h2>
</c:if>
<c:if test="${not empty postList}">
  <br>
  <h2 class="ml-1">Post Lists</h2>
  <br>
  <table class="cell-border hover responsive nowrap" id="pTable">
    <thead>
      <tr>
        <th>Title</th>
        <th>Description</th>
        <th>Posted User</th>
        <th>Posted Date</th>
        <th>Update</th>
        <th>Delete</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach items="${postList}" var="post">
        <sec:authorize access="hasRole('ADMIN')">
          <c:if test="${post.postStatus == 'true'}">
            <tr>
              <td align="left"><span class="text-primary cursor"
                data-bs-toggle="modal" data-bs-target="#detail">${post.postTitle}</span></td>
              <td align="left" class="td-truncate">${post.postDescription}</td>
              <td align="left">${post.user.userName}</td>
              <td align="left"><fmt:formatDate pattern="dd-MM-yyyy"
                  value="${post.createdAt}" /></td>
              <td align="center"><a
                href="${pageContext.request.contextPath}/editPost/${post.postId}"
                class="btn btn-success"><i
                  class="fa-solid fa-pen-to-square text-white"></i></a></td>
              <td align="center">
                <p class="btn btn-danger">
                  <a
                    href="${pageContext.request.contextPath}/deletePost/${post.postId}"
                    data-bs-toggle="modal" data-bs-target="#delete"
                    class="delete"><i
                    class="fa-solid fa-trash-can text-white"></i></a>
                </p>
              </td>
            </tr>
          </c:if>

          <c:if test="${post.postStatus == 'false'}">
            <c:if test="${post.user.userId == loggedInId}">
              <tr>
                <td align="left"><span class="text-primary cursor"
                  data-bs-toggle="modal" data-bs-target="#detail">${post.postTitle}</span></td>
                <td align="left" class="td-truncate">${post.postDescription}</td>
                <td align="left">${post.user.userName}</td>
                <td align="left"><fmt:formatDate
                    pattern="dd-MM-yyyy" value="${post.createdAt}" /></td>
                <td align="center"><a
                  href="${pageContext.request.contextPath}/editPost/${post.postId}"
                  class="btn btn-success"><i
                    class="fa-solid fa-pen-to-square text-white"></i></a></td>
                <td align="center">
                  <p class="btn btn-danger">
                    <a href="#"
                      data-bs-href="${pageContext.request.contextPath}/deletePost/${post.postId}"
                      data-bs-toggle="modal" data-bs-target="#delete"
                      class="delete"><i
                      class="fa-solid fa-trash-can text-white"></i></a>
                  </p>
                </td>
              </tr>
            </c:if>
          </c:if>
        </sec:authorize>
        <sec:authorize access="hasRole('USER')">
          <c:if test="${post.postStatus == 'true'}">
            <tr>
              <td align="left"><span class="text-primary cursor"
                data-bs-toggle="modal" data-bs-target="#detail">${post.postTitle}</span></td>
              <td align="left" class="td-truncate">${post.postDescription}</td>
              <td align="left">${post.user.userName}</td>
              <td align="left"><fmt:formatDate pattern="dd-MM-yyyy"
                  value="${post.createdAt}" /></td>
              <c:if test="${post.user.userId == loggedInId }">
                <td align="center"><a
                  href="${pageContext.request.contextPath}/editPost/${post.postId}"
                  class="btn btn-success"><i
                    class="fa-solid fa-pen-to-square text-white"></i></a></td>
                <td align="center">
                  <p class="btn btn-danger">
                    <a
                      href="${pageContext.request.contextPath}/deletePost/${post.postId}"
                      data-bs-toggle="modal" data-bs-target="#delete"
                      class="delete"><i
                      class="fa-solid fa-trash-can text-white"></i></a>
                  </p>
                </td>
              </c:if>
              <c:if test="${post.user.userId != loggedInId }">
                <td align="center"></td>
                <td align="center"></td>
              </c:if>
            </tr>
          </c:if>

          <c:if test="${post.postStatus == 'false'}">
            <c:if test="${post.user.userId == loggedInId}">
              <tr>
                <td align="left"><span class="text-primary cursor"
                  data-bs-toggle="modal" data-bs-target="#detail">${post.postTitle}</span></td>
                <td align="left" class="td-truncate">${post.postDescription}</td>
                <td align="left">${post.user.userName}</td>
                <td align="left"><fmt:formatDate
                    pattern="dd-MM-yyyy" value="${post.createdAt}" /></td>
                <c:if test="${post.user.userId == loggedInId }">
                  <td align="center"><a
                    href="${pageContext.request.contextPath}/editPost/${post.postId}"
                    class="btn btn-success"><i
                      class="fa-solid fa-pen-to-square text-white"></i></a></td>
                  <td align="center">
                    <p class="btn btn-danger">
                      <a href="#"
                        data-bs-href="${pageContext.request.contextPath}/deletePost/${post.postId}"
                        data-bs-toggle="modal" data-bs-target="#delete"
                        class="delete"><i
                        class="fa-solid fa-trash-can text-white"></i></a>
                    </p>
                  </td>
                </c:if>
                <c:if test="${post.user.userId != loggedInId }">
                  <td align="center"></td>
                  <td align="center"></td>
                </c:if>
              </tr>
            </c:if>
          </c:if>
        </sec:authorize>
        <!-- Post Detail Modal -->
        <div class="modal fade" id="detail" tabindex="-1"
          aria-hidden="true">
          <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
              <div class="modal-header">
                <h3 class="modal-title">Post Detail</h3>
                <button type="button" class="btn-close"
                  data-bs-dismiss="modal" aria-label="Close"></button>
              </div>
              <div class="modal-body">
                <h5 class="text-info">Post Title</h5>
                <p class="text-muted postTitle"></p>
                <br>
                <h5 class="text-info">Post Description</h5>
                <p class="text-muted postDesc"></p>
                <br>
                <h5 class="text-info">Posted User</h5>
                <p class="text-muted postedUser"></p>
                <br>
                <h5 class="text-info">Posted Date</h5>
                <p class="text-muted postedDate"></p>
                <br>
              </div>
            </div>
          </div>
        </div>

        <!-- Post Delete Confirm Modal -->
        <div class="modal fade" id="delete" tabindex="-1"
          aria-hidden="true">
          <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
              <div class="modal-header">
                <h3 class="modal-title">Post Delete?</h3>
                <button type="button" class="btn-close"
                  data-bs-dismiss="modal" aria-label="Close"></button>
              </div>
              <div class="modal-body text-center">
                <i class="fa-regular fa-circle-xmark text-danger cross"></i><br>
                <br>
                <h4 class="modal-title">Are Your Sure?</h4>
                <br>
                <p>
                  Do you really want to delete this post?<br>This
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