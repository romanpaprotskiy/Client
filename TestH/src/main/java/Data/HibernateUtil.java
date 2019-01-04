package Data;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component
@Scope("singleton")
public class HibernateUtil {

    private SessionFactory factory;

    public HibernateUtil(@Value("${config_hibernate}") String config) {
        Configuration configuration = new Configuration()
                .configure(config);
        ServiceRegistry registry = new ServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .buildServiceRegistry();
        factory = configuration.buildSessionFactory(registry);
    }

    @Bean
    public SessionFactory getSessionFactory() {
        return factory;
    }


}

