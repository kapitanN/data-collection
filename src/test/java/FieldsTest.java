import com.Entities.*;
import com.beans.FieldBean;
import com.beans.MainBean;
import com.dao.FieldsDAO;
import com.service.ResponsesService;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

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
    public void updateFieldTest(){
        LOGGER.info("set user test");
        fieldsDAO.updateField(15,"City","Combobox",false,true);
    }


    @Test
    public void deleteFieldTest(){
        FieldEntity entity = new FieldEntity();
        entity.setId(1);
        entity.setLabel("City");
        entity.setType("Combobox");
        entity.setRequired(true);
        entity.setActive(true);
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


    @Test
    public void getFieldByLabelTest(){
        FieldEntity field = fieldsDAO.getFieldByLabel("Name");
        System.out.println(field.getId() + " " + field.getType());
    }

    @Test
    public void getAllTypesOptions(){
        List<TypesOptionsEntity> typesOptions = fieldsDAO.getAllTypesOptions(1);
        for (TypesOptionsEntity item : typesOptions) {
            System.out.println(item.getValue());
        }
    }


    @Test
    public void getUsersTest(){
        List<UsersEntity> users = fieldsDAO.getUsers();
        for (UsersEntity item : users) {
            System.out.println(item.getId());
        }
    }

    @Test
    public void getLabelsTest(){
        ResponsesService responsesService = new ResponsesService();
        List<List<String>> lists = new ArrayList<List<String>>();
        int usersCount = fieldsDAO.getUsers().size();
        for (int i = 0; i<usersCount;i++){
            lists.add(responsesService.getLabels(i));
            for (String item : lists.get(i)) {
                System.out.println(item);
            }
        }
    }

    @Test
    public void setUserData(){
        Map<String,String> userMap = new HashMap<String, String>();
        userMap.put("Sample combobox", "Male");
        userMap.put("Full name", "Nikita Shapovalov");
        fieldsDAO.setUserData(userMap);
    }
}
