package com.service;

import com.dao.FieldsDAO;
import com.Entities.FieldEntity;
import com.Entities.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nikita on 29.03.2017.
 */
    public class ResponsesService {

    public List<String> getLabels(int user_id){
        FieldsDAO fieldsDAO = new FieldsDAO();
        List<String> labels = new ArrayList<String>();
        List<FieldEntity> fields = fieldsDAO.getAllFields();
        List<Integer> fieldsId = new ArrayList<Integer>();

        for (FieldEntity item : fields) {
            fieldsId.add(item.getId());
        }
        for (FieldEntity entity : fields){
            List<ResponseEntity> response = fieldsDAO.getResponse(user_id+1,entity.getId());
            if (response.size() == 0){
                labels.add("N/A");
            }else {
                labels.add(response.get(0).getValue());
            }
        }
        return labels;
    }
}
