import com.Entities.FieldEntity;
import com.beans.FieldBean;
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
        FieldEntity fieldEntity = fieldsDAO.getField(1);
        assertEquals(CORRECT_LABEL, fieldEntity.getLabel());
    }

    @Test
    public void setFieldTest(){
        LOGGER.info("set user test");
        FieldBean entity = new FieldBean(CORRECT_LABEL,CORRECT_TYPE,CORRECT_REQUIRED,CORRECT_ACTIVE);
        fieldsDAO.setField(entity);
    }

    @Test
    public void deleteFieldTest(){
        FieldEntity entity = new FieldEntity();
        entity.setId(2);
        entity.setLabel(CORRECT_LABEL);
        entity.setType(CORRECT_TYPE);
        entity.setRequired(CORRECT_REQUIRED);
        entity.setActive(CORRECT_ACTIVE);
        fieldsDAO.deleteField(entity);
    }
}
