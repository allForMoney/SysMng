select * from budget_audit_log WHERE ADJUST_ID IN (SELECT ID from file_import_log WHERE IMPORT_TYPE ='adjust2016' AND PROJECT_ID in (
	SELECT ID FROM project WHERE PROJECT_NO LIKE '%%' AND MAJOR_NAME LIKE '%%' AND SCHOOL_NAME LIKE '%%'
))
