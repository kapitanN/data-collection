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
        for (int i = 0; i<fields.size(); i++){
            List<ResponseEntity> response = fieldsDAO.getResponse(user_id+1,i+1);
            if (response.size() == 0){
                labels.add(i,"N/A");
            }else {
                labels.add(i, response.get(0).getValue());
            }
        }
        return labels;
    }
}
