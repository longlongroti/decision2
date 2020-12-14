package com.shanxi.coal.dao;

public interface HomeMapper {
    Integer countByStatus(String dicvalue);

    int countAllCompany();

    int countCompanyByType(String type);

    int countCompanyByBond(String type);

    int countCompanyByListed(String type);

    int countLeader(String s);

    int countEventByCatelog(String s);
    int countSystemByType(String s);

    int countMeetingType(String s);
}