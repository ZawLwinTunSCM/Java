<%@ taglib uri="http://www.springframework.org/security/tags"
  prefix="sec"%>
<nav class="navbar navbar-expand-sm navbar-light bg-light">
  <div class="container-fluid ">
    <a class="navbar-brand " href="#">SCM Bulletin Board</a>
    <sec:authorize access="hasAnyRole('ADMIN','USER')">
      <button class="navbar-toggler" type="button"
        data-bs-toggle="collapse"
        data-bs-target="#navbarSupportedContent"
        aria-controls="navbarSupportedContent" aria-expanded="false"
        aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse justify-content-end"
        id="navbarSupportedContent">
        <ul class="navbar-nav mx-5">
          <li class="nav-item"><a class="nav-link active"
            aria-current="page"
            href="${pageContext.request.contextPath}/postView">Posts</a></li>
          <li class="nav-item dropdown"><a
            class="nav-link dropdown-toggle" href="#"
            id="navbarDropdownMenuLink" role="button"
            data-bs-toggle="dropdown" aria-expanded="false"> <i
              class="fa-solid fa-user"></i> ${loggedInName}
          </a>
            <ul class="dropdown-menu"
              aria-labelledby="navbarDropdownMenuLink">
              <sec:authorize access="hasRole('ADMIN')">
                <li><a class="dropdown-item my-2"
                  href="${pageContext.request.contextPath}/users">User
                    List</a></li>
              </sec:authorize>
              <sec:authorize access="hasRole('USER')">
                <li><a class="dropdown-item my-2"
                  href="${pageContext.request.contextPath}/userDetailProfile">User
                    Information</a></li>
                <li><a class="dropdown-item my-2"
                  href="${pageContext.request.contextPath}/passwordChange">Password
                    Change</a></li>

              </sec:authorize>
              <li><form
                  action="<%=request.getContextPath()%>/logout"
                  method="POST">
                  <button type="submit" class="dropdown-item my-2">Logout</button>
                </form></li>
            </ul></li>
        </ul>
      </div>
    </sec:authorize>
  </div>
</nav>
</body>
</html>