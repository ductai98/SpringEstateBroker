package com.javaweb.repository.custom.impl;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.repository.custom.BuildingRepositoryCustom;
import com.javaweb.utils.NumericUtils;
import com.javaweb.utils.StringUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


@Repository
public class BuildingRepositoryImpl implements BuildingRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    public static void joinTable(Map<String, Object> params,
                                 List<String> typeCode,
                                 StringBuilder sql) {
        String staffId = (String) params.get("staffId");
        if (StringUtils.check(staffId)) {
            sql.append(" INNER JOIN assignmentbuilding ON b.id = assignmentbuilding.buildingid ");
        }

        String fromRentArea = (String) params.get("fromArea");
        String toRentArea = (String) params.get("toArea");

        if (StringUtils.check(fromRentArea) || StringUtils.check(toRentArea)) {
            sql.append(" INNER JOIN rentarea ON rentarea.buildingid = b.id ");
        }

        if (typeCode != null && !typeCode.isEmpty()) {
            sql.append(" INNER JOIN buildingrenttype ON b.id = buildingrenttype.buildingid ");
            sql.append(" INNER JOIN renttype ON renttype.id = buildingrenttype.renttypeid ");
        }
    }

    public static void normalQuery(Map<String, Object> params,
                                   StringBuilder where) {
        for (Map.Entry<String, Object> item : params.entrySet()) {
            if (!item.getKey().equals("staffId")
                    && !item.getKey().equals("typeCode")
                    && !item.getKey().contains("Area")
                    && !item.getKey().contains("Price")
                    && !item.getKey().equals("_typeCode")
            ) {
                String value = (String) item.getValue();
                if (NumericUtils.isLong(value)) {
                    where.append(" AND b.")
                            .append(item.getKey())
                            .append(" = ")
                            .append(value)
                            .append(" ");
                } else if (!value.isEmpty()) {
                    where.append(" AND b.")
                            .append(item.getKey())
                            .append(" LIKE '%")
                            .append(value)
                            .append("%' ");
                }
            }
        }
    }

    public static void specialQuery(Map<String, Object> params,
                                    List<String> typeCode,
                                    StringBuilder where) {
        String staffId = (String) params.get("staffId");
        if (StringUtils.check(staffId)) {
            where.append(" AND assignmentbuilding.staffid")
                    .append(" = ")
                    .append(staffId)
                    .append(" ");
        }

        String fromRentArea = (String) params.get("fromArea");
        String toRentArea = (String) params.get("toArea");
        if (NumericUtils.isLong(fromRentArea) || NumericUtils.isLong(toRentArea)) {
            if (NumericUtils.isLong(fromRentArea)) {
                where.append(" AND rentarea.value ")
                        .append(" >= ")
                        .append(fromRentArea);
            }

            if (NumericUtils.isLong(toRentArea)) {
                where.append(" AND rentarea.value ")
                        .append(" <= ")
                        .append(toRentArea);
            }
        }

        String fromRentPrice = (String) params.get("fromRentPrice");
        String toRentPrice = (String) params.get("toRentPrice");
        if (NumericUtils.isLong(fromRentPrice) || NumericUtils.isLong(toRentPrice)) {
            if (NumericUtils.isLong(fromRentPrice)) {
                where.append(" AND b.rentprice ")
                        .append(" >= ")
                        .append(fromRentPrice);
            }

            if (NumericUtils.isLong(toRentPrice)) {
                where.append(" AND b.rentprice ")
                        .append(" <= ")
                        .append(toRentPrice);
            }
        }

        if (typeCode != null && !typeCode.isEmpty()) {

            String sql = typeCode.stream().map(item ->  " renttype.code like " + "'" + item + "'").collect(Collectors.joining(" OR "));
            where.append(" AND ")
                    .append(sql);
        }
    }

    @Override
    public List<BuildingEntity> findAllBuilding(Map<String, Object> params, List<String> typeCode) {
        StringBuilder sql = new StringBuilder("SELECT * FROM building b ");
        joinTable(params, typeCode, sql);
        StringBuilder where = new StringBuilder(" WHERE 1=1  ");
        normalQuery(params, where);
        specialQuery(params, typeCode, where);
        //where.append(" GROUP BY b.id;");
        sql.append(where);

        Query query = entityManager.createNativeQuery(sql.toString(), BuildingEntity.class);

        return query.getResultList();
    }
}
