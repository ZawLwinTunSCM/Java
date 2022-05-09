package ojt.bulletin.app.bl.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import ojt.bulletin.app.bl.dto.PostDTO;
import ojt.bulletin.app.bl.service.PostService;
import ojt.bulletin.app.common.Constants;
import ojt.bulletin.app.persistence.dao.PostDao;
import ojt.bulletin.app.persistence.dao.UsersDao;
import ojt.bulletin.app.persistence.entity.Post;
import ojt.bulletin.app.web.form.PostForm;

/**
 * <h2>PostServiceImpl Class</h2>
 * <p>
 * Process for Displaying PostServiceImpl
 * </p>
 * 
 * @author ZawLwinTun
 *
 */
@Service
@Transactional
public class PostServiceImpl implements PostService {

    /**
     * <h2>postDao</h2>
     * <p>
     * postDao
     * </p>
     */
    @Autowired
    private PostDao postDao;

    /**
     * <h2>usersDao</h2>
     * <p>
     * usersDao
     * </p>
     */
    @Autowired
    private UsersDao usersDao;

    /**
     * <h2>session</h2>
     * <p>
     * session
     * </p>
     */
    @Autowired
    private HttpSession session;

    /**
     * <h2>doSavePost</h2>
     * <p>
     * Save Post
     * </p>
     * 
     * @param postForm PostForm
     */
    @Override
    public void doSavePost(PostForm postForm) {
        Date date = new Date();
        Post post = new Post(postForm);
        int uid = (int) session.getAttribute("loggedInId");
        post.setPostStatus(true);
        post.setUser(usersDao.dbGetUserById(uid));
        post.setUpdatedUserId(uid);
        post.setCreatedAt(date);
        post.setUpdatedAt(date);
        postDao.dbSavePost(post);
    }

    /**
     * <h2>doListPost</h2>
     * <p>
     * Post List
     * </p>
     * 
     */
    @Override
    public List<PostDTO> doListPost() {
        List<PostDTO> postList = new ArrayList<PostDTO>();
        for (Post post : this.postDao.dbListPost()) {
            PostDTO postDTO = new PostDTO(post);
            postList.add(postDTO);
        }
        return postList;
    }

    /**
     * <h2>doSearchPost</h2>
     * <p>
     * Search Post
     * </p>
     * 
     * @param search String
     */
    @Override
    public List<PostDTO> doSearchPost(String search) {
        List<PostDTO> postList = new ArrayList<PostDTO>();
        for (Post post : this.postDao.dbSearchPost(search)) {
            PostDTO postDTO = new PostDTO(post);
            postList.add(postDTO);
        }
        return postList;
    }

    /**
     * <h2>doGetPostById</h2>
     * <p>
     * Get Post By ID
     * </p>
     * 
     * @param id int
     */
    @Override
    public PostDTO doGetPostById(int id) {
        Post post = this.postDao.dbGetPostById(id);
        PostDTO postForm = post != null ? new PostDTO(post) : null;
        return postForm;
    }

    /**
     * <h2>doUpdatePost</h2>
     * <p>
     * Update Post
     * </p>
     * 
     * @param postForm PostForm
     */
    @Override
    public void doUpdatePost(PostForm postForm) {
        Date date = new Date();
        Post post = new Post(postForm);
        int uid = (int) session.getAttribute("loggedInId");
        post.setUpdatedAt(date);
        post.setUpdatedUserId(uid);
        postDao.dbUpdatePost(post);
    }

    /**
     * <h2>doDeletePost</h2>
     * <p>
     * Delete Post
     * </p>
     * 
     * @param id int
     */
    @Override
    public void doDeletePost(int id) {
        Date date = new Date();
        int uid = (int) session.getAttribute("loggedInId");
        postDao.dbDeletePost(id, date, uid);
    }

