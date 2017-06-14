package com.home.atm.database_spring;

import com.tacitknowledge.util.migration.MigrationException;
import com.tacitknowledge.util.migration.jdbc.AutoPatchService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sql.DataSource;

@Repository("AtmAutoPatch")
public class AtmAutoPatch extends AutoPatchService {

    @PostConstruct
    @Override
    public void patch() throws MigrationException {
        super.patch();
        System.out.println("Hello from AtmAutoPatch");
    }

    @Resource(name = "dataSource")
    @Override
    public void setDataSource(DataSource dataSource) {
        super.setDataSource(dataSource);
    }

    @Value("${patchPath}")
    @Override
    public void setPatchPath(String patchPath) {
        super.setPatchPath(patchPath);
    }

    @Value("${systemName}")
    @Override
    public void setSystemName(String systemName) {
        super.setSystemName(systemName);
    }

    @Value("${databaseType}")
    @Override
    public void setDatabaseType(String databaseType) {
        super.setDatabaseType(databaseType);
    }
}
