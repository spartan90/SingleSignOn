--RUNSCRIPT FROM '../backend/clean_install_db.sql';
RUNSCRIPT FROM '../backend/droptables.sql';
RUNSCRIPT FROM '../backend/tables/systemuser.sql';
RUNSCRIPT FROM '../backend/populatedata/basicdata.sql';

select * from systemuser;