package com.home.atm.database_spring;

import com.tacitknowledge.util.migration.MigrationException;
import com.tacitknowledge.util.migration.jdbc.AutoPatchService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sql.DataSource;

//@Repository("deligatedAutoPatch2")
public class DeligatedAutoPatch2 {

    @Resource(name = "dataSource")
    private DataSource dataSource;

    @Value("${patchPath}")
    private String patchPath;

    @Value("${systemName}")
    private String systemName;

    @Value("${databaseType}")
    private String databaseType;

    private AutoPatchService autoPatchService;

    @PostConstruct
    public void someTest() throws MigrationException {
        autoPatchService = new AutoPatchService();
        autoPatchService.setDataSource(dataSource);
        autoPatchService.setPatchPath(patchPath);
        autoPatchService.setSystemName(systemName);
        autoPatchService.setDatabaseType(databaseType);
        autoPatchService.patch();
        System.out.println("Hello from DeligatedAutoPatch");
    }
}