    /**
     * <h2>doDownlaodPost</h2>
     * <p>
     * Download Post
     * </p>
     * 
     * @param response HttpServletResponse
     * @throws IOException
     */
    @Override
    public void doDownlaodPost(HttpServletResponse response) throws IOException {
        String filename = Constants.POST;
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Post");
        CreationHelper creationHelper = workbook.getCreationHelper();
        Row row = sheet.createRow(0);
        for (int i = 0; i < Constants.EXCEL_DOWNLOAD_POST.size(); i++) {
            row.createCell(i).setCellValue(Constants.EXCEL_DOWNLOAD_POST.get(i));
        }
        Row datarow;
        int rowCount = 1;
        List<Post> postList = postDao.dbListPost();
        for (Post p : postList) {
            CellStyle style = workbook.createCellStyle();
            style.setDataFormat(creationHelper.createDataFormat().getFormat(Constants.DATE_FORMAT));
            datarow = sheet.createRow(rowCount++);

            Cell cellId = datarow.createCell(0);
            cellId.setCellValue(p.getPostId());
            cellId.setCellType(CellType.NUMERIC);

            Cell cellTitle = datarow.createCell(1);
            cellTitle.setCellValue(p.getPostTitle());
            cellTitle.setCellType(CellType.STRING);

            Cell cellDesc = datarow.createCell(2);
            cellDesc.setCellValue(p.getPostDescription());
            cellDesc.setCellType(CellType.STRING);

            Cell cellStatus = datarow.createCell(3);
            cellStatus.setCellValue(p.getPostStatus());
            cellStatus.setCellType(CellType.NUMERIC);

            Cell cellCreatedUserId = datarow.createCell(4);
            if (p.getUser().getUserId() == null) {
                cellCreatedUserId.setCellValue("");
            } else {
                cellCreatedUserId.setCellValue(p.getUser().getUserId());
                cellCreatedUserId.setCellType(CellType.NUMERIC);
            }

            Cell cellUpdatedUserId = datarow.createCell(5);
            if (p.getUpdatedUserId() == null) {
                cellUpdatedUserId.setCellValue("");
            } else {
                cellUpdatedUserId.setCellValue(p.getUpdatedUserId());
                cellUpdatedUserId.setCellType(CellType.NUMERIC);
            }

            Cell cellCreatedAt = datarow.createCell(6);
            if (p.getCreatedAt() == null) {
                cellCreatedAt.setCellValue("");
            } else {
                cellCreatedAt.setCellValue(p.getCreatedAt());
                cellCreatedAt.setCellStyle(style);
            }

            Cell cellUpdatedAt = datarow.createCell(7);
            if (p.getUpdatedAt() == null) {
                cellUpdatedAt.setCellValue("");
            } else {
                cellUpdatedAt.setCellValue(p.getUpdatedAt());
                cellUpdatedAt.setCellStyle(style);
            }
        }
        try {
            response.reset();
            response.setContentType("application/CSV");
            response.setHeader("Content-Disposition", "attachment; filename=" + filename);
            workbook.write(response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            workbook.close();
            IOUtils.closeQuietly(response.getOutputStream());
        }
    }

    /**
     * <h2>doUploadPost</h2>
     * <p>
     * Upload Post
     * </p>
     * 
     * @param file MultipartFile
     * @throws IOException
     */
    @SuppressWarnings("resource")
    @Override
    public String doUploadPost(MultipartFile file) throws IOException {
        int id = (int) session.getAttribute("loggedInId");
        String check = this.validExcelFile(file);
        if (check != null && check != "") {
            return check;
        }
        XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
        XSSFSheet sheet = workbook.getSheetAt(0);
        Date date = new Date();
        for (int i = sheet.getFirstRowNum() + 1; i <= sheet.getLastRowNum(); i++) {
            Post post = new Post();
            Row row = sheet.getRow(i);
            Cell cellPostTitle = row.getCell(1);
            post.setPostTitle(cellPostTitle.getStringCellValue());
            Cell cellPostDescription = row.getCell(2);
            post.setPostDescription(cellPostDescription.getStringCellValue());
            post.setPostStatus(true);
            post.setUser(usersDao.dbGetUserById(id));
            post.setUpdatedUserId(id);
            post.setCreatedAt(date);
            post.setUpdatedAt(date);
            postDao.dbSavePost(post);
        }
        return "File Upload Successful";
    }

    /**
     * <h2>validExcelFile</h2>
     * <p>
     * Validate File
     * </p>
     *
     * @param file MultipartFile
     * @throws IOException
     * @return String
     */
    @SuppressWarnings("resource")
    private String validExcelFile(MultipartFile file) throws IOException {
        // check if file is null
        if (file.isEmpty()) {
            return "No File Is Selected";
        }
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        // check extension
        if (!extension.equals("csv")) {
            return "File Extension Wrong!";
        }
        XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
        XSSFSheet sheet = workbook.getSheetAt(0);
        int rowStart = sheet.getFirstRowNum() + 1;
        int rowEnd = sheet.getLastRowNum();
        // check null
        for (int i = rowStart; i <= rowEnd; i++) {
            Row row = sheet.getRow(i);
            int cellStart = row.getFirstCellNum() + 1;
            int cellEnd = row.getLastCellNum();
            for (int k = cellStart; k < cellEnd; k++) {
                Cell cell = row.getCell(k);
                if (cell == null) {
                    return "There is Null in the file";
                }
            }
        }
        // check file has no data
        if (sheet.getFirstRowNum() == sheet.getLastRowNum()) {
            return "No Data in the File";
        }
        return "";
    }
}