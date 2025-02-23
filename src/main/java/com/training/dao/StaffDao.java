package com.training.dao;

import java.util.List;
import com.training.model.Staff;

public interface StaffDao {
	long addStaff(Staff stf);
	long updateStaff(Staff stf);
	Staff getStaffById(long id);
	List<Staff> getAllStaff();
	long deleteStaffById(long id);
    public String[] getAllColumns();
}
