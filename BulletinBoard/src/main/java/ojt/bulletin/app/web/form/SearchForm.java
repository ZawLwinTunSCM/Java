package ojt.bulletin.app.web.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <h2>SearchForm Class</h2>
 * <p>
 * Process for Displaying SearchForm
 * </p>
 * 
 * @author ZawLwinTun
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchForm {
    /**
     * <h2>name</h2>
     * <p>
     * name
     * </p>
     */
    String name;
    /**
     * <h2>email</h2>
     * <p>
     * email
     * </p>
     */
    String email;
    /**
     * <h2>CreatedFrom</h2>
     * <p>
     * CreatedFrom
     * </p>
     */
    String CreatedFrom;
    /**
     * <h2>CreatedTo</h2>
     * <p>
     * CreatedTo
     * </p>
     */
    String CreatedTo;
}
