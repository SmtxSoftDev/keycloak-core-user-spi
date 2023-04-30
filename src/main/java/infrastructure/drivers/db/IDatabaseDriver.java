package infrastructure.drivers.db;

import org.keycloak.component.ComponentModel;

import java.util.Properties;

public interface IDatabaseDriver {
    Properties getProperties(ComponentModel model);
}
