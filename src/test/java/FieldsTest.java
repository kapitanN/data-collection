import com.beans.FieldsBean;
import com.dao.FieldsDAO;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by nikita on 24.03.2017.
 */
public class FieldsTest {
    private static final Logger LOGGER = Logger.getLogger(FieldsTest.class);


    private static final String CORRECT_LABEL = "name";
    private static final String CORRECT_TYPE = "text";
    private static final boolean CORRECT_REQUIRED = true;
    private static final boolean CORRECT_ACTIVE = true;

    private FieldsDAO fieldsDAO;

    @Before
    public void init(){
        this.fieldsDAO = new FieldsDAO();
    }

    @Test
    public void getFieldTest(){
        LOGGER.info("get user test");
//        FieldsBean fieldsBean = new FieldsBean();
//        fieldsBean.setLabel("name");
//        fieldsBean.setType("text");
//        fieldsBean.setRequired(true);
//        fieldsBean.setActive(true);
        FieldsBean fieldsBean = fieldsDAO.getField(1);
        assertEquals(CORRECT_LABEL, fieldsBean.getLabel());
    }

    @Test
    public void setUserTest(){
        LOGGER.info("set user test");
        fieldsDAO.setField(CORRECT_LABEL);
    }
}
