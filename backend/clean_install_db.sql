--RUNSCRIPT FROM 'D:\indusJDE\Software\git\repositories\SingleSignOn\backend\clean_install_db.sql';
RUNSCRIPT FROM 'D:\indusJDE\Software\git\repositories\SingleSignOn\backend\droptables.sql';
RUNSCRIPT FROM 'D:\indusJDE\Software\git\repositories\SingleSignOn\backend\tables\systemuser.sql';
RUNSCRIPT FROM 'D:\indusJDE\Software\git\repositories\SingleSignOn\backend\populatedata\basicdata.sql';

select * from systemuser;