import com.Entities.FieldEntity;
import com.Entities.ResponseEntity;
import com.beans.FieldBean;
import com.dao.FieldsDAO;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
    public void getFieldsTest(){
        LOGGER.info("get user test");
        List<FieldEntity> fields= fieldsDAO.getAllFields();
        for (FieldEntity item:fields) {
            System.out.println(item.getLabel());
        }
    }

    @Test
    public void setFieldTest(){
        LOGGER.info("set user test");
        FieldBean entity = new FieldBean(CORRECT_LABEL,CORRECT_TYPE,CORRECT_REQUIRED,CORRECT_ACTIVE);
        fieldsDAO.setField(entity);
    }

//    @Test
//    public void setResponseTest(){
//        LOGGER.info("set user test");
//        fieldsDAO.setResponse(1,1,1,"nick");
//    }

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

    @Test
    public void getResponses(){
        List<String> list = new ArrayList<String>();
        List<ResponseEntity> response = new ArrayList<ResponseEntity>();
        List<FieldEntity> fields = fieldsDAO.getAllFields();
        for (int i = 0; i<fields.size(); i++){
            response = fieldsDAO.getResponse(1,i+1);
            if (response.size() == 0){
                list.add(i,"N/A");
                System.out.println(list.get(i));
            }else {
                list.add(i, response.get(0).getValue());
                System.out.println(list.get(i));
            }
        }
        for (Iterator iterator = response.iterator(); iterator.hasNext();){
            ResponseEntity item = (ResponseEntity)iterator.next();
            System.out.println(item.getValue());
        }
    }
}
