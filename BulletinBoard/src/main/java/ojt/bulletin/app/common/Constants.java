package ojt.bulletin.app.common;

import java.util.HashMap;
import java.util.Map;

/**
 * <h2>Constants Class</h2>
 * <p>
 * Process for Displaying Constants
 * </p>
 * 
 * @author ZawLwinTun
 *
 */
public class Constants {
    /**
     * <h2>EXCEL_DOWNLOAD_POST</h2>
     * <p>
     * EXCEL_DOWNLOAD_POST
     * </p>
     */
    public static final Map<Integer, String> EXCEL_DOWNLOAD_POST;
    static {
        EXCEL_DOWNLOAD_POST = new HashMap<Integer, String>();
        EXCEL_DOWNLOAD_POST.put(0, "ID");
        EXCEL_DOWNLOAD_POST.put(1, "Title");
        EXCEL_DOWNLOAD_POST.put(2, "Description");
        EXCEL_DOWNLOAD_POST.put(3, "Status");
        EXCEL_DOWNLOAD_POST.put(4, "Created User ID");
        EXCEL_DOWNLOAD_POST.put(5, "Updated User ID");
        EXCEL_DOWNLOAD_POST.put(6, "CreatedAt");
        EXCEL_DOWNLOAD_POST.put(7, "UpdatedAt");
    }
    /**
     * <h2>POST</h2>
     * <p>
     * POST
     * </p>
     */
    public static final String POST = "Post.csv";
    /**
     * <h2>DATE_FORMAT</h2>
     * <p>
     * DATE_FORMAT
     * </p>
     */
    public static final String DATE_FORMAT = "dd/mm/yyyy hh:mm:ss";
}
