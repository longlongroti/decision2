package com.shanxi.coal.dao;

import com.shanxi.coal.domain.ManageMeetingAttendee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ManageMeetingAttendeeMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(ManageMeetingAttendee record);

    int insertSelective(ManageMeetingAttendee record);

    ManageMeetingAttendee selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(ManageMeetingAttendee record);

    int updateByPrimaryKey(ManageMeetingAttendee record);

    void insertBatch(List<ManageMeetingAttendee> list);

    void deleteByMeetingId(String uuid);

    List<ManageMeetingAttendee> listByMeetingId(@Param("meetingId") String meetingId);
}