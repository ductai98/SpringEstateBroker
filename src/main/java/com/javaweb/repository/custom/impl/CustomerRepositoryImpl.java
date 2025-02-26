package com.javaweb.repository.custom.impl;

import com.javaweb.entity.CustomerEntity;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.repository.custom.CustomerRepositoryCustom;
import com.javaweb.utils.StringUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.util.List;

@Repository
public class CustomerRepositoryImpl implements CustomerRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    public static void joinTable(CustomerDTO customer,
                                 StringBuilder sql) {

        if (customer.getStaffId() != null) {
            sql.append(" INNER JOIN assignmentcustomer ON c.id = assignmentcustomer.customerid ");
            sql.append(" INNER JOIN user ON user.id = assignmentcustomer.staffid ");
        }
    }

    public static void normalQuery(CustomerDTO customer, StringBuilder where) {
        try {
            Field[] fields = CustomerDTO.class.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                String fieldName = field.getName();
                if (!fieldName.equals("staffId")) {
                    String value = String.valueOf(field.get(customer));
                    if (value == null || value.equals("null")) continue;
                    where.append(" AND c.")
                            .append(fieldName)
                            .append(" LIKE '%")
                            .append(value)
                            .append("%' ");
                }
            }
        } catch(IllegalAccessException e){
            e.printStackTrace();
        }
    }

    public static void specialQuery(CustomerDTO customerDTO, StringBuilder where) {
        String staffId = customerDTO.getStaffId() != null ? String.valueOf(customerDTO.getStaffId()) : null;
        if (StringUtils.check(staffId)) {
            where.append(" AND assignmentcustomer.staffid")
                    .append(" = ")
                    .append(staffId)
                    .append(" ");
        }
    }


    @Override
    public List<CustomerEntity> findAll(CustomerDTO customer) {
        StringBuilder sql = new StringBuilder("SELECT * FROM customer c ");
        joinTable(customer, sql);
        StringBuilder where = new StringBuilder(" WHERE 1=1  ");
        normalQuery(customer, where);
        specialQuery(customer, where);
        where.append(" AND c.status = 1;");
        sql.append(where);

        Query query = entityManager.createNativeQuery(sql.toString(), CustomerEntity.class);

        return query.getResultList();
    }
}
