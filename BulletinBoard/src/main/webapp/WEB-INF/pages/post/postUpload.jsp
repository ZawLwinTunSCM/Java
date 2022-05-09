<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="container my-4">
  <div class="row">
    <div class="col-md-3"></div>
    <div class="col-md-6 mt-5">
      <c:if test="${not empty msg}">
        <span class="msg1">${msg}</span>
      </c:if>
      <form action="${pageContext.request.contextPath}/uploadPost"
        method="POST" enctype="multipart/form-data">
        <fieldset class="border shadow-sm p-3 bg-body rounded">
          <legend class="float-none w-auto p-2">Post Upload</legend>
          <div class="mb-3">
            <label class="form-label">Choose Upload File</label> <input
              type="file" class="form-control" name="file" />
          </div>
          <div class="text-center">
            <input type="submit" name="cancel" class="btn btn-dark mx-3"
              value="Cancel"> <input type="button"
              class="btn btn-primary mx-3" value="Confirm"
              data-bs-toggle="modal" data-bs-target="#upload">
          </div>
        </fieldset>

        <!-- Post Upload Confirm Modal -->
        <div class="modal fade" id="upload" tabindex="-1"
          aria-hidden="true">
          <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
              <div class="modal-header">
                <h3 class="modal-title">Post Upload?</h3>
                <button type="button" class="btn-close"
                  data-bs-dismiss="modal" aria-label="Close"></button>
              </div>
              <div class="modal-body text-center">
                <i class="fa-solid fa-file-arrow-up cross text-info"></i><br>
                <br>
                <h4 class="modal-title">Are Your Sure?</h4>
                <br>
                <p>Do you really want to upload this post?</p>
                <br> <br>
                <button type="button" class="btn btn-secondary mr-auto"
                  data-bs-dismiss="modal">Cancel</button>
                <input type="submit" class="btn btn-primary mx-3"
                  value="Upload">
              </div>
            </div>
          </div>
        </div>
      </form>
    </div>
    <div class="col-md-3"></div>
  </div>
</div>